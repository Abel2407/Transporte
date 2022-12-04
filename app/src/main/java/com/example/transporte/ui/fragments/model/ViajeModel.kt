package com.example.transporte.ui.fragments.model

data class ViajeModel(
    val estado: String = "",
    val tarjeta: String = "",
    val precio: Int = 0,
    val dni_pasajero: String = "",
    var ruta: Map<String, *>,
    val cantidad: String = "",



)