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
package com.criticalay.sur.ui.composables.new_tracks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.R
import com.criticalay.sur.model.Track
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_NEW_TRACKS
import com.criticalay.sur.utils.Tags.TAG_TRACK

@ExperimentalFoundationApi
@Composable
fun NewTracksRow(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        val lazyListState = rememberLazyListState()
        LazyRow(
            modifier = modifier
                .testTag(TAG_NEW_TRACKS),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(tracks) { track ->
                val playTrackDescription = stringResource(
                    id = R.string.cd_play_track,
                    track.title, track.artist
                )
                NewTrack(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClickLabel = playTrackDescription) {
                            onTrackClicked(track)
                        }
                        .testTag(TAG_TRACK + track.id),
                    track = track
                )

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_NewTracksRow() {
    MaterialTheme {
        NewTracksRow(
            modifier = Modifier.fillMaxWidth(),
            tracks = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}