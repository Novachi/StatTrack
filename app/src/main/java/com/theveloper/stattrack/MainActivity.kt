package com.theveloper.stattrack

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.squareup.picasso.Picasso
import com.theveloper.stattrack.datamodel.*
import kotlinx.coroutines.*
import org.mindrot.jbcrypt.BCrypt
import java.io.InputStream
import java.net.URI
import java.util.stream.Stream

class MainActivity: AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var queue:RequestQueue
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sideNav: NavigationView
    private lateinit var headerImage: ImageView
    private lateinit var headerUsername: TextView
    private val loginProviders = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build())
    private val RC_SIGN_IN = 1

    companion object {
        var matchesList: MutableList<Match> = mutableListOf(
            Match(
                Team.OverwatchTeam(
                    "Prosy",
                    1.0,
                    mutableListOf(),
                    R.drawable.ic_train_black_24dp
                ),
                Team.OverwatchTeam(
                    "NieProsy",
                    1.0,
                    mutableListOf(),
                    R.drawable.ic_tram_black_24dp
                ), 2, 3
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)

            if(resultCode == Activity.RESULT_OK){
//                Inflating side nav header which will show logged user information
                sideNav.inflateHeaderView(R.layout.side_nav_header)
                headerImage = findViewById(R.id.header_image)
                headerUsername = findViewById(R.id.header_username)

                val user = FirebaseAuth.getInstance().currentUser

//                Making login option unavailable and logout option available
               switchLoginLogoutButtons()

//                Changing header image and text to show current user's data
                headerUsername.text = user?.displayName
                Picasso.get().load(user?.photoUrl).into(headerImage)

//                Showing successful login message
                Toast.makeText(this, "Successful login! Welcome back ${user?.displayName}", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("USERERERE", "EROR")
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar?>(R.id.toolbar)
        setSupportActionBar(toolbar)
//        database.collection("users")
//            .add(AppUser("dziadek", "prawiemruz", arrayListOf("prosy1", "prosy2", "prosy3"), arrayListOf("wow", "doopa", "sss")))
        drawerLayout = findViewById(R.id.drawer_main)
        sideNav = findViewById(R.id.side_nav)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        bottomNav = findViewById(R.id.bottom_nav)

        var teamsList: MutableList<Team> = mutableListOf(
            Team.OverwatchTeam(
                "Prosy",
                1.0,
                mutableListOf(),
                R.drawable.ic_train_black_24dp
            ),
            Team.OverwatchTeam(
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

        val owPlayers = listOf<Player.OverwatchPlayer>(
            Player.OverwatchPlayer("Hiko", "pro player 10", "Windowmarker", "PRo1"),
            Player.OverwatchPlayer("U kiddin me?!", "LOL", "PE ER O ES TE O", "Pro2"),
            Player.OverwatchPlayer("WHAT?!", "OH MY GOD!", "INHUMAN REACTION!", "pro3")
        )

        val pro = owPlayers.asSequence().map { it.lastName }.toList()
        for(i in pro){
            Log.d(TAG, i)
        }

        GlobalScope.launch(Dispatchers.Main) {
                val result = async { loadString() }.await()
                Log.d("TAG",
                    "Post execution thread:"+Thread.currentThread().name)
                Log.d(TAG, result)
        }


        sideNav.setNavigationItemSelectedListener { menuItem ->
            var selectedFragment: Fragment = MatchesFragment(this, matchesList)
            var loginLogout = false
            when(menuItem.itemId){
                R.id.side_nav_overwatch -> {
                    matchesList = mutableListOf(
                        Match(
                            Team.OverwatchTeam(
                                "Prosy",
                                1.0,
                                mutableListOf(),
                                R.drawable.ic_train_black_24dp
                            ),
                            Team.OverwatchTeam(
                                "NieProsy",
                                1.0,
                                mutableListOf(),
                                R.drawable.ic_tram_black_24dp
                            ), 2, 3
                        )
                    )
                    selectedFragment = MatchesFragment(this, matchesList)
                }

                R.id.side_nav_csgo -> {
                    matchesList = mutableListOf(
                        Match(
                            Team.CsgoTeam(
                                "PRO",
                                1.0,
                                mutableListOf(),
                                R.drawable.ic_results_black_24dp
                            ),
                            Team.CsgoTeam(
                                "Mooye",
                                1.0,
                                mutableListOf(),
                                R.drawable.ic_overwatch_circle_logo
                            ), 2, 3
                        )
                    )
                    selectedFragment = MatchesFragment(this, matchesList)
                }

                R.id.side_nav_login -> {
                    loginLogout = true
                    startActivityForResult(
                        AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(loginProviders)
                            .build(),RC_SIGN_IN

                    )
                }

                R.id.logoutButton -> {
                    loginLogout = true
                    AuthUI.getInstance().signOut(this).addOnCompleteListener(OnCompleteListener {
                        switchLoginLogoutButtons()
                        sideNav.removeHeaderView(sideNav.getHeaderView(0))
                        Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show()
                    })
                }

                else -> {
                    selectedFragment = MatchesFragment(this, matchesList)
                }
            }
            if(!loginLogout) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }




        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            var selectedFragment: Fragment = MatchesFragment(this, matchesList)
            when {
                menuItem.itemId == R.id.nav_matches -> {
                    selectedFragment = MatchesFragment(this, matchesList)
                }
                menuItem.itemId == R.id.nav_teams -> selectedFragment = TeamsFragment(this, teamsList)
                menuItem.itemId == R.id.nav_players -> selectedFragment = PlayersFragment(this)
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

            true
        }

        bottomNav.selectedItemId = R.id.nav_matches

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MatchesFragment(this, matchesList)).commit()
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

    private fun switchLoginLogoutButtons(){
        val loginOption = sideNav.menu.findItem(R.id.side_nav_login)
        val logoutOption = sideNav.menu.findItem(R.id.logoutButton)

//        Switching buttons visibility
        loginOption.isVisible = !loginOption.isVisible
        logoutOption.isVisible = !logoutOption.isVisible

//        Switching buttons state
        loginOption.isEnabled = !loginOption.isEnabled
        logoutOption.isEnabled = !logoutOption.isEnabled

    }

}