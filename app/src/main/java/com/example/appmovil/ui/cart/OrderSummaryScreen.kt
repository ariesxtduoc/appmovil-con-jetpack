package com.example.appmovil.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun OrderSummaryScreen(
    navController: NavController,
    total: String,
    productsEncoded: String
) {
    val decodedProducts = URLDecoder.decode(productsEncoded, StandardCharsets.UTF_8.toString())
    val productsList = if (decodedProducts.isNotEmpty()) {
        decodedProducts.split("|")
    } else emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Resumen de compra", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Lista de productos
        productsList.forEach { item ->
            Text(text = "- $item")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Total a pagar: $$total", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                val encodedProducts =
                    URLEncoder.encode(productsList.joinToString("|"), StandardCharsets.UTF_8.toString())

                navController.navigate("purchase_complete/$total/$encodedProducts")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar compra")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
