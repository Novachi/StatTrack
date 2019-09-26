package com.theveloper.stattrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchesList: MutableList<MatchItem> = mutableListOf(MatchItem(R.drawable.ic_train_black_24dp, R.drawable.ic_tram_black_24dp, "Pociagarze", "Tramwajarze"))
        val mRecycleView: RecyclerView = matches_list
        val mLayoutManager = LinearLayoutManager(parentFragment?.context)
        val mAdapter = MatchesListAdapter(matchesList, parentFragment?.context)
        mRecycleView.adapter = mAdapter
        mRecycleView.layoutManager = mLayoutManager
        mRecycleView.setHasFixedSize(true)
    }
}