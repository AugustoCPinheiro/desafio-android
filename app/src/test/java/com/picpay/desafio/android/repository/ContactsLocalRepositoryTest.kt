package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.datasource.db.UserDao
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class ContactsLocalRepositoryTest {

    private val mockUserDao = mockk<UserDao>()

    private lateinit var repository: ContactsLocalRepository


    @Before
    fun setup() {
        repository = ContactsLocalRepositoryImpl(mockUserDao)
    }

    @Test
    fun whenGetContacts_shouldBeRetrieved_fromDatabase() {
        every { mockUserDao.getAll() } returns mockCachedList
        every { mockUserDao.insertAll(*anyVararg()) } answers { nothing }

        val result = repository.getContacts()

        assertEquals(mockCachedList, result)
    }

    private companion object {
        val mockUser = User("", "", 0, "")
        val mockCachedList = listOf(mockUser)
    }
}