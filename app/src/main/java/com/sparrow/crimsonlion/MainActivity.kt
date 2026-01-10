package com.sparrow.crimsonlion

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.sparrow.crimsonlion.ui.theme.CrimsonLionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrimsonLionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotificationDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NotificationDemo(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!hasNotificationPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Button(onClick = {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }) {
                Text("Request Notification Permission")
            }
        } else {
            Button(onClick = {
                showNotification(context)
            }) {
                Text("Show Notification with PendingIntent")
            }
        }
    }
}

private fun showNotification(context: Context) {
    // Create an explicit intent for an Activity in your app.
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    
    // Create the PendingIntent.
    // FLAG_IMMUTABLE is required for targetSdk 31+.
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 
        0, 
        intent, 
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, MyApplication.CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Demo Notification")
        .setContentText("This notification uses a PendingIntent to open the app.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Set the intent that fires when the user taps the notification.
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    val notificationManager = NotificationManagerCompat.from(context)
    
    // Check permission again for Android 13+ before posting notification
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(1, builder.build())
        }
    } else {
        notificationManager.notify(1, builder.build())
    }
}
