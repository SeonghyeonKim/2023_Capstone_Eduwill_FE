package com.example.frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayerActivity

class OpenUnityActivity : UnityPlayerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_unity)

        val message = intent.getStringExtra("message")
        if (message != null) {
            val intent = Intent(this, UnityPlayerActivity::class.java)
            startActivity(intent)
            UnityPlayer.UnitySendMessage("Connection", "ReceivedMessage", message)
        }
    }
}
