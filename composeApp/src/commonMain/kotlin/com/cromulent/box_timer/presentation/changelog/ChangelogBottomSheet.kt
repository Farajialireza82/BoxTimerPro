package com.cromulent.box_timer.presentation.changelog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.changelog_button_got_it
import boxtimerpro.composeapp.generated.resources.changelog_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangelogBottomSheet(
    version: String,
    changelogContent: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val parentLayoutDirection = LocalLayoutDirection.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides parentLayoutDirection) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Title with version
                    Text(
                        text = stringResource(Res.string.changelog_title).replace("{0}", version),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Changelog content with markdown support
                    MarkdownText(
                        text = changelogContent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),

                    )
                }

                // Got it button
                Button(
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.changelog_button_got_it),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MarkdownText(
    text: String,
    modifier: Modifier = Modifier
) {
    val lines = text.split("\n")
    val annotatedString = buildAnnotatedString {
        lines.forEach { line ->
            val trimmedLine = line.trim()
            
            when {
                // Bullet point (starts with - or *)
                trimmedLine.startsWith("-") || trimmedLine.startsWith("*") -> {
                    val content = trimmedLine.substring(1).trim()
                    append("• ")
                    appendMarkdownContent(content)
                    append("\n\n")
                }
                // Empty line
                trimmedLine.isEmpty() -> {
                    append("\n")
                }
                // Regular text
                else -> {
                    appendMarkdownContent(trimmedLine)
                    append("\n\n")
                }
            }
        }
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}

private fun androidx.compose.ui.text.AnnotatedString.Builder.appendMarkdownContent(text: String) {
    var currentIndex = 0
    val boldPattern = Regex("\\*\\*(.*?)\\*\\*")
    
    val matches = boldPattern.findAll(text)
    val matchList = matches.toList()
    
    if (matchList.isEmpty()) {
        // No bold text, just append as is
        append(text)
    } else {
        // Process bold text
        matchList.forEach { match ->
            // Append text before the match
            if (match.range.first > currentIndex) {
                append(text.substring(currentIndex, match.range.first))
            }
            
            // Append bold text
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(match.groupValues[1])
            }
            
            currentIndex = match.range.last + 1
        }
        
        // Append remaining text after last match
        if (currentIndex < text.length) {
            append(text.substring(currentIndex))
        }
    }
}

