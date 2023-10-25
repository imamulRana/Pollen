package com.imstudio.pollen.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.navigation.Screens


@Composable
fun FlowerCartView(
    modifier: Modifier = Modifier,
    pollenViewModel: PollenViewModel,
    navController: NavController
) {
    val flowerCartState by pollenViewModel.flowerCartState.collectAsState()


    if (flowerCartState.cartFlowerList.isEmpty()) EmptyScreen() else
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn(
                modifier = modifier
                    .height(400.dp),
                contentPadding = PaddingValues(horizontal = PollenModifier.mediumPadding),
                verticalArrangement = Arrangement.spacedBy(PollenModifier.mediumPadding)
            ) {
                items(flowerCartState.cartFlowerList.size) { lazy ->
                    FlowerCartItem(
                        modifier = modifier,
                        flower = flowerCartState.cartFlowerList[lazy],
                        deleteFlower = { pollenViewModel.deleteFromCart(flowerCartState.cartFlowerList[lazy]) }
                    )
                }
            }


            Column(
                modifier = modifier.padding(
                    bottom = 30.dp,
                    start = PollenModifier.mediumPadding,
                    end = PollenModifier.mediumPadding
                )
            ) {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(25)
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = PollenModifier.largePadding
                                ), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total Price is",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "৳ ${flowerCartState.cartFlowerList.sumOf { it.flowerPrice }}",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(PollenModifier.mediumPadding))
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
                        navController.navigate(Screens.FlowerPaymentView.route)
                    },
                ) {
                    Text(
                        text = "Place Order",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
}


@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "NO ITEMS ADDED")
    }
}