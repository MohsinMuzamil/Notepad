package com.mohsin.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohsin.notesapp.R
import com.mohsin.notesapp.data.Note
import com.mohsin.notesapp.data.NoteDatabase
import com.mohsin.notesapp.repository.NoteRepository
import com.mohsin.notesapp.utils.NoteViewModelFactory
import com.mohsin.notesapp.viewmodel.NoteViewModel
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = NoteDatabase.getDatabase(this).noteDao()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        val viewModel: NoteViewModel by viewModels { factory }
        val noteCountText = findViewById<TextView>(R.id.tvNoteCount)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = NoteAdapter(emptyList(), onDelete = { note ->
            AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(note) }
                .setNegativeButton("Cancel", null)
                .show()
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allNotes.observe(this) { notes ->
            val noteList = notes ?: emptyList()
            adapter.updateNotes(noteList)
            noteCountText.text = "${noteList.size} notes"
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredNotes = viewModel.allNotes.value?.filter { note ->
                    note.title.contains(newText ?: "", ignoreCase = true) ||
                            note.content.contains(newText ?: "", ignoreCase = true)
                } ?: emptyList()
                adapter.updateNotes(filteredNotes)
                noteCountText.text = "${filteredNotes.size} notes"
                return true
            }
        })

        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null)
            val editTitle = dialogView.findViewById<EditText>(R.id.editTitle)
            val editContent = dialogView.findViewById<EditText>(R.id.editContent)

            AlertDialog.Builder(this)
                .setTitle("New Note")
                .setView(dialogView)
                .setPositiveButton("Add") { dialog, _ ->
                    val title = editTitle.text.toString().trim()
                    val content = editContent.text.toString().trim()
                    if (title.isNotBlank() && content.isNotBlank()) {
                        viewModel.insert(Note(title = title, content = content))
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                .show()
        }
    }
}