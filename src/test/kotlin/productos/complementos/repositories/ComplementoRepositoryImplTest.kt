package productos.complementos.repositories

import org.example.di.complementoModule
import org.example.productos.complementos.repositories.ComplementoRepository
import org.example.productos.models.Bebida
import org.example.productos.models.CategoriaBebida
import org.example.productos.models.CategoriaComida
import org.example.productos.models.Comida
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.fileProperties
import org.koin.test.junit5.AutoCloseKoinTest

/**
 * Tests para el repo de complementos
 * @author Yahya el hadri
 * @since 1.0.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComplementoRepositoryImplTest: AutoCloseKoinTest() {
    private val complementoRepository: ComplementoRepository by inject()

    @BeforeAll
    fun setUpAll() {
        println("Inicializando todos los tests...")
        startKoin {
            fileProperties("/config.properties")
            modules(complementoModule)
        }
    }


    @Test
    fun findAll() {
        val complementos = complementoRepository.findAll()
        assertEquals(1, complementos.size)
    }

    @Test
    fun findById() {
        val complemento = complementoRepository.findById("AGUA")
        assertAll(
            { assertEquals("AGUA", complemento?.id) },
            { assertEquals(CategoriaBebida.AGUA.toString(), (complemento as Comida).nombre) },
        )
    }

    @Test
    fun findByIdNotFound() {
        val complemento = complementoRepository.findById("-1")
        assertEquals(null, complemento)
    }

    @Test
    fun save() {
        val complemento = complementoRepository.save(
            Bebida(
                id = "REFRESCO",
                nombre = CategoriaBebida.REFRESCOS,
            )

        )
        assertAll(
            { assertEquals("REFRESCO", complemento.id) },
        )
    }

    @Test
    fun update() {
        val complemento = complementoRepository.update("1",
            Comida(
                id = "PATATAS",
                nombre = CategoriaComida.PATATAS,

            )
        )

        assertAll(
            { assertEquals("PATATAS", complemento?.id) },
        )
    }



    @Test
    fun delete() {
        val complemento = complementoRepository.delete("1")
        assertAll(
            { assertEquals("1", complemento?.id) },

        )
    }

    @Test
    fun deleteNotFound() {
        val complemento = complementoRepository.delete("9")
        assertEquals(null, complemento)
    }
}