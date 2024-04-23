package org.example.cliente.models

data class Cliente(
    val id: String,
    val nombre: String,
    val is_deleted: Boolean = false
) {
    override fun toString(): String {
        return "Cliente (id=$id, nombre=$nombre)"
    }
}