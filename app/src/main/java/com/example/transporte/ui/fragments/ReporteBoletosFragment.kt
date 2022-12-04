package com.example.transporte.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.transporte.R
import com.example.transporte.ui.fragments.adapter.BoletosAdapter
import com.example.transporte.ui.fragments.adapter.RutasAdapter
import com.example.transporte.ui.fragments.model.BoletoModel
import com.example.transporte.ui.fragments.model.RutaModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class ReporteBoletosFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_reporte_boletos, container, false)
        val db = FirebaseFirestore.getInstance()

        val lstBoletos: ArrayList<BoletoModel> = ArrayList()
        val rvBoletos: RecyclerView = view.findViewById(R.id.rvBoletos)

        db.collection("boleto")
            .addSnapshotListener { snap, e ->
                if (e != null) {
                    Log.w("Firebase error", "Error al listar los boletos...")
                    return@addSnapshotListener
                }

                for (dc in snap!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED -> {
                            lstBoletos.add(
                                BoletoModel(
                                    dc.document.data["cantidad"].toString(),
                                    dc.document.data["dni_pasajero"].toString(),
                                    dc.document.data["estado"].toString(),
                                    dc.document.data["precio"].toString()
                                )
                            )
                        }
                    }
                }
                rvBoletos.adapter = BoletosAdapter(lstBoletos)
                rvBoletos.layoutManager = LinearLayoutManager(requireContext())
            }
        return view
    }
}