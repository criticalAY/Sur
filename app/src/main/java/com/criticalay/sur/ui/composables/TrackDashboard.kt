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

package com.criticalay.sur.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.model.MusicDashboardState
import com.criticalay.sur.model.Track
import com.criticalay.sur.ui.composables.featured.FeaturedTracks
import com.criticalay.sur.ui.composables.new_tracks.NewTracks
import com.criticalay.sur.ui.composables.recent.RecentTracks
import com.criticalay.sur.ui.composables.search.SearchResults
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_SEARCH_RESULTS
import com.criticalay.sur.utils.Tags.TAG_TRACKS_DASHBOARD


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TracksDashboard(
    modifier: Modifier = Modifier,
    state: MusicDashboardState,
    onTrackClicked: (track: Track) -> Unit
) {
    if (state.searchTerm.isNullOrEmpty()) {
        LazyColumn(
            modifier = modifier.testTag(TAG_TRACKS_DASHBOARD),
            contentPadding = PaddingValues(top = 12.dp, bottom = 8.dp)
        ) {
            FeaturedTracks(
                tracks = state.featuredTracks(),
                onTrackClicked = onTrackClicked
            )
            NewTracks(
                tracks = state.newTracks(),
                onTrackClicked = onTrackClicked
            )
            if (state.recentTracks().isNotEmpty()) {
                RecentTracks(
                    tracks = state.recentTracks(),
                    onTrackClicked = onTrackClicked
                )
            }
        }
    } else {
        SearchResults(
            modifier = modifier
                .fillMaxWidth()
                .testTag(TAG_SEARCH_RESULTS),
            results = state.searchResults,
            onTrackClicked = onTrackClicked
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TracksDashboardPreview() {
    MaterialTheme {
        TracksDashboard(
            modifier = Modifier.fillMaxWidth(),
            state = MusicDashboardState(
                tracks = ContentFactory.makeContentList()
            ),
            onTrackClicked = { }
        )
    }
}