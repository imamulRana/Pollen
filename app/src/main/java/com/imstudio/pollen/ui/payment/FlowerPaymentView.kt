package com.imstudio.pollen.ui.payment

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.core.PollenModifier
import com.imstudio.pollen.navigation.Screens
import kotlin.system.exitProcess


@Composable
fun FlowerPaymentView(
    modifier: Modifier = Modifier,
    navController: NavController,
    pollenViewModel: PollenViewModel
) {
    val flowerPaymentState by pollenViewModel.flowerPaymentState.collectAsState()

    var dialogState by remember {
        mutableStateOf(false)
    }
    var nameValue by rememberSaveable {
        mutableStateOf("")
    }
    var addressValue by rememberSaveable {
        mutableStateOf("")
    }
    var phnNumValue by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PollenModifier.mediumPadding),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Order Details",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = modifier.height(PollenModifier.mediumPadding))
            Text(
                text = "Please Provide Details To Complete The Order.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = modifier.height(30.dp))

            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp),
                singleLine = true,
                value = nameValue,
                onValueChange = { nameValue = it },
                placeholder = { Text(text = "Your Name") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.large,
                keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(imeAction = ImeAction.Next) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            Spacer(modifier = modifier.height(PollenModifier.mediumPadding))
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp),
                singleLine = true,
                value = addressValue,
                onValueChange = { addressValue = it },
                placeholder = { Text(text = "Your Address") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.large,
                keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(imeAction = ImeAction.Next) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),

                )
            Spacer(modifier = modifier.height(PollenModifier.mediumPadding))
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp),
                singleLine = true,
                value = phnNumValue,
                onValueChange = { phnNumValue = it },
                placeholder = { Text(text = "Phone Number") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.large,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { defaultKeyboardAction(imeAction = ImeAction.Done) })
            )
        }
        Column(
            modifier = modifier
                .padding(bottom = 30.dp)
        ) {
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
                    if (nameValue == "") {
                        Toast.makeText(
                            context,
                            "Please Type Your Name!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        dialogState = !dialogState
                        pollenViewModel.sendOrderToAdmin(
                            userName = nameValue,
                            userAddress = addressValue,
                            userPhone = phnNumValue
                        )
                    }
//                navController.navigate(Screens.FlowerPaymentView.route)
                },
            ) {
                Text(
                    text = "Confirm Order",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


    if (dialogState) {
        AlertDialog(
            onDismissRequest = { dialogState = false },
            dismissButton = {
                Button(onClick = {
                    dialogState = false
                    pollenViewModel.resetCart()
                    navController.navigate(Screens.FlowerListView.route) {
                        popUpTo(Screens.FlowerListView.route) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Continue")
                }
            },
            title = {
                Text(
                    text = "Order Successfully Received.",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = "Would You Like To Exit The App Or Continue Shopping?"
                )
            },


            confirmButton = {
                Button(onClick = {
                    exitProcess(1)
                }) {
                    Text(text = "Exit")
                }
            },

            )

    }
}




