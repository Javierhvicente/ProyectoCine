package org.example


import com.github.michaelbull.result.mapBoth
import org.example.cliente.models.Cliente
import org.example.cliente.services.ClienteService
import org.example.locale.toDefaultMoneyString
import org.example.productos.butaca.services.ButacaService
import org.example.productos.complementos.services.ComplementoService
import org.example.productos.models.*
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import org.example.ventas.services.VentasService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.nio.file.Files
import java.time.LocalDate
import java.util.*
import kotlin.io.path.Path
import kotlin.random.Random

class CineApp : KoinComponent{

    fun run(){
        val butacasService: ButacaService by inject()
        val complementoService: ComplementoService by inject()
        val ventaService : VentasService by inject()
        val clienteService: ClienteService by inject()



        menu(butacasService,complementoService,ventaService,clienteService)
    }

    private fun menu(butacasService: ButacaService, complementoService: ComplementoService, ventaService: VentasService, clienteService: ClienteService) {
        do{
            println("BIENVENIDOS AL CINE")
            println("SELECCIONE UNA OPCION DE LAS SIGUIENTES")
            println()
            println("1. Comprar entrada")
            println("2. Devolver entrada")
            println("3. Estado del cine")
            println("4. Obtener recaudacion")
            println("5. Importar complementos")
            println("6. Exportar Cine")
            println("7. Configurar butacas")
            println("8. Actualizar butaca")
            println("Para salir del Menu pulse cualquier tecla")

            var respuesta= readln().toIntOrNull() ?: 9
            if (respuesta !in 1..8) respuesta=9


                when(respuesta){
                    1-> comprarEntrada(butacasService,ventaService)
                    2-> devolverEntrada(butacasService,ventaService)
                    3-> estadoCine(butacasService)
                    4 -> recaudacionTotal(ventaService)
                    5-> importarComplmentos(complementoService)
                    6-> exportarCine(butacasService)
                    7-> configuraraButaca(butacasService)
                    8-> actualizacionButacas(butacasService)
                }
        }while (respuesta != 9)
        println("Has abandonado el cine.")
    }

    private fun actualizacionButacas(butacasService: ButacaService) {
        println("ACTUALIZAR BUTACA")
        val numButaca=preguntaButaca()
        val estado=preguntarEstado()
        val tipo= preguntarTipo()
        val butaca=Butaca(numButaca, estado, tipo)
        butacasService.update(numButaca,butaca).mapBoth(
            success = {
                println("Butaca actualizada correctamente")
            },
            failure = {

            }
        )
    }

    private fun preguntarTipo(): Tipo {
        var tipoString=""
        var tipo:Tipo=Tipo.NORMAL
        var valido=false
        val listaTipo= arrayOf("VIP","NORMAL")
        println("Que tipo de butaca es")
        do {
            tipoString= readln()
            if (tipoString.uppercase() in listaTipo) valido=true
            else println("Tipo nop valido, recuerda que solo puede ser NORMAL o VIP")
        }while (!valido)
        when (tipoString.uppercase()) {
            "VIP" -> tipo=Tipo.VIP
            "NORMAL" -> tipo=Tipo.NORMAL
        }
        return tipo
    }

    private fun preguntarEstado(): Estado {
        var estadoString=""
        var estado:Estado=Estado.ACTIVA
        var valida=false
        val listaEstado= arrayOf("ACTIVA", "MANTENIMIENTO", "OUTSERVICE")
        println("Cual es el estado de la butaca")
        do {
            estadoString=readln()
            if (estadoString.uppercase() in listaEstado) valida=true
            else println("Estado no valido, introduce un estado valido, recuerda que solo puede ser" +
                    "ACTIVA, MANTENIMIENTO, OUTSERVICE")
        }while (!valida)
        when(estadoString.uppercase()) {
            "ACTIVA" -> estado= Estado.ACTIVA
            "MANTENIMIENTO" -> estado= Estado.MANTENIMIENTO
            "OUTSERVICE" -> estado= Estado.OUTSERVICE
        }
        return estado
    }


    private fun configuraraButaca(butacasService: ButacaService) {
        println("IMPORTAR BUTACAS")
        val file=File("data","butacas.csv")
        val importar=butacasService.import(file)
        importar.mapBoth(
            success = {
                println("Las butacas se han importado correctamente")
            },
            failure = {

            }
        )

    }

    private fun exportarCine(butacasService: ButacaService) {
        println("EXPORTAR CINE")
        val fecha=fecha()
        val lista=butacasService.getAll()
        lista.mapBoth(
            success = {
                exportarJsonButacas(fecha,lista.value,butacasService)
            },
            failure = {

            }
        )

    }

