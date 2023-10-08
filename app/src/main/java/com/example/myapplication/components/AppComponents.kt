package com.example.myapplication.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import org.w3c.dom.Text


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
fun ClickableRegisterTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "¿No tienes ninguna cuenta?"
    val loginText = " Registrate aquí."

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