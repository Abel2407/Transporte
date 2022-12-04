package com.example.transporte.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.transporte.R
import com.example.transporte.ui.fragments.model.VehiculoModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class RegistroFlotaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_registro_flota, container, false)

        val view2: View = inflater.inflate(R.layout.fragment_confirm_registro_flota,container, false)

        val btnRegistrar: Button = view.findViewById(R.id.btnRegistrar)

        val txtPlaca: TextView = view.findViewById(R.id.etRegPlaca)
        val txtAnio: TextView = view.findViewById(R.id.etRegAnio)
        val txtMarca: TextView = view.findViewById(R.id.etRegMarca)
        val txtModelo: TextView = view.findViewById(R.id.etRegModelo)
        val txtAsientos: TextView = view.findViewById(R.id.etRegAsientos)
        val txtSoat: TextView = view.findViewById(R.id.etRegSoat)

        val db = FirebaseFirestore.getInstance()


        btnRegistrar.setOnClickListener{
            val placa = txtPlaca.text.toString()
            val anio = txtAnio.text.toString()
            val marca = txtMarca.text.toString()
            val modelo = txtModelo.text.toString()
            val asientos = txtAsientos.text.toString()
            val soat = txtSoat.text.toString()

            val nuevoVehiculo = VehiculoModel(placa, anio, marca, modelo, asientos, soat)



            db.collection("vehiculos")
                .document(placa)
                .set(nuevoVehiculo)
                .addOnSuccessListener {
                    showAlert(view, "Vehículo registrado correctamente")
                    txtPlaca.text =""
                    txtAnio.text =""
                    txtAsientos.text = ""
                    txtSoat.text = ""
                    txtModelo.text = ""
                    txtMarca.text = ""
                }.addOnFailureListener{
                    showAlert(view, "Se generó un error al registrar vehículo...")
                }



        }
        return view
    }

    private fun showAlert(vista:View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()

    }


}