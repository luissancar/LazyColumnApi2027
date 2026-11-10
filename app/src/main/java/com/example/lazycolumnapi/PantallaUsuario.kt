package com.example.lazycolumnapi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazycolumnapi.http.UsuariosViewModel
import com.example.lazycolumnapi.model.Usuario

@Composable
fun PantallaUsuario(vm: UsuariosViewModel = viewModel()) {
    val usuarios by vm.usuarios.collectAsStateWithLifecycle()
    val cargando by vm.cargando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Usuarios", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { vm.cargarUsuarios() }) {
            Text(text = "Actualizar Usuarios")
        }
        Spacer(Modifier.height(12.dp))
        when {
            cargando -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = error ?: "",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            else ->{
                LazyColumn(verticalArrangement = Arrangement.SpaceAround) {
                    items(items=usuarios){
                        usuario->UsuarioItem(usuario)
                    }
                }
            }
        }

    }
}

@Composable
fun UsuarioItem(usuario: Usuario) {
    Card(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(13.dp)) {
            Text(text = usuario.name, style = MaterialTheme.typography.titleMedium)
            Text(text = usuario.email, style = MaterialTheme.typography.bodyMedium)
            Text(text = usuario.address.city, style = MaterialTheme.typography.bodyMedium)
        }
    }
    Spacer(Modifier.height(12.dp))
}























