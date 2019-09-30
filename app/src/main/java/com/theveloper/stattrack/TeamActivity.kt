package com.theveloper.stattrack

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.team_activity.*

class TeamActivity: AppCompatActivity() {
    private lateinit var navBar: BottomNavigationView

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

    }

    private fun getIncomingIntent(){
        var teamName: String? = ""
        var teamLogo: Int = 0
        if(intent.hasExtra("team-name")){
            teamName  = intent.getStringExtra("team-name")
            teamLogo = intent.getIntExtra("team-logo", R.drawable.ic_launcher_background)
        }

        setupActivity(teamName, teamLogo)
    }

    private fun setupActivity(teamName: String?, teamLogo: Int?){
        val name: TextView = findViewById(R.id.teamName)
        val logo: ImageView = findViewById(R.id.teamLogo)
        name.text = teamName
        logo.setImageResource(teamLogo!!)
    }
}