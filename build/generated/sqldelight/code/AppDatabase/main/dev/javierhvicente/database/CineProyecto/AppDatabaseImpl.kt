package dev.javierhvicente.database.CineProyecto

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import database.DatabaseQueries
import dev.javierhvicente.database.AppDatabase
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), AppDatabase {
  override val databaseQueries: DatabaseQueries = DatabaseQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS clientes(
          |    id TEXT PRIMARY KEY NOT NULL,
          |    nombre TEXT NOT NULL,
          |    is_deleted INTEGER NOT NULL DEFAULT 0
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS ButacaEntity(
          |    id TEXT PRIMARY KEY NOT NULL,
          |    estado TEXT NOT NULL,
          |    tipo TEXT NOT NULL,
          |    precio INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS ComplementoEntity(
          |    tipo TEXT NOT NULL ,
          |    id INTEGER PRIMARY KEY NOT NULL,
          |    nombre TEXT NOT NULL,
          |    precio INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS VentaEntity (
          |    id TEXT PRIMARY KEY,
          |    cliente_id TEXT NOT NULL REFERENCES clientes(id),
          |    total REAL NOT NULL,
          |    created_at TEXT NOT NULL,
          |    updated_at TEXT NOT NULL,
          |    is_deleted INTEGER NOT NULL DEFAULT 0
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS LineaVentaEntity (
          |     id TEXT PRIMARY KEY,
          |     venta_id TEXT NOT NULL REFERENCES VentaEntity(id),
          |     Butaca_id TEXT NOT NULL REFERENCES ButacaEntity(id),
          |     Complemento1_id INTEGER REFERENCES ComplementoEntity(id) DEFAULT NULL,
          |     Complemento2_id INTEGER REFERENCES ComplementoEntity(id) DEFAULT NULL,
          |     Complemento3_id INTEGER REFERENCES ComplementoEntity(id) DEFAULT NULL,
          |     cantidad INTEGER NOT NULL,
          |     precio REAL NOT NULL,
          |     created_at TEXT NOT NULL,
          |     updated_at TEXT NOT NULL,
          |     is_deleted INTEGER NOT NULL DEFAULT 0
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
