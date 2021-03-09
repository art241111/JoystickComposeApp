package com.art241111.joystickcomposeview

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import com.art241111.joystickcomposeview.utils.toFloat
import kotlin.math.roundToInt

/**
 * A round joystick written using Jetpack Compose.
 *
 * @param modifier - the modifier to be applied to the layout.
 * @param x - tracking the change in the center position by X
 * @param y - tracking the change in the center position by Y
 * @param size - joystick size
 * @param backgroundColor - color of the back circle
 * @param joystickColor - center color
 * @param joystickSize - center size
 *
 * @author Artem Gerasimov (gerasimov.av.dev@gmail.com)
 */
@Composable
fun JoystickView(
    modifier: Modifier = Modifier,
    x: MutableState<Float>? = null,
    y: MutableState<Float>? = null,
    size: Dp = 150.dp,
    backgroundColor: Color = Color.White,
    joystickColor: Color = Color.Red,
    joystickSize: Dp = 50.dp
) {
    val context = LocalContext.current

    val calculationPosition = remember {
        CalculatingPosition(
            joystickSize = joystickSize.toFloat(context),
            frameSize = size.toFloat(context)
        )
    }

    val joystickPosition = calculationPosition.joystickPosition.collectAsState()

    Box(
        modifier = modifier
            .size(size)
            .background(color = backgroundColor, shape = CircleShape)
    ) {
        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = joystickPosition.value.x.roundToInt(),
                        y = joystickPosition.value.y.roundToInt()
                    )
                }
                .background(joystickColor, RoundedCornerShape(50))
                .size(joystickSize)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // При отпускании, возвращаем центр в центр
                            calculationPosition.moveHome()

                            if (x != null && y != null) {
                                x.value = 0f
                                y.value = 0f
                            }
                        }
                    ) { change, dragAmount ->
                        change.consumeAllChanges()

                        calculationPosition.moveNewPosition(
                            dragAmount = dragAmount,
                            x = x,
                            y = y
                        )
                    }
                }
        )
    }
}
