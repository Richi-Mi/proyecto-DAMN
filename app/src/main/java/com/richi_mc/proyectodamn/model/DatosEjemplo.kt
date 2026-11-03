package com.richi_mc.proyectodamn.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Datos fijos para mostrar las pantallas mientras no exista la base de datos.
 * Las fechas son relativas a hoy para que Inicio siempre tenga eventos de los próximos 5 días.
 */
object DatosEjemplo {

    fun eventos(): List<Evento> {
        val hoy = LocalDate.now()
        return listOf(
            Evento(
                id = 1,
                categoria = "Práctica",
                fecha = hoy.minusDays(3),
                hora = LocalTime.of(9, 0),
                descripcion = "Práctica 4: listas y arreglos en Kotlin",
                estatus = Estatus.REALIZADO,
                ubicacion = "Laboratorio de cómputo 3, ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "Prof. Ramírez",
                recordatorio = Recordatorio.AL_MOMENTO,
            ),
            Evento(
                id = 2,
                categoria = "Reunión",
                fecha = hoy,
                hora = LocalTime.of(10, 0),
                descripcion = "Revisión del primer avance de la app",
                estatus = Estatus.PENDIENTE,
                ubicacion = "Edificio 2, ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "Alejandro",
                recordatorio = Recordatorio.DIEZ_MINUTOS,
            ),
            Evento(
                id = 3,
                categoria = "Práctica",
                fecha = hoy,
                hora = LocalTime.of(13, 30),
                descripcion = "Práctica 5: RecyclerView y adaptadores",
                estatus = Estatus.REALIZADO,
                ubicacion = "Laboratorio de cómputo 4, ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "Prof. Ramírez",
                recordatorio = Recordatorio.AL_MOMENTO,
            ),
            Evento(
                id = 4,
                categoria = "Entrega de proyecto",
                fecha = hoy.plusDays(1),
                hora = LocalTime.of(17, 0),
                descripcion = "Entrega del diseño de pantallas en Teams",
                estatus = Estatus.PENDIENTE,
                ubicacion = "Biblioteca ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "María Fernanda",
                recordatorio = Recordatorio.UN_DIA,
            ),
            Evento(
                id = 5,
                categoria = "Examen",
                fecha = hoy.plusDays(2),
                hora = LocalTime.of(8, 30),
                descripcion = "Segundo parcial de Compiladores",
                estatus = Estatus.APLAZADO,
                ubicacion = "Salón 2104, ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "Prof. Ramírez",
                recordatorio = Recordatorio.UN_DIA,
            ),
            Evento(
                id = 6,
                categoria = "Otro",
                fecha = hoy.plusDays(3),
                hora = LocalTime.of(19, 0),
                descripcion = "Cumpleaños de Sofía",
                estatus = Estatus.PENDIENTE,
                ubicacion = "Plaza Lindavista – Av. Montevideo 363, Lindavista, CDMX",
                contacto = "Sofía",
                recordatorio = Recordatorio.UN_DIA,
            ),
            Evento(
                id = 7,
                categoria = "Reunión",
                fecha = hoy.plusDays(4),
                hora = LocalTime.of(16, 0),
                descripcion = "Junta de equipo para integrar la base de datos",
                estatus = Estatus.PENDIENTE,
                ubicacion = "Cafetería ESCOM – Av. Juan de Dios Bátiz s/n, Lindavista, CDMX",
                contacto = "Alejandro",
                recordatorio = Recordatorio.DIEZ_MINUTOS,
            ),
        )
    }

    /** Eventos de hoy y de los próximos 4 días. */
    fun eventosProximos(): List<Evento> {
        val hoy = LocalDate.now()
        val limite = hoy.plusDays(4)
        return eventos().filter { !it.fecha.isBefore(hoy) && !it.fecha.isAfter(limite) }
    }

    fun eventosDelDia(fecha: LocalDate): List<Evento> = eventos().filter { it.fecha == fecha }

    fun respaldos(): List<Respaldo> {
        val ahora = LocalDateTime.now().withSecond(0).withNano(0)
        return listOf(
            Respaldo(ahora.minusDays(2).withHour(18).withMinute(40), 7, "4 KB"),
            Respaldo(ahora.minusDays(9).withHour(21).withMinute(15), 5, "3 KB"),
            Respaldo(ahora.minusDays(23).withHour(8).withMinute(5), 2, "1 KB"),
        )
    }
}
