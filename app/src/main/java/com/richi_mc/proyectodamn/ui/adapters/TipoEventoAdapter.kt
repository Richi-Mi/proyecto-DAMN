package com.richi_mc.proyectodamn.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.richi_mc.proyectodamn.databinding.ItemTipoEventoBinding

/** Renglón de un tipo de evento en Configuración. */
class TipoEventoAdapter(
    private val tipos: List<String>,
) : RecyclerView.Adapter<TipoEventoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTipoEventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTipoEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nombre.text = tipos[position]
    }

    override fun getItemCount() = tipos.size
}
