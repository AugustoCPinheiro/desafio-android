package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.datasource.db.UserDao
import com.picpay.desafio.android.model.User
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ContactsLocalDataSourceTest {
    private val mockUserDao = mockk<UserDao>()

    private lateinit var mockDataSource: ContactsLocalDataSource


    @Before
    fun setup() {
        mockDataSource = ContactsLocalDataSourceImpl(mockUserDao)
    }

    @Test
    fun whenGetContacts_shouldBeRetrieved_fromDatabase() {
        every { mockUserDao.getAll() } returns mockCachedList
        every { mockUserDao.insertAll(*anyVararg()) } answers { nothing }

        val result = mockDataSource.getContacts()

        assertEquals(mockCachedList, result)
    }

    private companion object {
        val mockUser = User("", "", 0, "")
        val mockCachedList = listOf(mockUser)
    }
}