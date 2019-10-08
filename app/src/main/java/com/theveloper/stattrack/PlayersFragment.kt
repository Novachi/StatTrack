package com.theveloper.stattrack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.OverwatchPlayer

class PlayersFragment(val mContext: Context) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.list)
        recyclerView.adapter = PlayersListAdapter(mutableListOf(
            OverwatchPlayer("Hiko", "pro player 10", "Windowmarker", "PRo1"),
            OverwatchPlayer("U kiddin me?!", "LOL", "PE ER O ES TE O", "Pro2"),
            OverwatchPlayer("WHAT?!", "OH MY GOD!", "INHUMAN REACTION!", "pro3")))
        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context)
    }
}