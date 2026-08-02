package com.sparrow.crimsonlion.ridechat.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
  AlertDialog(
    onDismissRequest = onDismiss,
    text = {
        Text(
           text = "This Feature is not available \uD83D\uDE48",
            style = MaterialTheme.typography.bodyMedium
        )
    },
    confirmButton = {
        TextButton(onClick = onDismiss) {
            Text("Close")
        }
    },
  )
}
