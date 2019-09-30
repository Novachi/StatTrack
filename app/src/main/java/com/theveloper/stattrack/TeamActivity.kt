package com.theveloper.stattrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar

class TeamActivity: AppCompatActivity() {
    private lateinit var navBar: BottomAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
        navBar = findViewById(R.id.team_activity_nav)

        navBar.setOnMenuItemClickListener {
            var selectedFragment: Fragment = MatchesFragment(this)

            selectedFragment = when {
                it.itemId == R.id.results_tab -> MatchesFragment(this)
                it.itemId == R.id.rooster_tab -> PlayersFragment(this)
                else -> MatchesFragment(this)
            }

            supportFragmentManager.beginTransaction().replace(R.id.tab_fragment_container, selectedFragment).commit()
            true
        }

        getIncomingIntent()

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
        val logo: ImageView = findViewById(R.id.teamLogo)
        name.text = teamName
    }
}