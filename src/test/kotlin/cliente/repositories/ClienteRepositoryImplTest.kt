package cliente.repositories

import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepository
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.fileProperties
import org.koin.test.junit5.AutoCloseKoinTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClienteRepositoryImplTest: AutoCloseKoinTest() {
    private val dbManager: SqlDeLightManager by inject()
    private val clienteRepository: ClienteRepository by inject()

    @BeforeAll
    fun setUpAll() {
        println("Inicializando todos los tests...")
        startKoin {
            fileProperties("/config.properties")
            modules(databaseModule, clienteModule)
        }
    }

    @BeforeEach
    fun setUp() {
        dbManager.initializeTest()
        dbManager.initDataExamplesTest()
    }

    @Test
    fun findAll() {
        val clientes = clienteRepository.findAll()

        assertEquals(2, clientes.size)
    }

    @Test
    fun findById() {
        val cliente = clienteRepository.findById("YAH123")

        assertAll(
            { assertEquals("YAH123", cliente?.id) },
            { assertEquals("Yahya", cliente?.nombre) },
        )
    }

    @Test
    fun findByIdNotFound() {
        val cliente = clienteRepository.findById(5)
        assertEquals(null, cliente)
    }

    @Test
    fun save() {
        val cliente = clienteRepository.save(Cliente(id = "yah124", nombre = "Test", email = "test@gmail.com", numSocio = "TTT333"))
        assertAll(
            { assertEquals(3, cliente.id) },
            { assertEquals("Test", cliente.nombre) },
            { assertEquals("test@gmail.com", cliente.email) },
            { assertEquals("TTT333", cliente.numSocio) }
        )
    }

    @Test
    fun update() {
        val cliente = clienteRepository.update(1, Cliente(id = 1, nombre = "Test", email = "test@gmail.com", numSocio = "TTT333"))
        assertAll(
            { assertEquals(1, cliente?.id) },
            { assertEquals("Test", cliente?.nombre) },
            { assertEquals("test@gmail.com", cliente?.email) },
            { assertEquals("TTT333", cliente?.numSocio) }
        )
    }

    @Test
    fun updateNotFound() {
        val cliente = clienteRepository.update(5, Cliente(id = 1, nombre = "Test", email = "test@gmail.com", numSocio = "TTT333"))
        assertEquals(null, cliente)
    }

    @Test
    fun delete() {
        val cliente = clienteRepository.delete(1)
        assertEquals(1, cliente?.id)
    }

    @Test
    fun deleteNotFound() {
        val cliente = clienteRepository.delete(10)
        assertEquals(null, cliente)
    }
}