package org.example.cliente.services

import org.example.cliente.models.Cliente

interface ClienteService {
    fun getAll(): List<Cliente>
    fun getById(id: String): Cliente
    fun save(cliente: Cliente): Cliente
    fun update(id: String, cliente: Cliente): Cliente
    fun delete(id: String): Cliente
}