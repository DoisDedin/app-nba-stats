package com.example.nbastat_origin.ui.list_players.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.observeOnError
import com.example.nbastat_origin.common.observeOnLoading
import com.example.nbastat_origin.common.observeOnSuccess
import com.example.nbastat_origin.databinding.FragmentListPlayersBinding
import com.example.nbastat_origin.ui.detail_playe.DetailPlayerActivity
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO
import com.google.android.material.appbar.AppBarLayout
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class PlayersListFragment :
    Fragment(),
    KodeinAware,
    AppBarLayout.OnOffsetChangedListener,
    PlayerFilterListener {

    override val kodein by closestKodein()

    private val viewModel: PlayersListViewModel by instance()
    private lateinit var myAdapter: PlayersListAdapter

    private var isSearchViewExpanded = false

    private var _binding: FragmentListPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarlayout.addOnOffsetChangedListener(this)
        startAdapter()
        setObservers()
        viewModel.getPlayers()
    }

    private fun startAdapter() {
        myAdapter = PlayersListAdapter(PlayersFilter(this))
        binding.recyclerviewPlayers.adapter = myAdapter
        myAdapter.setOnClick(itemClickListener)
        setupSearchView()
    }

    private val itemClickListener: (PlayerVO) -> Unit = { player ->
        DetailPlayerActivity.start(requireContext(), playerId = player.playerID, false)
    }

    private fun setObservers() {
        viewModel.playersListLiveData.observeOnSuccess(this, ::onSuccess)
            .observeOnLoading(this, ::onLoading)
            .observeOnError(this, ::onError)
    }

    private fun onSuccess(listPlayers: List<PlayerVO>) {
        myAdapter.setPlayers(listPlayers)
    }

    private fun onLoading() {

    }

    private fun onError(errorData: ErrorData) {}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                myAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val offsetThreshold = -200 // Define um valor de deslocamento limite para a expansão/colapso
        if (verticalOffset <= offsetThreshold && !isSearchViewExpanded) {
            binding.searchView.isIconified = false // Expandir a SearchView
            isSearchViewExpanded = true
        } else if (verticalOffset > offsetThreshold && isSearchViewExpanded) {
            binding.searchView.isIconified = true // Colapsar a SearchView
            isSearchViewExpanded = false
        }
    }

    override fun onFilteredList(filteredPlayers: List<PlayerVO>) {
        myAdapter.updateData(filteredPlayers)
    }

}