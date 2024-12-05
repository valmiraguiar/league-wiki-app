package com.valmiraguiar.core.entities

interface DataConverter<DTO, MODEL> {
    fun convert(dtoData: DTO): MODEL
}