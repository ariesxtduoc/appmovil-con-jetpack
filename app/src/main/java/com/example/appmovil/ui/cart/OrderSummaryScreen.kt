package com.example.appmovil.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OrderSummaryScreen(
    navController: NavController,
    onConfirm: () -> Unit   // ⭐ SE AGREGA ESTE PARÁMETRO
) {

    val exampleItems = listOf("Producto 1", "Producto 2", "Producto 3")
    val total = "$15.990"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text("Resumen de la Compra")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(exampleItems) { item ->
                Text(item, modifier = Modifier.padding(6.dp))
            }
        }

        Text(
            text = "Total: $total",
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver")
        }

        Button(
            onClick = {
                // ⬇️ Ahora usamos el callback enviado desde MainActivity
                onConfirm()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text("Finalizar compra")
        }
    }
}
