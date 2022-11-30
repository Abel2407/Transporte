package com.example.transporte.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.transporte.R


class ConfirmRegistroFlotaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_confirm_registro_flota, container, false)

        val tvPlaca: TextView = view.findViewById(R.id.tvConfirmPlaca)
        val args = this.arguments
        val inputData = args?.get("data")
        tvPlaca.text = inputData.toString()

        val btnCheck: ImageView = view.findViewById(R.id.check)

        btnCheck.setOnClickListener{

        }

        return view
    }

}