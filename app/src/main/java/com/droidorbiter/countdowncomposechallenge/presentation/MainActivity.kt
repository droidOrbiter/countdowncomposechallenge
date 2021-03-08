package com.droidorbiter.countdowncomposechallenge.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.countdowncomposechallenge.R
import com.droidorbiter.countdowncomposechallenge.ui.theme.CardBackground
import com.droidorbiter.countdowncomposechallenge.ui.theme.CountdownComposeChallengeTheme
import com.droidorbiter.countdowncomposechallenge.ui.theme.Rect1Color


class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdownComposeChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun App() {

    var buttonVisibility by remember {mutableStateOf(false)}
    var initVerticalProgress by remember { mutableStateOf(true)}

    AnimatedVisibility(
        visible = initVerticalProgress,
        exit = shrinkVertically()
    ) {
        VerticalRectArray {
            buttonVisibility =
                when (it) {
                    LoadingStatus.Loading -> false
                    LoadingStatus.Finished -> true
                }
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize())
    {
        val (image, card) = createRefs()

        Image(
            painter = painterResource(R.drawable.jetpack_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 40.dp)
                .height(280.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Fit
        )

        Card(
            elevation = 6.dp,
            border = BorderStroke(2.dp, Color.White),
            backgroundColor = CardBackground,
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(image.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(id = R.string.title),
                    textAlign = TextAlign.Center,
                    style = (MaterialTheme.typography).h3,
                    modifier = Modifier.padding(18.dp)
                )
                AnimatedVisibility(
                    visible = buttonVisibility,
                    enter = expandIn() + fadeIn(),
                    exit = shrinkHorizontally() + fadeOut()
                ) {
                    Button(
                        onClick = {
                            initVerticalProgress = false

                            Handler(Looper.getMainLooper()).postDelayed({
                                initVerticalProgress = true
                            }, 600)
                        },
                        modifier = Modifier.padding(22.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Rect1Color)
                    ) {
                        Text(
                            text = stringResource(id = R.string.button_label),
                            style = (MaterialTheme.typography).button,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountdownComposeChallengeTheme {
        App()
    }
}