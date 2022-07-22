package com.picpay.desafio.android.core_database.converter

import com.picpay.desafio.android.core_database.model.PersistenceUser
import com.picpay.desafio.android.core_entity.User

fun PersistenceUser.convertToUser(): User {
    return User(img, name, id, username)
}

fun User.convertToPersistenceUser(): PersistenceUser {
    return PersistenceUser(img, name, id, username)
}