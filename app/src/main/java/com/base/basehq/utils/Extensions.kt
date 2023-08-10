package com.base.basehq.utils

import java.util.*

fun String.capitalise(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else {
            it.toString()
        }
    }
}
