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
import com.theveloper.stattrack.datamodel.OverwatchTeam
import com.theveloper.stattrack.datamodel.Player
import com.theveloper.stattrack.datamodel.Team
import kotlinx.android.synthetic.main.team_activity.*
import java.io.Serializable

class TeamActivity: AppCompatActivity() {
    private lateinit var navBar: BottomNavigationView
    private var team: Team? = null

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
        if(intent.hasExtra("team")){
            team = intent.getParcelableExtra("team")

        }

        setupActivity(team!!)
    }

    private fun setupActivity(team: Team){
        this.team = team
        val name: TextView = findViewById(R.id.teamName)
        val logo: ImageView = findViewById(R.id.teamLogo)
        name.text = team.name
        logo.setImageResource(team.logoResource)

    }
}