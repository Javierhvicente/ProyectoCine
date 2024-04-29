package org.example.locale

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
/**
 *pasar local datetime a español
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun LocalDateTime.toDefaultDateTimeString():String{
    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(this)
}
/**
 *pasar double a español
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun Double.toDefaultMoneyString():String{
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
}