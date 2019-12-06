package com.theveloper.stattrack

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.theveloper.stattrack.datamodel.Match
import com.theveloper.stattrack.datamodel.Team

class TeamActivity(): AppCompatActivity() {
    private lateinit var navBar: BottomNavigationView
    private var team: Team? = null
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sideNav: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
        setSupportActionBar(toolbar)
        navBar = findViewById(R.id.team_activity_nav)
        drawerLayout = findViewById(R.id.drawer_team)
        sideNav = findViewById(R.id.side_nav)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navBar.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment = MatchesFragment(this, MainActivity.matchesList)

            selectedFragment = when {
                it.itemId == R.id.results_tab -> MatchesFragment(this, MainActivity.matchesList)
                it.itemId == R.id.roster_tab -> PlayersFragment(this)
                else -> MatchesFragment(this, MainActivity.matchesList)
            }

            supportFragmentManager.beginTransaction().replace(R.id.tab_fragment_container, selectedFragment).commit()
            true
        }

        navBar.selectedItemId = R.id.results_tab

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

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}