package com.sparrow.crimsonlion.ridechat.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
    channelName: String,
    channelMembers: String,
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    onNaveIconPressed: () -> Unit = {}
) {
    var functionalNotAvailableShow by remember { mutableStateOf(false) }
    if (functionalNotAvailableShow) {

    }



}
