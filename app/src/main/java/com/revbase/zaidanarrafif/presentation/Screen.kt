package com.revbase.zaidanarrafif.presentation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object StudentMainScreen: Screen("main_student_screen")
    object QuranScreen: Screen("quran_screen")
    object SurahScreen: Screen("surah_screen")
    object HafalanScreen: Screen("hafalan_screen")
    object JurnalScreen: Screen("jurnal_screen")
    object RankScreen: Screen("rank_screen")
    object ProfileScreen: Screen("profile_screen")
}
