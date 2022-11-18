package com.example.ejemploclase.screens.utils

import android.content.Context
import android.content.Intent
import com.example.ejemploclase.BuildConfig
import com.example.ejemploclase.R

fun shareApp(context: Context) {
    val appPackageName = BuildConfig.APPLICATION_ID
    val appName = context.getString(R.string.app_name)
    val shareBodyText = "https://play.google.com/store/apps/details?id=$appPackageName"

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, appName)
        putExtra(Intent.EXTRA_TEXT, shareBodyText)
    }
    context.startActivity(Intent.createChooser(sendIntent, null))
}