package br.app.ubuntu.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.app.ubuntu.R
import br.app.ubuntu.auxiliar.ServicoDePerfil
import br.app.ubuntu.client.impl.UbuntuClientImplementation
import br.app.ubuntu.dto.AuthorizationRequestBody
import br.app.ubuntu.dto.AuthorizationResponseBody
import br.app.ubuntu.enums.Rotas
import br.app.ubuntu.enums.TipoConta
import br.app.ubuntu.telas.viewmodel.TelaInicialViewModel
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDeLogin(controlador: NavHostController) {
    var email by remember { mutableStateOf("entregador@entregador.com") }
    var senha by remember { mutableStateOf("hellotester") }
    val context = LocalContext.current
    var notificacao by remember { mutableStateOf(false) }
    var textoNotificacao by remember { mutableStateOf("") }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val height = with(LocalDensity.current) { screenHeight * 0.8f }
    val vm = viewModel<TelaInicialViewModel>()
    vm.controlador=controlador

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(height),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_ubuntu_circular),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                textAlign = TextAlign.Center,
                text = "Para iniciar, insira suas credenciais fornecidas previamente",
                fontSize = 15.sp
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Usuario") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Handle next action */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            OutlinedTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = senha,
                onValueChange = { senha = it },
                label = { Text(text = "Senha") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle done action */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    Color(0x4D4EBC52),
                    Color.Black,
                    Color.Gray,
                    Color.Black
                ),
                onClick = {
                    if (email == "" || senha == "") {
                        println("texto vazio")
                        notificacao = true
                        textoNotificacao = "Usuario e senha invalidos"
                        return@Button
                    }
                    runBlocking {
                        val resposta: Response<AuthorizationResponseBody> =
                            UbuntuClientImplementation.api.autenticar(
                                AuthorizationRequestBody(
                                    email = email, password = senha
                                )
                            )
                        if (resposta.isSuccessful) {
                            if (resposta.body()!!.accountType == TipoConta.ENTREGADOR.toString()) {
                                val token = "Bearer " + resposta.body()!!.token

                                ServicoDePerfil(context = context).definirTokenProd(
                                    token,
                                    resposta.body()!!.accountType,
                                    resposta.body()!!.idEntregador.toString()
                                )
                                println(Gson().toJson(resposta.body()))

//                                val statusEntregador = resposta.body()!!.statusEntregador.toString()
//                                if(statusEntregador.equals( "EM_VIAGEM")){
//                                    controlador.navigate(Rotas.TELA_EM_CORRIDA.rota)
//                                    vm.telaViagemRecuperada = true
//                                    return@runBlocking
//                                }
                                controlador.navigate(Rotas.TELA_INICIAL.rota)
                            } else {

                                notificacao = true;
                                textoNotificacao =
                                    "Este aplicativo destina-se apenas a entregadores"
                            }

                        }else if(resposta.code() == 403){
                            notificacao = true;
                            textoNotificacao =
                                "Usuario e senha inválidos."
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Text(text = "Login")
            }
        }
        Spacer(
            modifier = Modifier
                .height(0.dp) // Altura zero para ocupar o espaço restante
                .weight(1f) // Preenche o espaço restante da coluna
        )
        if (notificacao) {
            Snackbar(
                modifier = Modifier.background(Color.Red), // Cor de fundo da Snackbar
                action = {
                    Button(onClick = { notificacao = false }) {
                        Text("OK")
                    }
                }
            ) {
                Text(text = textoNotificacao, color = Color.White) // Texto da Snackbar
            }
        }
    }

}