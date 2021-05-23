package com.ganeshhulyal.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.ViewModel.ViewModel
import com.ganeshhulyal.notesapp.databinding.FragmentCreateNoteBinding
import com.ganeshhulyal.notesapp.model.Note

class CreateNoteFragment : Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.storeNote.setOnClickListener {
            getData()
        }

        binding.backButton.setOnClickListener {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    private fun getData() {
        val title = binding.titleEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val email = "ganeshhulyal@gmail.com"
        val currentTimestamp = System.currentTimeMillis()
        val note = Note(null, email, title, description, currentTimestamp)
        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        viewModel.addNotes(note)
        Toast.makeText(
            requireContext(),
            getString(R.string.note_added_successfully),
            Toast.LENGTH_SHORT
        ).show()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_container, HomeFragment())
            commit()
        }
    }
}