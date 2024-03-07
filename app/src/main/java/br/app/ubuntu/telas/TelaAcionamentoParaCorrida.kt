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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.runBlocking
import okhttp3.WebSocket




@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaAcionamentoParaCorrida(controladorDeNavegacao: NavHostController) {
    val vm = viewModel<TelaInicialViewModel>()
    val context = LocalContext.current
    val perfil: Perfil = ServicoDePerfil(context).obterPerfil()

    LaunchedEffect(context) {
       vm.atualizarTela(perfil)
    }

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
            vm.corrida?.let {

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
            Image(painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                contentDescription = "Logo do Estabelecimento" )
            Row {
                Button(onClick = {
                   runBlocking {
                       vm.responderCorrida(false,perfil)
                   }
                }) {
                    Text(text = "Rejeitar")
                }
                Button(onClick = {
                   runBlocking {
                       vm.responderCorrida(true,perfil)
                   }
                }) {
                    Text(text = "Aceitar")
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1f)
        )
    }
}
