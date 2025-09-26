package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutModeGrid(
    selectedMode: WorkoutMode,
    onModeSelected: (WorkoutMode) -> Unit,
    modifier: Modifier = Modifier,
    emojiSize: TextUnit = 32.sp,
    titleSize: TextUnit = 24.sp,
    descriptionSize: TextUnit = 12.sp,
    columns: Int = 2
) {
    val modes = WorkoutMode.entries.toTypedArray()
    val rows = (modes.size + columns - 1) / columns // Calculate number of rows needed

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (rowIndex in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (colIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + colIndex
                    if (itemIndex < modes.size) {
                        val mode = modes[itemIndex]
                        ModeCard(
                            mode = mode,
                            isSelected = selectedMode == mode,
                            onClick = onModeSelected,
                            emojiSize = emojiSize,
                            titleSize = titleSize,
                            descriptionSize = descriptionSize,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        // Empty space to maintain grid alignment
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(heightDp = 950)
@Composable
private fun Preview() {

    var selectedMode by remember { mutableStateOf(WorkoutMode.BOXING) }


    WorkoutModeGrid(
        selectedMode = selectedMode,
        onModeSelected = { mode ->
            selectedMode = mode
        },
    )

}