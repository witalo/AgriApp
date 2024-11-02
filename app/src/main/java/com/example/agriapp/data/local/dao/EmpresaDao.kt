package com.example.agriapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.agriapp.data.local.entity.EmpresaEntity
import com.example.agriapp.data.remote.models.EmpresaDataModel

@Dao
interface EmpresaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmpresa(empresa: EmpresaEntity)

    @Update
    suspend fun updateEmpresa(empresa: EmpresaEntity)

    @Query("SELECT * FROM empresa WHERE id = :empresaId")
    suspend fun getEmpresaById(empresaId: Int): EmpresaEntity?
}