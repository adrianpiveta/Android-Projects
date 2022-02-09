package livrokotlin.com.br

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/*
* Em banco de dados devemos criar uma unica instancia
* para evitar multiplas operações no banco, de forma a evitar corrupção*/
class ListaComprasDatabase (context: Context) : ManagedSQLiteOpenHelper
    (ctx = context, name = "listaCompras.db", version = 1){


    //singleton da classe
    companion object{
        private var instance: ListaComprasDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ListaComprasDatabase{
            if(instance==null){
                instance = ListaComprasDatabase(ctx.applicationContext)
            }
            return instance!!
        }

    }

    //é rodado quando abre, caso não detecte o BD
    override fun onCreate(db: SQLiteDatabase?) {
        //ifNotExists:	 Boolean	 =	 false, irá criar a tabela somente se não existir,
        // usamos o verdadeiro para evitar recriar caso exista, no caso
        if (db != null) {
            db.createTable("produtos", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,//id será integer, chave primaria e unico
                "nome" to TEXT,
                "quantidade" to INTEGER,
                "valor" to REAL,
                "foto" to BLOB)
        }
    }

    // rodado ao atualizar banco, criar mais uma tabela, por exemplo
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}

val Context.database: ListaComprasDatabase
    get() = ListaComprasDatabase.getInstance(getApplicationContext())