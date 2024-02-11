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
package com.criticalay.sur.ui.composables.recent


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.R
import com.criticalay.sur.model.Track
import com.criticalay.sur.ui.composables.track.Tracks
import com.criticalay.sur.utils.Tags

fun LazyListScope.RecentTracks(
    tracks: List<Track>,
    onTrackClicked: (track: Track) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            text = stringResource(id = R.string.heading_recently_played),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
    items(tracks, key = { it.id }) {
        val playTrackDescription = stringResource(
            id = R.string.cd_play_track,
            it.title, it.artist
        )
        Tracks(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClickLabel = playTrackDescription) { onTrackClicked(it) }
                .testTag(Tags.TAG_TRACK + it.id),
            track = it
        )
    }
}