package com.revbase.zaidanarrafif.data.remote.quran.dto

// For transliteration there is two kind of value for one key from the api response
// To accommodate all the possibilities of value provided by the api, i decided to make translation have Any Type
// transliteration Type can either be Transliteration or TransliterationEnId

data class Name(
    val long: String,
    val short: String,
    val translation: Translation,
    val transliteration: Transliteration
)