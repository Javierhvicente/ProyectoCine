package database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Long
import kotlin.String

public class DatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllButacaEntity(mapper: (
    id: String,
    estado: String,
    tipo: String,
    precio: Long,
  ) -> T): Query<T> = Query(122_392_116, arrayOf("ButacaEntity"), driver, "database.sq",
      "getAllButacaEntity",
      "SELECT ButacaEntity.id, ButacaEntity.estado, ButacaEntity.tipo, ButacaEntity.precio FROM ButacaEntity") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getAllButacaEntity(): Query<ButacaEntity> = getAllButacaEntity { id, estado, tipo,
      precio ->
    ButacaEntity(
      id,
      estado,
      tipo,
      precio
    )
  }

  public fun <T : Any> getByIdButacaEntity(id: String, mapper: (
    id: String,
    estado: String,
    tipo: String,
    precio: Long,
  ) -> T): Query<T> = GetByIdButacaEntityQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getByIdButacaEntity(id: String): Query<ButacaEntity> = getByIdButacaEntity(id) { id_,
      estado, tipo, precio ->
    ButacaEntity(
      id_,
      estado,
      tipo,
      precio
    )
  }

  public fun <T : Any> getbutacaByTipo(tipo: String, mapper: (
    id: String,
    estado: String,
    tipo: String,
    precio: Long,
  ) -> T): Query<T> = GetbutacaByTipoQuery(tipo) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getbutacaByTipo(tipo: String): Query<ButacaEntity> = getbutacaByTipo(tipo) { id,
      estado, tipo_, precio ->
    ButacaEntity(
      id,
      estado,
      tipo_,
      precio
    )
  }

  public fun <T : Any> getAllComplemetoEntity(mapper: (
    tipo: String,
    id: Long,
    nombre: String,
    precio: Long,
  ) -> T): Query<T> = Query(1_265_361_777, arrayOf("ComplementoEntity"), driver, "database.sq",
      "getAllComplemetoEntity",
      "SELECT ComplementoEntity.tipo, ComplementoEntity.id, ComplementoEntity.nombre, ComplementoEntity.precio FROM ComplementoEntity") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getAllComplemetoEntity(): Query<ComplementoEntity> = getAllComplemetoEntity { tipo, id,
      nombre, precio ->
    ComplementoEntity(
      tipo,
      id,
      nombre,
      precio
    )
  }

  public fun <T : Any> getByIdComplemetoEntity(id: Long, mapper: (
    tipo: String,
    id: Long,
    nombre: String,
    precio: Long,
  ) -> T): Query<T> = GetByIdComplemetoEntityQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getByIdComplemetoEntity(id: Long): Query<ComplementoEntity> =
      getByIdComplemetoEntity(id) { tipo, id_, nombre, precio ->
    ComplementoEntity(
      tipo,
      id_,
      nombre,
      precio
    )
  }

  public fun <T : Any> getComplementoByTipo(tipo: String, mapper: (
    tipo: String,
    id: Long,
    nombre: String,
    precio: Long,
  ) -> T): Query<T> = GetComplementoByTipoQuery(tipo) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!
    )
  }

  public fun getComplementoByTipo(tipo: String): Query<ComplementoEntity> =
      getComplementoByTipo(tipo) { tipo_, id, nombre, precio ->
    ComplementoEntity(
      tipo_,
      id,
      nombre,
      precio
    )
  }

  public fun <T : Any> selectAllVentas(mapper: (
    id: String,
    cliente_id: String,
    total: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = Query(-1_591_653_436, arrayOf("VentaEntity"), driver, "database.sq",
      "selectAllVentas",
      "SELECT VentaEntity.id, VentaEntity.cliente_id, VentaEntity.total, VentaEntity.created_at, VentaEntity.updated_at, VentaEntity.is_deleted FROM VentaEntity") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getDouble(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!
    )
  }

  public fun selectAllVentas(): Query<VentaEntity> = selectAllVentas { id, cliente_id, total,
      created_at, updated_at, is_deleted ->
    VentaEntity(
      id,
      cliente_id,
      total,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun <T : Any> selectVentaById(id: String, mapper: (
    id: String,
    cliente_id: String,
    total: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = SelectVentaByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getDouble(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!
    )
  }

  public fun selectVentaById(id: String): Query<VentaEntity> = selectVentaById(id) { id_,
      cliente_id, total, created_at, updated_at, is_deleted ->
    VentaEntity(
      id_,
      cliente_id,
      total,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun existsVenta(id: String): Query<Boolean> = ExistsVentaQuery(id) { cursor ->
    cursor.getBoolean(0)!!
  }

  public fun <T : Any> selectAllVentasByIsDeleted(is_deleted: Long, mapper: (
    id: String,
    cliente_id: String,
    total: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = SelectAllVentasByIsDeletedQuery(is_deleted) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getDouble(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!
    )
  }

  public fun selectAllVentasByIsDeleted(is_deleted: Long): Query<VentaEntity> =
      selectAllVentasByIsDeleted(is_deleted) { id, cliente_id, total, created_at, updated_at,
      is_deleted_ ->
    VentaEntity(
      id,
      cliente_id,
      total,
      created_at,
      updated_at,
      is_deleted_
    )
  }

  public fun <T : Any> selectVentaLastInserted(mapper: (
    id: String,
    cliente_id: String,
    total: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = Query(950_557_014, arrayOf("VentaEntity"), driver, "database.sq",
      "selectVentaLastInserted",
      "SELECT VentaEntity.id, VentaEntity.cliente_id, VentaEntity.total, VentaEntity.created_at, VentaEntity.updated_at, VentaEntity.is_deleted FROM VentaEntity WHERE id = last_insert_rowid()") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getDouble(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!
    )
  }

  public fun selectVentaLastInserted(): Query<VentaEntity> = selectVentaLastInserted { id,
      cliente_id, total, created_at, updated_at, is_deleted ->
    VentaEntity(
      id,
      cliente_id,
      total,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun <T : Any> selectAllLineasVentas(mapper: (
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = Query(1_730_545_866, arrayOf("LineaVentaEntity"), driver, "database.sq",
      "selectAllLineasVentas",
      "SELECT LineaVentaEntity.id, LineaVentaEntity.venta_id, LineaVentaEntity.Butaca_id, LineaVentaEntity.Complemento1_id, LineaVentaEntity.Complemento2_id, LineaVentaEntity.Complemento3_id, LineaVentaEntity.cantidad, LineaVentaEntity.precio, LineaVentaEntity.created_at, LineaVentaEntity.updated_at, LineaVentaEntity.is_deleted FROM LineaVentaEntity") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3),
      cursor.getLong(4),
      cursor.getLong(5),
      cursor.getLong(6)!!,
      cursor.getDouble(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!
    )
  }

  public fun selectAllLineasVentas(): Query<LineaVentaEntity> = selectAllLineasVentas { id,
      venta_id, Butaca_id, Complemento1_id, Complemento2_id, Complemento3_id, cantidad, precio,
      created_at, updated_at, is_deleted ->
    LineaVentaEntity(
      id,
      venta_id,
      Butaca_id,
      Complemento1_id,
      Complemento2_id,
      Complemento3_id,
      cantidad,
      precio,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun <T : Any> selectLineaVentaById(id: String, mapper: (
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = SelectLineaVentaByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3),
      cursor.getLong(4),
      cursor.getLong(5),
      cursor.getLong(6)!!,
      cursor.getDouble(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!
    )
  }

  public fun selectLineaVentaById(id: String): Query<LineaVentaEntity> = selectLineaVentaById(id) {
      id_, venta_id, Butaca_id, Complemento1_id, Complemento2_id, Complemento3_id, cantidad, precio,
      created_at, updated_at, is_deleted ->
    LineaVentaEntity(
      id_,
      venta_id,
      Butaca_id,
      Complemento1_id,
      Complemento2_id,
      Complemento3_id,
      cantidad,
      precio,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun <T : Any> selectAllLineasVentaByVentaId(venta_id: String, mapper: (
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = SelectAllLineasVentaByVentaIdQuery(venta_id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3),
      cursor.getLong(4),
      cursor.getLong(5),
      cursor.getLong(6)!!,
      cursor.getDouble(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!
    )
  }

  public fun selectAllLineasVentaByVentaId(venta_id: String): Query<LineaVentaEntity> =
      selectAllLineasVentaByVentaId(venta_id) { id, venta_id_, Butaca_id, Complemento1_id,
      Complemento2_id, Complemento3_id, cantidad, precio, created_at, updated_at, is_deleted ->
    LineaVentaEntity(
      id,
      venta_id_,
      Butaca_id,
      Complemento1_id,
      Complemento2_id,
      Complemento3_id,
      cantidad,
      precio,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun <T : Any> selectAllLineasVentasByIsDeleted(is_deleted: Long, mapper: (
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = SelectAllLineasVentasByIsDeletedQuery(is_deleted) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3),
      cursor.getLong(4),
      cursor.getLong(5),
      cursor.getLong(6)!!,
      cursor.getDouble(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!
    )
  }

  public fun selectAllLineasVentasByIsDeleted(is_deleted: Long): Query<LineaVentaEntity> =
      selectAllLineasVentasByIsDeleted(is_deleted) { id, venta_id, Butaca_id, Complemento1_id,
      Complemento2_id, Complemento3_id, cantidad, precio, created_at, updated_at, is_deleted_ ->
    LineaVentaEntity(
      id,
      venta_id,
      Butaca_id,
      Complemento1_id,
      Complemento2_id,
      Complemento3_id,
      cantidad,
      precio,
      created_at,
      updated_at,
      is_deleted_
    )
  }

  public fun <T : Any> selectLineaVentaLastInserted(mapper: (
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
    is_deleted: Long,
  ) -> T): Query<T> = Query(503_588_145, arrayOf("LineaVentaEntity"), driver, "database.sq",
      "selectLineaVentaLastInserted",
      "SELECT LineaVentaEntity.id, LineaVentaEntity.venta_id, LineaVentaEntity.Butaca_id, LineaVentaEntity.Complemento1_id, LineaVentaEntity.Complemento2_id, LineaVentaEntity.Complemento3_id, LineaVentaEntity.cantidad, LineaVentaEntity.precio, LineaVentaEntity.created_at, LineaVentaEntity.updated_at, LineaVentaEntity.is_deleted FROM LineaVentaEntity WHERE id = last_insert_rowid()") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3),
      cursor.getLong(4),
      cursor.getLong(5),
      cursor.getLong(6)!!,
      cursor.getDouble(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!,
      cursor.getLong(10)!!
    )
  }

  public fun selectLineaVentaLastInserted(): Query<LineaVentaEntity> =
      selectLineaVentaLastInserted { id, venta_id, Butaca_id, Complemento1_id, Complemento2_id,
      Complemento3_id, cantidad, precio, created_at, updated_at, is_deleted ->
    LineaVentaEntity(
      id,
      venta_id,
      Butaca_id,
      Complemento1_id,
      Complemento2_id,
      Complemento3_id,
      cantidad,
      precio,
      created_at,
      updated_at,
      is_deleted
    )
  }

  public fun insertarbutaca(
    id: String,
    estado: String,
    tipo: String,
    precio: Long,
  ) {
    driver.execute(-1_189_083_184,
        """INSERT INTO ButacaEntity (id,estado,tipo,precio) VALUES (?,?,?,?)""", 4) {
          bindString(0, id)
          bindString(1, estado)
          bindString(2, tipo)
          bindLong(3, precio)
        }
    notifyQueries(-1_189_083_184) { emit ->
      emit("ButacaEntity")
    }
  }

  public fun updateButacaEntity(
    id: String,
    estado: String,
    tipo: String,
    precio: Long,
  ) {
    driver.execute(-365_232_238, """UPDATE ButacaEntity SET id=?, estado=?, tipo=?, precio=?""", 4)
        {
          bindString(0, id)
          bindString(1, estado)
          bindString(2, tipo)
          bindLong(3, precio)
        }
    notifyQueries(-365_232_238) { emit ->
      emit("ButacaEntity")
    }
  }

  public fun deleteAllButacaEntity() {
    driver.execute(-546_607_697, """DELETE FROM ButacaEntity""", 0)
    notifyQueries(-546_607_697) { emit ->
      emit("ButacaEntity")
    }
  }

  public fun deleteButacaId(id: String) {
    driver.execute(401_122_092, """DELETE FROM ButacaEntity WHERE id=?""", 1) {
          bindString(0, id)
        }
    notifyQueries(401_122_092) { emit ->
      emit("ButacaEntity")
    }
  }

  public fun updateComplementoEntity(
    id: Long,
    nombre: String,
    precio: Long,
    tipo: String,
  ) {
    driver.execute(634_510_139, """UPDATE ComplementoEntity SET id=?, nombre=?, precio=?, tipo=?""",
        4) {
          bindLong(0, id)
          bindString(1, nombre)
          bindLong(2, precio)
          bindString(3, tipo)
        }
    notifyQueries(634_510_139) { emit ->
      emit("ComplementoEntity")
    }
  }

  public fun deleteAllComplemetoEntity() {
    driver.execute(1_229_557_100, """DELETE FROM ComplementoEntity""", 0)
    notifyQueries(1_229_557_100) { emit ->
      emit("ComplementoEntity")
    }
  }

  public fun insertComplemento(
    tipo: String,
    nombre: String,
    precio: Long,
  ) {
    driver.execute(-1_635_732_152,
        """INSERT INTO ComplementoEntity(tipo,nombre,precio) VALUES(?,?,?)""", 3) {
          bindString(0, tipo)
          bindString(1, nombre)
          bindLong(2, precio)
        }
    notifyQueries(-1_635_732_152) { emit ->
      emit("ComplementoEntity")
    }
  }

  public fun deleteComplementoByID(id: Long) {
    driver.execute(-1_079_616_536, """DELETE FROM ComplementoEntity WHERE id=?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(-1_079_616_536) { emit ->
      emit("ComplementoEntity")
    }
  }

  public fun removeAllVentas() {
    driver.execute(1_063_584_060, """DELETE FROM VentaEntity""", 0)
    notifyQueries(1_063_584_060) { emit ->
      emit("VentaEntity")
    }
  }

  public fun insertVenta(
    id: String,
    cliente_id: String,
    total: Double,
    created_at: String,
    updated_at: String,
  ) {
    driver.execute(-1_965_663_285,
        """INSERT INTO VentaEntity (id, cliente_id, total, created_at, updated_at) VALUES (?, ?, ?, ?, ?)""",
        5) {
          bindString(0, id)
          bindString(1, cliente_id)
          bindDouble(2, total)
          bindString(3, created_at)
          bindString(4, updated_at)
        }
    notifyQueries(-1_965_663_285) { emit ->
      emit("VentaEntity")
    }
  }

  public fun updateVenta(
    cliente_id: String,
    total: Double,
    updated_at: String,
    is_deleted: Long,
    id: String,
  ) {
    driver.execute(1_859_006_395,
        """UPDATE VentaEntity SET cliente_id = ?, total = ?, updated_at = ?, is_deleted = ? WHERE id = ?""",
        5) {
          bindString(0, cliente_id)
          bindDouble(1, total)
          bindString(2, updated_at)
          bindLong(3, is_deleted)
          bindString(4, id)
        }
    notifyQueries(1_859_006_395) { emit ->
      emit("VentaEntity")
    }
  }

  public fun deleteVenta(id: String) {
    driver.execute(1_310_629_913, """DELETE FROM VentaEntity WHERE id = ?""", 1) {
          bindString(0, id)
        }
    notifyQueries(1_310_629_913) { emit ->
      emit("VentaEntity")
    }
  }

  public fun removeAllLineasVentas() {
    driver.execute(-1_694_907_326, """DELETE FROM LineaVentaEntity""", 0)
    notifyQueries(-1_694_907_326) { emit ->
      emit("LineaVentaEntity")
    }
  }

  public fun insertLineaVenta(
    id: String,
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    created_at: String,
    updated_at: String,
  ) {
    driver.execute(536_059_200,
        """INSERT INTO LineaVentaEntity (id, venta_id, Butaca_id, Complemento1_id, Complemento2_id, Complemento3_id, cantidad, precio, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""",
        10) {
          bindString(0, id)
          bindString(1, venta_id)
          bindString(2, Butaca_id)
          bindLong(3, Complemento1_id)
          bindLong(4, Complemento2_id)
          bindLong(5, Complemento3_id)
          bindLong(6, cantidad)
          bindDouble(7, precio)
          bindString(8, created_at)
          bindString(9, updated_at)
        }
    notifyQueries(536_059_200) { emit ->
      emit("LineaVentaEntity")
    }
  }

  public fun updateLineaVenta(
    venta_id: String,
    Butaca_id: String,
    Complemento1_id: Long?,
    Complemento2_id: Long?,
    Complemento3_id: Long?,
    cantidad: Long,
    precio: Double,
    updated_at: String,
    is_deleted: Long,
    id: String,
  ) {
    driver.execute(-965_558_448,
        """UPDATE LineaVentaEntity SET venta_id = ?, Butaca_id = ?, Complemento1_id= ?, Complemento2_id = ?, Complemento3_id = ?, cantidad = ?, precio = ?, updated_at = ?, is_deleted = ? WHERE id = ?""",
        10) {
          bindString(0, venta_id)
          bindString(1, Butaca_id)
          bindLong(2, Complemento1_id)
          bindLong(3, Complemento2_id)
          bindLong(4, Complemento3_id)
          bindLong(5, cantidad)
          bindDouble(6, precio)
          bindString(7, updated_at)
          bindLong(8, is_deleted)
          bindString(9, id)
        }
    notifyQueries(-965_558_448) { emit ->
      emit("LineaVentaEntity")
    }
  }

  public fun deleteLineaVenta(id: String) {
    driver.execute(-1_202_726_478, """DELETE FROM LineaVentaEntity WHERE id = ?""", 1) {
          bindString(0, id)
        }
    notifyQueries(-1_202_726_478) { emit ->
      emit("LineaVentaEntity")
    }
  }

  private inner class GetByIdButacaEntityQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ButacaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ButacaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(2_013_691_137,
        """SELECT ButacaEntity.id, ButacaEntity.estado, ButacaEntity.tipo, ButacaEntity.precio FROM ButacaEntity WHERE id = ?""",
        mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "database.sq:getByIdButacaEntity"
  }

  private inner class GetbutacaByTipoQuery<out T : Any>(
    public val tipo: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ButacaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ButacaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_164_191_945,
        """SELECT ButacaEntity.id, ButacaEntity.estado, ButacaEntity.tipo, ButacaEntity.precio FROM ButacaEntity WHERE tipo = ?""",
        mapper, 1) {
      bindString(0, tipo)
    }

    override fun toString(): String = "database.sq:getbutacaByTipo"
  }

  private inner class GetByIdComplemetoEntityQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ComplementoEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ComplementoEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-196_566_082,
        """SELECT ComplementoEntity.tipo, ComplementoEntity.id, ComplementoEntity.nombre, ComplementoEntity.precio FROM ComplementoEntity WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "database.sq:getByIdComplemetoEntity"
  }

  private inner class GetComplementoByTipoQuery<out T : Any>(
    public val tipo: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ComplementoEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ComplementoEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(534_687_270,
        """SELECT ComplementoEntity.tipo, ComplementoEntity.id, ComplementoEntity.nombre, ComplementoEntity.precio FROM ComplementoEntity WHERE tipo=?""",
        mapper, 1) {
      bindString(0, tipo)
    }

    override fun toString(): String = "database.sq:getComplementoByTipo"
  }

  private inner class SelectVentaByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("VentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("VentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_874_348_198,
        """SELECT VentaEntity.id, VentaEntity.cliente_id, VentaEntity.total, VentaEntity.created_at, VentaEntity.updated_at, VentaEntity.is_deleted FROM VentaEntity WHERE id = ?""",
        mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "database.sq:selectVentaById"
  }

  private inner class ExistsVentaQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("VentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("VentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(232_634_376,
        """SELECT COUNT(*) > 0 AS es_mayor_cero FROM VentaEntity WHERE id = ?""", mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "database.sq:existsVenta"
  }

  private inner class SelectAllVentasByIsDeletedQuery<out T : Any>(
    public val is_deleted: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("VentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("VentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_968_269_676,
        """SELECT VentaEntity.id, VentaEntity.cliente_id, VentaEntity.total, VentaEntity.created_at, VentaEntity.updated_at, VentaEntity.is_deleted FROM VentaEntity WHERE is_deleted = ?""",
        mapper, 1) {
      bindLong(0, is_deleted)
    }

    override fun toString(): String = "database.sq:selectAllVentasByIsDeleted"
  }

  private inner class SelectLineaVentaByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("LineaVentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("LineaVentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-800_158_155,
        """SELECT LineaVentaEntity.id, LineaVentaEntity.venta_id, LineaVentaEntity.Butaca_id, LineaVentaEntity.Complemento1_id, LineaVentaEntity.Complemento2_id, LineaVentaEntity.Complemento3_id, LineaVentaEntity.cantidad, LineaVentaEntity.precio, LineaVentaEntity.created_at, LineaVentaEntity.updated_at, LineaVentaEntity.is_deleted FROM LineaVentaEntity WHERE id = ?""",
        mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "database.sq:selectLineaVentaById"
  }

  private inner class SelectAllLineasVentaByVentaIdQuery<out T : Any>(
    public val venta_id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("LineaVentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("LineaVentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(854_080_871,
        """SELECT LineaVentaEntity.id, LineaVentaEntity.venta_id, LineaVentaEntity.Butaca_id, LineaVentaEntity.Complemento1_id, LineaVentaEntity.Complemento2_id, LineaVentaEntity.Complemento3_id, LineaVentaEntity.cantidad, LineaVentaEntity.precio, LineaVentaEntity.created_at, LineaVentaEntity.updated_at, LineaVentaEntity.is_deleted FROM LineaVentaEntity WHERE venta_id = ?""",
        mapper, 1) {
      bindString(0, venta_id)
    }

    override fun toString(): String = "database.sq:selectAllLineasVentaByVentaId"
  }

  private inner class SelectAllLineasVentasByIsDeletedQuery<out T : Any>(
    public val is_deleted: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("LineaVentaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("LineaVentaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-278_160_946,
        """SELECT LineaVentaEntity.id, LineaVentaEntity.venta_id, LineaVentaEntity.Butaca_id, LineaVentaEntity.Complemento1_id, LineaVentaEntity.Complemento2_id, LineaVentaEntity.Complemento3_id, LineaVentaEntity.cantidad, LineaVentaEntity.precio, LineaVentaEntity.created_at, LineaVentaEntity.updated_at, LineaVentaEntity.is_deleted FROM LineaVentaEntity WHERE is_deleted = ?""",
        mapper, 1) {
      bindLong(0, is_deleted)
    }

    override fun toString(): String = "database.sq:selectAllLineasVentasByIsDeleted"
  }
}
