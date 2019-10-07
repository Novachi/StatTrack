package com.theveloper.stattrack

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.theveloper.stattrack.datamodel.OverwatchPlayer
import com.theveloper.stattrack.datamodel.Player
import com.theveloper.stattrack.datamodel.Team
import kotlinx.android.synthetic.main.team_activity.*
import java.io.Serializable

class TeamActivity: AppCompatActivity() {
    private lateinit var navBar: BottomNavigationView
    private var team: Serializable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
        navBar = findViewById(R.id.team_activity_nav)


        navBar.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment = MatchesFragment(this)

            selectedFragment = when {
                it.itemId == R.id.results_tab -> MatchesFragment(this)
                it.itemId == R.id.roster_tab -> PlayersFragment(this)
                else -> MatchesFragment(this)
            }

            supportFragmentManager.beginTransaction().replace(R.id.tab_fragment_container, selectedFragment).commit()
            true
        }



        getIncomingIntent()

        Log.d("TeamActivity", (team as Team).toString())



    }

    private fun getIncomingIntent(){
        var teamName: String? = ""
        var teamLogo: Int = 0
        if(intent.hasExtra("team-name")){
            teamName  = intent.getStringExtra("team-name")
            teamLogo = intent.getIntExtra("team-logo", R.drawable.ic_launcher_background)
            team = intent.getSerializableExtra("team")

        }

        setupActivity(teamName, teamLogo, team)
    }

    private fun setupActivity(teamName: String?, teamLogo: Int?, team: Serializable?){
        val name: TextView = findViewById(R.id.teamName)
        val logo: ImageView = findViewById(R.id.teamLogo)
        name.text = teamName
        logo.setImageResource(teamLogo!!)
        this.team = team!!
    }
}