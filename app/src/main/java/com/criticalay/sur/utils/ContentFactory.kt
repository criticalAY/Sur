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
package com.criticalay.sur.utils

import androidx.compose.ui.graphics.Color
import com.criticalay.sur.model.NowPlaying
import com.criticalay.sur.model.NowPlayingState
import com.criticalay.sur.model.Track

object ContentFactory {

    fun makeNowPlaying(track: Track): NowPlaying {
        return NowPlaying(
            track,
            50,
            NowPlayingState.PAUSED
        )
    }

    fun makeTrack(): Track {
        return Track(
            "1",
            "Dude Ranch",
            "Blink 182",
            Color.Red,
            length = 340,
            isFeatured = true,
            isNew = false
        )
    }

    fun makeContentList(): List<Track> {
        return listOf(
            Track(
                "1",
                "Dude Ranch",
                "Blink 182",
                Color.Red,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "2",
                "Liquor",
                "Lydia",
                Color.Magenta,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "3",
                "Blurryface",
                "Twenty One Pilots",
                Color.Cyan,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "4",
                "American Candy",
                "The Maine",
                Color.Blue,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "5",
                "Umbra",
                "Grayscale",
                Color.Gray,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "6",
                "The Myth of The Happily Ever After",
                "Biffy Clyro",
                Color.Blue,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "7",
                "Along the Shadow",
                "Saosin",
                Color.Red,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "8",
                "Still Sucks",
                "Limp Bizkit",
                Color.Magenta,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "9",
                "Survivors Guilt",
                "Kenny Hoopla",
                Color.Cyan,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "10",
                "Suckapunch",
                "You Me At Six",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "11",
                "Tell Me About Tomorrow",
                "jxdn",
                Color.Gray,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "12",
                "Greatest Hits",
                "Waterparks",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "13",
                "Model Citizen",
                "Meet Me @ The Altar",
                Color.Red,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "14",
                "Futures",
                "Jimmy Eat World",
                Color.Magenta,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "15",
                "Don Broco",
                "Amazing Things",
                Color.Cyan,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "16",
                "Internal Atomics",
                "Stray From The Path",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "17",
                "Bloodlust",
                "nothing,nowhere.",
                Color.Gray,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "18",
                "Mother Nature",
                "The Dangerous Summer",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = false
            )
        )
    }

}