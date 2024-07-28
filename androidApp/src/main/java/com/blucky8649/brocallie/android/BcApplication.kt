package com.blucky8649.brocallie.android

import android.app.Application
import com.google.firebase.FirebaseApp

class BcApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}