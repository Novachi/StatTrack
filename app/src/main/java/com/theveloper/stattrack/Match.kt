package com.theveloper.stattrack

import com.theveloper.stattrack.datamodel.Player
import com.theveloper.stattrack.datamodel.Team

class Match<K : Player, T : Team<K>>(val team1: T, val team2: T, val team1Score: Int, val team2Score: Int) {

}