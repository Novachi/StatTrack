package com.theveloper.stattrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.Player

class PLayersListAdapter(val players: MutableList<Player>): RecyclerView.Adapter<PlayersViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return PlayersViewHolder(v)
    }

    override fun getItemCount(): Int = players.size


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.playerNameText.text = players[position].nickname
    }
}

class PlayersViewHolder(itemView: View,
                        val playerNameText: TextView = itemView.findViewById(R.id.player_name)) : RecyclerView.ViewHolder(itemView){

}