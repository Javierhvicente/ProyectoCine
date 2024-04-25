package org.example.cache

import org.example.cache.errors.CacheError
import com.github.michaelbull.result.Result
<<<<<<< HEAD

interface Cache<K, T> {
    fun get(key: K): Result<T,CacheError>
=======
interface Cache<K, T> {
    fun get(key: K): Result<T, CacheError>
>>>>>>> feature/serviceCliente
    fun put(key: K, value: T): Result<T, Nothing>
    fun remove(key: K): Result<T, CacheError>
    fun clear(): Result<Unit, Nothing>
}