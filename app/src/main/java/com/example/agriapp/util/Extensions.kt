package com.example.agriapp.util

import com.example.agriapp.data.local.entity.EmpresaEntity
import com.example.agriapp.data.local.entity.FundoEntity
import com.example.agriapp.data.local.entity.ZonaEntity
import com.example.agriapp.data.local.entity.UsuarioEntity
import com.example.agriapp.domain.models.EmpresaDomainModel
import com.example.agriapp.domain.models.FundoDomainModel
import com.example.agriapp.domain.models.ZonaDomainModel
import com.example.agriapp.domain.models.UsuarioDomainModel

fun EmpresaEntity.toDomainModel() = EmpresaDomainModel(
    id = this.id,
    ruc = this.ruc,
    razonSocial = this.razonSocial,
    direccion = this.direccion,
    telefono = this.telefono,
    correo = this.correo
)

fun ZonaEntity.toDomainModel() = ZonaDomainModel(
    id = this.id,
    codigo = this.codigo,
    nombre = this.nombre,
    activo = this.activo,
    empresaId = this.empresaId
)

fun FundoEntity.toDomainModel() = FundoDomainModel(
    id = this.id,
    codigo = this.codigo,
    nombre = this.nombre,
    activo = this.activo,
    zonaId = this.zonaId
)

fun UsuarioEntity.toDomainModel() = UsuarioDomainModel(
    id = this.id,
    document = this.document,
    firstName = this.firstName,
    lastName = this.lastName,
    phone = this.phone,
    email = this.email,
    isActive = this.isActive
)
