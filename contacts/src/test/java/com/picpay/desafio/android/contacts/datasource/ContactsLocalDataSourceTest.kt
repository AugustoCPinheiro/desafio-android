package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_database.model.PersistenceUser
import com.picpay.desafio.android.core_entity.User
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ContactsLocalDataSourceTest {
    private val mockUserDao = mockk<com.picpay.desafio.android.core_database.db.UserDao>()

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

        assertEquals(mockUserList, result)
    }

    private companion object {
        val mockUser = User("", "", 0, "")
        val mockPersistenceUser = PersistenceUser("", "", 0, "")
        val mockCachedList = listOf(mockPersistenceUser)
        val mockUserList = listOf(mockUser)
    }
}