package com.richi_mc.proyectodamn.model

import java.time.LocalDateTime

data class Respaldo(
    val fecha: LocalDateTime,
    val totalEventos: Int,
    val tamano: String,
)
