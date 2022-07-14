package com.picpay.desafio.android.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.business.ContactsBusiness
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactsViewModel(private val contactsBusiness: ContactsBusiness): ViewModel() {
    val contactsState: MutableLiveData<ContactsState> by lazy {
        MutableLiveData<ContactsState>()
    }

    init {
        getUsers()
    }
    private fun getUsers(){
        contactsState.value = ContactsState.Loading
        viewModelScope.launch{
            contactsBusiness.getUsers().collect{
                if(it.isNotEmpty()){
                    contactsState.value = ContactsState.Success(it)
                }else{
                    contactsState.value = ContactsState.Error
                }
            }
        }
    }
}