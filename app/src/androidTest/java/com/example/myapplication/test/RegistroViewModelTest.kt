package com.example.myapplication.test

import com.example.myapplication.data.viewModel.RegistroViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.testng.annotations.BeforeMethod

@RunWith(MockitoJUnitRunner::class)
class RegistroViewModelTest {


    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var mockUser: FirebaseUser

    var viewModel: RegistroViewModel = RegistroViewModel()

    @BeforeMethod
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = RegistroViewModel()
    }

    @Test
    fun createUserInFireBase_should_sign_up_successfully() {
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword("test@gmail.com", "password"))


        viewModel.createUserInFireBase("test@gmail.com", "password")

        assertTrue(viewModel.signUpInProgress.value)
    }

}






/**
@Test
fun createUserInFireBase_should_handle_failure() {
Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword("@test", "pass"))

viewModel.createUserInFireBase("@test", "pass")

assertFalse(viewModel.signUpInProgress.value)
}*/
