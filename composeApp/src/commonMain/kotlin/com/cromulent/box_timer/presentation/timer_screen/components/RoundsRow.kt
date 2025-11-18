package com.cromulent.box_timer.presentation.timer_screen.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.chip_round_number
import boxtimerpro.composeapp.generated.resources.chip_total_rounds
import com.cromulent.box_timer.presentation.timer_screen.TimerState
import org.jetbrains.compose.resources.stringResource


@Composable
fun RoundsRow(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(8.dp),
    state: TimerState) {

    var chipTextSize by remember { mutableStateOf(16.sp) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 24.dp,
                alignment = Alignment.CenterHorizontally
            )
    ) {

        Chip(
            modifier = Modifier
                .aspectRatio(1.9f),
            paddingValues = paddingValues,
            text = stringResource(Res.string.chip_round_number) + state.currentRound,
            onSizeChanged = {
                chipTextSize = it
            }
        )

        Chip(
            modifier = Modifier
                .aspectRatio(1.9f),
            paddingValues = paddingValues,
            text = if (state.totalRounds == 1000) "∞" else stringResource(Res.string.chip_total_rounds) + state.totalRounds,
            textSize = chipTextSize
        )

    }
}
