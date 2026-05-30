package com.sparrow.crimsonlion.ridechat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat

class NavActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        //ViewCompat.setOnApplyWindowInsetsListener(window.decorView){ _, insets -> insets }
        setContent {

        }
    }
}