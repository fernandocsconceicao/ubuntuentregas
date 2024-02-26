package br.app.ubuntu.telas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.app.ubuntu.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaInicial(controlador: NavHostController, nome: MutableState<String>) {
    
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp), horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Status", fontSize = 15.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ellipse100x100),
                    contentDescription = "Status"
                )
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            modifier = Modifier
                .fillMaxWidth()

                .padding(0.dp, 80.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, ) {
                Image(
                    painter = painterResource(R.drawable.perfill200x200),
                    contentDescription = "Imagem de Perfil",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
                )
                Text(text = "Nome do trabalhador")
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .padding(0.dp, 8.dp)
        ) {
            Button(onClick = {}) {
                Text(text = "Iniciar trabalho")
            }
        }
    }
}
