package br.app.ubuntu.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.app.ubuntu.R

@Composable
fun BotaoImagem(nome: MutableState<String>, icone: Painter?) {
    val logo: Painter = painterResource(id = R.drawable.logo_ubuntu_circular)
    if (icone != null) {
        Button(
            shape = ShapeDefaults.Large,
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                Color(0x4D4EBC52),
                Color.Black,
                Color.Gray,
                Color.Black
            ),
            onClick = { /*TODO*/ }) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize()

            ) {
                Image(
                    painter = logo,
                    contentDescription = "notificações"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = nome.value, Modifier.wrapContentSize(), fontSize = 15.sp)

            }

        }
    } else {
        Button(
            shape = ButtonDefaults.elevatedShape,
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                Color.Green,
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
