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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.R
import com.criticalay.sur.model.Track
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.FeaturedTracks(
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.heading_featured),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        FeaturedTracksGrid(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(Tags.TAG_FEATURED_TRACKS),
            tracks = tracks,
            onTrackClicked = { track ->
                onTrackClicked(track)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview_NewTracks() {
    MaterialTheme {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 8.dp
            ),
            content = {
            FeaturedTracks(tracks = ContentFactory.makeContentList(), onTrackClicked = {})
        })
    }
}