package com.example.nbastat_origin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.observeOnError
import com.example.nbastat_origin.common.observeOnLoading
import com.example.nbastat_origin.common.observeOnSuccess
import com.example.nbastat_origin.databinding.FragmentListPlayersBinding
import com.example.nbastat_origin.ui.vo.PlayerVO
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class PlayersListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel: PlayersListViewModel by instance()
    private lateinit var myAdapter: PlayersListAdapter

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
        startAdapter()
        setObservers()
        viewModel.getPlayers()
    }

    private fun startAdapter() {
        myAdapter = PlayersListAdapter()
        binding.recyclerviewPlayers.adapter = myAdapter
        myAdapter.setOnClick(itemClickListener)

    }

    private val itemClickListener: (PlayerVO) -> Unit = { player ->
        // Lidar com o clique no item aqui
        Toast.makeText(this.context, "Clicou no jogador: ${player.firstName}", Toast.LENGTH_SHORT)
            .show()
    }

    private fun setObservers() {
        viewModel.playersListLiveData.observeOnSuccess(this, ::onSuccess)
            .observeOnLoading(this, ::onLoading)
            .observeOnError(this, ::onError)
    }

    private fun onSuccess(listPlayers: List<PlayerVO>) {
        myAdapter.addPlayers(listPlayers)
    }

    private fun onLoading() {

    }

    private fun onError(errorData: ErrorData) {}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}