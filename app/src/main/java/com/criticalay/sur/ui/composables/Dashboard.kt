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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.criticalay.sur.model.MusicDashboardEvent
import com.criticalay.sur.model.MusicDashboardState
import com.criticalay.sur.ui.composables.player.MusicPlayerCollapsed
import com.criticalay.sur.ui.composables.player.MusicPlayerExpanded
import com.criticalay.sur.ui.composables.search.SearchBar
import com.criticalay.sur.utils.ContentFactory
import com.criticalay.sur.utils.Tags.TAG_SEARCH_BAR


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Dashboard(
    state: MusicDashboardState,
    handleEvent: (contentEvent: MusicDashboardEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        handleEvent(MusicDashboardEvent.RefreshContent)
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TAG_SEARCH_BAR),
                query = state.searchTerm,
                handleQuery = { searchString ->
                    handleEvent(MusicDashboardEvent.Search(searchString))
                },
                clearQuery = {
                    handleEvent(MusicDashboardEvent.ClearSearchQuery)
                }
            )
        },
        bottomBar = {
            MusicPlayerCollapsed(state = state, handleEvent = handleEvent) {
                showBottomSheet = true
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            TracksDashboard(
                modifier = Modifier
                    .fillMaxSize(),
                state = state,
                onTrackClicked = { track ->
                    handleEvent(MusicDashboardEvent.PlayTrack(track))
                }
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                MusicPlayerExpanded(state = state, handleEvent = handleEvent) {
                    showBottomSheet = false
                }
            }
        }
    }
}

@Composable
@Preview
fun DashBoardPreview() {
    Dashboard(
        state = MusicDashboardState(
            tracks = ContentFactory.makeContentList(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack())
        ),
        handleEvent = { }
    )
}