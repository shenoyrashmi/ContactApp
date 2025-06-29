package com.app.contactapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.contactapp.entity.Contact
import com.app.contactapp.repository.ContactRepository
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    val allContacts: LiveData<List<Contact>> = contactRepository.allcontact.asLiveData()

    fun addContact(image: String, name: String, phoneNumber: String, email: String) {
        viewModelScope.launch {
            val contact =
                Contact(0, image = image, name = name, phoneNumber = phoneNumber, email = email)
            contactRepository.insert(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.update(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.delete(contact)
        }
    }


    class ContactViewModelFactory( private val repository: ContactRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
                return ContactViewModel(repository) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel Class")
            }
        }
    }
}