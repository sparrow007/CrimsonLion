package com.sparrow.crimsonlion.ridechat

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sparrow.crimsonlion.ridechat.components.RideChatAppBar
import com.sparrow.crimsonlion.ridechat.converstation.ConversationUiState
import com.sparrow.crimsonlion.ui.theme.CrimsonLionTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideChatMessageScreen(
    initialConversationUiState: ConversationUiState,
    navigateToProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit
    ) {
    val scrollState = rememberLazyListState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            RideChatAppBar(
                title = {
                    Text("Your's Ride Chat")
                },
                onNavigationIconPressed = onNavIconPressed,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->

    }
    CrimsonLionTheme {
        RideChatAppBar(
            title = {
                Text("Your's Ride Chat")
            }
        )
    }
}