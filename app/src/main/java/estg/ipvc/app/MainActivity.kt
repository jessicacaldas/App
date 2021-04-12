package estg.ipvc.app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import estg.ipvc.app.adapter.NotaAdapter
import estg.ipvc.app.entities.Nota
import estg.ipvc.app.viewModel.NotaViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycleview)
        val adapter = NotaAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //view model
        NotaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        NotaViewModel.allNotas.observe(this, { notas ->
            notas?.let{adapter.setNotas(it)}
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newNotaActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AddNota.EXTRA_REPLY)?.let {
                val nota = Nota(nota = buraco)
                NotaViewModel.insert(nota)
            }
        }else{
            Toast.makeText(
                    applicationContext,
                    "Nota vazia: não inserida",
                    Toast.LENGTH_LONG).show()

        }
    }
}