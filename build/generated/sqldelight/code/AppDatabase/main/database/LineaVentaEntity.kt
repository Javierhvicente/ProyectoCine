package database

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class LineaVentaEntity(
  public val id: String,
  public val venta_id: String,
  public val Butaca_id: String,
  public val Complemento1_id: Long?,
  public val Complemento2_id: Long?,
  public val Complemento3_id: Long?,
  public val cantidad: Long,
  public val precio: Double,
  public val created_at: String,
  public val updated_at: String,
  public val is_deleted: Long,
)
