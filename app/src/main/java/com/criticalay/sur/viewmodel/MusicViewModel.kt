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
package com.criticalay.sur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.criticalay.sur.model.MusicDashboardEvent
import com.criticalay.sur.model.MusicDashboardState
import com.criticalay.sur.model.NowPlayingState
import com.criticalay.sur.model.Status
import com.criticalay.sur.utils.ContentFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    val uiState = MutableStateFlow(MusicDashboardState())
    private var nowPlayingFlow: Job? = null

    fun handleEvent(contentEvent: MusicDashboardEvent) {
        when (contentEvent) {
            is MusicDashboardEvent.RefreshContent -> loadContent()
            is MusicDashboardEvent.Search -> performSearch(contentEvent.searchTerm)
            is MusicDashboardEvent.ClearSearchQuery -> {
                uiState.value = uiState.value.copy(
                    searchTerm = null
                )
            }

            is MusicDashboardEvent.PlayTrack -> {
                uiState.value = uiState.value.copy(
                    nowPlaying = ContentFactory.makeNowPlaying(
                        contentEvent.track
                    )
                )
                playMusic()
            }

            MusicDashboardEvent.ToggleNowPlayingState -> {
                toggleNowPlayingState()
            }

            is MusicDashboardEvent.SeekTrack -> {
                uiState.value = uiState.value.copy(
                    nowPlaying =
                    uiState.value.nowPlaying!!.copy(
                        position =
                        contentEvent.position.toLong()
                    )
                )
            }

            MusicDashboardEvent.FastForwardNowPlaying -> fastForwardMusic()
            MusicDashboardEvent.RewindNowPlaying -> rewindMusic()
        }
    }

    private fun toggleNowPlayingState() {
        if (uiState.value.nowPlaying?.state ==
            NowPlayingState.PLAYING
        ) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    private fun pauseMusic() {
        nowPlayingFlow?.cancel()
        uiState.value = uiState.value.copy(
            nowPlaying = uiState.value.nowPlaying!!.copy(
                state = NowPlayingState.PAUSED
            )
        )
    }

    private fun playMusic() {
        nowPlayingFlow?.cancel()
        val nowPlaying = uiState.value.nowPlaying!!
        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                state = NowPlayingState.PLAYING
            )
        )
        nowPlayingFlow = viewModelScope.launch {
            while (isActive) {
                val maxLength =
                    uiState.value.nowPlaying!!.track.length
                val newPosition =
                    uiState.value.nowPlaying!!.position + 1
                if (newPosition >= maxLength) {
                    uiState.value = uiState.value.copy(
                        nowPlaying = nowPlaying.copy(
                            state = NowPlayingState.STOPPED,
                            position = 0
                        )
                    )
                    cancel()
                } else {
                    uiState.value = uiState.value.copy(
                        nowPlaying = nowPlaying.copy(
                            state = NowPlayingState.PLAYING,
                            position = newPosition
                        )
                    )
                }
                delay(1000)
            }
        }
    }

    private fun fastForwardMusic() {
        val nowPlaying = uiState.value.nowPlaying!!

        val maxLength = nowPlaying.track.length
        val newPosition = nowPlaying.position + 10
        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                position = if (newPosition > maxLength) maxLength else newPosition
            )
        )
    }

    private fun rewindMusic() {
        val nowPlaying = uiState.value.nowPlaying!!
        val newPosition = nowPlaying.position - 10
        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                position = if (newPosition < 0) 0 else newPosition
            )
        )
    }

    private fun performSearch(searchTerm: String) {
        val results = uiState.value.tracks?.filter {
            it.title.contains(searchTerm, ignoreCase = true) ||
                    it.artist.contains(searchTerm, ignoreCase = true)
        }
        uiState.value = uiState.value.copy(
            searchTerm = searchTerm,
            searchResults = results
        )
    }

    private fun loadContent() {
        val data = ContentFactory.makeContentList()
        uiState.value = uiState.value.copy(
            status = Status.SUCCESS,
            tracks = data,
            nowPlaying = ContentFactory.makeNowPlaying(data[0])
        )
    }

    override fun onCleared() {
        nowPlayingFlow?.cancel()
        super.onCleared()
    }
}