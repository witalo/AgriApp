package com.example.agriapp.data.remote.models

import com.example.agriapp.data.local.entity.UsuarioEntity

data class UsuarioDataModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val document: String,
    val email: String,
    val phone: String,
    val isActive: Boolean
){
    fun toEntity(): UsuarioEntity {
        return UsuarioEntity(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            document = this.document,
            email = this.email,
            phone = this.phone,
            isActive = this.isActive
        )
    }
}