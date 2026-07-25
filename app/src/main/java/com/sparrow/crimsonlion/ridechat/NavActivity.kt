package com.sparrow.crimsonlion.ridechat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sparrow.crimsonlion.ridechat.components.RideChatAppBar
import com.sparrow.crimsonlion.ui.theme.CrimsonLionTheme

class NavActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        //ViewCompat.setOnApplyWindowInsetsListener(window.decorView){ _, insets -> insets }
        setContent {
            CrimsonLionTheme {
                ShowInitialScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowInitialScreen() {

}