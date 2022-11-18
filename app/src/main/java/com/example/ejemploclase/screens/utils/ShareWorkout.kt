package com.example.ejemploclase.screens.utils

import android.content.Context
import android.content.Intent
import com.example.ejemploclase.R

fun shareWorkout(context: Context,workoutId: Int) {
    val appName = context.getString(R.string.app_name)
    val shareBodyText = "Check out this workout!\n\nwww.hercules.com/workout/${workoutId}"

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, appName)
        putExtra(Intent.EXTRA_TEXT, shareBodyText)
    }
    context.startActivity(Intent.createChooser(sendIntent, null))
}