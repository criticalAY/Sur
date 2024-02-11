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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.sur.R
import com.criticalay.sur.utils.Tags.TAG_SEARCH_BAR
import timber.log.Timber

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String?,
    handleQuery: (query: String) -> Unit,
    clearQuery: () -> Unit
) {
    var inputHasFocus by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val onSurfaceWithAlpha = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputHasFocus = it.hasFocus
                }
                .testTag(TAG_SEARCH_BAR),
            singleLine = true,
            value = query ?: "",
            onValueChange = {
                Timber.d("Query value", it)
                handleQuery(it)
            },
            trailingIcon = {
                if (query.isNullOrEmpty() && !inputHasFocus) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.cd_search)
                    )
                } else {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                        clearQuery()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.cd_clear_query)
                        )
                    }
                }
            }
        )
        if (!inputHasFocus && query.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                color = onSurfaceWithAlpha,
                text = stringResource(id = R.string.hint_search)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SearchBar() {
    MaterialTheme {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = "",
            handleQuery = {},
            clearQuery = {}
        )
    }
}