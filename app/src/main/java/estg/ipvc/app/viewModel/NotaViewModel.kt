package estg.ipvc.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import estg.ipvc.app.db.NotaDB
import estg.ipvc.app.db.NotaRepository
import estg.ipvc.app.entities.Nota

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotaRepository
    val allNotas: LiveData<List<Nota>>

    init{
        val notasDao = NotaDB.getDatabase(application, viewModelScope).NotaDao()
        repository = NotaRepository(notasDao)
        allNotas =  repository.allNotas
    }

    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(nota)
    }

}