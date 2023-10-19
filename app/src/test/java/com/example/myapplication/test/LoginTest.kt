package com.example.myapplication.test

import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.UIEventLogin
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
class LoginTest {


    /**
     *
     * OTRA PRUEBA QUE INTENTE Y NO FUNCIONÓ

    @Test
    fun testLogin() {
        val mockedAuth = mock(FirebaseAuth::class.java)
        val viewModel = LoginViewModel()

        // Mock del resultado exitoso de inicio de sesión
        val mockTask = mock(Task::class.java) as Task<AuthResult>
        `when`(mockedAuth.signInWithEmailAndPassword("carito@gmail.com", "123456")).thenReturn(mockTask)

        // Simular el evento de inicio de sesión
        viewModel.onEvent(UIEventLogin.EmailChanged("carito@gmail.com"))
        viewModel.onEvent(UIEventLogin.PasswordChanged("123456"))
        viewModel.onEvent(UIEventLogin.LoginButtonClicked)

        // Verificar que se haya llamado a signInWithEmailAndPassword con los credenciales correctos
        verify(mockedAuth).signInWithEmailAndPassword("carito@gmail.com", "123456")
    }
    */

    /**
     * LA QUE HABIAMOS HECHO
     *
    @Mock
    private val firebaseAuth: FirebaseAuth? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSuccessfulLogin() {
        // Configurar FirebaseUser simulado
        val user: FirebaseUser = TestUtilities.createMockFirebaseUser("user@example.com")

        // Configurar Firebase para devolver el usuario simulado al autenticar
        `when`<Task<AuthResult>>(
            firebaseAuth!!.signInWithEmailAndPassword(
                "user@example.com",
                "password"
            )
        )
            .thenReturn(TestUtilities.createMockAuthResult(user))

        // Realizar la autenticación
        val userManager = FirebaseAuthUserManager(firebaseAuth)
        val loggedInUser: FirebaseUser =
            userManager.loginWithEmailAndPassword("user@example.com", "password")

        // Realizar aserciones para verificar que la autenticación fue exitosa
        assertNotNull(loggedInUser)
        assertEquals("user@example.com", loggedInUser.email)
    }

    @Test
    fun testInvalidLogin() {
        // Configurar Firebase para devolver una excepción cuando se intente la autenticación
        `when`(firebaseAuth!!.signInWithEmailAndPassword("invalid@example.com", "password"))
            .thenThrow(FirebaseAuthInvalidUserException("User not found."))

        // Realizar la autenticación
        val userManager = FirebaseAuthUserManager(firebaseAuth)
        try {
            val loggedInUser: FirebaseUser =
                userManager.loginWithEmailAndPassword("invalid@example.com", "password")
            fail("Se esperaba una excepción")
        } catch (e: FirebaseAuthInvalidUserException) {
            // Se espera una excepción porque el usuario no existe
        }
    }

    @Test
    fun testUserCollision() {
        // Configurar Firebase para devolver una excepción cuando se intente la autenticación debido a una colisión de usuarios
        `when`(firebaseAuth!!.signInWithEmailAndPassword("collision@example.com", "password"))
            .thenThrow(FirebaseAuthUserCollisionException("User collision."))

        // Realizar la autenticación
        val userManager = FirebaseAuthUserManager(firebaseAuth)
        try {
            val loggedInUser: FirebaseUser =
                userManager.loginWithEmailAndPassword("collision@example.com", "password")
            fail("Se esperaba una excepción")
        } catch (e: FirebaseAuthUserCollisionException) {
            // Se espera una excepción debido a una colisión de usuarios
        }
    }
}

object TestUtilities {
    fun createMockFirebaseUser(email: String?): FirebaseUser {
        val user: FirebaseUser = mock(FirebaseUser::class.java)
        `when`(user.email).thenReturn(email)
        // Agregar más configuraciones según tus necesidades.
        return user
    }

    fun createMockAuthResult(user: FirebaseUser?): AuthResult {
        val authResult: AuthResult = mock(AuthResult::class.java)
        `when`(authResult.user).thenReturn(user)
        // Agregar más configuraciones según tus necesidades.
        return authResult
    }*/
}