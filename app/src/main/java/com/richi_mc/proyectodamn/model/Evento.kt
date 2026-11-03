package com.richi_mc.proyectodamn.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.richi_mc.proyectodamn.R
import java.time.LocalDate
import java.time.LocalTime

data class Evento(
    val id: Int,
    val categoria: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val descripcion: String,
    val estatus: Estatus,
    val ubicacion: String,
    val contacto: String,
    val recordatorio: Recordatorio,
)

enum class Estatus(
    @StringRes val etiqueta: Int,
    @ColorRes val colorFondo: Int,
    @ColorRes val colorTexto: Int,
) {
    PENDIENTE(R.string.estatus_pendiente, R.color.estatus_pendiente_fondo, R.color.estatus_pendiente_texto),
    REALIZADO(R.string.estatus_realizado, R.color.estatus_realizado_fondo, R.color.estatus_realizado_texto),
    APLAZADO(R.string.estatus_aplazado, R.color.estatus_aplazado_fondo, R.color.estatus_aplazado_texto),
}

enum class Recordatorio(@StringRes val etiqueta: Int) {
    AL_MOMENTO(R.string.recordatorio_al_momento),
    DIEZ_MINUTOS(R.string.recordatorio_diez_minutos),
    UN_DIA(R.string.recordatorio_un_dia),
    SIN_RECORDATORIO(R.string.recordatorio_sin),
}
