package com.sparrow.crimsonlion.ridechat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparrow.crimsonlion.R
import com.sparrow.crimsonlion.ui.theme.CrimsonLionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideChatAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationIconPressed: ()-> Unit = {},
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = title,
        actions = actions,
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        navigationIcon = {
            RideChatIcon(
                contentDescription = stringResource(R.string.navigation_drawer_open),
                modifier = Modifier.size(64.dp)
                    .clickable(onClick = onNavigationIconPressed)
                    .padding(all = 16.dp)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RidechatAppBarPreview() {
    CrimsonLionTheme {
        RideChatAppBar(title = { Text("Preview!") })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RidechatAppBarPreviewDark() {
    CrimsonLionTheme(darkTheme = true) {
        RideChatAppBar(title = { Text("Preview!") })
    }
}
