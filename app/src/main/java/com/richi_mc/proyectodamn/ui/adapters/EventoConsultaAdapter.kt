package com.richi_mc.proyectodamn.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.richi_mc.proyectodamn.R
import com.richi_mc.proyectodamn.databinding.ItemEventoConsultaBinding
import com.richi_mc.proyectodamn.model.Evento
import com.richi_mc.proyectodamn.ui.formato
import com.richi_mc.proyectodamn.ui.formatoCorto
import com.richi_mc.proyectodamn.ui.mostrarEstatus

/** Tarjeta de resultado en Consultar, con botón para modificar el evento. */
class EventoConsultaAdapter(
    private val alEditar: (Evento) -> Unit,
) : ListAdapter<Evento, EventoConsultaAdapter.ViewHolder>(EventoDiff) {

    class ViewHolder(val binding: ItemEventoConsultaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemEventoConsultaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = getItem(position)
        with(holder.binding) {
            categoria.text = evento.categoria
            estatus.mostrarEstatus(evento.estatus)
            fecha.text = root.context.getString(
                R.string.evento_fecha_hora, evento.fecha.formatoCorto(), evento.hora.formato()
            )
            contacto.text = evento.contacto
            ubicacion.text = evento.ubicacion
            descripcion.text = evento.descripcion
            botonEditar.setOnClickListener { alEditar(evento) }
        }
    }
}
