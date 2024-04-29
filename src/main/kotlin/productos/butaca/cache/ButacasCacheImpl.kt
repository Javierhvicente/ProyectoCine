package org.example.productos.butaca.cache

import org.example.cache.CacheImpl
import org.example.productos.models.Butaca

/**
 * Cache de butacas
 * Hereda de CacheImpl y le pasamos los genericos que vamos a necesitar
 * @see CacheImpl
 * @author Yahya el hadri el bakkali
 * @since 1.0
 **/
class ButacasCacheImpl(size:Int):CacheImpl<String,Butaca>(size) {
}