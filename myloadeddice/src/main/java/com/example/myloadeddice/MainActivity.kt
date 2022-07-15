package com.example.myloadeddice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myloadeddice.ui.theme.LoadedDiceTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadedDiceTheme {
                RollDice()
            }
        }
    }
}

@Composable
fun RollDice() {
    Column(
        Modifier.fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DiceImageButton()
    }
}
@Preview(showBackground = true)
@Composable
fun DiceImageButton(modifier: Modifier = Modifier) {

    var number by rememberSaveable {mutableStateOf(1)}
    var number1 by rememberSaveable {mutableStateOf(1)}

    val resultId = when(number){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    val resultId1 = when(number1){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Row {
        Image(
            painter = painterResource(id = resultId),
            contentDescription = null
        )
        Spacer(modifier.width(20.dp))
        Image(
            painter = painterResource(id = resultId1),
            contentDescription = null
        )
    }

    Button(
        onClick = {
            GlobalScope.launch {
                //delay(1000)
                number = (1..6).random()
                delay(1000)
                number1 = (1..6).random()
            }
        }
    ) {
        Text(text = stringResource(R.string.roll))

    }
}
































