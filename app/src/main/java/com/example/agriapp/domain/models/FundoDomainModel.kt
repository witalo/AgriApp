package com.example.agriapp.domain.models

data class FundoDomainModel(
    val id: Int,
    val codigo: String,
    val nombre: String,
    val activo: Boolean,
    val zonaId: Int,
//    val userFundoSet: List<UserFundo>
)