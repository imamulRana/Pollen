package com.imstudio.pollen.ui.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderConfirmationView(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Thanks For Shopping With Us.",
            style = MaterialTheme.typography.titleLarge
        )
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
//                navController.navigate(Screens.FlowerPaymentView.route)
            },
        ) {
            Text(
                text = "Continue Shopping",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}