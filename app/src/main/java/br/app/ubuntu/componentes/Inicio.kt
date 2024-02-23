package br.app.ubuntu.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.app.ubuntu.R

@Composable
fun Inicio(controlador: NavHostController, nome: MutableState<String>) {
    val logo: Painter = painterResource(id = R.drawable.logoubuntucircular50x50)
    Column(modifier = Modifier.fillMaxSize()) {
            Row(horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.wrapContentSize(), onClick = { /*TODO*/ }) {
                Image(
                    painter = logo,
                    contentDescription = "notificações"
                )
                Row  {
                 Text(text = nome.value)
                }
            }
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Image(
                        painter = logo,
                        contentDescription = "notificações"
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Row {
                        Image(
                            painter = logo,
                            contentDescription = "ajuda"
                        )
                        Text(text = "Ajuda")
                    }
                }
            }
            }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 2.dp)
                .background(Color.Green)
        ) {
            Row {
                Text(text = "Saldo")

            }

        }

    }
    // Conteúdo da tela Home
}