    private fun exportarJsonButacas(fecha: String, lista: List<Butaca>, butacasService: ButacaService) {
        lista.filter { it.create.toString().replace("-","/") == fecha }
        butacasService.export(fecha,lista).mapBoth(
            success = {
                println("La butacas se han exportado correctamente")
            },
            failure = {

            }
        )

    }

    private fun importarComplmentos(complementoService: ComplementoService) {
        println("IMPORTAR COMPLEMENTOS")
        val file=File("data","complementos.csv")
        val importar=complementoService.import(file)
        importar.mapBoth(
            success = {
                println("Los complementos se han importado correctamente")
            },
            failure = {

            }
        )
    }

    private fun recaudacionTotal(ventaService: VentasService) {
        println("RECAUDACION TOTAL")
        val fecha=fecha()
        val lista= ventaService.getAll()

        lista.mapBoth(
            success = {
                recaudacionLista(lista.value,fecha)
            },
            failure = {

            }
        )

    }

    private fun fecha(): String {
        val regex= "^{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$".toRegex()
        var fecha=""
        do {
            println("Introduce la fecha")
            fecha= readln()
            if (!regex.matches(fecha)) println("fecha no válida, recuerda el formato AAAA/MM/DD")
        }while (regex.matches(fecha))
        return fecha
    }

    private fun recaudacionLista(lista: List<Venta>, fecha: String) {
        var recaudacion=0.0
        val li=lista.filter {
            it.createdAt.toString().substring(0..10).replace("-","/")  == fecha
        }.forEach { recaudacion+=it.total }
        println(" La recaudacion total del $fecha es de ${recaudacion.toDefaultMoneyString()}")
    }

    private fun estadoCine(butacasService: ButacaService) {
        val lista= butacasService.getAll()
        lista.mapBoth(
            success = {
                imprimirCine(lista.value)
            },
            failure = {

            }
        )

    }

    private fun devolverEntrada(butacasService: ButacaService,ventaService: VentasService) {
        println("DEVOLVER ENTRADA")


        println("Introduce el id de la venta")
        val idVenta= readln()
        val venta = ventaService.delete(UUID.fromString(idVenta))

        venta.mapBoth(
            success = {
                eliminarButaca(venta.value)
                println(" Devolucion Completada, se le van a ingresar el total de ${venta.value.total.toDefaultMoneyString()}")
            },
            failure = {

            }
        )

    }
    private fun eliminarButaca(venta: Venta) {
        var butaca:Butaca? =null
        venta.lineas.forEach {
            butaca = it.butaca
        }
        butaca?.ocupacion = Ocupacion.LIBRE

    }

    private fun comprarEntrada(
        butacasService: ButacaService,
        ventaService: VentasService,
    ) {
        println("COMPRA ENTRADA")
        val listaButacas = butacasService.getAll()

        listaButacas.mapBoth(
            success = {
                if (it.isNotEmpty()){
                    imprimirCine(it)
                    eleccionCompra(butacasService,ventaService)
               }
            },
            failure = {
                println("El cine esta vacio")
            }

        )

    }



    private fun eleccionCompra(butacaService: ButacaService, ventaService: VentasService) {
        println("Introduce tu nombre")
        val nombre = readln()
        val id = creacionId(nombre)
        val cliente = Cliente(id,nombre)

        var numButaca=""
        do {
            numButaca= preguntaButaca()
        }while (!comprobarButaca(numButaca,butacaService))

        val butaca = actualizarOcupacionButaca(numButaca,butacaService)

        val listaComplementos = Array<Complemento?>(3){null}
        seleccionComplementos(listaComplementos)

        val precio=precioLineaVenta(listaComplementos,butaca)

        val cantidad= cantidadLineaVenta(listaComplementos,butaca)

        val listaLineaVenta= mutableListOf<LineaVenta>()

        val lineaVenta= LineaVenta(
            butaca= butaca,
            complemento1 = listaComplementos[0],
            complemento2 = listaComplementos[1],
            complemento3 = listaComplementos[2],
            cantidad = cantidad,
            precio = precio
        )
        listaLineaVenta.add(lineaVenta)

        val venta=ventaService.create(cliente,listaLineaVenta)

        val file= crearFileHtml(cliente,butaca)

        venta.mapBoth(
            success = {
                ventaService.exportToHtml(venta.value,file)
            },
            failure = {
                println("Html no creado")
            }
        )

    }

    private fun crearFileHtml(cliente: Cliente, butaca: Butaca): File {
        Files.createDirectories(Path("data"))

        return File("data","entrada_${butaca.id}_${cliente.id}_${LocalDate.now()}.html")

    }

