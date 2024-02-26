package br.app.ubuntu.componentes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.app.ubuntu.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun TelaTeste(controlador: NavHostController, nome: MutableState<String>) {
    val logo: Painter = painterResource(id = R.drawable.logoubuntucircular50x50)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(horizontalArrangement = Arrangement.Center) {
            Botao(
                nome = mutableStateOf(nome.value), logo
            )
            Button(modifier = Modifier.wrapContentSize(), onClick = { /*TODO*/ }) {
                Image(
                    painter = logo,
                    contentDescription = "notificações"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row {
                Botao(nome = mutableStateOf("Ajuda"), logo)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 2.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Saldo", fontSize = 16.sp)
                Botao(nome = mutableStateOf("Ir ao extrato"), icone = null)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(20.dp))
                Text(text = "50,00", fontSize = 30.sp)
                Image(
                    painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                    contentDescription = "Olho para revelar saldo"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.background(
                    Color(0x4D4EBC52),
                    shape = RoundedCornerShape(16.dp)
                )

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier

                        .padding(10.dp)

                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                        contentDescription = "moedas"
                    )
                    Text(text = "Aqui seu saldo rende desde o primeiro dia")
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BotaoImagem(
                    nome = mutableStateOf("Area Pix"),
                    icone = painterResource(id = R.drawable.logo_ubuntu)
                )
                BotaoImagem(
                    nome = mutableStateOf("Area Pix"),
                    icone = painterResource(id = R.drawable.logo_ubuntu)
                )
                BotaoImagem(
                    nome = mutableStateOf("Area Pix"),
                    icone = painterResource(id = R.drawable.logo_ubuntu)
                )

            }
            Spacer(modifier = Modifier.height(30.dp))
            Column( modifier = Modifier
                .background(
                    Color.DarkGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp, 10.dp)
            ) {
                Row {
                    Column {
                        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Text(text = "Visa", color = Color.White)

                            Image(
                                modifier = Modifier.padding(5.dp),
                                painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                                contentDescription = "moedas"
                            )
                        }
                        BotaoDeTexto(nome = mutableStateOf("Ir para meu cartao"), icone = logo )

                    }
                    Row {
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Image(painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                                contentDescription =" cartão" )
                            Text(text = "Cartão virtual",Modifier.wrapContentSize(), color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painter = painterResource(id = R.drawable.logoubuntucircular50x50),
                                contentDescription =" wireless" )
                            Text(text = "Cartão aproxime e pague", fontSize = 12.sp, modifier = Modifier.wrapContentWidth(), color = Color.White)
                        }
                    }
                }
                BotaoDeTexto(nome = mutableStateOf("Peça seu cartão de crédito grátis"),
                    icone = null )
            }
        }
    }
    // Conteúdo da tela Home
}
