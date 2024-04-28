package org.example.productos.butaca.cache

import org.example.cache.CacheImpl
import org.example.productos.models.Butaca

class ButacasCacheImpl(size:Int):CacheImpl<String,Butaca>(size) {
}