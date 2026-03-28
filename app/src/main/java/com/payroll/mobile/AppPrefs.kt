package com.payroll.mobile

import android.content.Context

object AppPrefs {
    private const val PREF_NAME = "payroll_mobile_prefs"
    private const val KEY_SERVER_URL = "server_url"

    fun getServerUrl(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_SERVER_URL, "")?.trim().orEmpty()
    }

    fun setServerUrl(context: Context, url: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_SERVER_URL, normalizeUrl(url)).apply()
    }

    fun hasServerUrl(context: Context): Boolean {
        return getServerUrl(context).isNotBlank()
    }

    fun normalizeUrl(raw: String): String {
        return raw.trim().trimEnd('/')
    }

    fun isValidHttpsUrl(raw: String): Boolean {
        val value = normalizeUrl(raw)
        if (value.isBlank()) return false
        if (!value.startsWith("https://", ignoreCase = true)) return false

        return try {
            val uri = android.net.Uri.parse(value)
            !uri.host.isNullOrBlank() && uri.scheme.equals("https", ignoreCase = true)
        } catch (_: Exception) {
            false
        }
    }
}
