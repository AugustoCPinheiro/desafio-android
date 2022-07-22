package com.picpay.desafio.android.core_database

import com.picpay.desafio.android.core_database.converter.convertToPersistenceUser
import com.picpay.desafio.android.core_database.converter.convertToUser
import com.picpay.desafio.android.core_database.model.PersistenceUser
import com.picpay.desafio.android.core_entity.User
import org.junit.Test
import kotlin.test.assertEquals

class PersistenceUserConverterTest {
    @Test
    fun fromPersistenceUserToUser_shouldReturn_equivalentUser(){
        val convertedUser = mockPersistenceUser.convertToUser()

        assertEquals(mockUser.img, convertedUser.img)
        assertEquals(mockUser.name, convertedUser.name)
        assertEquals(mockUser.id, convertedUser.id)
        assertEquals(mockUser.username, convertedUser.username)
    }

    @Test
    fun fromUserToPersistenceUser_shouldReturn_equivalentPersistenceUser(){
        val convertedPersistenceUser = mockUser.convertToPersistenceUser()

        assertEquals(mockPersistenceUser.img, convertedPersistenceUser.img)
        assertEquals(mockPersistenceUser.name, convertedPersistenceUser.name)
        assertEquals(mockPersistenceUser.id, convertedPersistenceUser.id)
        assertEquals(mockPersistenceUser.username, convertedPersistenceUser.username)
    }

    private companion object{
        val mockPersistenceUser = PersistenceUser("a", "b", 0, "c")
        val mockUser = User("a", "b", 0, "c")
    }
}