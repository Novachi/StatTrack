package com.theveloper.stattrack


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchesListAdapter(val matchesList: MutableList<MatchItem>, val mContext: Context?): RecyclerView.Adapter<MatchesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return matchesList.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val currentItem: MatchItem = matchesList[position]
        holder.mImageViewOne.setImageResource(currentItem.mImageResource1)
        holder.mImageViewTwo.setImageResource(currentItem.mImageResource2)
        holder.textViewOne.text = currentItem.mText1
        holder.textViewTwo.text = currentItem.mText2

        holder.textViewOne.setOnClickListener { v ->

            val intent = Intent(mContext, TeamActivity::class.java)
            intent.putExtra("team-name", matchesList[position].mText1)
            mContext?.startActivity(intent)
        }
    }

}

class MatchesViewHolder(itemView: View,
//                        val parentLayout: RelativeLayout = itemView.findViewById(R.id.fragment_container),
                        val mImageViewOne: ImageView = itemView.findViewById(R.id.teamOneLogo),
                        val mImageViewTwo: ImageView = itemView.findViewById(R.id.teamTwoLogo),
                        val textViewOne: TextView = itemView.findViewById(R.id.teamOneName),
                        val textViewTwo: TextView = itemView.findViewById(R.id.teamTwoName)): RecyclerView.ViewHolder(itemView){
}