package com.multimedia.kotlin_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Class that enables the generation of Hilt code, it is incorporated into the Application's lifecycle
 * and provides dependencies, allowing other components to access them
 */
@HiltAndroidApp
class DaggerHiltApp: Application()