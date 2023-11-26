package com.example.myapplication.screens

import android.Manifest
import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.data.viewModel.UserViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.util.concurrent.Executor

private var savedImagePath: String = ""
var TAG3 ="CAMARA"
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CamaraScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

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
            takePicture(cameraController, executor)
            Log.d(TAG3, "FILE")
            Log.d(TAG3, savedImagePath)
            savedImagePath?.let { userViewModel.uploadImage(it) }
            navController.navigate(BottomBarScreen.Settings.route)
        }) {
            Text(text = "Camara!")
        }
    }) {
        if (permissionState.status.isGranted) {
            CamaraComposable(cameraController, lifecycle, modifier = Modifier.padding(it))
            ImageView(savedImagePath)
        } else {
            Text(text = "Permiso Denegado!", modifier = Modifier.padding(it))
        }
    }
}

private fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
    val file = File.createTempFile("imagentest", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                savedImagePath = file.absolutePath
                outputFileResults.savedUri
                //savedImagePath = getRealPathFromUri(context, outputFileResults.savedUri)
                Log.d(TAG3, outputFileResults.toString())
            }

            override fun onError(exception: ImageCaptureException) {
                println()
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

    fun getRealPathFromUri(context: Context, uri: Uri): String {
        val filePath: String
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(columnIndex)
            cursor.close()
        } else {
            filePath = uri.path ?: ""
        }
        return filePath
    }
