package com.example.agriapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agriapp.data.local.dao.EmpresaDao
import com.example.agriapp.data.local.dao.FundoDao
import com.example.agriapp.data.local.dao.UsuarioDao
import com.example.agriapp.data.local.dao.ZonaDao
import com.example.agriapp.data.local.entity.EmpresaEntity
import com.example.agriapp.data.local.entity.FundoEntity
import com.example.agriapp.data.local.entity.UsuarioEntity
import com.example.agriapp.data.local.entity.UsuarioFundoCrossRef
import com.example.agriapp.data.local.entity.ZonaEntity
import com.example.agriapp.util.Constants

@Database(entities = [
    EmpresaEntity::class,
    FundoEntity::class,
    ZonaEntity::class,
    UsuarioEntity::class,
    UsuarioFundoCrossRef::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun empresaDao(): EmpresaDao
    abstract fun fundoDao(): FundoDao
    abstract fun zonaDao(): ZonaDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}