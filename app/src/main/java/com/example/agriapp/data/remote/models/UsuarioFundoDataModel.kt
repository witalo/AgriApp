package com.example.agriapp.data.remote.models

import com.example.agriapp.data.local.entity.UsuarioFundoCrossRef

data class UsuarioFundoDataModel(
    val userId: Int,
    val fundoId: Int
){
    fun toEntity(): UsuarioFundoCrossRef {
        return UsuarioFundoCrossRef(
            usuarioId = this.userId,
            fundoId = this.fundoId
        )
    }
}