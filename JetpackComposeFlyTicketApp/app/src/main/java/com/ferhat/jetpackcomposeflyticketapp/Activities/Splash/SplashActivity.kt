package com.ferhat.jetpackcomposeflyticketapp.Activities.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ferhat.jetpackcomposeflyticketapp.Activities.Dashboard.DashboardActivity
import com.ferhat.jetpackcomposeflyticketapp.MainActivity
import com.ferhat.jetpackcomposeflyticketapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.text.Normalizer.Form

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SplashScreen(onGetStartClick = {
                startActivity(Intent(this,DashboardActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetStartClick:() -> Unit ={}){
    StatusTopBarColor()
    Column (modifier = Modifier.fillMaxSize()) {
        ConstraintLayout() {
            val(backgroundImg,title,subtitle,startbtn)=createRefs()

            Image(
                painter = painterResource(R.drawable.splash),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backgroundImg){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .fillMaxSize()
            )

            val styledText = buildAnnotatedString {
                append("Hayalindeki Uçuşa \nKolayca ")
                withStyle(style = SpanStyle(color = colorResource(R.color.teal_200))){
                    append("Ulaş")
                }
            }
            Text(
                text = styledText,
                fontSize = 53.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
                    .constrainAs(title){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }

            )

            Text(
                text = stringResource(R.string.subtitle_splash),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.orange),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 32.dp, start = 5.dp)
                    .constrainAs(subtitle){
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {}
            Box(modifier = Modifier.constrainAs(startbtn){
                bottom.linkTo(parent.bottom)
            }){
                GradientButton(onClick = onGetStartClick,"Haydi Başlıyalım", padding = 32)
            }
        }
    }
}

@Composable
fun StatusTopBarColor(){

    val systemUiController= rememberSystemUiController()
    
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}