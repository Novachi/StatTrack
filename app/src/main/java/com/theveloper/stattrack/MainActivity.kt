package com.theveloper.stattrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.theveloper.stattrack.datamodel.OverwatchPlayer
import com.theveloper.stattrack.datamodel.OverwatchTeam
import com.theveloper.stattrack.datamodel.Team
import kotlinx.coroutines.*

class MainActivity: AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var queue:RequestQueue
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val teamsList: MutableList<Team> = mutableListOf(
            OverwatchTeam(
                "Prosy",
                1.0,
                mutableListOf(),
                R.drawable.ic_train_black_24dp
            ),
            OverwatchTeam(
                "NieProsy",
                1.0,
                mutableListOf(),
                R.drawable.ic_tram_black_24dp
            )
        )

//        queue = Volley.newRequestQueue(this)

//        var x: Deferred<String>? = null
//        var job = GlobalScope.launch {
//            x = async { sendRequest() }
//        }

        val owPlayers = listOf<OverwatchPlayer>(
            OverwatchPlayer("Hiko", "pro player 10", "Windowmarker", "PRo1"),
            OverwatchPlayer("U kiddin me?!", "LOL", "PE ER O ES TE O", "Pro2"),
            OverwatchPlayer("WHAT?!", "OH MY GOD!", "INHUMAN REACTION!", "pro3")
        )

        val pro = owPlayers.asSequence().map { it.lastName }.toList()
        for(i in pro){
            Log.d(TAG, i)
        }

        val owTeam = OverwatchTeam("WOWY", 22.toDouble(),owPlayers, R.drawable.ic_train_black_24dp)

        owTeam.listAllPlayers()

        GlobalScope.launch(Dispatchers.Main) {
                val result = async { loadString() }.await()
                Log.d("TAG",
                    "Post execution thread:"+Thread.currentThread().name)
                Log.d(TAG, result)
        }

        bottomNav = findViewById(R.id.bottom_nav)


        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            var selectedFragment: Fragment = MatchesFragment(this)
            when {
                menuItem.itemId == R.id.nav_matches -> selectedFragment = MatchesFragment(this)
                menuItem.itemId == R.id.nav_teams -> selectedFragment = TeamsFragment(this, teamsList)
                menuItem.itemId == R.id.nav_players -> selectedFragment = PlayersFragment(this)
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

            true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MatchesFragment(this)).commit()






    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private suspend fun loadString(): String{
        delay(20000)
        return "PRO"
    }


    private fun sendRequest(): String{

        val url = "https://api.pandascore.co/lol/champions?token=7unAk2Kqd5haj_vLHuqowHzxzinUY9FIz_7M1cbtBVRgu6-Fnb8"
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
//                    val parser: Parser = Parser.default()
//                    val result: JSONObject = parser.parse(response) as JSONObject
//
//                    this.lolek = result
                    Log.d(TAG, "${response}}]")
                },

                Response.ErrorListener { Log.d(TAG, "SMTH WENT WRONQ") }
        )

        queue.add(stringRequest)

        return "PRO!"
    }
}
