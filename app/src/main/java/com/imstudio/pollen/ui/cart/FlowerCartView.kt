package com.imstudio.pollen.ui.cart

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.PollenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun FlowerCartView(pollenViewModel: PollenViewModel) {
    val flowerList by pollenViewModel.flowersList.collectAsState()
    val flowerCart by pollenViewModel.flowerCartState.collectAsState()
    val flower by pollenViewModel.flower.collectAsState()
    LazyColumn {
        items(flowerCart.flowerList.size) {
            ListItem(leadingContent = {
                GlideImage(
                    modifier = Modifier
                        .size(80.dp),
                    model = flowerCart.flowerList[it].flowerImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }, headlineText = { Text(text = "") }, trailingContent = {
                IconButton(
                    onClick = { }) {
                    Icons.Rounded.Delete
                }
            })
        }
    }
}