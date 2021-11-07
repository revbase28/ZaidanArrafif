package com.revbase.zaidanarrafif.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.revbase.zaidanarrafif.common.Constant.AS_STUDENT
import com.revbase.zaidanarrafif.common.Constant.AS_TEACHER
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Guru
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceManager(val context: Context) {
    private val Context.prefDataStore: DataStore<Preferences> by preferencesDataStore("userPref")
    private val dataStore = context.prefDataStore

    private val TOKEN = stringPreferencesKey("token")
    private val USERTYPE = stringPreferencesKey("userType")
    private val NIS = stringPreferencesKey("nis")
    private val NAME = stringPreferencesKey("name")
    private val USERNAME = stringPreferencesKey("username")
    private val BIRTH_DATE = stringPreferencesKey("birth_date")
    private val ENROLL_YEAR = intPreferencesKey("enroll_year")
    private val STARS = intPreferencesKey("stars")
    private val NICKNAME = stringPreferencesKey("nickname")
    private val CLASS = stringPreferencesKey("class")
    private val WALI = stringPreferencesKey("wali")
    private val GURU_SETORAN = stringPreferencesKey("guru_setoran")
    private val NIP = stringPreferencesKey("nip")

    suspend fun storeStudentData(studentData: Siswa, token: String) {
        dataStore.edit { pref ->
            pref[TOKEN] = token
            pref[USERTYPE] = AS_STUDENT
            pref[NIS] = studentData.nis.toString()
            pref[NAME] = studentData.nama_siswa
            pref[USERNAME] = studentData.username
            pref[BIRTH_DATE]= studentData.tanggal_lahir
            pref[ENROLL_YEAR] = studentData.tahun_masuk
            pref[NICKNAME] = studentData.nama_panggilan
            pref[CLASS] = studentData.kelas
            pref[STARS] = studentData.stars
            pref[WALI] = studentData.walikelas
            pref[GURU_SETORAN] = studentData.guru_setoran
        }
    }

    fun getToken(): Flow<String> = dataStore.data.map { pref ->
        pref[TOKEN] ?: ""
    }

    fun getUserType(): Flow<String> = dataStore.data.map { pref ->
        pref[USERTYPE] ?: ""
    }

    fun getStudentDataFromPreferences(): Flow<Siswa> = dataStore.data.map { pref ->
        Siswa(
            nis = pref[NIS]?.toInt() ?: 0,
            nama_siswa = pref[NAME] ?: "",
            nama_panggilan = pref[NICKNAME] ?: "",
            kelas = pref[CLASS] ?: "",
            stars = pref[STARS] ?: -1,
            walikelas = pref[WALI] ?: "",
            guru_setoran = pref[GURU_SETORAN] ?: "",
            tahun_masuk = pref[ENROLL_YEAR] ?: 0,
            tanggal_lahir = pref[BIRTH_DATE] ?: "",
            username = pref[USERNAME] ?: ""
        )
    }

    suspend fun storeTeacherData(teacherData: Guru, token: String) {
        dataStore.edit { pref ->
            pref[TOKEN] = token
            pref[USERTYPE] = AS_TEACHER
            pref[NAME] = teacherData.nama_guru
            pref[NIP] = teacherData.nip.toString()
        }
    }

    fun getTeacherData(): Flow<Guru> = dataStore.data.map { pref ->
        Guru(
            nama_guru = pref[NAME] ?: "",
            nip = pref[NIP]?.toInt() ?: 0,
            username = pref[USERNAME] ?: ""
        )
    }

    fun getNip(): Flow<Int> = dataStore.data.map { pref ->
        pref[NIP]?.toInt() ?: 0
    }

    suspend fun cleanPref() {
        dataStore.edit {
            it.clear()
        }
    }
}