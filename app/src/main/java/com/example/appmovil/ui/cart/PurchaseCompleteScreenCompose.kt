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

data class UserData(
    val name: String,
    val email: String,
    val address: String
)

data class PurchaseData(
    val total: String,
    val products: List<String>,
    val location: LatLng
)

@Composable
fun PurchaseCompleteScreenCompose(
    navController: NavController,
    user: UserData = UserData("Juan Perez", "juan@ejemplo.com", "Av. Siempre Viva 123"),
    purchase: PurchaseData = PurchaseData(
        total = "$15.990",
        products = listOf("Producto 1", "Producto 2", "Producto 3"),
        location = LatLng(-33.4489, -70.6693) // Santiago de ejemplo
    )
) {
    val scrollState = rememberScrollState()

    // ✅ Inicializamos correctamente CameraPositionState
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
        Text("Usuario: ${user.name}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Correo: ${user.email}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Dirección: ${user.address}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Último total pagado: ${purchase.total}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Productos comprados: ${purchase.products.joinToString(", ")}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            GoogleMap(cameraPositionState = cameraPositionState)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}
