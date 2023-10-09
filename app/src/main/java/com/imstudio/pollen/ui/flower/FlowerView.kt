package com.imstudio.pollen.ui.flower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.navigation.Screens

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FlowerView(
    pollenViewModel: PollenViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val flowerList by pollenViewModel.flowersList.collectAsState()
    val flower by pollenViewModel.flower.collectAsState()
    val flowerCartState by pollenViewModel.flowerCartState.collectAsState()
//    val flowerCart by pollenViewModel.flowerCart.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth(.5f)
                .fillMaxSize(.4f)
        ) {
            GlideImage(
                model = flowerList[flower].flowerImage,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = flowerList[flower].flowerName,
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedButton(modifier = modifier.fillMaxWidth(),
            onClick = {
                pollenViewModel.addFlower(flower, flowerList[flower])
                navController.navigate(Screens.FlowerCartView.route)
            }) {
            Text(text = "Add To Cart")
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "Buy")
        }
    }
}