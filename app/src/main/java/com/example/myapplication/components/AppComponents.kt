@file:OptIn(ExperimentalFoundationApi::class)


package com.example.myapplication.components

import android.annotation.SuppressLint
import android.media.Image
import android.provider.MediaStore.Images
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import androidx.paging.Pager
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.math.absoluteValue


@Composable
fun NormalTextComponent(value:String){
    Text(
        text= value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        )
        , color= colorResource(id = R.color.gris),
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponent(value:String){
    Text(
        text= value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        )
        , color= colorResource(id = R.color.gris),
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponentBlack(value:String){
    Text(
        text= value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        )
        , color= colorResource(id = R.color.black),
        textAlign = TextAlign.Center
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, painterResource: Painter,
                onTextSelected: (String) -> Unit,
                errorStatus :Boolean = false) {
    val textValue = remember {
        mutableStateOf("")
    }

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = colorResource(id = R.color.colorPrimary2),
        focusedBorderColor = colorResource(id = R.color.colorPrimary),
        unfocusedBorderColor = colorResource(id = R.color.colorPrimary1),
        containerColor = colorResource(id = R.color.label),
        focusedLabelColor = colorResource(id = R.color.colorPrimary2),
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(5.dp)
            .clip(shape = RoundedCornerShape(4.dp)),

        label = { Text(text = labelValue) },

        colors = customTextFieldColors,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine= true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .padding(4.dp) // Puedes ajustar el padding según tus necesidades
                    .size(24.dp) // Puedes ajustar el tamaño del icono aquí
            ) {
                Icon(
                    painterResource,
                    contentDescription = ""
                )
            }

},
        isError= !errorStatus
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String, painterResource: Painter,
                      onTextSelected: (String) -> Unit,
                      errorStatus :Boolean = false) {

    val localFocusManager= LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible= remember {
        mutableStateOf(false)
    }

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = colorResource(id = R.color.colorPrimary2),
        focusedBorderColor = colorResource(id = R.color.colorPrimary),
        unfocusedBorderColor = colorResource(id = R.color.colorPrimary1),
        containerColor = colorResource(id = R.color.label),
        focusedLabelColor = colorResource(id = R.color.colorPrimary2)
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)),

        label = { Text(text = labelValue) },
        colors = customTextFieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine= true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .padding(4.dp) // Puedes ajustar el padding según tus necesidades
                    .size(24.dp) // Puedes ajustar el tamaño del icono aquí
            ) {
                Icon(
                    painterResource,
                    contentDescription = ""
                )
            }

        },

        trailingIcon = {
            val iconPainter = if (passwordVisible.value) {
                androidx.compose.ui.res.painterResource(id = R.drawable.visibilityon)
            } else {
                androidx.compose.ui.res.painterResource(id = R.drawable.visibilityoff)
            }

            var description = if (passwordVisible.value) {
                stringResource(id = R.string.hide)
            } else {
                stringResource(id = R.string.show)
            }

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(painter = iconPainter, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError= !errorStatus



    )
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit){

    Row(modifier= Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        ){

        val checkedState = remember{
            mutableStateOf(false)
        }

        Checkbox(
            checked= checkedState.value,
            onCheckedChange= {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            }
            )

        ClickableTextComponent(value= value, onTextSelected)

    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "A continuación tú aceptas nuestra "
    val privacy = "Política de privacidad"
    val and = " y "
    val terminos = "Términos de uso."

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(initialText)
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary2))) {
            pushStringAnnotation(tag = privacy, annotation = privacy)
            append(privacy)
        }

        withStyle(style = SpanStyle(color = Color.White)) {
            append(and)
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary2))) {
            pushStringAnnotation(tag = terminos, annotation = terminos)
            append(terminos)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span.item}")

                if(span.item == terminos || span.item==privacy){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit, isEnabled: Boolean= false){
    Button(onClick = {onButtonClicked.invoke() },
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(20.dp),
        contentPadding = PaddingValues(),
        colors= ButtonDefaults.buttonColors(Color.Transparent),
        enabled= isEnabled
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.arribaBoton),
                        colorResource(id = R.color.abajoBoton),
                        colorResource(id = R.color.sombraBoton)
                    )
                ),
                shape = RoundedCornerShape(50.dp)

            ),

            contentAlignment = Alignment.Center

            ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            }
    }
}

