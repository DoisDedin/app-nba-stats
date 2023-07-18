package com.example.nbastat_origin.ui.list_players.home.list

import android.widget.Filter
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO


class PlayersFilter(private val listener: PlayerFilterListener) : Filter() {

    private var players: List<PlayerVO> = emptyList()

    fun setPlayers(players : List<PlayerVO>) {
        this.players = players
    }

    override fun performFiltering(filterString: CharSequence?): FilterResults {
        val results = FilterResults()
        if (filterString.isNullOrEmpty()) {
            results.values = players
        } else {
            val query = filterString.toString().lowercase()
            val filteredPlayers = players.filter { player ->
                player.firstName.lowercase().contains(query) //||
//                        player..toString().contains(query) ||
//                        player.age.toString().contains(query)
            }
            results.values = filteredPlayers
        }
        return results
    }

    override fun publishResults(filterString: CharSequence?, results: FilterResults?) {
        val filteredPlayers = results?.values as? List<PlayerVO> ?: emptyList()
        listener.onFilteredList(filteredPlayers)
    }

}