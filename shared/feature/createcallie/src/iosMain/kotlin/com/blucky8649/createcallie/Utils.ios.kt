package com.blucky8649.createcallie

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun getLanguageCode(): String = NSLocale.currentLocale.languageCode