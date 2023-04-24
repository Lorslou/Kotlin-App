package com.multimedia.kotlin_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
 * Clase que permite la generación de código de Hilt, ésta se incorpora al ciclo de vida de Application y le
 * proporciona dependencias, permitiendo que otros componentes puedan acceder a las mismas
 */
@HiltAndroidApp
class DaggerHiltApp: Application()