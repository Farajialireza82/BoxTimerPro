package com.cromulent.box_timer.presentation.settings_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StringPickerBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    title: String,
    items: List<String>,
    selectedString: String,
    onItemSelected: (String) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val parentLayoutDirection = LocalLayoutDirection.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides parentLayoutDirection) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {

                TitleText(
                    modifier = Modifier
                        .padding(12.dp),
                    text = title
                )

                Spacer(Modifier.size(8.dp))

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    items(items) {

                        StringItem(
                            title = it,
                            isSelected = it == selectedString,
                            onItemSelected = onItemSelected
                        )

                    }
                }
            }
        }
    }


}

@Preview
@Composable
private fun StringItem(
    title: String,
    isSelected: Boolean = false,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemSelected(title) }),
        border = BorderStroke(
            2.dp,
            if (isSelected) MaterialTheme.colorScheme.secondary else Transparent
        ),
        colors = CardDefaults.cardColors(
            containerColor = Transparent,
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                text = title,
                textAlign = TextAlign.Start, // This will automatically be RTL-aware
                color = if (isSelected) MaterialTheme.colorScheme.secondary else White,
                fontWeight = FontWeight.W500
            )
        }
    }


}