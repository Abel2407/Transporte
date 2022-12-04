package com.example.transporte.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.transporte.R
import com.example.transporte.ui.MainActivity
import com.example.transporte.ui.fragments.model.ViajeModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RegistroViajeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_registro_viaje, container, false)

        val btnRegistrarViaje: Button = view.findViewById(R.id.btnRegistrarViaje)

        val spRuta: Spinner = view.findViewById(R.id.spRuta)
        val etTarjeta: EditText = view.findViewById(R.id.etTarjeta)
        val etCantidad: EditText = view.findViewById(R.id.etCantidad)


        val dni=(context as Activity?)!!.intent.getStringExtra("dni");
        val db = FirebaseFirestore.getInstance()
        val sdFormat = SimpleDateFormat("yyyy-MM-dd")

        db.collection("rutas")
            .get()
            .addOnSuccessListener { result ->
                val options = ArrayList<String>()
                for (document in result) {
                    options.add(
                        sdFormat.format( Date()) + " " +
                        document.data["origen"].toString() + " " +
                        document.data["destino"].toString() + " " +
                        document.data["salida"].toString() + " " +
                        document.data["precio"].toString() + " "
                    )
                }
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    (context as Activity?)!!,
                    android.R.layout.simple_spinner_item,
                    options
                )
                spRuta.setAdapter(adapter)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    (context as Activity?)!!,
                    "Error . Intente nuevamente $exception",
                    Toast.LENGTH_LONG
                ).show()
            }

        btnRegistrarViaje.setOnClickListener{
            val tarjeta = etTarjeta.text.toString()
            val cantidad = etCantidad.text.toString()
            val ruta:String = spRuta.selectedItem.toString()

            val arrayRuta = ruta.split(" ")
            val date = SimpleDateFormat("yyyy-MM-dd hh:mm").parse(arrayRuta[0] + " " + arrayRuta[3])
            val nestedRuta = hashMapOf(
                "origen" to arrayRuta[1],
                "destino" to arrayRuta[2],
                "salida" to date
            )

            val newViaje = ViajeModel("Pagado", tarjeta, arrayRuta[4].toInt() * cantidad.toInt(), dni!!, nestedRuta, cantidad)
            val id: UUID = UUID.randomUUID()

            db.collection("boleto")
                .document(id.toString())
                .set(newViaje)
                .addOnSuccessListener {
                    showAlert(view, "Boleto registrado correctamente")

                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.nav_host_fragment_activity_main, DetalleViajesFragment())
                    transaction?.commit()

                }.addOnFailureListener{
                    showAlert(view, "Se generó un error al registrar boleto...")
                }



        }
        return view
    }

    private fun showAlert(vista:View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()

    }


}