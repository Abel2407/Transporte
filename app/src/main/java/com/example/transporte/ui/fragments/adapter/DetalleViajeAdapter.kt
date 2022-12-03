package com.example.transporte.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.transporte.R
import androidx.recyclerview.widget.RecyclerView
import com.example.transporte.ui.fragments.model.DetalleViajeModel

class DetalleViajeAdapter (private var lstViajes: List<DetalleViajeModel>)
    : RecyclerView.Adapter<DetalleViajeAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
        val tvViaje: TextView = itemView.findViewById(R.id.tvViaje)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
        val tvTarjeta: TextView = itemView.findViewById(R.id.tvTarjeta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_viaje, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCourse = lstViajes[position]
        holder.tvEstado.text = itemCourse.estado
        holder.tvViaje.text = itemCourse.ruta
        holder.tvPrecio.text = itemCourse.precio
        holder.tvTarjeta.text = itemCourse.tarjeta
    }

    override fun getItemCount(): Int {
        return lstViajes.size
    }
}