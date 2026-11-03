package com.richi_mc.proyectodamn.ui.anadir

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.FragmentAnadirEventoBinding

class AnadirEventoFragment : Fragment(R.layout.fragment_anadir_evento) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAnadirEventoBinding.bind(view)

        // false: sin filtrar, para que el menú siga mostrando los tres estatus.
        binding.campoEstatus.setText(getString(R.string.estatus_pendiente), false)
    }
}