@Composable
fun DividerTextComponent(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

        Divider(modifier= Modifier
            .fillMaxWidth()
            .weight(1f),
            color= Color.LightGray,
            thickness = 1.dp)


        Text(modifier = Modifier.padding(8.dp),
            text = "o",
            fontSize = 18.sp, color= Color.White)
        Divider(modifier= Modifier
            .fillMaxWidth()
            .weight(1f),
            color= Color.LightGray,
            thickness = 1.dp)

    }
}

@Composable
fun ClickableLoginTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "¿Ya tienes una cuenta?"
    val loginText = " Inicia sesión"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(initialText)
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary2))) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
        ,
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span.item}")

                if(span.item == loginText){
                    onTextSelected(span.item)
                }
            }
    })

}

@Composable
fun ClickableRegisterTextComponent(value: Int, onTextSelected: (String) -> Unit) {
    val initialText = "¿No tienes ninguna cuenta?"
    val loginText = stringResource(id = value)

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(initialText)
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary2))) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
        ,
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{$span.item}")

                    if(span.item == loginText){
                        onTextSelected(span.item)
                    }
                }
        })

}

@Composable
fun ClickablePasswordTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "¿Olvidaste tu contraseña?"
    val passwordText = " Ingresa aquí."

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(initialText)
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary2))) {
            pushStringAnnotation(tag = passwordText, annotation = passwordText)
            append(passwordText)
        }
    }

    ClickableText(
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
        ,
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{$span.item}")

                    if(span.item == passwordText){
                        onTextSelected(span.item)
                    }
                }
        })

}



@Composable
fun LoginLandInButtonComponent(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(35.dp)
            .padding(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        contentPadding = PaddingValues(5.dp),

    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RegisterLandInButtonComponent(
    text: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(35.dp)
            .padding(horizontal = 20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        contentPadding = PaddingValues(5.dp),

        ) {
        Text(
            text = stringResource(id = text),
            fontSize = 15.sp,
            color = Color.Black,
        )
    }
}

@Composable
fun CardSlider() {
    Column() {
        CarouselCard()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard() {

    val sliderList = listOf(
        "https://i.pinimg.com/564x/d6/ce/7d/d6ce7dfc665e9f9b3931a33e8a6b8be0.jpg",
        "https://i.pinimg.com/564x/4b/54/f6/4b54f68f4e8534b05f2161158888a376.jpg",
        "https://i.pinimg.com/564x/1c/63/d2/1c63d274fa70ffaa88b4f9eba86f0e0e.jpg",
        "https://i.pinimg.com/564x/07/4c/73/074c73b731471f08b2aa7dc8225b820c.jpg",
        "https://i.pinimg.com/564x/3c/b4/28/3cb428f7b5e7246ee9c2727862e423e4.jpg",
        "https://i.pinimg.com/564x/17/b4/89/17b489d227879c76df6e18b526b7ce6c.jpg",
        "https://i.pinimg.com/564x/9f/4e/53/9f4e5381ad87d4077e8c754b6e39377c.jpg",
        "https://i.pinimg.com/564x/30/50/c2/3050c280bc17ac926cfdeb7abfcd410d.jpg",
        "https://i.pinimg.com/564x/24/b2/b1/24b2b1792f616501f1a161b6eda9641c.jpg"
    )

    val pageCount = remember { mutableStateOf(sliderList.size) }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        pageCount.value
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            val leftarrow = androidx.compose.ui.res.painterResource(id = R.drawable.lefttarrow)
            IconButton(
                enabled = pagerState.currentPage > 0,
                onClick = {
                    scope.launch{
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                })
            {
                Icon(painter = leftarrow, contentDescription = null)
            }

            val titulos = listOf(
                "Back to the future",
                "Once upon a time in Hollywood",
                "Interstellar",
                "Rocky",
                "Avengers",
                "The Dark Knight",
                "Djando: Unchained",
                "Kill Bill",
                "Oppenheimer"
                )

            val generos = listOf(
                "Ciencia ficción, Aventura",
                "Drama, Comedia cinematográfica",
                "Ciencia ficción, Aventura",
                "Acción, Drama",
                "Acción, Aventura, Fantasía",
                "Acción, Crimen, Aventura",
                "Western, Acción",
                "Acción, Artes Marciales, Thriller",
                "Historia, Suspenso, Drama"
            )



            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 65.dp),
                modifier = Modifier
                    .height(300.dp)
                    .weight(1f)
            ) { page ->
                val pageOffset = (pagerState.currentPage - page).absoluteValue
                val scale = 0.85f + (0.15f * (1f - pageOffset.coerceIn(0, 1)));

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer() {
                            scaleX = scale
                            scaleY = scale
                            alpha = lerp(0.1f, 1f, 1f - pageOffset.coerceIn(0, 1))
                        }
                ) {
                    // Agregar la imagen dentro del Card
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(sliderList[page])
                                .crossfade(true)
                                .scale(Scale.FILL)
                                .build(),
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(id = R.drawable.error)
                        )
                        // Agregar el título y la descripción debajo de la Card
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = titulos.getOrNull(page) ?: "Título predeterminado",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(text = generos.getOrNull(page) ?: "Título predeterminado", fontSize = 10.sp)
                        }
                    }
                }
            }

            val rightarrow = androidx.compose.ui.res.painterResource(id = R.drawable.rightarrow)
            IconButton(
                enabled = pagerState.currentPage < pagerState.pageCount-1,
                onClick = {
                    scope.launch{
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                })
            {
                Icon(painter = rightarrow, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            Modifier
                .height(40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderList.size){it->
                val color =
                    if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .size(15.dp)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                ){

                }
            }
        }
    }
}

