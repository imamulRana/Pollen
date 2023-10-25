package com.imstudio.pollen.ui.flowerList

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.navigation.Screens
import com.imstudio.pollen.ui.cart.EmptyScreen
import com.imstudio.pollen.ui.theme.PollenTheme

@Composable
fun FlowerListView(
    modifier: Modifier = Modifier,
    pollenViewModel: PollenViewModel,
    navController: NavController,
    context: Context
) {
    val item = listOf("All Flowers", "Recommended", "Indoor", "Outdoor")
    val flowerList by pollenViewModel.flowersList.collectAsState()
    var currentFlowerIndex by remember {
        pollenViewModel.currentFlowerIndex
    }
    var currentItem by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = modifier
                .padding(horizontal = PollenModifier.largePadding),
            text = "Find The Best\nFlowers",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            contentPadding = PaddingValues(
                horizontal = PollenModifier.largePadding,
                vertical = PollenModifier.largePadding
            )
        ) {
            items(item.size) {
                Text(
                    modifier = modifier.clickable { currentItem = it },
                    text = item[it],
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (currentItem == it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        if (currentItem == 0) {
            LazyVerticalStaggeredGrid(
                modifier = modifier
                    .fillMaxWidth(),
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(PollenModifier.mediumPadding),
                verticalItemSpacing = PollenModifier.mediumPadding,
                contentPadding = PaddingValues(
                    horizontal = PollenModifier.mediumPadding,
                    vertical = PollenModifier.largePadding
                )
            ) {
                items(flowerList.size) {
                    FlowerListItem(
                        flower = flowerList[it],
                        onClick = {
                            currentFlowerIndex = it
                            pollenViewModel.getFlowerFromList(
                                flowerList[currentFlowerIndex]
                            )
                            navController.navigate(Screens.FlowerView.route)
                        },
                        context = context
                    )
                }
            }
        } else EmptyScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    PollenTheme {
//        FlowerList(flower = Flower())
    }
}