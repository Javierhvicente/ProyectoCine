package org.example.cliente.models

class Cliente(
    val id: String,
    val nombre: String
) {
    override fun toString(): String {
        return "Cliente (id=$id, nombre=$nombre)"
    }
}