package com.theveloper.stattrack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.OverwatchTeam
import com.theveloper.stattrack.datamodel.Team

class TeamsFragment(val mContext: Context, val teams: MutableList<Team>) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val teamsList: RecyclerView = view.findViewById(R.id.list)
        teamsList.layoutManager = LinearLayoutManager(parentFragment?.context)
        teamsList.adapter = TeamsListAdapter(teams,  mContext)
    }
}