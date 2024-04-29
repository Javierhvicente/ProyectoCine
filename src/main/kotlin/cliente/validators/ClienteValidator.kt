package org.example.cliente.validators

import org.example.cliente.Exceptions.ClienteException
import org.example.cliente.models.Cliente
/**
 * valida un cliente
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ClienteValidator {
    /**
     * valida cliente
     * @param cliente
     * @return cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    fun validate(cliente: Cliente): Cliente{
        val nombreRegex = Regex("[a-zA-Z0-9]{3,15}")
        if (!cliente.nombre.matches(nombreRegex) && cliente.nombre.isBlank()){
            throw ClienteException.ClienteInvalido("El nombre deberá contener entre 3 y 15 caracteres")
        }
        return cliente
    }
}
