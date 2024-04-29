package database

import kotlin.Long
import kotlin.String

public data class Clientes(
  public val id: String,
  public val nombre: String,
  public val is_deleted: Long,
)
