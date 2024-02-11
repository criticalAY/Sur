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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.R
import com.criticalay.sur.model.Track
import com.criticalay.sur.utils.ContentFactory

@ExperimentalFoundationApi
fun LazyListScope.NewTracks(
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.heading_new),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        NewTracksRow(
            modifier = Modifier.fillMaxWidth(),
            tracks = tracks,
            onTrackClicked = { track ->
                onTrackClicked(track)
            }
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_NewTracks() {
    MaterialTheme {
        LazyColumn(content = {
            NewTracks(tracks = ContentFactory.makeContentList(), onTrackClicked = {})
        })
    }
}