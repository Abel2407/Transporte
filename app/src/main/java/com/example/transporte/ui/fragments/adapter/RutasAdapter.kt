package com.example.transporte.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transporte.R
import com.example.transporte.ui.fragments.model.RutaModel

class RutasAdapter (private var lstRutas: List<RutaModel>)
    : RecyclerView.Adapter<RutasAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvOrigen: TextView = itemView.findViewById(R.id.tvOrigen)
        val tvDestino: TextView = itemView.findViewById(R.id.tvDestino)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_ruta, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRutas = lstRutas[position]
        holder.tvOrigen.text = itemRutas.origen
        holder.tvDestino.text = itemRutas.destino
    }

    override fun getItemCount(): Int {
        return lstRutas.size
    }
}