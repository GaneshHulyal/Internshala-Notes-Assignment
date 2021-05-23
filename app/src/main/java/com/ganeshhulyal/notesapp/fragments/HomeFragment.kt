package com.ganeshhulyal.notesapp.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.ViewModel.ViewModel
import com.ganeshhulyal.notesapp.adapter.RecyclerViewAdapter
import com.ganeshhulyal.notesapp.databinding.FragmentHomeBinding
import com.ganeshhulyal.notesapp.model.Note
import com.google.android.gms.common.data.DataHolder


class HomeFragment : Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteList: List<Note>
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteList = arrayListOf();
        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        initAdapter()
        initFilter()
        setOnClickListeners()
    }

    private fun initFilter() {
        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }


    private fun setOnClickListeners() {
        binding.addNotes.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container,CreateNoteFragment())
                addToBackStack("home")
                commit()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initAdapter() {
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getAllNotes().observe(requireActivity(), Observer<List<Note>>() {
            noteList = it
            adapter = RecyclerViewAdapter(mContext, noteList)
            binding.notesRecyclerView.adapter = adapter
        })
    }

}