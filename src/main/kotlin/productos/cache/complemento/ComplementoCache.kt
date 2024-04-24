package org.example.productos.cache.complemento

import org.example.cache.CacheImpl
import org.example.productos.models.Complemento

class ComplementoCache(size:Int):CacheImpl<String,Complemento>(size) {
}