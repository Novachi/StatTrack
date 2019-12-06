package com.theveloper.stattrack.datamodel

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


object DatabaseAPI {
    private val database = FirebaseFirestore.getInstance()

    fun getAllUsers(): MutableList<DocumentSnapshot>{
        val users: MutableList<DocumentSnapshot> = mutableListOf()
        database.collection("users").get().addOnSuccessListener { collection ->
            users.addAll(collection.documents)

        }
            .addOnFailureListener { exception ->
                Log.d("PRO17", "get failed with ", exception)
            }
        return users
    }
}