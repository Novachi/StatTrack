package com.theveloper.stattrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TeamActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
    }

    private fun getIncomingIntent(){
        var teamName: String? = ""
        if(intent.hasExtra("team-name")){
            teamName  = intent.getStringExtra("team-name")
        }

        setupActivity(teamName)
    }

    private fun setupActivity(teamName: String?){
        val name: TextView = findViewById(R.id.teamName)
        name.text = teamName
    }
}