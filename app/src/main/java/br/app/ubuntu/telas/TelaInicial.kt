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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.app.ubuntu.R
import br.app.ubuntu.auxiliar.Perfil
import br.app.ubuntu.auxiliar.ServicoDePerfil
import br.app.ubuntu.client.MyWebSocketClient
import br.app.ubuntu.client.impl.UbuntuClientImplementation
import br.app.ubuntu.dto.Mensagem
import br.app.ubuntu.dto.ResTelaMinhaArea
import br.app.ubuntu.enums.TipoMensagem
import com.google.gson.Gson
import okhttp3.WebSocket
import retrofit2.Response


var conexaoWebSocket: WebSocket? = null
var status = mutableStateOf("bla")

@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaInicial(controlador: NavHostController, nome: MutableState<String>) {
    val context = LocalContext.current
    var resposta: Response<ResTelaMinhaArea>? = null

    val perfil: Perfil = ServicoDePerfil(context).obterPerfil()
    LaunchedEffect(context) {
        // Lógica a ser executada apenas uma vez


        resposta =
            UbuntuClientImplementation.api.obterTelaMinhaArea(
                perfil.token!!
            )
        status.value = resposta!!.body()!!.status


    }



    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp), horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = status.value, fontSize = 15.sp)
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.perfill200x200),
                    contentDescription = "Imagem de Perfil",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
                )
                resposta?.body()?.nome?.let { Text(text = it) }
            }

        }
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .padding(0.dp, 8.dp)
        ) {
            Button(onClick = {
                conexaoWebSocket =
                    MyWebSocketClient
                        .connectWebSocket(
                            perfil
                        )
                val mensagem = Mensagem(
                    TipoMensagem.INICIAR_TRABALHO,
                    perfil.idEntregador!!.toLong(),
                    null,
                    null,
                    null,
                    null,
                    null
                )

                val mensagemEmJson: String = Gson().toJson(mensagem)

                conexaoWebSocket?.send(
                    mensagemEmJson
                )
                println(status)
                status.value = "Trabalhando"
                println(status)

            }) {
                Text(text = "Iniciar")
            }
            Button(onClick = {
                conexaoWebSocket!!.send(
                    Gson().toJson(
                        Mensagem(
                            TipoMensagem.FINALIZAR_TRABALHO,
                            perfil.idEntregador!!.toLong(),
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                    )
                )

            }
            ) {
                Text(text = "Encerrar")
            }
        }
    }
}
