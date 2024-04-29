package database

import kotlin.Long
import kotlin.String

public data class ComplementoEntity(
  public val tipo: String,
  public val id: Long,
  public val nombre: String,
  public val precio: Long,
)
