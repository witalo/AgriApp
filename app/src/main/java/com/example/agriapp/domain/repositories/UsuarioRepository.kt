package com.example.agriapp.domain.repositories

import com.example.agriapp.data.local.dao.UsuarioDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    suspend fun validateUser(dni: String, fundoId: Int): Boolean {
        return usuarioDao.isUserAssociatedWithFundo(dni, fundoId)
    }
}