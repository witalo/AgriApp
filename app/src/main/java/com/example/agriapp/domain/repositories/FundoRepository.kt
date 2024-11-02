package com.example.agriapp.domain.repositories

import com.example.agriapp.data.local.dao.FundoDao
import com.example.agriapp.data.local.entity.FundoEntity
import com.example.agriapp.domain.models.FundoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FundoRepository @Inject constructor(
    private val fundoDao: FundoDao
) {
    // Mapeo de FundoEntity a FundoDomainModel
    private fun FundoEntity.toDomainModel(): FundoDomainModel {
        return FundoDomainModel(
            id = this.id,
            codigo = this.codigo,
            nombre = this.nombre,
            activo = this.activo,
            zonaId = this.zonaId
        )
    }

    // Función para obtener todos los fundos
    fun getFundos(): Flow<List<FundoDomainModel>> {
        return fundoDao.getAllFundos().map { fundos ->
            fundos.map { it.toDomainModel() } // Mapea cada FundoEntity a FundoDomainModel
        }
    }
}
