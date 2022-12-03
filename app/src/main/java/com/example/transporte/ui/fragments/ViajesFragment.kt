package com.example.transporte.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.transporte.R
import com.example.transporte.ui.fragments.adapter.DetalleViajeAdapter
import com.example.transporte.ui.fragments.model.DetalleViajeModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.text.SimpleDateFormat
import java.util.*

class ViajesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_viajes, container, false)

        val btnOpenCalendar: Button =  view.findViewById(R.id.btnOpenCalendar)

        val dni=(context as Activity?)!!.intent.getStringExtra("dni");
        val dbStore = FirebaseFirestore.getInstance()


        val rvViajes: RecyclerView = view.findViewById(R.id.rvViajes)
        val today = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val nextMonth =  Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        today.set(Calendar.DAY_OF_MONTH, 1);
        nextMonth.add(Calendar.MONTH, 1);
        nextMonth.set(Calendar.DAY_OF_MONTH, 1);

        //filter listado de boletos
        filterList(dbStore, dni, today, nextMonth, rvViajes)

        btnOpenCalendar.text = "${getMonthString(today.get(Calendar.MONTH))} ${today.get(Calendar.YEAR)}"
        btnOpenCalendar.setOnClickListener {
            val builder = MonthPickerDialog.Builder(
                (context as Activity?)!!, { selectedMonth, selectedYear ->
                    btnOpenCalendar.text = "${getMonthString(selectedMonth)} $selectedYear"

                    today.set(Calendar.MONTH, selectedMonth);
                    today.set(Calendar.YEAR, selectedYear);

                    nextMonth.set(Calendar.YEAR, selectedYear);
                    nextMonth.set(Calendar.MONTH, selectedMonth+1);

                    //filter listado de boletos
                    filterList(dbStore, dni, today, nextMonth, rvViajes)

                },
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH)
            )

            builder.setActivatedMonth(Calendar.DECEMBER)
                .setMinYear(1980)
                .setActivatedYear(2022)
                .setMaxYear(2030)
                .setMinMonth(Calendar.DECEMBER)
                .setTitle("Seleccione el mes a buscar")
                .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                .build()
                .show();
        }



        return view
    }

    private fun filterList(
        dbStore: FirebaseFirestore,
        dni: String?,
        today: Calendar,
        nextMonth: Calendar,
        rvViajes: RecyclerView
    ) {
        val sfd = SimpleDateFormat("HH:mm")
        val lstViajes: ArrayList<DetalleViajeModel> = ArrayList()
        rvViajes.adapter = DetalleViajeAdapter(lstViajes)
        rvViajes.layoutManager = LinearLayoutManager(requireContext())

        dbStore.collection("boleto")
            .whereEqualTo("dni_pasajero", dni)
            .whereGreaterThan("ruta.salida", today.time)
            .whereLessThan("ruta.salida", nextMonth.time)
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    val rutaMap = document.get("ruta") as Map<String, *>
                    var ruta: String =
                        " ${rutaMap.get("origen")} - ${rutaMap.get("destino")}  ${sfd.format( (rutaMap.get("salida") as Timestamp).toDate()) }"
                    lstViajes.add(
                        DetalleViajeModel(
                            document.data["estado"].toString(),
                            ruta,
                            document.data["tarjeta"].toString(),
                            "S/ ${document.data["precio"].toString()}"
                        )
                    )

                }
                rvViajes.adapter = DetalleViajeAdapter(lstViajes)
                rvViajes.layoutManager = LinearLayoutManager(requireContext())
            }
            .addOnFailureListener { exception ->
                System.out.println(exception)
                Toast.makeText(
                    (context as Activity?)!!,
                    "Error . Intente nuevamente $exception",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun getMonthString(month: Int): String {
        val result = when (month) {
            0 -> "Enero"
            1 -> "Febrero"
            2 -> "Marzo"
            3 -> "Abril"
            4 -> "Mayo"
            5 -> "Junio"
            6 -> "Julio"
            7 -> "Agosto"
            8 -> "Septiembre"
            9 -> "Octubre"
            10 -> "Noviembre"
            11 -> "Diciembre"
            else -> {
                "Apr"
            }
        }
        return result
    }

}