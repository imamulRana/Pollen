package com.imstudio.pollen.ui.cart

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.domain.model.Flower

@OptIn(
    ExperimentalGlideComposeApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun FlowerCartItem(
    modifier: Modifier = Modifier,
    flower: Flower,
    deleteFlower: () -> Unit
) {
    val state = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                deleteFlower()
                true
            } else false
        }
    )
    var timesClicked by rememberSaveable {
        mutableIntStateOf(1)
    }
    SwipeToDismiss(state = state, background = {
        val color by animateColorAsState(
            when (state.targetValue) {
                DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.surface
                DismissValue.Default -> MaterialTheme.colorScheme.surface
                else -> Color(0xFFF3295D)
            }, label = ""
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.large)
                .background(color)
        )
    }, dismissContent = {
        ListItem(
            modifier = modifier
                .clip(MaterialTheme.shapes.large),
            leadingContent = {
                GlideImage(
                    modifier = Modifier
                        .padding(vertical = PollenModifier.mediumPadding)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .size(80.dp),
                    model = flower.flowerImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            },
            headlineContent = { Text(text = flower.flowerName) },
            supportingContent = { Text(text = flower.flowerPrice.toString()) },
//            trailingContent = {
//                Row {
//                    OutlinedIconButton(
//                        shape = RoundedCornerShape(35),
//                        onClick = {
//                            timesClicked -= 1
//                        }) {
//                        Icon(
//                            imageVector = Icons.Outlined.Delete,
//                            contentDescription = ""
//                        )
//                    }
//                    FilledIconButton(
//                        shape = RoundedCornerShape(35),
//                        colors = IconButtonDefaults.outlinedIconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.primary
//                        ),
//                        onClick = {
//
//                        },
//                    ) {
//                        Text(
//                            timesClicked.toString()
//                        )
//                    }
//                    OutlinedIconButton(
//                        shape = RoundedCornerShape(35),
//                        onClick = {
//                            timesClicked++
//                        }) {
//                        Icon(
//                            imageVector = Icons.Outlined.Add,
//                            contentDescription = ""
//                        )
//                    }
//                }
//            },
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.secondary)
        )
    }, directions = setOf(DismissDirection.EndToStart))
}