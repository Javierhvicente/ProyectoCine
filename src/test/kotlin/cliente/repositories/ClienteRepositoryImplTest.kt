package cliente.repositories

import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepository
import org.example.di.clienteModule
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.fileProperties
import org.koin.test.junit5.AutoCloseKoinTest
/**
 * Tests para comprobar el correcto funcionamiento del repositorio de clientes
 * @author Yahya el hadri
 * @since 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClienteRepositoryImplTest: AutoCloseKoinTest() {
    private val clienteRepository: ClienteRepository by inject()

    @BeforeAll
    fun setUpAll() {
        println("Inicializando todos los tests...")
        startKoin {
            fileProperties("/config.properties")
            modules( clienteModule)
        }
    }


    @Test
    fun findAll() {
        val clientes = clienteRepository.findAll()

        assertEquals(2, clientes.size)
    }

    @Test
    fun findById() {
        val cliente = clienteRepository.findById("Yah785")

        assertAll(
            { assertEquals("Yah785", cliente?.id) },
            { assertEquals("Yahya", cliente?.nombre) },
        )
    }

    @Test
    fun findByIdNotFound() {
        val cliente = clienteRepository.findById("Yah785")
        assertEquals(null, cliente)
    }

    @Test
    fun save() {
        val cliente = clienteRepository.save(Cliente(id = "Yah795", nombre = "Javi"))
        assertAll(
            { assertEquals("Yah795", cliente.id) },
            { assertEquals("Javi", cliente.nombre) }
        )
    }

    @Test
    fun update() {
        val cliente = clienteRepository.update("Yah795", Cliente(id = "Yah795", nombre = "Test"))
        assertAll(
            { assertEquals("Yah795", cliente?.id) },
            { assertEquals("Test", cliente?.nombre) },
        )
    }

    @Test
    fun delete() {
        val cliente = clienteRepository.delete("Yah795")
        assertEquals("Yah795", cliente?.id)
    }

}