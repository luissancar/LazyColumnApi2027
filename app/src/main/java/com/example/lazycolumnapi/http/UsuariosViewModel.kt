package com.example.lazycolumnapi.http

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumnapi.model.Usuario
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsuariosViewModel: ViewModel(){
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios = _usuarios.asStateFlow()
    private val _cargando= MutableStateFlow(false)
    val cargando =_cargando.asStateFlow()
    private val _error= MutableStateFlow<String?>(null)
    val error =_error.asStateFlow()

    init {
        cargarUsuarios()
    }

    fun cargarUsuarios(){
        viewModelScope.launch {
            _cargando.value=true
            _error.value=null
            try{
                _usuarios.value=RetrofitClient.api.obtenerUsuarios()
            }
            catch(e: CancellationException){
                throw e
            }
            catch (e: Exception){
                _error.value="No se ha podido cargar los usuarios"
            }
            finally
            {
                _cargando.value=false
            }
        }
    }
}













