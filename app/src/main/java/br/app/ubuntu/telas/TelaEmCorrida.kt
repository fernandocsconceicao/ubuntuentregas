package br.app.ubuntu.telas

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    val context = LocalContext.current
    val perfil: Perfil = ServicoDePerfil(context).obterPerfil()

    LaunchedEffect(context) {
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
            Button(onClick = { vm.finalizarCorrida() }) {
                Text(text = "Finalizar Corrida")
            }
        }
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top,
//
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(0.dp, 80.dp)
//        ) {
//            Column(
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .background(Color.Transparent)
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .border(BorderStroke(5.dp, Color.Black))
//            ) {
//
//                Column(
//                    modifier = Modifier.weight(0.3f)
//                        .border(BorderStroke(5.dp, Color.Green)),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//
//                ) {
//                    vm.imagemLogoEstabelecimento?.let {
//                        vm.bitmapLogoEstabelecimento?.let { it1 ->
//                            Image(
//                                bitmap = it1, contentDescription = "logo estabelecimento"
//                            )
//                        }
//                    }
//                    Row(modifier = Modifier.weight(0.5f).border(BorderStroke(5.dp, Color.Black))) {
//
//                        vm.enderecoEstabelecimento?.let {
//
//                            Row() {
//                                Text(text = it)
//                                Button(onClick = {
//
//                                }) {
//                                    val clipboard: ClipboardManager? =
//                                        getSystemService(context, ClipboardManager::class.java)
//
//                                    val clip2 =
//                                        ClipData.newPlainText(
//                                            "QR Code",
//                                            vm.enderecoEstabelecimento
//                                        )
//
//                                    clipboard?.setPrimaryClip(clip2)
//                                }
//                            }
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.weight(0.2f).border(BorderStroke(5.dp, Color.Red)))
//
//                Column(
//                    modifier = Modifier.weight(0.3f)
//                        .border(BorderStroke(5.dp, Color.Yellow)),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    vm.imagemLogoEstabelecimento?.let {
//                        vm.bitmapLogoEstabelecimento?.let { it1 ->
//                            Image(
//                                bitmap = it1, contentDescription = "logo estabelecimento"
//                            )
//                        }
//                    }
//
//                }
//
//
//            }
//        }
    }
}
