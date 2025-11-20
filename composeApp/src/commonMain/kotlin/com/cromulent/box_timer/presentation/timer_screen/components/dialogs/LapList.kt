package com.cromulent.box_timer.presentation.timer_screen.components.dialogs
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.title_round
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.core.util.formatTimeWithMillis
import com.cromulent.box_timer.domain.timer.Lap
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Trash
import org.jetbrains.compose.resources.stringResource

@Composable
fun LapList(
    modifier: Modifier = Modifier,
    laps: List<Lap>,
    roundDuration: Long,
    deleteLap: (Lap) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(laps.size) {
        if (laps.isNotEmpty()) {
            listState.animateScrollToItem(0)
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        itemsIndexed(
            laps,
            key = { _, item -> item.createTime }
        ) { index, item ->

            val previousItem = laps.getOrNull(index + 1)
            val previousItemCreateTime =
                if (previousItem != null && previousItem.roundNumber == item.roundNumber) {
                    laps[index + 1].createTime
                } else {
                    roundDuration
                }

            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { dismissValue ->
                    if (dismissValue == SwipeToDismissBoxValue.EndToStart || dismissValue == SwipeToDismissBoxValue.StartToEnd) {
                        deleteLap(item)
                        true
                    } else {
                        false
                    }
                },
                positionalThreshold = with(LocalDensity.current) { { 320.dp.toPx() } }
            )

            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .background(
                                MaterialTheme.colorScheme.errorContainer,
                                RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp),
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Trash,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(20.dp)
                                .align(CenterEnd)
                        )
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Trash,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(20.dp)
                                .align(CenterStart)
                        )
                    }
                },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( vertical = 4.dp)
                        .background(
                            MaterialTheme.colorScheme.surface,
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
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
}
