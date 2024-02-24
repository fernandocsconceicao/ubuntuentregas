package br.app.ubuntu.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import br.app.ubuntu.R

@Composable
fun Botao(nome: MutableState<String>, icone: Painter?) {
    val logo: Painter = painterResource(id = R.drawable.logoubuntucircular50x50)
    if (icone != null) {
        Button(
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4EBC52),
                Color.Black,
                Color.Gray,
                Color.Black
            ),
            onClick = { /*TODO*/ }) {
            Image(
                painter = logo,
                contentDescription = "notificações"
            )
            Row {
                Text(text = nome.value, fontSize = 15.sp)
            }
        }
    } else {
        Button(
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4EBC52),
                Color.Black,
                Color.Gray,
                Color.Black
            ),
            onClick = { /*TODO*/ }) {
            Row {
                Text(text = nome.value)
            }
        }
    }

    // Conteúdo da tela Home
}
