package productos.butaca.repositories

import org.example.di.butacaModule
import org.example.productos.butaca.repositories.ButacasRepository
import org.example.productos.models.Butaca
import org.example.productos.models.Estado
import org.example.productos.models.Ocupacion
import org.example.productos.models.Tipo
import org.example.service.DataBaseManager
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.fileProperties
import org.koin.test.junit5.AutoCloseKoinTest

/**
 * Tests para comprobar el correcto funcionamiento del repositorio de butacas
 * @author Yahya el hadri
 * @since 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ButacaRepositoryImplTest: AutoCloseKoinTest() {
    private val butacasRepository: ButacasRepository by inject()
    private val dataBaseManager: DataBaseManager by inject()

    @BeforeAll
    fun setUpAll() {
        println("Inicializando todos los tests...")
        startKoin {
            fileProperties("/config.properties")
            modules(butacaModule)
        }
    }


    @Test
    fun findAll() {
        val butacas = butacasRepository.findAll()
        assertEquals(1, butacas.size)
    }

    @Test
    fun findById() {
        val butaca = butacasRepository.findById("A1")
        assertAll(
            { assertEquals("A1", butaca?.id) },
            { assertEquals(5.0, butaca?.precio) },
            { assertEquals(Estado.ACTIVA, butaca?.estado) },
            { assertEquals(Tipo.NORMAL, butaca?.tipo) }
        )
    }

    @Test
    fun save() {
        val butaca = butacasRepository.save(
            Butaca(
                id = "A2",
                tipo = Tipo.NORMAL,
                estado = Estado.ACTIVA,
            )

        )
        assertAll(
            { assertEquals("A2", butaca.id) },
            { assertEquals(Tipo.NORMAL, butaca.tipo) },
            { assertEquals(5.0, butaca.precio) },
            { assertEquals(Ocupacion.LIBRE, butaca.ocupacion) }
        )
    }

    @Test
    fun update() {
        val butaca = butacasRepository.update("A1",
            Butaca(
                id = "A1",
                tipo = Tipo.VIP,
                estado = Estado.MANTENIMIENTO,
            )
        )

        assertAll(
            { assertEquals("A1", butaca?.id) },
            { assertEquals(8.0, butaca?.precio) },
            { assertEquals(Tipo.VIP, butaca?.tipo) },
            { assertEquals(Estado.MANTENIMIENTO, butaca?.estado) },
            { assertEquals(Ocupacion.LIBRE, butaca?.ocupacion) },
        )
    }


    @Test
    fun delete() {
        val butaca = butacasRepository.delete("A1")
        assertAll(
            { assertEquals("A1", butaca?.id) },
        )
    }

    @Test
    fun deleteNotFound() {
        val butaca = butacasRepository.delete("A9")
        assertEquals(null, butaca)
    }
}