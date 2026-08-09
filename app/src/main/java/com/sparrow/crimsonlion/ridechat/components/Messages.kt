package com.sparrow.crimsonlion.ridechat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.sparrow.crimsonlion.R
import com.sparrow.crimsonlion.ridechat.converstation.Message
import com.sparrow.crimsonlion.ridechat.converstation.SymbolAnnotationType
import com.sparrow.crimsonlion.ridechat.converstation.messageFormatter

const val ConversationTestTag = "ConversationTestTag"

@Composable
fun Messages(
  messages: List<Message>,
  navigateToProfile: (String) -> Unit,
  scrollState: LazyListState,
  modifier: Modifier = Modifier,
) {
  val scope = rememberCoroutineScope()
  Box(modifier = modifier) {
    val authorMe = stringResource(R.string.author_me)
    LazyColumn(
      reverseLayout = true,
      state = scrollState,
      modifier = Modifier.testTag(ConversationTestTag).fillMaxSize(),
    ) {
      for (index in messages.indices) {
        val prevAuthor = messages.getOrNull(index - 1)?.author
        val nextAuthor = messages.getOrNull(index + 1)?.author
        val content = messages[index]
        val isFirstMessageByAuthor = prevAuthor != content.author
        val isLastMessageByAuthor = nextAuthor != content.author

        if (index == messages.size - 1) {
          item { DayHeader("20 Aug") }
        } else if (index == 2) {
          item { DayHeader("Today") }
        }

        item {
          Message(
            onAuthorClick = { name -> navigateToProfile(name) },
            msg = content,
            isUserMe = content.author == authorMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
          )
        }
      }
    }
  }
}

@Composable
fun Message(
  onAuthorClick: (String) -> Unit,
  msg: Message,
  isUserMe: Boolean,
  isFirstMessageByAuthor: Boolean,
  isLastMessageByAuthor: Boolean,
) {
  val borderColor =
    if (isUserMe) {
      MaterialTheme.colorScheme.primary
    } else {
      MaterialTheme.colorScheme.tertiary
    }

  val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
  Row(modifier = spaceBetweenAuthors) {
    if (isLastMessageByAuthor) {
      Image(
        modifier =
          Modifier.clickable(onClick = { onAuthorClick(msg.author) })
            .padding(horizontal = 16.dp)
            .size(42.dp)
            .border(width = 1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .align(Alignment.Top),
        painter = painterResource(id = msg.authorImage),
        contentScale = ContentScale.Crop,
        contentDescription = null,
      )
    } else {
      Spacer(modifier = Modifier.width(74.dp))
    }
  }
}

@Composable
fun AuthorAndTExtMessage(
  msg: Message,
  isUserMe: Boolean,
  isFirstMessageByAuthor: Boolean,
  isLastMessageByAuthor: Boolean,
  authorClicked: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier) {
    if (isLastMessageByAuthor) {
      AuthorNameTimestamp(msg)
    }
    ChatItemBubble(message = msg, isUserMe = isUserMe, authorClicked = authorClicked)
    if (isFirstMessageByAuthor) {
      // last message by author
      Spacer(modifier = Modifier.height(8.dp))
    } else {
      // Between each bubbles
      Spacer(modifier = Modifier.height(4.dp))
    }
  }
}

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun ChatItemBubble(message: Message, isUserMe: Boolean, authorClicked: (String) -> Unit) {
  val backgroundBubbleColor =
    if (isUserMe) {
      MaterialTheme.colorScheme.primary
    } else {
      MaterialTheme.colorScheme.surfaceVariant
    }

  Column {
    Surface(color = backgroundBubbleColor, shape = ChatBubbleShape) {
      ClickableMessage(message, isUserMe, authorClicked)
    }
    message.image?.let {
      Spacer(modifier = Modifier.height(4.dp))
      Surface(color = backgroundBubbleColor, shape = ChatBubbleShape) {
        Image(
          painter = painterResource(it),
          contentScale = ContentScale.Fit,
          modifier = Modifier.size(160.dp),
          contentDescription = stringResource(id = R.string.attached_image),
        )
      }
    }
  }
}

@Composable
fun ClickableMessage(message: Message, isUserMe: Boolean, authorClicked: (String) -> Unit) {
  val uriHandler = LocalUriHandler.current

  val styleMessage = messageFormatter(text = message.content, primary = isUserMe)

  ClickableText(
    text = styleMessage,
    style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
    modifier = Modifier.padding(all = 16.dp),
    onClick = {
      styleMessage.getStringAnnotations(it, it).firstOrNull()?.let { annotation ->
        when (annotation.tag) {
          SymbolAnnotationType.PERSON.name -> {
            authorClicked(annotation.item)
          }
          SymbolAnnotationType.LINK.name -> {
            uriHandler.openUri(annotation.item)
          }
          else -> Unit
        }
      }
    },
  )
}

@Composable
private fun AuthorNameTimestamp(msg: Message) {
  Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
    Text(
      text = msg.author,
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.alignBy(LastBaseline).paddingFrom(LastBaseline, after = 8.dp),
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = msg.timestamp,
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.alignBy(LastBaseline),
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
  }
}

@Composable
fun DayHeader(dayString: String) {
  Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).height(16.dp)) {
    DayHeaderLine()
    Text(
      text = dayString,
      modifier = Modifier.padding(horizontal = 16.dp),
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    DayHeaderLine()
  }
}

@Composable
fun RowScope.DayHeaderLine() {
  HorizontalDivider(
    modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
  )
}
