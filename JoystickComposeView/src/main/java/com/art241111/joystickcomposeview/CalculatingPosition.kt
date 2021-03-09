package com.art241111.joystickcomposeview

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import com.art241111.joystickcomposeview.data.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.min
import kotlin.math.sin

/**
 * View model for calculating the position of the central circle of the joystick.
 *
 * @author Artem Gerasimov (gerasimov.av.dev@gmail.com)
 */
internal class CalculatingPosition(joystickSize: Float, frameSize: Float) {
    private val _joystickPosition: MutableStateFlow<Position> =
        MutableStateFlow(Position(0f, 0f))

    /**
     * Position of the center circle relative to the center.
     */
    internal val joystickPosition: StateFlow<Position> = _joystickPosition

    private var homePosition = Position(0f, 0f)
    private var maxRadius = 0f

    private var fingerPosition = Position(0f, 0f)

    /**
     * Setting the initial data of the joystick.
     */
    init {
        val centerPosition = frameSize / 2 - joystickSize / 2

        homePosition = Position(
            x = centerPosition,
            y = centerPosition
        )

        _joystickPosition.value = homePosition
        maxRadius = min(homePosition.x, homePosition.y)
    }

    /**
     * When the user releases the joystick, the center circle moves to the home position.
     */
    internal fun moveHome() {
        _joystickPosition.value = homePosition
        fingerPosition = Position(0f, 0f)
    }

    /**
     * We calculate the new position of the central circle.
     *
     *
     * You can also pass the
     * @param x and
     * @param y to which the move values will be passed.
     */
    internal fun moveNewPosition(
        dragAmount: Offset,
        x: MutableState<Float>? = null,
        y: MutableState<Float>? = null
    ) {
        // Remember the distance that the finger has passed
        fingerPosition.x += dragAmount.x
        fingerPosition.y += dragAmount.y
        var radius = hypot(fingerPosition.x, fingerPosition.y)

        // We assume that moving the center of the joystick is equal to moving the finger
        var dragX = fingerPosition.x
        var dragY = fingerPosition.y

        // If the movement of the finger is greater than the possible movement of the center,
        // we remember the angle to which the finger moved,
        // and then adjust the radius of the center movement
        if (radius > maxRadius) {
            var radians = atan2(fingerPosition.y, fingerPosition.x)

            if (radians < 0) {
                radians += 2 * Math.PI.toFloat()
            }

            radius = maxRadius

            dragX = (cos(radians) * radius)
            dragY = (sin(radians) * radius)
        }

        // Save the new position
        _joystickPosition.value = Position(
            x = dragX + homePosition.x,
            y = dragY + homePosition.y
        )

        // Transmit this data
        if (x != null && y != null) {
            x.value = dragX
            y.value = dragY
        }
    }
}
