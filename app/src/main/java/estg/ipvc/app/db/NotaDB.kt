package estg.ipvc.app.db

import android.content.Context
import estg.ipvc.app.dao.NotaDao
import estg.ipvc.app.entities.Nota

@DataBase(entities = arrayOf(Nota::class), version = 5 , exportSchema = false)
public abstract class NotaDB: RoomDataBase(){

    abstract fun notaDao(): NotaDao

    private class NotaDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLLiteDatabase){
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var NotaDao = database.notaDao()

                    var nota = Nota("Buraco no estrada")
                    NotaDao.insert(nota)
                    nota = Nota("Tampa de esgoto deslocada")
                    NotaDao.insert(nota)
                }

            }
        }

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
                )
                //estrategia de destrucao
                        .fallbackToDestructiveMigration()
                        .addCallback(NotaDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

}