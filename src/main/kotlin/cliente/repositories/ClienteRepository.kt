package org.example.cliente.repositories

import org.example.cliente.models.Cliente

interface ClienteRepository {
    fun findAll(): List<Cliente>
    fun findById(id: String): Cliente?
    fun save(cliente: Cliente): Cliente
    fun update(id: String, cliente: Cliente): Cliente?
    fun delete(id: String): Cliente?
}