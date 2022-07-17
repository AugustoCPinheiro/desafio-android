package com.picpay.desafio.android.ui.contacts

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityContactsBinding
import com.picpay.desafio.android.ui.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity(R.layout.activity_contacts) {

    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var adapter: UserListAdapter

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)

        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter

        bindListeners()
    }

    private fun bindListeners() {
        viewModel.contactsState.observe(this) {
            when (it) {
                is ContactsState.Success -> {
                    binding.userListProgressBar.visibility = View.GONE

                    adapter.users = it.data
                }

                is ContactsState.Loading -> {
                    binding.userListProgressBar.visibility = View.VISIBLE
                }

                is ContactsState.Error -> {
                    val message = getString(R.string.error)

                    binding.userListProgressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE

                    Toast.makeText(this@ContactsActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
