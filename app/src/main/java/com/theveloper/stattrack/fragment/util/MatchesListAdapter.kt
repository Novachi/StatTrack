package com.theveloper.stattrack


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theveloper.stattrack.datamodel.Match

class MatchesListAdapter(val matchesList: MutableList<Match>, val mContext: Context?): RecyclerView.Adapter<MatchesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return matchesList.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val current: Match = matchesList[position]
        holder.mImageViewOne.setImageResource(current.team1!!.logoResource)
        holder.mImageViewTwo.setImageResource(current.team2!!.logoResource)
        holder.textViewOne.text = current.team1.name
        holder.textViewTwo.text = current.team2.name
        holder.scoreTextView.text = mContext?.getString(R.string.matchScore, current.team1Score, current.team2Score)

        holder.textViewOne.setOnClickListener {
            switchToTeamActivity(position, 1)
        }

        holder.textViewTwo.setOnClickListener {
            switchToTeamActivity(position, 2)
        }

        holder.mImageViewOne.setOnClickListener {
            switchToTeamActivity(position, 1)
        }

        holder.mImageViewTwo.setOnClickListener {
            switchToTeamActivity(position, 2)
        }
    }

    private fun switchToTeamActivity(position: Int, teamNumber: Int){
        val intent = Intent(mContext, TeamActivity::class.java)
        when (teamNumber) {
            1 -> {

                intent.putExtra("team", matchesList[position].team1)
            }

            2 -> {
                Log.d("PRO", matchesList[position].team2!!.name)
                intent.putExtra("team", matchesList[position].team2)
            }
        }

        mContext?.startActivity(intent)
    }

}

class MatchesViewHolder(itemView: View,
//                        val parentLayout: RelativeLayout = itemView.findViewById(R.id.fragment_container),
                        val mImageViewOne: ImageView = itemView.findViewById(R.id.teamOneLogo),
                        val mImageViewTwo: ImageView = itemView.findViewById(R.id.teamTwoLogo),
                        val textViewOne: TextView = itemView.findViewById(R.id.teamOneName),
                        val textViewTwo: TextView = itemView.findViewById(R.id.teamTwoName),
                        val scoreTextView: TextView = itemView.findViewById(R.id.VSTextView)): RecyclerView.ViewHolder(itemView){
}