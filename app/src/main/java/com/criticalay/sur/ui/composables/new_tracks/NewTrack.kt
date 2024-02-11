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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.sur.model.Track
import com.criticalay.sur.ui.composables.track.CoverArt
import com.criticalay.sur.utils.ContentFactory

@Composable
fun NewTrack(
    modifier: Modifier = Modifier,
    track: Track
) {
    Column(
        modifier = modifier.padding(8.dp)
            .widthIn(max = 120.dp)
            .semantics(mergeDescendants = true) { },
    ) {
        CoverArt(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            track = track
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = track.title,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp
        )
        Text(
            text = track.artist,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_NewTrack() {
    MaterialTheme {
        NewTrack(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}