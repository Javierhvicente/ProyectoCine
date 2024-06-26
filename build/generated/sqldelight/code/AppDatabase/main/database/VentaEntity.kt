package database

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class VentaEntity(
  public val id: String,
  public val cliente_id: String,
  public val total: Double,
  public val created_at: String,
  public val updated_at: String,
  public val is_deleted: Long,
)
