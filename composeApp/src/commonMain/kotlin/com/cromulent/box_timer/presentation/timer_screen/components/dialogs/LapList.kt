package com.cromulent.box_timer.presentation.timer_screen.components.dialogs
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.title_round
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.core.util.formatTimeWithMillis
import com.cromulent.box_timer.domain.timer.Lap
import org.jetbrains.compose.resources.stringResource

@Composable
fun LapList(modifier: Modifier = Modifier, laps: List<Lap>, roundDuration: Long) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            laps
        ) { index, item ->

            val previousItem = laps.getOrNull(index + 1)
            val previousItemCreateTime =
                if (previousItem != null && previousItem.roundNumber == item.roundNumber) {
                    laps[index + 1].createTime
                } else {
                    roundDuration
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${stringResource(Res.string.title_round)} ${item.roundNumber}",
                    fontWeight = W500,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = formatTime(item.createTime),
                    fontWeight = W500,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = formatTimeWithMillis(previousItemCreateTime - item.createTime),
                    fontWeight = W500,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
        }
    }
}
