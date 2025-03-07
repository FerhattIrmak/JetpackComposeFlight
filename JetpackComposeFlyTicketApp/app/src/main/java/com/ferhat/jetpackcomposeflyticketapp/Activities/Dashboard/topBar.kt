package com.ferhat.jetpackcomposeflyticketapp.Activities.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ferhat.jetpackcomposeflyticketapp.R
import com.google.android.gms.common.internal.StringResourceValueReader

@Composable
@Preview
fun TopBar() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        val (world, name, profile, notification, title) = createRefs()

        Image(
            painter = painterResource(R.drawable.world),
            contentDescription = null,
            modifier = Modifier
                .clickable {  }
                .constrainAs(world){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Image(
            painter = painterResource(R.drawable.baba),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(profile){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "  Müslüm Gürses",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(name){
                top.linkTo(parent.top)
                start.linkTo(profile.end)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(
            text = stringResource(R.string.dahboard_title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(title){
                    top.linkTo(profile.bottom)
                    bottom.linkTo(parent.bottom)
                }

        )
    }
}