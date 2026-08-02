package com.sparrow.crimsonlion.ridechat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sparrow.crimsonlion.R
import com.sparrow.crimsonlion.ridechat.dialog.FunctionalityNotAvailablePopup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
  channelName: String,
  channelMembers: String,
  modifier: Modifier = Modifier,
  scrollBehaviour: TopAppBarScrollBehavior? = null,
  onNaveIconPressed: () -> Unit = {},
) {
  var functionalNotAvailableShow by remember { mutableStateOf(false) }
  if (functionalNotAvailableShow) {
    FunctionalityNotAvailablePopup { functionalNotAvailableShow = false }
  }
  RideChatAppBar(
    modifier = modifier,
    scrollBehavior = scrollBehaviour,
    onNavigationIconPressed = onNaveIconPressed,
    title = {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Channel name
        Text(text = channelName, style = MaterialTheme.typography.titleMedium)
          // channel members
          Text(
              text = stringResource(R.string.members, channelMembers),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
          )
      }
    },
    actions = {
        Icon(
            painter = painterResource(R.drawable.ic_search),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable(onClick = { functionalNotAvailableShow = true }),
            contentDescription = stringResource(R.string.search)
            )

        Icon(
            painter = painterResource(R.drawable.ic_info),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable(onClick = { functionalNotAvailableShow = true }),
            contentDescription = stringResource(R.string.info)
        )
    },
  )
}
