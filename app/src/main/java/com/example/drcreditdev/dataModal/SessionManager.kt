package com.example.drcreditdev.dataModal
import android.content.Context
import android.content.SharedPreferences;
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("drFile", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val CREDIT_SCORE = "user_credit"
    }
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()

        editor.putString(USER_TOKEN, token)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun saveCreditScore(creditScore: String)
    {
        val editor = prefs.edit()
        editor.putString(CREDIT_SCORE, creditScore)
        editor.apply()
    }
}