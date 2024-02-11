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
package com.criticalay.sur.ui.composables.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.R
import com.criticalay.sur.model.Track
import com.criticalay.sur.ui.composables.track.Tracks
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_SEARCH_RESULTS
import com.criticalay.sur.utils.Tags.TAG_TRACK

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    results: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (results.isNullOrEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.message_search_results))
        }
    } else {
        LazyColumn(
            modifier = modifier.testTag(TAG_SEARCH_RESULTS),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(results) {
                val selectTrackAction = stringResource(id = R.string.action_select_track, it.title)
                Tracks(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClickLabel = selectTrackAction) {
                            onTrackClicked(it)
                        }
                        .testTag(TAG_TRACK + it.id),
                    track = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SearchResults() {
    MaterialTheme {
        SearchResults(
            modifier = Modifier.fillMaxWidth(),
            results = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SearchResults_empty() {
    MaterialTheme {
        SearchResults(
            modifier = Modifier.fillMaxSize(),
            results = emptyList(),
            onTrackClicked = { }
        )
    }
}