package estg.ipvc.app.db

import android.content.Context
import estg.ipvc.app.dao.NotaDao
import estg.ipvc.app.entities.Nota


class NotaDB {


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var INSTANCE: NotaDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotaDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotaDB::class.java,
                        "nota_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

}