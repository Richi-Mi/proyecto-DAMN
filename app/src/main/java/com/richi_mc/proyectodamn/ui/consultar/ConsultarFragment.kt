package com.richi_mc.proyectodamn.ui.consultar

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.DialogModificarEventoBinding
import com.richi_mc.proyectodamn.databinding.FragmentConsultarBinding
import com.richi_mc.proyectodamn.model.DatosEjemplo
import com.richi_mc.proyectodamn.model.Estatus
import com.richi_mc.proyectodamn.model.Evento
import com.richi_mc.proyectodamn.ui.adapters.EventoConsultaAdapter
import com.richi_mc.proyectodamn.ui.formatoCorto
import java.time.LocalDate

class ConsultarFragment : Fragment(R.layout.fragment_consultar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConsultarBinding.bind(view)

        binding.campoCategoria.setText(getString(R.string.categoria_todas), false)
        binding.campoDia.setText(LocalDate.now().formatoCorto())

        // Muestra solo los campos del tipo de consulta elegido.
        binding.grupoTipoConsulta.addOnButtonCheckedListener { _, id, marcado ->
            if (!marcado) return@addOnButtonCheckedListener
            binding.bloqueDia.isVisible = id == R.id.consulta_dia
            binding.bloqueRango.isVisible = id == R.id.consulta_rango
            binding.bloqueMes.isVisible = id == R.id.consulta_mes
            binding.bloqueAnio.isVisible = id == R.id.consulta_anio
        }

        val eventos = DatosEjemplo.eventos()
        binding.listaResultados.adapter =
            EventoConsultaAdapter(::mostrarDialogoModificar).apply { submitList(eventos) }
        binding.totalResultados.text =
            resources.getQuantityString(R.plurals.resultados, eventos.size, eventos.size)
    }

    private fun mostrarDialogoModificar(evento: Evento) {
        val binding = DialogModificarEventoBinding.inflate(layoutInflater)
        binding.subtitulo.text =
            getString(R.string.modificar_subtitulo, evento.categoria, evento.fecha.formatoCorto())
        binding.grupoEstatus.check(
            when (evento.estatus) {
                Estatus.PENDIENTE -> R.id.estatus_pendiente
                Estatus.APLAZADO -> R.id.estatus_aplazado
                Estatus.REALIZADO -> R.id.estatus_realizado
            }
        )
        binding.campoContacto.setText(evento.contacto)
        binding.campoUbicacion.setText(evento.ubicacion)

        val dialogo = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.modificar_titulo)
            .setView(binding.root)
            .setNegativeButton(R.string.cancelar, null)
            .setPositiveButton(R.string.actualizar, null)
            .show()
        binding.botonEliminar.setOnClickListener { dialogo.dismiss() }
    }
}
