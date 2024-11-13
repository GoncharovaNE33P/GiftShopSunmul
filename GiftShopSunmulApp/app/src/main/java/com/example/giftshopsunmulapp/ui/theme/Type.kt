package com.example.giftshopsunmulapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.giftshopsunmulapp.R

/*
@OptIn(ExperimentalTextApi::class)
private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val NameFont1 = GoogleFont("Jersey 10")
val NameFont2 = GoogleFont("Montserrat Alternates")
@OptIn(ExperimentalTextApi::class)
val Jersey_10_fontFamily = FontFamily(
    Font(googleFont = NameFont1, fontProvider = fontProvider)
)
val Montserrat_Alternates_fontFamily = FontFamily(
    Font(googleFont = NameFont2, fontProvider = fontProvider)
)

// Set of Material typography styles to start with
val typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = Jersey_10_fontFamily
    ),

    bodyLarge = TextStyle(
        fontFamily = Jersey_10_fontFamily
    )
)*/

@OptIn(ExperimentalTextApi::class)
private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val NameFont1 = GoogleFont("Jura")
val NameFont2 = GoogleFont("Montserrat Alternates")
@OptIn(ExperimentalTextApi::class)
val fontFamily1 = FontFamily(
    Font(googleFont = NameFont1, fontProvider = fontProvider)
)
val fontFamily2 = FontFamily(
    Font(googleFont = NameFont2, fontProvider = fontProvider)
)

// Set of Material typography styles to start with
val typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fontFamily1,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
