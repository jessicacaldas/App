package estg.ipvc.app.db

import androidx.lifecycle.LiveData
import estg.ipvc.app.dao.NotaDao
import estg.ipvc.app.entities.Nota

class NotaRepository (private val notaDao: NotaDao)  {
    val allNotas: LiveData<List<Nota>> = NotaDao.getAlpabetizedNotas()

    suspend fun insert(nota: Nota) {
        notaDao.insert(nota)
    }

}