    private fun cantidadLineaVenta(listaComplementos: Array<Complemento?>, butaca: Butaca): Int {
        var cantidad=1
        for (i in listaComplementos.indices){
            if (listaComplementos[i] is Complemento)cantidad++
        }
        return cantidad
    }

    private fun precioLineaVenta(listaComplementos: Array<Complemento?>, butaca: Butaca): Double {
        var precio = 0.0
        listaComplementos.forEach {
            when(it){
                is Bebida -> precio += it.precio
                is Comida -> precio += it.precio
                null -> precio += 0
            }
        }
        precio += butaca.precio

        return precio
    }

    private fun seleccionComplementos(listaComplementos: Array<Complemento?>) {
        var contador = 0
        do {
            println("Elige un complemento si lo desea. MAX 3")
            println("1. Refresco")
            println("2. Agua")
            println("3. Palomitas")
            println("4. Frutos Secos")
            println("5. Patatas")
            println("Si no desea nada pulse culquier tecla")
            var respuesta= readln().toIntOrNull()?: 6
            if (respuesta !in 1..5) respuesta=6
            if (respuesta in 1..5){
                agrgarComplementos(respuesta,listaComplementos,contador)
                contador++
            }
            if (contador==3) println("Has llegado al maximo de complementos")
        }while (respuesta != 6 && contador<3)
    }

    private fun agrgarComplementos(respuesta: Int, listaComplementos: Array<Complemento?>, contador: Int) {
        if (contador < 3){
            when(respuesta){
                1-> listaComplementos[contador]=Bebida("REFRESCO",CategoriaBebida.REFRESCOS)
                2-> listaComplementos[contador]=Bebida("AGUA",CategoriaBebida.AGUA)
                3-> listaComplementos[contador]=Comida("PALOMITAS",CategoriaComida.PALOMITAS)
                4-> listaComplementos[contador]=Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS)
                5-> listaComplementos[contador]=Comida("PATATAS",CategoriaComida.PATATAS)
            }
        }

    }

    private fun actualizarOcupacionButaca(numButaca: String, butacaService: ButacaService): Butaca {
        val butaca = butacaService.getById(numButaca)
        butaca.value.ocupacion = Ocupacion.OCUPADA
        return butaca.value
    }

    private fun comprobarButaca(numButaca: String, butacaService: ButacaService):Boolean {
        val butaca = butacaService.getById(numButaca)
        if (butaca.value.estado == Estado.ACTIVA){
            if (butaca.value.ocupacion == Ocupacion.LIBRE) return true
            else{
                println("Selecciona una butaca Libre")
                return false
            }
        }else{
            println("Selecciona una butaca Libre")
            return false
        }

    }

    private fun preguntaButaca(): String {
        println("Seleccione Una butaca, recuerda que debe ser LN")
        var numButaca=""
        do {
            numButaca= readln()
            val regex= "^[A-E][1-7]$".toRegex()
            if (!regex.matches(numButaca.uppercase())){
                println("Introduce una butaca valida")
            }
        }while (!regex.matches(numButaca.uppercase()))
        return numButaca.uppercase()
    }
    private fun creacionId(nombre:String): String {
        var letras=""
        if (nombre.length > 3){
            letras = nombre.substring(0, 3)
        } else letras = List(3) { ('A'..'Z').random() }.joinToString("")
        val numeros = List(3) { Random.nextInt(0, 10).toString() }.joinToString("")
        return letras + numeros
    }

    private fun imprimirCine(lista: List<Butaca>) {
        println("ESTADO ACTUAL DEL CINE")
        val cine : Array<Array<Butaca?>> = Array(5){Array(7){null}}
        posicionarButacas(lista,cine)
        for (i in cine.indices){
            for (j in cine[i].indices){
                when(cine[i][j]?.estado){
                    Estado.ACTIVA-> {
                        when(cine[i][j]?.tipo){
                            Tipo.VIP -> print("[V]")
                            Tipo.NORMAL-> print("[N]")
                            else -> print("[ ]")
                        }
                    }
                    Estado.OUTSERVICE-> print("[O]")
                    Estado.MANTENIMIENTO-> print("[M]")
                    else -> print("[ ]")
                }

            }
            println()
        }
    }

    private fun posicionarButacas(lista: List<Butaca>, cine: Array<Array<Butaca?>>) {
        lista.forEach {
            val letra = posicionButaca(it.id)
            cine[letra][it.id[1].digitToInt()-1]=it
        }
    }

    private fun posicionButaca(id: String): Int {
        when(id[0]){
            'A'-> return 0
            'B'-> return 1
            'C'-> return 2
            'D'-> return 3
            'E'-> return 4
        }
        return 5
    }

}