package com.example.transporte.ui.fragments

import android.companion.AssociationRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.transporte.R


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btnReporteBoletos: Button = view.findViewById(R.id.btn_repBoletos)
        val btnReporteRutas: Button = view.findViewById(R.id.btn_repRutas)

        btnReporteBoletos.setOnClickListener{
            findNavController().navigate(R.id.action_navdashboard_to_reporteBoletosFragment)
        }

        btnReporteRutas.setOnClickListener{
            findNavController().navigate(R.id.action_navdashboard_to_reporteRutasFragment)
        }





        return view
    }

}