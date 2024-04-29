package org.example.cliente.Exceptions

sealed class ClienteException(message: String) : Exception(message) {
    class ClienteNotFoundException(message: String) : ClienteException(message)
    class ClienteNotSavedException(message: String) : ClienteException(message)
    class ClienteNotUpdatedException(message: String) : ClienteException(message)
    class ClienteNotDeletedException(message: String) : ClienteException(message)
    class ClienteNotFetchedException(message: String) : ClienteException(message)
    class ClienteInvalido(message: String): ClienteException(message)
}