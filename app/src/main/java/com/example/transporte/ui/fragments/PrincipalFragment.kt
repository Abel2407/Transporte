package com.example.transporte.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.navigation.fragment.findNavController
import com.example.transporte.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class PrincipalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment


        val view: View = inflater.inflate(R.layout.fragment_principal, container, false)

        val btnBuscar: Button = view.findViewById(R.id.btn_buscarBoletos)

        btnBuscar.setOnClickListener{
            findNavController().navigate(R.id.action_navprincipal_to_resultadoBusquedaFragment)
        }


        return view
    }

}