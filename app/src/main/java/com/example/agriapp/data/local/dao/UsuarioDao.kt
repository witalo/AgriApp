package com.example.agriapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.agriapp.data.local.entity.EmpresaEntity
import com.example.agriapp.data.local.entity.UsuarioEntity
import com.example.agriapp.data.local.entity.UsuarioFundoCrossRef
import com.example.agriapp.data.remote.models.UsuarioDataModel


@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(usuario: UsuarioEntity)

    @Update
    suspend fun updateUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario WHERE id = :id")
    suspend fun getUsuarioById(id: Int): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsuarioFundoCrossRef(usuarioFundoCrossRef: UsuarioFundoCrossRef)

    @Query("SELECT COUNT(*) FROM UsuarioFundoCrossRef WHERE usuarioId = :userId AND fundoId = :fundoId")
    suspend fun checkUsuarioFundoExists(userId: Int, fundoId: Int): Int



    @Query("SELECT * FROM usuario WHERE document = :document")
    suspend fun getUsuarioByDocument(document: String): UsuarioEntity?

    // Verificar si un usuario está asociado con un fundo específico
    @Query("""
        SELECT EXISTS (
            SELECT 1 FROM UsuarioFundoCrossRef
            WHERE usuarioId = (SELECT usuarioId FROM usuario WHERE document = :dni LIMIT 1)
            AND fundoId = :fundoId
        )
    """)
    suspend fun isUserAssociatedWithFundo(dni: String, fundoId: Int): Boolean


//    @Query("SELECT * FROM usuario WHERE document = :dni AND fundoId = :fundoId LIMIT 1")
//    suspend fun getUserByDniAndFundoId(dni: String, fundoId: String): UsuarioEntity?

//    @Update
//    suspend fun updateUsuario(usuario: UsuarioEntity)
//
//    @Delete
//    suspend fun deleteUsuario(usuario: UsuarioEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUsuario(usuario: UsuarioEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFundo(fundo: FundoEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUserFundoCrossRef(crossRef: UserFundoCrossRef)
}