package com.example.lemonlemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonlemonade.ui.theme.LoadedDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadedDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade() {
    LemonSelecting()

}

@Composable
fun LemonSelecting() {

    var count by remember { mutableStateOf(1) }
    var taps by remember { mutableStateOf(0) }

    when (count) {
        1 -> {
            OneLayout(
                textResourceId = R.string.select_lemon,
                imageResourceId = R.drawable.lemon_tree,
                contentDescId = R.string.desc1,
                onImageClick = {
                    taps = (2..4).random()
                    count++
                }
            )

        }
        2 -> {
            OneLayout(
                textResourceId = R.string.squeeze_lemon,
                imageResourceId = R.drawable.lemon_squeeze,
                contentDescId = R.string.desc2,
                onImageClick = {
                    if (taps-- == 0) {
                        count++
                    }
                }
            )

        }
        3 -> {
            OneLayout(
                textResourceId = R.string.drink,
                imageResourceId = R.drawable.lemon_drink,
                contentDescId = R.string.desc3,
                onImageClick = { count++ })

        }
        else -> {
            OneLayout(
                textResourceId = R.string.start_again,
                imageResourceId = R.drawable.lemon_restart,
                contentDescId = R.string.desc4,
                onImageClick = {
                    taps = 0
                    count = 1
                }
            )
        }
    }
}

@Composable

fun OneLayout(
    textResourceId: Int,
    imageResourceId: Int,
    contentDescId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(text = stringResource(id = textResourceId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = contentDescId),
            modifier
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoadedDiceTheme {
        Lemonade()
    }
}