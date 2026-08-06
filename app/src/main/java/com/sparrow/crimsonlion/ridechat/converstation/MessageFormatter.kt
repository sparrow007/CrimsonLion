package com.sparrow.crimsonlion.ridechat.converstation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight


val symbolPattern by lazy {
    Regex("""(https?://[^\s\t\n]+)|(`[^`]+`)|(@\w+)|(\*[\w]+\*)|(_[\w]+_)|(~[\w]+~)""")
}


enum class SymbolAnnotationType {
    PERSON,
    LINK
}

typealias StringAnnotation = AnnotatedString.Range<String>

typealias SymbolAnnotation = Pair<AnnotatedString, StringAnnotation?>

@Composable
fun messageFormatter(text: String, primary: Boolean): AnnotatedString {
    val tokens = symbolPattern.findAll(text)
    return buildAnnotatedString {
        var cursorPosition = 0

        val codeSnippetBackground =
            if (primary) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.surface
            }

        for (token in tokens) {
            append(text.slice(cursorPosition until token.range.first))

            val (annotatedString, stringAnnotation = )
        }
    }
}

//Last staart from here
private fun getSymbolAnnotation(
    matchResult: MatchResult,
    colorScheme: ColorScheme,
    primary: Boolean,
    codeSnippetBackground: Color,
): SymbolAnnotation {

    return when (matchResult.value.first()) {
        '@' -> SymbolAnnotation(
            AnnotatedString(
                text = matchResult.value,
                spanStyle = SpanStyle(
                    color = if (primary) colorScheme.inversePrimary else colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            ),
            StringAnnotation(
                item = matchResult.value.substring(1),
                start = matchResult.range.first,
                end = matchResult.range.last,
                tag = SymbolAnnotationType.PERSON.name,
            ),
        )
    }

}

