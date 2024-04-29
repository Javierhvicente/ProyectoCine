package org.example.productos.complementos.cache

import org.example.cache.CacheImpl
import org.example.productos.models.Complemento
/**
 * Cache de complementos
 * Hereda de CacheImpl y le pasamos los genericos que vamos a necesitar
 * @see CacheImpl
 * @author Yahya el hadri el bakkali
 * @since 1.0
 **/
class ComplementoCacheImpl(size:Int):CacheImpl<String,Complemento>(size) {
}