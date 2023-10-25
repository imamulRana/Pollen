package com.imstudio.pollen.ui.flower

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.navigation.Screens

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FlowerView(
    pollenViewModel: PollenViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val flowerViewState by pollenViewModel.flowerViewState.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(PollenModifier.largePadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = flowerViewState.flower.flowerName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "৳ ${flowerViewState.flower.flowerPrice}",
                    style = MaterialTheme.typography.titleLarge
                )

            }
            Text(
                modifier = modifier
                    .padding(
                        start = PollenModifier.largePadding,
                        bottom = PollenModifier.largePadding
                    ),
                text = flowerViewState.flower.flowerDescription,
                style = MaterialTheme.typography.bodyLarge
            )
            Card(
                modifier = modifier
                    .padding(PollenModifier.mediumPadding)
                    .clip(MaterialTheme.shapes.medium)
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                GlideImage(
                    model = flowerViewState.flower.flowerImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }


        Column(
            modifier
                .fillMaxWidth()
                .padding(
                    start = PollenModifier.mediumPadding,
                    end = PollenModifier.mediumPadding,
                    bottom = 30.dp
                ),
            verticalArrangement = Arrangement.spacedBy(PollenModifier.mediumPadding)
        ) {

            OutlinedButton(modifier = modifier
                .fillMaxWidth()
                .height(80.dp),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
                onClick = {
                    pollenViewModel.addToCart(flowerViewState.flower)
                    Toast.makeText(context, "Successfully Added to Cart", Toast.LENGTH_SHORT).show()
                }) {
                Text(text = "Add To Cart", style = MaterialTheme.typography.bodyLarge)
            }
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    pollenViewModel.addToCart(flowerViewState.flower)
                    navController.navigate(Screens.FlowerPaymentView.route)
                }) {
                Text(
                    text = "Buy",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
