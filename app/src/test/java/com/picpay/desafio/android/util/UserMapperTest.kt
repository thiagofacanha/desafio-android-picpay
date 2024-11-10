package com.picpay.desafio.android.util

import com.picpay.desafio.android.util.UserMapper.mapLocalToRemote
import com.picpay.desafio.android.util.UserMapper.mapRemoteToLocal
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class UserMapperTest {

    @get:Rule
    val exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun shouldMapRemoteUserToLocalUserSuccessfully() {
        val localResult = mapRemoteToLocal(remoteUser = remoteUser01)
        assertEquals(localResult.id, remoteUser01.id)
        assertEquals(localResult.name, remoteUser01.name)
        assertEquals(localResult.username, remoteUser01.username)
        assertEquals(localResult.img, remoteUser01.img)
    }

    @Test
    fun shouldMapLocalUserToRemoteUserSuccessfully() {
        val remoteResult = mapLocalToRemote(localUser = localUser01)
        assertEquals(remoteResult.id, localUser01.id)
        assertEquals(remoteResult.name, localUser01.name)
        assertEquals(remoteResult.username, localUser01.username)
        assertEquals(remoteResult.img, localUser01.img)
    }

    @Test
    fun shouldThrowsRemoteExceptionWhenMappingRemoteUserWithBlankNameToLocalUser() {
        exceptionRule.expect(InvalidRemoteUserException::class.java)
        exceptionRule.expectMessage("RemoteUser has invalid fields.")
        mapRemoteToLocal(invalidBlankNameRemoteUser)
    }

    @Test
    fun shouldThrowsRemoteExceptionWhenMappingRemoteUserWithNullNameToLocalUser() {
        exceptionRule.expect(InvalidRemoteUserException::class.java)
        exceptionRule.expectMessage("RemoteUser has invalid fields.")
        mapRemoteToLocal(invalidNullNameRemoteUser)
    }
}