package com.example.challenge2.Activity

import android.content.Context
import android.content.SharedPreferences

class SaveData(mContext: Context) {
    private var preferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init{
        preferences = mContext.getSharedPreferences("dummy", 0)
        editor = preferences.edit()
    }

    fun setString(key:String, value: String){
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String{
        return preferences.getString(key, "")
    }
}