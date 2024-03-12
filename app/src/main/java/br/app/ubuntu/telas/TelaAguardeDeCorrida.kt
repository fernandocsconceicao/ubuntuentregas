package br.app.ubuntu.telas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.app.ubuntu.R
import br.app.ubuntu.auxiliar.Perfil
import br.app.ubuntu.auxiliar.ServicoDePerfil
import br.app.ubuntu.client.MyWebSocketClient
import br.app.ubuntu.dto.Mensagem
import br.app.ubuntu.enums.Remetente
import br.app.ubuntu.enums.TipoMensagem
import br.app.ubuntu.telas.viewmodel.TelaInicialViewModel
import com.google.gson.Gson


@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaAguardeDeCorrida(controladorDeNavegacao: NavHostController) {
    val vm = viewModel<TelaInicialViewModel>()
    val context = LocalContext.current
    val perfil: Perfil = ServicoDePerfil(context).obterPerfil()


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp), horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = vm.status, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id =vm.iconeStatus),
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.perfill200x200),
                    contentDescription = "Imagem de Perfil",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
                )
                vm.nome?.let {  Text(text =it ) }
            }


        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(50.dp, 8.dp)
        ) {
            Text(text = vm.mensagemAguardeDeDeCorrida , textAlign = TextAlign.Center)
            Spacer(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f)
            )
            Button(onClick = {
                vm.finalizarTrabalho()
            }) {
                Text(text = "Finalizar trabalho")
            }
        }
    }
}
