package com.theveloper.stattrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Parser
import com.theveloper.stattrack.datamodel.OverwatchPlayer
import com.theveloper.stattrack.datamodel.Team
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var queue:RequestQueue
    private lateinit var lolek: JSONObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        queue = Volley.newRequestQueue(this)

//        var x: Deferred<String>? = null
//        var job = GlobalScope.launch {
//            x = async { sendRequest() }
//        }

        val owPlayers = listOf<OverwatchPlayer>(
            OverwatchPlayer("Hiko", "pro player 10", "Windowmarker"),
            OverwatchPlayer("U kiddin me?!", "LOL", "PE ER O ES TE O"),
            OverwatchPlayer("WHAT?!", "OH MY GOD!", "INHUMAN REACTION!")
        )

        val pro = owPlayers.asSequence().map { it.name }.toList()
        for(i in pro){
            Log.d(TAG, i)
        }

        val owTeam = Team<OverwatchPlayer>("WOWY", 22.toDouble(),owPlayers)

        owTeam.listAllPlayers()

        GlobalScope.launch(Dispatchers.Main) {
                val result = async { loadString() }.await()
                Log.d("TAG",
                    "Post execution thread:"+Thread.currentThread().name)
                Log.d(TAG, result)
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
