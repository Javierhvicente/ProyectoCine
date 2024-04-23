package org.example.cliente.repositories

import org.example.cliente.models.Cliente
import org.example.service.DataBaseManager
import org.lighthousegames.logging.logging
import java.sql.ResultSet

private val logger = logging()
class ClienteRepositoryImpl: ClienteRepository {

    private fun ResultSet.toCliente(): Cliente {
        return Cliente(
            id = getString("id"),
            nombre = getString("nombre"),
            is_deleted = getBoolean("is_deleted")
        )
    }
    override fun findAll(): List<Cliente> {
        logger.debug { "Obteniendo todos los clientes" }
        val result = mutableListOf<Cliente>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM clientes"
            val stmt = db.connection?.prepareStatement(sql)!!
            val rs = stmt.executeQuery()
            while (rs.next()){
                result.add(rs.toCliente())
            }
        }
        return result
    }

    override fun findById(id: String): Cliente? {
        logger.debug { "Obteniendo cliente por id:$id" }
        var result: Cliente? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM clientes WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)!!.apply {
                setString(1,id)
            }
            val rs = stmt.executeQuery()
            if(rs.next()){
                result = rs.toCliente()
            }
        }
        return result
    }

    override fun save(cliente: Cliente): Cliente {
        logger.debug { "Guardando cliente: $cliente" }
        var result: Cliente = cliente
        DataBaseManager.use { db ->
            val sql = "INSERT INTO clientes (id, nombre) VALUES (?, ?)"
            val stmt = db.connection?.prepareStatement(sql)!!.apply {
                setString(1, cliente.id)
                setString(2,cliente.nombre)
                setBoolean(3, false)
            }
            val rs = stmt.executeUpdate()
        }
        return result
    }

    override fun update(id: String, cliente: Cliente): Cliente? {
        logger.debug { "Actualizanod cliente por id: $id" }
        var result: Cliente = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "UPDATE clientes SET nombre = ? WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)!!.apply {
                setString(1, cliente.nombre)
                setString(2, cliente.id)
            }
            val rs = stmt.executeUpdate()
            if(rs > 0){
                result = cliente.copy(
                    id = id
                )
            }
        }
        return result
    }

    override fun delete(id: String): Cliente? {
        logger.debug { "Borrando cliente por id: $id" }
        var result: Cliente = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "DELETE FROM clientes WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)!!.apply {
                setString(1, id)
            }
            val rs = stmt.executeUpdate()
            if(rs > 0){
                result = result.copy(is_deleted = true)
            }
        }
        return result
    }
}