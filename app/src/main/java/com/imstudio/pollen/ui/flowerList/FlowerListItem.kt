package com.imstudio.pollen.ui.flowerList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.domain.model.Flower
import com.imstudio.pollen.ui.theme.PollenTheme

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FlowerListItem(
    modifier: Modifier = Modifier,
    flower: Flower,
    onClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = onClick
        ) {
            GlideImage(
                model = flower.flowerImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Text(
                modifier = modifier
                    .offset(x = 10.dp),
                text = flower.flowerName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen(modifier: Modifier = Modifier) {
    PollenTheme {
//        FlowerListItem(flower = Flower())
    }
}