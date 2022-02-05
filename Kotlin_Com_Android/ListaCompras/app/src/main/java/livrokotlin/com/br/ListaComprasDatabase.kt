package livrokotlin.com.br

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class ListaComprasDatabase (context: Context) : ManagedSQLiteOpenHelper
    (ctx = context, name = "listaCompras.db", version = 1){
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}