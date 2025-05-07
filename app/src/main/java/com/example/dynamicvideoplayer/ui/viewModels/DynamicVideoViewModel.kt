package com.example.dynamicvideoplayer.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DynamicVideoViewModel : ViewModel() {

    private val _videoUrl = MutableStateFlow<String?>(null)
    val videoUrl: StateFlow<String?> = _videoUrl

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance("https://dynamic-video-player-2b7a9-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("url")

    init {
        fetchVideoUrl()
    }

    private fun fetchVideoUrl() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val url = snapshot.getValue(String::class.java)
                _videoUrl.value = url
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to fetch video URL", error.toException())
            }
        })
    }
}