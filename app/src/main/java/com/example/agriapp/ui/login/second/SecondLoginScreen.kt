package com.example.agriapp.ui.login.second

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import com.example.agriapp.domain.models.FundoDomainModel
@Composable
fun SecondLoginScreen(
    viewModel: SecondLoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit  // Cambiado para recibir solo la función de navegación
) {
    val fundus by viewModel.fundos.collectAsState() // Lista de fundos
    val validationState by viewModel.validationState.collectAsState() // Estado de validación
    val syncState by viewModel.syncState.collectAsState() // Estado de sincronización

    var dni by remember { mutableStateOf("") }
    var selectedFundo by remember { mutableStateOf<FundoDomainModel?>(null) }

    // Observa companyId directamente desde el ViewModel
    val empresaId by viewModel.companyId.collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Seleccione un Fundo y ingrese su DNI",
            style = MaterialTheme.typography.headlineSmall // En lugar de h6
        )

        // Selector de Fundo
        Text("Seleccione el Fundo:")
        LazyColumn(
            modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(fundus) { fundo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = selectedFundo == fundo,
                        onClick = { selectedFundo = fundo }
                    )
                    Text(fundo.nombre)
                }
            }
        }

        // Campo para el DNI
        TextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("Ingrese su DNI") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Botón Ingresar


        Button(
            onClick = {
                viewModel.validateUser(dni, selectedFundo?.id)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Sincronizar
        Button(
            onClick = {
                empresaId?.let { id: Int -> // Asegurarse de que id no es nulo antes de llamar a syncData
                    viewModel.syncData(id)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sincronizar")
        }

        // Estado de sincronización
        when (syncState) {
            is SyncState.Loading -> {
                CircularProgressIndicator()
                Text("Sincronizando datos...")
            }
            is SyncState.Error -> {
                Text("Error al sincronizar datos", color = MaterialTheme.colorScheme.error)
            }
            is SyncState.Success -> {
                Text("Datos sincronizados exitosamente")
                viewModel.loadFundos() // Cargar los fundos después de la sincronización
            }
            else -> {}
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Estado de validación
        when (validationState) {
            is ValidationState.Loading -> {
                CircularProgressIndicator()
                Text("Validando usuario...")
            }
            is ValidationState.Valid -> {
                // Navegar a HomeScreen cuando el usuario es válido
                LaunchedEffect(Unit) {
                    onNavigateToHome() // Llama a la función que navega a HomeScreen
                }
            }
            is ValidationState.Invalid -> {
                Text("Usuario no válido. Verifique el DNI y el fundo seleccionado.", color = MaterialTheme.colorScheme.error)
            }
            else -> {}
        }
    }
}
