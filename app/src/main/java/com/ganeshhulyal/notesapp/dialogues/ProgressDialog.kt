package com.ganeshhulyal.notesapp.dialogues

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.databinding.LoadingDailougeBinding

class ProgressDialog(context: Context,val message:String) : Dialog(context, R.style.FullScreenTransparentDialog) {

    private lateinit var binding  : LoadingDailougeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingDailougeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.message.text = message
        setCancelable(false)
    }
}