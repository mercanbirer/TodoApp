package com.example.todoapp.extension

import android.content.Context
import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * TODO
 *  - divide sharedPref for each portal
 */
inline fun <reified T> saveSharedPref(sharedPrefName: String, context: Context, sharedPrefKey: String, t: T): T {
    val sharedPrefs = getPreferences(sharedPrefName, context)
    val jsonString = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }.encodeToString(t)

    sharedPrefs.edit().let {
        it.putString(sharedPrefKey, jsonString)
        it.apply()
    }

    return t
}

inline fun <reified T> getSharedPref(sharedPrefName: String, context: Context, sharedPrefKey: String): T {
    val sharedPrefs = getPreferences(sharedPrefName, context)
    val jsonString = sharedPrefs.getString(sharedPrefKey, "")

    return Json {
        isLenient = true
        ignoreUnknownKeys = true
    }.decodeFromString(jsonString!!)
}

fun deleteSharedPref(sharedPrefName: String, context: Context, sharedPrefKey: String) {
    val sharedPrefs = getPreferences(sharedPrefName, context)
    sharedPrefs.edit().remove(sharedPrefKey).apply()
}

fun getPreferences(sharedPrefName: String, context: Context): SharedPreferences {
    return context.getSharedPreferences(sharedPrefName, 0)
}
