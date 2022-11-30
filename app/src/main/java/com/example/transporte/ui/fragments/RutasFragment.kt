package com.example.transporte.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.transporte.R


class RutasFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_rutas, container, false)

        val btnRegistrarNuevaRuta: Button = view.findViewById(R.id.btn_registroRuta)
        val btnRegistrarFlota: Button = view.findViewById(R.id.btn_registroFlota)

        btnRegistrarNuevaRuta.setOnClickListener{
            findNavController().navigate(R.id.action_navrutas_to_registroRutaFragment)
        }

        btnRegistrarFlota.setOnClickListener{
            findNavController().navigate(R.id.action_navrutas_to_registroFlotaFragment)
        }

        return view
    }


}