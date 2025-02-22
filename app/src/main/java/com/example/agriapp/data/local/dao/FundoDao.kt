package com.example.agriapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.agriapp.data.local.entity.FundoEntity
import com.example.agriapp.data.local.entity.UsuarioEntity
import com.example.agriapp.data.local.entity.ZonaEntity
import com.example.agriapp.data.remote.models.FundoDataModel
import kotlinx.coroutines.flow.Flow
@Dao
interface FundoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFundo(fundo: FundoEntity)

    @Update
    suspend fun updateFundo(fundo: FundoEntity)

    @Query("SELECT * FROM fundo WHERE id = :fundoId")
    suspend fun getFundoById(fundoId: Int): FundoEntity?

    @Query("SELECT * FROM fundo")
    fun getAllFundos(): Flow<List<FundoEntity>> // Retorna un Flow para observar los cambios en la lista de fundos

}