package com.example.appmovil.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

data class PurchaseData(
    val total: String,
    val products: List<String>,
    val location: LatLng
)

@Composable
fun PurchaseCompleteScreenCompose(
    navController: NavController,
    purchase: PurchaseData
) {
    val scrollState = rememberScrollState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.builder()
            .target(purchase.location)
            .zoom(12f)
            .build()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        // 🧾 Total pagado
        Text(
            "Último total pagado: ${purchase.total}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🛍 Lista de productos comprados
        Text(
            "Productos comprados:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        purchase.products.forEach { product ->
            Text("• $product", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🗺 Mapa
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            GoogleMap(
                cameraPositionState = cameraPositionState
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔙 Botón volver al home
        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}
