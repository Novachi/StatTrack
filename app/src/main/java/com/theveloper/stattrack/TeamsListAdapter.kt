package com.theveloper.stattrack

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.Match
import com.theveloper.stattrack.datamodel.Team

class TeamsListAdapter(val teamsList: MutableList<Team>, val mContext: Context?, val onTeamListener: onTeamListener): RecyclerView.Adapter<TeamListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.team_item,parent,false)
        return TeamListViewHolder(v, onTeamListener)
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.teamName.text = teamsList[position].name
        holder.logo.setImageResource(teamsList[position].logoResource)
    }
}

class TeamListViewHolder(itemView: View,
                        val  onTeamListener: onTeamListener,
                        val teamName: TextView = itemView.findViewById(R.id.team_name),
                        val logo: ImageView = itemView.findViewById(R.id.team_logo)
): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    init {
        itemView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        onTeamListener.teamOnClick(adapterPosition)
    }

}

interface onTeamListener{
    fun teamOnClick(position: Int)
}