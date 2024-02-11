/*
 * Copyright (c) 2024 Ashish Yadav <mailtoashish693@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.criticalay.sur.ui.composables.player

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.R
import com.criticalay.sur.model.MusicDashboardEvent
import com.criticalay.sur.model.MusicDashboardState
import com.criticalay.sur.utils.Tags.TAG_PLAYER
import com.criticalay.sur.utils.Tags.TAG_PLAYER_BAR



@ExperimentalAnimationApi
@Composable
fun MusicPlayerCollapsed(
    state: MusicDashboardState,
    handleEvent: (event: MusicDashboardEvent) -> Unit,
    playerClick : ()->Unit
) {
    Column {
        HorizontalDivider(
            modifier = Modifier.height(2.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
        val onClickLabel = stringResource(id = R.string.action_show_player)
        PlayerBar(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClickLabel = onClickLabel
                ) {
                    playerClick()
                }
                .testTag(TAG_PLAYER_BAR),
            nowPlaying = state.nowPlaying,
            toggleNowPlayingState = {
                handleEvent(MusicDashboardEvent.ToggleNowPlayingState)
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun MusicPlayerExpanded(
    state: MusicDashboardState,
    handleEvent: (event: MusicDashboardEvent) -> Unit,
    onClose:() -> Unit
) {

    state.nowPlaying?.let { nowPlaying ->
        Player(
            modifier = Modifier
                .fillMaxSize()
                .testTag(TAG_PLAYER),
            nowPlaying = nowPlaying,
            rewindTrack = {
                handleEvent(MusicDashboardEvent.RewindNowPlaying)
            },
            fastForwardTrack = {
                handleEvent(MusicDashboardEvent.FastForwardNowPlaying)
            },
            onSeekChanged = {
                handleEvent(MusicDashboardEvent.SeekTrack(it))
            },
            toggleNowPlayingState = {
                handleEvent(MusicDashboardEvent.ToggleNowPlayingState)
            },
            onClose = {
                onClose()
            }
        )
    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewMusicPlayer() {
    MaterialTheme {
        MusicPlayerExpanded(state = MusicDashboardState(), handleEvent ={} ) {
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewMusicPlayerCollapsed() {
    MaterialTheme {
        MusicPlayerCollapsed(state = MusicDashboardState(), handleEvent ={} ) {
        }
    }
}