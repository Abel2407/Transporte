package com.example.transporte.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transporte.R
import com.example.transporte.ui.fragments.model.BoletoModel

class BoletosAdapter (private var lstBoletos: List<BoletoModel>)
    : RecyclerView.Adapter<BoletosAdapter.ViewHolder>(){
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
            val tvDniPasajero: TextView = itemView.findViewById(R.id.tvDniPasajero)
            val tvEstadoPasajero: TextView = itemView.findViewById(R.id.tvEstadoPasajero)
            val tvPrecioBoleto: TextView = itemView.findViewById(R.id.tvPrecioBoleto)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BoletosAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_boleto, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemBoletos = lstBoletos[position]
        holder.tvCantidad.text = itemBoletos.cantidad
        holder.tvDniPasajero.text = itemBoletos.dni_pasajero
        holder.tvEstadoPasajero.text = itemBoletos.estado
        holder.tvPrecioBoleto.text = itemBoletos.precio
    }

    override fun getItemCount(): Int {
        return lstBoletos.size
    }


}