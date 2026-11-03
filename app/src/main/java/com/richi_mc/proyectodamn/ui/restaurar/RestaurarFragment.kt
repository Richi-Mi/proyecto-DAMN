package com.richi_mc.proyectodamn.ui.restaurar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.FragmentRestaurarBinding
import com.richi_mc.proyectodamn.model.DatosEjemplo
import com.richi_mc.proyectodamn.ui.adapters.RespaldoAdapter

class RestaurarFragment : Fragment(R.layout.fragment_restaurar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRestaurarBinding.bind(view)
        binding.listaRespaldos.adapter = RespaldoAdapter(DatosEjemplo.respaldos())
    }
}
