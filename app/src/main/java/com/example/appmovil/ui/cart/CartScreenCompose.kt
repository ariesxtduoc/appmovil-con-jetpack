package com.example.appmovil.ui.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CartScreenCompose(
    userId: String,
    onBack: () -> Unit,
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    val context = LocalContext.current
    val items by viewModel.items.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadCart(context, userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(onClick = onBack) {
            Text("◀ Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(items) { item ->

                CartItemRow(
                    item = item,
                    onIncrease = {
                        viewModel.updateQuantity(
                            context,
                            userId,
                            item.product.id,
                            item.quantity + 1
                        )
                    },
                    onDecrease = {
                        if (item.quantity > 1) {
                            viewModel.updateQuantity(
                                context,
                                userId,
                                item.product.id,
                                item.quantity - 1
                            )
                        }
                    },
                    onDelete = {
                        viewModel.removeItem(context, userId, item.product.id)
                        Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        val total = items.sumOf { it.product.price * it.quantity }

        Text(
            text = "Total: $$total",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (items.isEmpty()) {
                    Toast.makeText(context, "El carrito está vacío", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // 🔥 Codificar la info para enviarla por navegación
                val raw = items.joinToString("|") {
                    "${it.product.name};${it.quantity};${it.product.price}"
                }

                val encoded = URLEncoder.encode(raw, StandardCharsets.UTF_8.toString())

                navController.navigate("orderSummary/$total/$encoded")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pagar")
        }
    }
}