@Composable
fun ClickeableTextComponent(text: String, icon: ImageVector, onTextSelected: () -> Unit, modifier: Modifier = Modifier  ){

    Row ( modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp))
    {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier
                .clickable {
                    onTextSelected()
                    //navController.navigate("ruta_de_la_otra_pagina")
                }
                .padding(16.dp),

        )
        
        Icon(imageVector = icon, // Reemplaza con el icono que desees
            contentDescription = null,
            tint = colorResource(id = R.color.sombraBoton)
        )

    }
}

@Composable
fun movieCard(image: Int, onClick: () -> Unit ,titulo: String, descripcion:String ){
    Surface(modifier = Modifier
        .clickable(onClick = onClick)
        .shadow(8.dp, shape = MaterialTheme.shapes.medium)
        .width(180.dp)
    ) {
        Row (modifier =
        Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(5.dp)
            , verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = image), // Reemplaza con tu imagen
                contentDescription = "Imagen",
                modifier = Modifier
                    .shadow(8.dp, shape = MaterialTheme.shapes.medium)

            )

            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
            ) {
                Text(text = titulo,
                    color = colorResource(id = R.color.titulocard),
                    fontSize = 13.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif),
                    maxLines = 2, // Ajusta según tus necesidades
                    overflow = TextOverflow.Ellipsis // Ajusta según tus necesidades
                )
                Spacer(modifier = Modifier.size(10.dp))
                Box (modifier = Modifier.width(50.dp)){
                    Text(text = descripcion,
                        color = Color.White,
                        fontSize = 7.sp,
                        style = TextStyle(fontFamily = FontFamily.Serif),
                        maxLines = 12, // Ajusta según tus necesidades
                        overflow = TextOverflow.Ellipsis // Ajusta según tus necesidades
                    )

                }

            }
        }
    }

}

@Preview
@Composable

fun PreviewMovieCard(){
    MyApplicationTheme {
        movieCard( R.drawable.grinch, {},"El grinch",
            "Narra la historia del cascarrabias Grinch, quien intenta robar la Navidad de los habitantes de Villaquien, pero descubre el verdadero significado de la festividad en el proceso.")
    }
}




