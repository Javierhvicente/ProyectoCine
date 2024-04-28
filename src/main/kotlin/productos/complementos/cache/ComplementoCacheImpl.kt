package org.example.productos.complementos.cache

import org.example.cache.CacheImpl
import org.example.productos.models.Complemento

class ComplementoCacheImpl(size:Int):CacheImpl<String,Complemento>(size) {
}