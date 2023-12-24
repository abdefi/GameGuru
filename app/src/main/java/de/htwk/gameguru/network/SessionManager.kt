package de.htwk.gameguru.network

import android.content.Context
import android.content.SharedPreferences
import de.htwk.gameguru.R

class SessionManager (private val context: Context) {
    private var settings: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val TOKEN = "user_token"
    }
    fun getToken(): String? {
        return settings.getString(TOKEN, null)
    }

    fun saveToken(token: String) {
        val editor = settings.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }
}