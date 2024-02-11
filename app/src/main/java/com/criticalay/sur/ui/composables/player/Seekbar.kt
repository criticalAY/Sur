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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.model.NowPlaying
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_SEEK_BAR

@Composable
fun PlayerSeekBar(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying,
    canSeekTrack: Boolean,
    onSeekChanged: ((value: Float) -> Unit)? = null
) {
    val currentPosition = nowPlaying.position.toFloat() / nowPlaying.track.length.toFloat()
    if (canSeekTrack) {
        Slider(
            modifier = modifier.testTag(TAG_SEEK_BAR),
            enabled = canSeekTrack,
            value = currentPosition,
            onValueChange = {
                onSeekChanged?.invoke(nowPlaying.track.length * it)
            }
        )
    } else {
        LinearProgressIndicator(
            progress = {
                currentPosition
            },
            modifier = modifier.testTag(TAG_SEEK_BAR),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PlayerSeekBar_CanSeek() {
    MaterialTheme {
        PlayerSeekBar(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            canSeekTrack = true,
            onSeekChanged = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PlayerSeekBar_CannotSeek() {
    MaterialTheme {
        PlayerSeekBar(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            canSeekTrack = false,
            onSeekChanged = { }
        )
    }
}