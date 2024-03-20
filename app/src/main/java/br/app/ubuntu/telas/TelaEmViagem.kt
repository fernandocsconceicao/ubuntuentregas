package br.app.ubuntu.telas

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.app.ubuntu.auxiliar.Perfil
import br.app.ubuntu.auxiliar.ServicoDePerfil
import br.app.ubuntu.telas.viewmodel.TelaInicialViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaEmCorrida(controladorDeNavegacao: NavHostController) {
    val vm = viewModel<TelaInicialViewModel>()
    var confirmacao by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val perfil: Perfil = ServicoDePerfil(context).obterPerfil()

    LaunchedEffect(context) {
        vm.perfil = ServicoDePerfil(context).obterPerfil()
        if(vm.telaViagemRecuperada){
            vm.obterViagem(perfil)
        }
        vm.atualizarTela(perfil)
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = vm.status, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = vm.iconeStatus), contentDescription = "Status"
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            vm.imagemLogoEstabelecimento?.let {
                vm.bitmapLogoEstabelecimento?.let { it1 ->
                    Image(
                        bitmap = it1, contentDescription = "logo estabelecimento"
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center


                    ) {
                vm.enderecoEstabelecimento?.let {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){

                            Text(text = it)
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(onClick = {
                                val clipboard: ClipboardManager? =
                                    getSystemService(context, ClipboardManager::class.java)

                                val clip2 =
                                    ClipData.newPlainText("QR Code", vm.enderecoEstabelecimento)

                                clipboard?.setPrimaryClip(clip2)
                            }) {
                                Text(text = "Copiar")
                            }
                        }

                    }
            }

            Row (verticalAlignment = Alignment.CenterVertically){
                vm.enderecoClienteFinal?.let {
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {

                        Text(text = it)
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = {
                            val clipboard: ClipboardManager? =
                                getSystemService(context, ClipboardManager::class.java)

                            val clip2 =
                                ClipData.newPlainText("QR Code", vm.enderecoClienteFinal)

                            clipboard?.setPrimaryClip(clip2)
                        }) {
                            Text(text = "Copiar")
                        }
                    }

                }
            }
            Button(onClick = {
                confirmacao= true
            }) {
                Text(text = "Finalizar Viagem")
            }

        }

        if (confirmacao) {
            Snackbar(
                modifier = Modifier.background(Color.Red).fillMaxWidth(), // Cor de fundo da Snackbar
                action = {

                }
            ) {
                Row (){
                    Text(text = "Deseja finalizar a viagem?", color = Color.White
                        ,modifier = Modifier.weight(0.3f)
                    ) // Texto da Snackbar

                    Button(onClick = { confirmacao = false }
                        ,modifier = Modifier.weight(0.3f)
                    ) {
                        Text("Cancelar")
                    }
                    Button(onClick = { vm.finalizarViagem()
                        confirmacao = false}
                        ,modifier = Modifier.weight(0.3f)
                    ) {
                        Text("Confirmar")
                    }
                    

                }
            }
        }
    }

}
