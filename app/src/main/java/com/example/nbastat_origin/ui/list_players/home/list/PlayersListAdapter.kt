package com.example.nbastat_origin.ui.list_players.home.list

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nbastat_origin.databinding.ItemPlayerBinding
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

class PlayersListAdapter(
    private val playersFilter : PlayersFilter
) :
    RecyclerView.Adapter<PlayersListAdapter.ItemViewHolder>(), Filterable {

    private var playersList: List<PlayerVO> = arrayListOf()
    private var filteredPlayers: List<PlayerVO> = emptyList()
    private val filter: PlayersFilter = playersFilter

    private lateinit var executeOnClick: (postDomain: PlayerVO) -> Unit
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding, executeOnClick, parent.context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filteredPlayers[position])
    }

    override fun getItemCount(): Int = filteredPlayers.size

    fun setOnClick(executeOnClick: (playerVO: PlayerVO) -> Unit) {
        this.executeOnClick = executeOnClick
    }

    fun setPlayers(players: List<PlayerVO>) {
        this.playersList = players
        this.filteredPlayers = players
        filter.setPlayers(players)
        notifyDataSetChanged()
    }

    fun updateData(filteredPlayers: List<PlayerVO>) {
        this.filteredPlayers = filteredPlayers
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        private val binding: ItemPlayerBinding,
        private val executeOnClick: (playerVO: PlayerVO) -> Unit,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var longClickHandler: Handler? = null
        val longClickDuration = 2000L

        // Bind item data to the view
        fun bind(item: PlayerVO) {
            binding.textviewPlayerName.text = "Nome: ${item.firstName} ${item.lastName}"
            binding.textviewPosition.text = "Posição: ${item.position}"
            binding.textviewTeam.text = "Time: ${item.team}"

            Glide.with(context)
                .load(item.photoUrl)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Opcional: cache da imagem
                .into(binding.imageviewPhoto)

            binding.constraintContainer.setOnLongClickListener { view ->
                longClickHandler = Handler()
                longClickHandler?.postDelayed({
                    executeOnClick.invoke(item)
                }, longClickDuration)

                true // Retorna true para indicar que o clique longo foi consumido
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<PlayerVO>() {
        override fun areItemsTheSame(oldItem: PlayerVO, newItem: PlayerVO): Boolean {
            return oldItem.playerID == newItem.playerID
        }

        override fun areContentsTheSame(oldItem: PlayerVO, newItem: PlayerVO): Boolean {
            return oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}