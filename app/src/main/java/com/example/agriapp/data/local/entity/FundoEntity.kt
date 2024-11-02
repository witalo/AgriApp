package com.example.agriapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "fundo",
    foreignKeys = [
        ForeignKey(
            entity = ZonaEntity::class,
            parentColumns = ["id"],
            childColumns = ["zonaId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index(value = ["zonaId"])]
)
data class FundoEntity(
    @PrimaryKey val id: Int,
    val codigo: String,
    val nombre: String,
    val zonaId: Int,
    val activo: Boolean
)