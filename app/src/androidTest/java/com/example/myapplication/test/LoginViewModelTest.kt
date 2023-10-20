package com.example.myapplication.test

import com.example.myapplication.data.LoginIUState
import com.example.myapplication.data.LoginViewModel
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var mockUser: FirebaseUser

    var viewModel: LoginViewModel = LoginViewModel()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel()
    }

    @Test
    fun signInWithEmailAndPassword_should_sign_in_successfully() {
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword("carito@gmail.com", "123456"))

        viewModel.loginIUState.value = LoginIUState(email = "carito@gmail.com", password = "123456")
        viewModel.login()

        assertTrue(viewModel.loginInProgress.value)
    }


}

/**

@Test
fun signInWithEmailAndPassword_should_handle_failure() {
    Mockito.`when`(firebaseAuth.signInWithEmailAndPassword("test@", "pass"))

    viewModel.loginIUState.value = LoginIUState(email = "test@", password = "pass")
    viewModel.login()

    Assert.assertFalse(viewModel.loginInProgress.value)
}*/