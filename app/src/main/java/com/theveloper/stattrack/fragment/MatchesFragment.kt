package com.theveloper.stattrack

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.Match
import com.theveloper.stattrack.datamodel.Team
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment(val mContext: Context, val matchesList: MutableList<Match>) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecycleView: RecyclerView = list
        val mLayoutManager = LinearLayoutManager(parentFragment?.context)
        val mAdapter = MatchesListAdapter(matchesList, mContext)
        mRecycleView.adapter = mAdapter
        mRecycleView.layoutManager = mLayoutManager
        mRecycleView.setHasFixedSize(true)
    }
}