package com.richi_mc.proyectodamn.ui

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.richi_mc.proyectodamn.model.Estatus
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val espanol: Locale = Locale.forLanguageTag("es-MX")
private val fechaLarga = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM", espanol)
private val fechaCompleta = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy", espanol)
private val fechaCorta = DateTimeFormatter.ofPattern("dd/MM/yyyy", espanol)
private val hora = DateTimeFormatter.ofPattern("hh:mm a", espanol)
private val fechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm", espanol)

/** "Lunes 27 de octubre" */
fun LocalDate.formatoLargo(): String = format(fechaLarga).replaceFirstChar { it.titlecase(espanol) }

/** "Lunes 27 de octubre de 2026" */
fun LocalDate.formatoCompleto(): String = format(fechaCompleta).replaceFirstChar { it.titlecase(espanol) }

/** "27/10/2026" */
fun LocalDate.formatoCorto(): String = format(fechaCorta)

/** "10:00 a.m." */
fun LocalTime.formato(): String = format(hora)

/** "25/10/2026, 18:40" */
fun LocalDateTime.formato(): String = format(fechaHora)

/** Pinta la etiqueta redondeada de estatus con su texto y colores. */
fun TextView.mostrarEstatus(estatus: Estatus) {
    setText(estatus.etiqueta)
    setTextColor(ContextCompat.getColor(context, estatus.colorTexto))
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, estatus.colorFondo))
}
