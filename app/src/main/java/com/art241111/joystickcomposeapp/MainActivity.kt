package com.art241111.joystickcomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.art241111.joystickcomposeapp.ui.theme.JoystickComposeAppTheme
import com.art241111.joystickcomposeview.JoystickView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoystickComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    JoystickApp()
                }
            }
        }
    }
}

@Composable
fun JoystickApp() {
    val size = remember { mutableStateOf(IntSize.Zero) }

    val dx = remember { mutableStateOf(0f) }
    val dy = remember { mutableStateOf(0f) }

    val offsetX = remember { mutableStateOf(100f) }
    val offsetY = remember { mutableStateOf(100f) }

    Log.d("pos_dx_dy", "${dx.value}, ${dy.value}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { size.value = it.size }
    ) {

        Box(
            Modifier
                .offset {
                    if (size.value.height > 0) {
                        offsetX.value = (offsetX.value + dx.value / 10).coerceIn(
                            0f,
                            size.value.width.toFloat() - 50.dp.toPx()
                        )
                        offsetY.value = (offsetY.value + dy.value / 10).coerceIn(
                            0f,
                            size.value.height.toFloat() - 50.dp.toPx() - 200.dp.toPx()
                        )
                    }

                    IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt())
                }
                .background(Color.Blue)
                .size(50.dp)
        )

        JoystickView(
            modifier = Modifier.align(Alignment.BottomCenter),
            backgroundColor = Color.Black,
            x = dx,
            y = dy,
            size = 150.dp,
        )
    }
}
