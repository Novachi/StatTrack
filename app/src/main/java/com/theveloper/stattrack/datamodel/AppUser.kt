package com.theveloper.stattrack.datamodel

import android.util.Log
import org.mindrot.jbcrypt.BCrypt

data class AppUser(val username: String, var password: String, var favTeams: ArrayList<String>, var favPlayers: ArrayList<String>) {
    init {
        var wow = password
        password = BCrypt.hashpw(password, BCrypt.gensalt(12))
        Log.d("PROsto10", BCrypt.checkpw(wow, password).toString())
    }
}