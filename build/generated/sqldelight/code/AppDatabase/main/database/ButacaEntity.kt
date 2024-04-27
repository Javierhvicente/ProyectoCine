package database

import kotlin.Long
import kotlin.String

public data class ButacaEntity(
  public val id: String,
  public val estado: String,
  public val tipo: String,
  public val precio: Long,
)
