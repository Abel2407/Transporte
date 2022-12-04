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
import com.example.transporte.ui.fragments.adapter.RutasAdapter
import com.example.transporte.ui.fragments.model.RutaModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class ReporteRutasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_reporte_rutas, container, false)
        val db = FirebaseFirestore.getInstance()

        val lstRutas: ArrayList<RutaModel> = ArrayList()
        val rvRutas: RecyclerView = view.findViewById(R.id.rvRutas)

        db.collection("rutas")
            .addSnapshotListener{snap, e->
                if(e!=null){
                    Log.w("Firebase error","Error al listar las rutas...")
                    return@addSnapshotListener
                }

                for (dc in snap!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED ->{
                            lstRutas.add(
                                RutaModel(
                                    dc.document.data["origen"].toString(),
                                    dc.document.data["destino"].toString())
                            )
                        }
                    }
                }
                rvRutas.adapter = RutasAdapter(lstRutas)
                rvRutas.layoutManager = LinearLayoutManager(requireContext())
            }
        return view
    }
}