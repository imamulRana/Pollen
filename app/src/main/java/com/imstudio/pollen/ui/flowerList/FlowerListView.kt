package com.imstudio.pollen.ui.flowerList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.navigation.Screens
import com.imstudio.pollen.ui.theme.PollenTheme

@Composable
fun FlowerListView(
    modifier: Modifier = Modifier,
    pollenViewModel: PollenViewModel,
    navController: NavController
) {
    val item = listOf("Popular", "Recommended", "Indoor", "Outdoor")
    val flowerList by pollenViewModel.flowersList.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Find The Best\nFlowers",
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(item.size) {
                Text(
                    text = item[it],
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxWidth(),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(PollenModifier.mediumPadding),
            verticalItemSpacing = PollenModifier.mediumPadding
        ) {
            items(flowerList.size) {
                FlowerListItem(
                    flower = flowerList[it],
                    onClick = {
                        pollenViewModel.updateFlower(it)
                        navController.navigate(Screens.FlowerView.route)
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    PollenTheme {
//        FlowerList(flower = Flower())
    }
}