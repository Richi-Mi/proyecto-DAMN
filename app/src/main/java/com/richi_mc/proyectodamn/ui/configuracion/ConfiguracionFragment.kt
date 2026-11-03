package com.richi_mc.proyectodamn.ui.configuracion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.FragmentConfiguracionBinding
import com.richi_mc.proyectodamn.ui.adapters.TipoEventoAdapter

class ConfiguracionFragment : Fragment(R.layout.fragment_configuracion) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConfiguracionBinding.bind(view)
        binding.listaTipos.adapter =
            TipoEventoAdapter(resources.getStringArray(R.array.categorias_evento).toList())
    }
}
