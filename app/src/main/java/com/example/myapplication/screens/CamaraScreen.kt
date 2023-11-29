package com.example.myapplication.screens

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.ImageCallBack
import com.example.myapplication.data.LoginCallBack
import com.example.myapplication.data.response.UserLoginResponse
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.navigation.CineCritixAppRouter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import retrofit2.Response
import java.io.File
import java.util.concurrent.Executor

private var savedImagePath: String = ""
var TAG3 ="CAMARA"
var fotoTomada =false
var imageBitmap: ImageBitmap? = null
var showDialog = false
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CamaraScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val navControllerState = remember { mutableStateOf<NavController?>(navController) }
    val viewModelState = remember { mutableStateOf<UserViewModel?>(userViewModel) }


    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = {
            val executor = ContextCompat.getMainExecutor(context)
            takePicture(cameraController, executor, navControllerState, viewModelState, context)
            Log.d(TAG3, "FILE")
            //Log.d(TAG3, savedImagePath)
            //savedImagePath?.let { userViewModel.uploadImage(it) }

            //navController.navigate(BottomBarScreen.Settings.route)
        }) {
            Text(text = "Camara!")
        }
    }) {
        if (permissionState.status.isGranted) {
            CamaraComposable(cameraController, lifecycle, modifier = Modifier.padding(it))
            if (fotoTomada){
                navController.navigate(BottomBarScreen.Settings.route)

            }else{

            }
        } else {
            Text(text = "Permiso Denegado!", modifier = Modifier.padding(it))
        }
    }

    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clip(RoundedCornerShape(30.dp)) // Ajusta el radio según lo desees
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    HeadingTextComponentBlack(
                        value = stringResource(id = R.string.eliminarCuenta)
                    )
                },


                text = {
                    Row {
                        imageBitmap?.let { Image(bitmap = it, contentDescription = null ) }
                    }
                },

                confirmButton = {
                    Button(
                        onClick = {
                            //Llamar el endpoint para eliminar la cuenta y para cerrar sesión y volver al login

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.abajoBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.sombraBoton),
                            disabledContentColor = Color.White
                        ),

                        ) {
                        androidx.compose.material3.Text( stringResource(id = R.string.confirmar_salir), color= Color.White)
                    }
                },
            )
        }
    }
}


private fun takePicture(cameraController: LifecycleCameraController, executor: Executor, navControllerState: MutableState<NavController?>, viewModelState: MutableState<UserViewModel?>, context: Context) {
    val file = File.createTempFile("imagentest", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                Log.d(TAG3, "TAKE FOTO")
                Log.d(TAG3, "Uri: ${outputFileResults.savedUri}")

                //cameraController.unbind()
                viewModelState.value?.uploadImage(
                    object : ImageCallBack {

                        override fun onImageResult(success: Bitmap) {
                            imageBitmap = success.asImageBitmap()

                        }

                    }
                    ,outputFileResults.savedUri, context)
                navControllerState.value?.navigate(BottomBarScreen.Settings.route)


                //savedImagePath = getRealPathFromUri(context, outputFileResults.savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.d(TAG3, "ERROR ${exception.message}")
            }
        },
    )
}

///data/data/com.example.myapplication/cache/imagentest1134430340793179438.jpg

@Composable
fun CamaraComposable(
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner,
    modifier: Modifier = Modifier,
) {
    cameraController.bindToLifecycle(lifecycle)
    AndroidView(modifier = modifier, factory = { context ->
        val previewView = PreviewView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
        }
        previewView.controller = cameraController

        previewView
    })
}

@Composable
fun ImageView(savedImagePath: String?) {
    if (savedImagePath != "") {
        val bitmap = BitmapFactory.decodeFile(savedImagePath)
        val imageBitmap: ImageBitmap = bitmap.asImageBitmap()

        Image(
            painter = BitmapPainter(imageBitmap),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Puedes mostrar un marcador de posición o un mensaje aquí
    }

}
