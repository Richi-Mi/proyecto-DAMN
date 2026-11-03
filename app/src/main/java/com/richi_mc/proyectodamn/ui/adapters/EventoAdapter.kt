package com.richi_mc.proyectodamn.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.ItemEventoBinding
import com.richi_mc.proyectodamn.model.Evento
import com.richi_mc.proyectodamn.ui.formato
import com.richi_mc.proyectodamn.ui.formatoLargo
import com.richi_mc.proyectodamn.ui.mostrarEstatus

/** Tarjeta de evento de Inicio y Calendario. */
class EventoAdapter(
    private val alTocar: ((Evento) -> Unit)? = null,
) : ListAdapter<Evento, EventoAdapter.ViewHolder>(EventoDiff) {

    class ViewHolder(val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = getItem(position)
        with(holder.binding) {
            val contexto = root.context
            categoria.text = evento.categoria
            estatus.mostrarEstatus(evento.estatus)
            fecha.text = contexto.getString(
                R.string.evento_fecha_hora, evento.fecha.formatoLargo(), evento.hora.formato()
            )
            contacto.text = evento.contacto
            ubicacion.text = evento.ubicacion
            descripcion.text = contexto.getString(R.string.evento_descripcion, evento.descripcion)
            alTocar?.let { accion -> root.setOnClickListener { accion(evento) } }
        }
    }
}

object EventoDiff : DiffUtil.ItemCallback<Evento>() {
    override fun areItemsTheSame(oldItem: Evento, newItem: Evento) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Evento, newItem: Evento) = oldItem == newItem
}
