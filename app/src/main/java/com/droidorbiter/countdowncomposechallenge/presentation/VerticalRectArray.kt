package com.droidorbiter.countdowncomposechallenge.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.droidorbiter.countdowncomposechallenge.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


enum class LoadingStatus {
    Loading,
    Finished
}

@Composable
fun VerticalRectArray(loadingStatus: ((LoadingStatus) -> Unit)? = null) {

    loadingStatus?.invoke(LoadingStatus.Loading)

    var rect1Height by remember { mutableStateOf(0F) }
    var rect2Height by remember { mutableStateOf(0F) }
    var rect3Height by remember { mutableStateOf(0F) }
    var rect4Height by remember { mutableStateOf(0F) }
    var rect5Height by remember { mutableStateOf(0F) }

    var rectangleProgressHeight = 0F

    // Increase rectangle array height
    rememberCoroutineScope().launch {
        for (i in 1..140) {

            if (i <= 100) {
                rect1Height += rectangleProgressHeight
            }

            if (rect1Height >= (rectangleProgressHeight * 10) && i <= 110) {
                rect2Height += rectangleProgressHeight
            }

            if (rect1Height >= (rectangleProgressHeight * 20) && i <= 120) {
                rect3Height += rectangleProgressHeight
            }

            if (rect1Height >= (rectangleProgressHeight * 30) && i <= 130) {
                rect4Height += rectangleProgressHeight
            }
            if (rect1Height >= (rectangleProgressHeight * 40) && i <= 140) {
                rect5Height += rectangleProgressHeight
            }

            delay(100)
        }
        loadingStatus?.invoke(LoadingStatus.Finished)
    }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val rectangleWidth = canvasWidth / 5
        rectangleProgressHeight = canvasHeight / 100

        drawRect(
            topLeft = Offset.Zero,
            size = Size(width = (rectangleWidth), height = rect1Height),
            color = Rect1Color
        )

        drawRect(
            topLeft = Offset(x = rectangleWidth, y = 0F),
            size = Size(width = rectangleWidth, height = rect2Height),
            color = Rect2Color
        )

        drawRect(
            topLeft = Offset(x = (rectangleWidth * 2), y = 0F),
            size = Size(width = rectangleWidth, height = rect3Height),
            color = Rect3Color
        )

        drawRect(
            topLeft = Offset(x = (rectangleWidth * 3), y = 0F),
            size = Size(width = rectangleWidth, height = rect4Height),
            color = Rect4Color
        )

        drawRect(
            topLeft = Offset(x = (rectangleWidth * 4), y = 0F),
            size = Size(width = rectangleWidth, height = rect5Height),
            color = Rect5Color
        )

    }
}
