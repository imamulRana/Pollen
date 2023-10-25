package com.imstudio.pollen.ui.flowerList

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.domain.model.Flower

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FlowerListItem(
    modifier: Modifier = Modifier,
    flower: Flower,
    onClick: () -> Unit,
    context: Context
) {
    var clicked by rememberSaveable {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = onClick
        ) {
            Box(modifier = modifier, contentAlignment = Alignment.TopEnd) {
                GlideImage(
                    model = flower.flowerImage,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Box(modifier = modifier
                    .padding(PollenModifier.mediumPadding)
                    .clickable { clicked = !clicked }
                    .clip(RoundedCornerShape(25))
                    .background(MaterialTheme.colorScheme.primary)
                    .size(40.dp),
                    contentAlignment = Alignment.Center) {
                    if (clicked) {
                        Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "")
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = PollenModifier.mediumPadding,
                        vertical = PollenModifier.largePadding
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = flower.flowerName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "৳ ${flower.flowerPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
