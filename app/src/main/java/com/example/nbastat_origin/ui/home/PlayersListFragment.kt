package com.example.nbastat_origin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.observeOnError
import com.example.nbastat_origin.common.observeOnLoading
import com.example.nbastat_origin.common.observeOnSuccess
import com.example.nbastat_origin.databinding.FragmentListPlayersBinding

class PlayersListFragment : Fragment() {

    private var _binding: FragmentListPlayersBinding? = null

    private val viewModel : PlayersListViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val playersListViewModel =
            ViewModelProvider(this).get(PlayersListViewModel::class.java)

        _binding = FragmentListPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observers(){
        viewModel.playersListLiveData
            .observeOnSuccess(this, ::onSuccess)
            .observeOnLoading(this, ::onLoading)
            .observeOnError(this, ::onError)

    }

    private fun onSuccess(){

    }

    private fun onLoading(){

    }

    private fun onError(errorData : ErrorData){

    }
}