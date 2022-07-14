package com.picpay.desafio.android.ui.contacts

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity(R.layout.activity_contacts) {

    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bindListeners()
    }

    private fun bindListeners() {
        viewModel.contactsState.observe(this) {
            when (it) {
                is ContactsState.Success -> {
                    progressBar.visibility = View.GONE

                    adapter.users = it.data
                }

                is ContactsState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is ContactsState.Error -> {
                    val message = getString(R.string.error)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@ContactsActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
