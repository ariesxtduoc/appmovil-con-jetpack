package com.example.appmovil.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Datos de ejemplo para el historial
data class OrderHistoryItem(
    val orderId: String,
    val products: List<String>,
    val total: String
)

@Composable
fun OrderHistoryScreen(
    userName: String,
    userAddress: String,
    orders: List<OrderHistoryItem> = listOf(
        OrderHistoryItem("0001", listOf("Producto A", "Producto B"), "$50.0"),
        OrderHistoryItem("0002", listOf("Producto C"), "$30.0"),
        OrderHistoryItem("0003", listOf("Producto D", "Producto E", "Producto F"), "$120.0")
    ),
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔙 Botón volver
        Button(onClick = onBack) {
            Text("◀ Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 👤 Información del usuario
        Text("Usuario: $userName", style = MaterialTheme.typography.titleMedium)
        Text("Dirección: $userAddress", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 🛒 Historial de pedidos
        Text("Historial de Compras", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(orders) { order ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Pedido ID: ${order.orderId}", style = MaterialTheme.typography.titleMedium)
                        Text("Total: ${order.total}", style = MaterialTheme.typography.bodyMedium)
                        Text("Productos:", style = MaterialTheme.typography.bodyMedium)
                        order.products.forEach { product ->
                            Text("• $product", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
