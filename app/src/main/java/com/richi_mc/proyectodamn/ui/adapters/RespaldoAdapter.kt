package com.richi_mc.proyectodamn.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.ItemRespaldoBinding
import com.richi_mc.proyectodamn.model.Respaldo
import com.richi_mc.proyectodamn.ui.formato

/** Renglón de un respaldo disponible en Restaurar; el primero (el más reciente) aparece seleccionado. */
class RespaldoAdapter(
    private val respaldos: List<Respaldo>,
) : RecyclerView.Adapter<RespaldoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRespaldoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRespaldoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val respaldo = respaldos[position]
        with(holder.binding) {
            fecha.text = respaldo.fecha.formato()
            detalle.text = root.context.getString(
                R.string.respaldo_detalle, respaldo.totalEventos, respaldo.tamano
            )
            seleccion.isChecked = position == 0
        }
    }

    override fun getItemCount() = respaldos.size
}
