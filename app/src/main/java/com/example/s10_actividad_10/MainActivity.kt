package com.example.s10_actividad_10

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var lvTareas: ListView
    private lateinit var btnNuevaTarea: Button
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // enlaces de elementos de la vista XML
        lvTareas = findViewById(R.id.lvTareas)
        btnNuevaTarea = findViewById(R.id.btnNuevaTarea)

        // tareas de prueba por si la lista está vacía
        if (RepositorioTareas.listaTareas.isEmpty()) {
            RepositorioTareas.agregarTarea("Estudiar Kotlin", "Repasar sintaxis de colecciones y vistas")
            RepositorioTareas.agregarTarea("Avanzar DBP", "Por completar")
        }

        // acción al presionar el botón agregar
        btnNuevaTarea.setOnClickListener {
            Toast.makeText(this, "AgregarTarea", Toast.LENGTH_SHORT).show()
        }

        // acción al tocar una tarea de la lista
        lvTareas.setOnItemClickListener { _, _, position, _ ->
            val tarea = RepositorioTareas.listaTareas[position]
            Toast.makeText(this, "Seleccionado: ${tarea.titulo}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarLista()
    }

    private fun actualizarLista() {
        // se formatea el texto de cada fila según si está completada o no
        val items = RepositorioTareas.listaTareas.map {
            if (it.completada) "[✓] ${it.titulo}" else "[ ] ${it.titulo}"
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvTareas.adapter = adapter
    }
}