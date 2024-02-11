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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.model.NowPlaying
import com.criticalay.sur.ui.composables.track.CoverArt
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_PLAY_PAUSE
import com.criticalay.sur.utils.Tags.TAG_SEEK_BAR
import com.criticalay.sur.utils.descriptionForNowPlayingState
import com.criticalay.sur.utils.iconForPlayingState

@Composable
fun PlayerBar(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying?,
    toggleNowPlayingState: () -> Unit
) {
    nowPlaying?.let {
        Column(modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            PlayerSeekBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TAG_SEEK_BAR),
                nowPlaying = nowPlaying,
                canSeekTrack = false
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoverArt(
                    modifier = Modifier.size(32.dp),
                    track = nowPlaying.track
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = nowPlaying.track.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Text(
                        text = nowPlaying.track.artist,
                        fontSize = 10.sp
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
                            id = descriptionForNowPlayingState(
                                nowPlaying.state
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerBar() {
    MaterialTheme {
        PlayerBar(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            toggleNowPlayingState = { }
        )
    }
}