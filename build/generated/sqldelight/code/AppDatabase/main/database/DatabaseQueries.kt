package database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
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
    id: Long,
    nombre: String,
    precio: Long,
  ) -> T): Query<T> = Query(1_265_361_777, arrayOf("ComplemetoEntity"), driver, "database.sq",
      "getAllComplemetoEntity",
      "SELECT ComplemetoEntity.id, ComplemetoEntity.nombre, ComplemetoEntity.precio FROM ComplemetoEntity") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!
    )
  }

  public fun getAllComplemetoEntity(): Query<ComplemetoEntity> = getAllComplemetoEntity { id,
      nombre, precio ->
    ComplemetoEntity(
      id,
      nombre,
      precio
    )
  }

  public fun <T : Any> getByIdComplemetoEntity(id: Long, mapper: (
    id: Long,
    nombre: String,
    precio: Long,
  ) -> T): Query<T> = GetByIdComplemetoEntityQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!
    )
  }

  public fun getByIdComplemetoEntity(id: Long): Query<ComplemetoEntity> =
      getByIdComplemetoEntity(id) { id_, nombre, precio ->
    ComplemetoEntity(
      id_,
      nombre,
      precio
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
  ) {
    driver.execute(634_510_139, """UPDATE ComplemetoEntity SET id=?, nombre=?, precio=?""", 3) {
          bindLong(0, id)
          bindString(1, nombre)
          bindLong(2, precio)
        }
    notifyQueries(634_510_139) { emit ->
      emit("ComplemetoEntity")
    }
  }

  public fun deleteAllComplemetoEntity() {
    driver.execute(1_229_557_100, """DELETE FROM ComplemetoEntity""", 0)
    notifyQueries(1_229_557_100) { emit ->
      emit("ComplemetoEntity")
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
      driver.addListener("ComplemetoEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ComplemetoEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-196_566_082,
        """SELECT ComplemetoEntity.id, ComplemetoEntity.nombre, ComplemetoEntity.precio FROM ComplemetoEntity WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "database.sq:getByIdComplemetoEntity"
  }
}
