package com.example.transporte.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.transporte.R
import com.example.transporte.ui.fragments.model.RutaModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class RegistroRutaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_registro_ruta, container, false)

        val btnRegistrar: Button = view.findViewById(R.id.btnRegistrar)

        val tvOrigen: TextView = view.findViewById(R.id.etRegOrigRuta)
        val tvDestino: TextView = view.findViewById(R.id.etRegDestRuta)
        val tvPrecio: TextView = view.findViewById(R.id.etRegPrecioRuta)
        val tvSalida: TextView = view.findViewById(R.id.etRegSalidaRuta)
        val tvLlegada: TextView = view.findViewById(R.id.etRegLlegadaRuta)

        val db = FirebaseFirestore.getInstance()


        btnRegistrar.setOnClickListener{
            val origen = tvOrigen.text.toString()
            val destino = tvDestino.text.toString()
            val precio = tvPrecio.text.toString()
            val salida = tvSalida.text.toString()
            val llegada = tvLlegada.text.toString()

            val nuevaRuta = RutaModel(origen, destino, precio, salida, llegada)
            val id: UUID = UUID.randomUUID()

            db.collection("rutas")
                .document(id.toString())
                .set(nuevaRuta)
                .addOnSuccessListener {
                    showAlert(view, "Ruta registrada correctamente")
                    tvOrigen.text = ""
                    tvDestino.text = ""
                    tvPrecio.text = ""
                    tvSalida.text = ""
                    tvLlegada.text = ""

                }.addOnFailureListener{
                    showAlert(view, "Ocurrió un error al registrar ruta")
                }

        }

        return view
    }
    private fun showAlert(vista:View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()

    }

}