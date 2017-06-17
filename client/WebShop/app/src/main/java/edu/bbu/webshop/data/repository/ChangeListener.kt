package edu.bbu.webshop.data.repository

/**
 * Purpose
 * <p>
 * Description
 * <p/>
 * Notes:
 * @author (OPTIONAL! Use only if the code is complex, otherwise delete this line.)
 */
interface ChangeListener<T> {
    fun onChange(var1: T)

    fun onError(var1: Exception)
}