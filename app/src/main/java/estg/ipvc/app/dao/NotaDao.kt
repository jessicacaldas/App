package estg.ipvc.app.dao

import androidx.app.Query
import androidx.lifecycle.LiveData
import androidx.room.Query
import estg.ipvc.app.entities.Nota


interface NotaDao {
    @Query("SELECT * FROM nota_table ORDER BY nota ASC")
    fun getAlphabetizedNotas(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()



}



