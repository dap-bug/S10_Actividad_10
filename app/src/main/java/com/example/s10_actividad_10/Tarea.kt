package com.example.s10_actividad_10

import java.io.Serializable

// Modelo de datos para cada tarea
data class Tarea(
    var id: Int,
    var titulo: String,
    var descripcion: String,
    var completada: Boolean = false
) : Serializable

// Repositorio para gestionar el CRUD
object RepositorioTareas {
    val listaTareas = mutableListOf<Tarea>()
    private var contadorId = 1

    fun agregarTarea(titulo: String, descripcion: String) {
        listaTareas.add(Tarea(contadorId++, titulo, descripcion))
    }

    fun eliminarTarea(id: Int) {
        listaTareas.removeAll { it.id == id }
    }

    fun actualizarTarea(id: Int, nuevoTitulo: String, nuevaDescripcion: String, completada: Boolean) {
        val tarea = listaTareas.find { it.id == id }
        tarea?.let {
            it.titulo = nuevoTitulo
            it.descripcion = nuevaDescripcion
            it.completada = completada
        }
    }
}