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

package com.criticalay.sur.ui.composables.featured


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.model.Track
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_FEATURED_TRACKS
import com.criticalay.sur.utils.Tags.TAG_TRACK

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalFoundationApi
@Composable
fun FeaturedTracksGrid(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        val itemSize = (LocalConfiguration.current.screenWidthDp.dp / 2)
        FlowRow(
            modifier = modifier
                .testTag(TAG_FEATURED_TRACKS),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tracks.forEach { track ->
                FeaturedTrack(
                    modifier = Modifier
                        .width(itemSize)
                        .clickable {
                            onTrackClicked(track)
                        }
                        .testTag(TAG_TRACK + track.id),
                    track = track
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_FeaturedTracksGrid() {
    MaterialTheme {
        FeaturedTracksGrid(
            modifier = Modifier.fillMaxWidth(),
            tracks = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}