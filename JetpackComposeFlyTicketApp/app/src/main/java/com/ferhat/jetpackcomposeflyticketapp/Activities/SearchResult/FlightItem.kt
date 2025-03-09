package com.ferhat.jetpackcomposeflyticketapp.Activities.SearchResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.ferhat.jetpackcomposeflyticketapp.Domain.FlightModel
import com.ferhat.jetpackcomposeflyticketapp.R


@Composable
fun FlightItem(item: FlightModel, index: Int) {
    val context = LocalContext.current


    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                //        val intent = Intent(context,)
            }
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        val (logo, timeTxt, airplaneIcon, dashLine, priceTxt, seatIcon, classTxt, fromTxt,
            fromShortTxt, toTxt, toShortTxt) = createRefs()
        AsyncImage(
            model = item.AirLineLogo,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 50.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.AirLineLogo,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTxt) {
                    start.linkTo(parent.start)
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.ArriveTime,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTxt) {
                    start.linkTo(parent.start)
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(R.drawable.line_airple_blue),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(airplaneIcon){
                    start.linkTo(parent.start)
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(dashLine){
                    start.linkTo(parent.start)
                    top.linkTo(airplaneIcon.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "$${String.format("%.2f",item.Price)}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = colorResource(R.color.orange),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceTxt){
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(R.drawable.seat_black_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(seatIcon){
                    start.linkTo(parent.start)
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = item.ClassSeat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier
                .constrainAs(classTxt) {
                    start.linkTo(seatIcon.start)
                    top.linkTo(seatIcon.top)
                    bottom.linkTo(seatIcon.bottom)
                }
        )
    }

}