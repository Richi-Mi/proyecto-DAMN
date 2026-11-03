package com.richi_mc.proyectodamn.ui.inicio

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.FragmentInicioBinding
import com.richi_mc.proyectodamn.model.DatosEjemplo
import com.richi_mc.proyectodamn.ui.adapters.EventoAdapter
import com.richi_mc.proyectodamn.ui.formatoLargo
import java.time.LocalDate

class InicioFragment : Fragment(R.layout.fragment_inicio) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInicioBinding.bind(view)

        val hoy = LocalDate.now()
        binding.rango.text = getString(
            R.string.inicio_rango,
            hoy.formatoLargo().lowercase(),
            hoy.plusDays(4).formatoLargo().lowercase(),
        )

        val eventos = DatosEjemplo.eventosProximos()
        binding.listaEventos.adapter = EventoAdapter().apply { submitList(eventos) }
        binding.vacio.isVisible = eventos.isEmpty()
    }
}
