package com.theveloper.stattrack.datamodel

import java.io.Serializable

class Match<K : Player, T : Team<K>>(val team1: T, val team2: T, val team1Score: Int, val team2Score: Int): Serializable {

}