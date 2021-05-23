package com.ganeshhulyal.notesapp.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.databinding.NoteItemBinding
import com.ganeshhulyal.notesapp.getDateInDisplayFormFromLong
import com.ganeshhulyal.notesapp.model.Note
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private val context: Context, private val list: List<Note>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {
    private  var filteredList: List<Note> = arrayListOf()
            init{
                filteredList=list
            }

    class ViewHolder(
        itemView: View,
        val binding: NoteItemBinding = NoteItemBinding.bind(itemView),
    ) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = filteredList[position]
        Log.d(TAG, "onBindViewHolder: " + note.title)
        holder.binding.title.text = note.title
        holder.binding.description.text = note.description
        holder.binding.date.text = note.createTimeStamp.getDateInDisplayFormFromLong()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredList = list
                } else {
                    val resultList = ArrayList<Note>()
                    for (row in list) {
                        if (row.title?.toLowerCase(Locale.ROOT)!!
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Note>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
}