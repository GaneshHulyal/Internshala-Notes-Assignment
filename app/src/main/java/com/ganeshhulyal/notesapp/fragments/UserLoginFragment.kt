package com.ganeshhulyal.notesapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.constants.Constants
import com.ganeshhulyal.notesapp.constants.Constants.Companion.RC_SIGN_IN
import com.ganeshhulyal.notesapp.databinding.FragmentUserLoginBinding
import com.ganeshhulyal.notesapp.dialogues.ProgressDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class UserLoginFragment : Fragment() {
    private lateinit var binding: FragmentUserLoginBinding
    private lateinit var signInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var dialogue: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSignIn()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setupGoogleLogin()
    }

    private fun setupGoogleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun showDialogue(context: Context, msg: String) {
        dialogue = ProgressDialog(context, msg)
        dialogue.show()
    }

    private fun initSignIn() {
        binding.googleSignInButton.setOnClickListener {
            showDialogue(requireContext(), "Please wait...")
            login()
        }
    }

    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        dialogue.cancel()
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), getString(R.string.login_successfull_msg), Toast.LENGTH_SHORT).show()
                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fragment_container,HomeFragment())
                    commit()
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.login_failed_msg), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}