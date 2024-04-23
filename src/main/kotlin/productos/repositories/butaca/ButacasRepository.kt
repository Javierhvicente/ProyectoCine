package org.example.productos.repositories.butaca

import org.example.productos.models.Butaca

interface ButacasRepository {
    fun findAll(): List<Butaca>
    fun findById(id: String): Butaca?
    fun findByTipo(tipo: String): List<Butaca>
    fun save(producto: Butaca): Butaca
    fun update(id: String, producto: Butaca): Butaca?
    fun delete(id: String): Butaca?
}