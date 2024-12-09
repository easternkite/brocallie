package com.blucky8649.createcallie

import java.util.Locale

actual fun getLanguageCode(): String = Locale.getDefault().language
