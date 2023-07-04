package com.example.nbastat_origin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbastat_origin.common.UiError
import com.example.nbastat_origin.common.UiLoading
import com.example.nbastat_origin.common.UiSuccess
import com.example.nbastat_origin.databinding.FragmentListPlayersBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class PlayersListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel: PlayersListViewModel by instance()

    private var _binding: FragmentListPlayersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.getPlayers()

    }

    private fun setObservers() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiLoading -> stateLoading()
                is UiSuccess -> stateSuccess()
                is UiError -> stateError()
                else -> stateError()
            }
        }
    }

    private fun stateLoading() {

    }

    private fun stateError() {

    }

    private fun stateSuccess() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}