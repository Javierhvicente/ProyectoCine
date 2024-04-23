package org.example.cliente.errors

sealed class ClienteErrors(val message: String) {
    class clienteInvalido(message: String): ClienteErrors(message)
}