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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.R
import com.criticalay.sur.model.NowPlaying
import com.criticalay.sur.ui.composables.track.CoverArt
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_DISMISS_PLAYER
import com.criticalay.sur.utils.Tags.TAG_PLAY_PAUSE
import com.criticalay.sur.utils.Tags.TAG_TRACK_ARTIST
import com.criticalay.sur.utils.Tags.TAG_TRACK_TITLE
import com.criticalay.sur.utils.descriptionForNowPlayingState
import com.criticalay.sur.utils.iconForPlayingState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun Player(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying,
    toggleNowPlayingState: () -> Unit,
    rewindTrack: () -> Unit,
    fastForwardTrack: () -> Unit,
    onSeekChanged: (position: Float) -> Unit,
    onClose: () -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("mm:ss", Locale.getDefault()) }
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val closeAction = stringResource(id = R.string.cd_close_now_playing)
        Icon(
            modifier = Modifier
                .align(Alignment.Start)
                .clickable(onClickLabel = closeAction) { onClose() }
                .testTag(TAG_DISMISS_PLAYER),
            imageVector = Icons.Default.Cancel,
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(1f))

        CoverArt(
            modifier = Modifier.size(240.dp),
            track = nowPlaying.track
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.semantics(mergeDescendants = true) { }
        ) {
            Text(
                modifier = Modifier.testTag(
                    TAG_TRACK_TITLE
                ),
                fontSize = 22.sp,
                text = nowPlaying.track.title
            )

            Text(
                modifier = Modifier.testTag(
                    TAG_TRACK_ARTIST
                ),
                fontSize = 18.sp,
                text = nowPlaying.track.artist,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(36.dp))

        val horizontalPadding = 32.dp
        PlayerSeekBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            nowPlaying = nowPlaying,
            canSeekTrack = true
        ) {
            onSeekChanged(it)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
        ) {
            Text(text = dateFormat.format(Date(nowPlaying.position * 1000L)))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = dateFormat.format(Date(nowPlaying.track.length * 1000L)))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    rewindTrack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FastRewind,
                    contentDescription = stringResource(id = R.string.cd_rewind)
                )
            }
            IconButton(
                modifier = Modifier.testTag(TAG_PLAY_PAUSE),
                onClick = {
                    toggleNowPlayingState()
                }
            ) {
                Icon(
                    imageVector = iconForPlayingState(nowPlaying.state),
                    contentDescription = stringResource(
                        id = descriptionForNowPlayingState(nowPlaying.state)
                    )
                )
            }
            IconButton(
                onClick = {
                    fastForwardTrack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FastForward,
                    contentDescription = stringResource(id = R.string.cd_fast_forward)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Player() {
    MaterialTheme {
        Player(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            toggleNowPlayingState = { },
            rewindTrack = { },
            fastForwardTrack = { },
            onSeekChanged = { },
            onClose = { }
        )
    }
}