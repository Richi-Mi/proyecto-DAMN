package com.richi_mc.proyectodamn.ui.calendario

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.BottomSheetDetalleEventoBinding
import com.richi_mc.proyectodamn.databinding.FragmentCalendarioBinding
import com.richi_mc.proyectodamn.model.DatosEjemplo
import com.richi_mc.proyectodamn.model.Evento
import com.richi_mc.proyectodamn.ui.adapters.EventoAdapter
import com.richi_mc.proyectodamn.ui.formato
import com.richi_mc.proyectodamn.ui.formatoCompleto
import com.richi_mc.proyectodamn.ui.formatoLargo
import com.richi_mc.proyectodamn.ui.mostrarEstatus
import java.time.LocalDate

class CalendarioFragment : Fragment(R.layout.fragment_calendario) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalendarioBinding.bind(view)
        val adapter = EventoAdapter(alTocar = ::mostrarDetalle)
        binding.listaEventos.adapter = adapter

        fun mostrarDia(fecha: LocalDate) {
            val eventos = DatosEjemplo.eventosDelDia(fecha)
            binding.tituloDia.text =
                getString(R.string.calendario_eventos_del_dia, fecha.formatoLargo().lowercase())
            binding.vacio.isVisible = eventos.isEmpty()
            adapter.submitList(eventos)
        }

        binding.calendario.setOnDateChangeListener { _, anio, mes, dia ->
            // CalendarView cuenta los meses desde 0.
            mostrarDia(LocalDate.of(anio, mes + 1, dia))
        }
        mostrarDia(LocalDate.now())
    }

    private fun mostrarDetalle(evento: Evento) {
        val dialogo = BottomSheetDialog(requireContext())
        val binding = BottomSheetDetalleEventoBinding.inflate(layoutInflater)
        binding.categoria.text = evento.categoria
        binding.estatus.mostrarEstatus(evento.estatus)
        binding.descripcion.text = evento.descripcion
        binding.fecha.text = evento.fecha.formatoCompleto()
        binding.hora.text = evento.hora.formato()
        binding.contacto.text = evento.contacto
        binding.ubicacion.text = evento.ubicacion
        binding.recordatorio.text =
            getString(R.string.evento_recordatorio, getString(evento.recordatorio.etiqueta))
        binding.botonCerrar.setOnClickListener { dialogo.dismiss() }
        dialogo.setContentView(binding.root)
        dialogo.show()
    }
}
