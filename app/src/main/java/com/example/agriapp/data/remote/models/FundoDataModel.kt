package com.example.agriapp.data.remote.models

import com.example.agriapp.data.local.entity.FundoEntity

data class FundoDataModel(
    val id: Int,
    val codigo: String,
    val nombre: String,
    val activo: Boolean,
    val zonaId: Int,
    val userFundoSet: List<UsuarioFundoDataModel>?
)
{
    fun toEntity(): FundoEntity {
        return FundoEntity(
            id = this.id,
            codigo = this.codigo,
            nombre = this.nombre,
            activo = this.activo,
            zonaId = this.zonaId
        )
    }
}