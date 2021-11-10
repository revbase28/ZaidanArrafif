package com.revbase.zaidanarrafif.presentation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object StudentMainScreen: Screen("main_student_screen")
    object TeacherMainScreen: Screen("main_teacher_screen")
    object QuranScreen: Screen("quran_screen")
    object SurahScreen: Screen("surah_screen")
    object HafalanScreen: Screen("hafalan_screen")
    object JournalMainScreen: Screen("journal_main_screen")
    object ActivityJournalScreen: Screen("activity_journal_screen")
    object RankScreen: Screen("rank_screen")
    object ProfileScreen: Screen("profile_screen")

    object TeacherJournalScreen: Screen("teacher_journal_screen")
    object TeacherProfileScreen: Screen("teacher_profile_screen")
    object TeacherActivityDetailScreen: Screen("teacher_activity_detail_screen")
    object TeacherHafalanScreen: Screen("teacher_hafalan_screen")
    object HafalanFeedbackScreen: Screen("hafalan_feedback_screen")
    object ListStudentScreen: Screen("list_student_screen")
}
