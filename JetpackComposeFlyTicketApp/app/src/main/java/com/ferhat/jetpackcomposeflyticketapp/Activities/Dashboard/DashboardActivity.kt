package com.ferhat.jetpackcomposeflyticketapp.Activities.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ferhat.jetpackcomposeflyticketapp.Activities.SearchResult.SearchResultActivity
import com.ferhat.jetpackcomposeflyticketapp.Activities.Splash.GradientButton
import com.ferhat.jetpackcomposeflyticketapp.Activities.Splash.StatusTopBarColor
import com.ferhat.jetpackcomposeflyticketapp.Domain.LocationModel
import com.ferhat.jetpackcomposeflyticketapp.R
import com.ferhat.jetpackcomposeflyticketapp.ViewModel.MainViewModel
import org.w3c.dom.Text

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val locations = remember { mutableStateListOf<LocationModel>() }
    val viewModel = MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from: String = ""
    var to: String = ""
    var classes: String = ""
    var adultPassgener: String = ""
    var childPassgener: String = ""
    val context = LocalContext.current



    StatusTopBarColor()

    LaunchedEffect(Unit) {
        viewModel.loadLocations().observeForever { result ->
            locations.clear()
            locations.addAll(result)
            showLocationLoading = false


        }
    }

    Scaffold(
        bottomBar = { MyBottomBar() },

        ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.darkPurple))
                .padding(paddingValues = paddingValues)
        ) {
            item { TopBar() }
            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(R.color.purple), shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {

                    //Nereden Bölümü
                    YellowTitle("Nereden")
                    val locationNames: List<String> = locations.map { it.Name }
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Konum Seçin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        from = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    //Nereye Bölümü
                    YellowTitle("Nereye")

                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Konum Seçin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        to = selectedItem
                    }


                    //Yolcu Sayacı
                    Spacer(modifier = Modifier.height(16.dp))
                    YellowTitle("Yolcu Sayısı")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        PassengerCounter(
                            title = "Yetişkin",
                            modifier = Modifier.weight(1f),
                            onItemSelected = { adultPassgener = it }
                        )
                        //HATALI!!! Burayı Bir türlü Çalıştıramadım Hatayıda bulamadım!!!
//                        Spacer(modifier = Modifier.width(16.dp))
//                        PassengerCounter(
//                            title = "Çocuk",
//                            modifier = Modifier.weight(1f),
//                            onItemSelected = { childPassgener = it }
//                        )

                    }

                    //Takvim               (Frhtrmk)
                    Row {
                        YellowTitle("Uçuş Tarihi", Modifier.weight(1f))
                        // YellowTitle("Dönüş Tarihi",Modifier.weight(1f))

                    }
                    DatePickerScreen(Modifier.weight(1f))


                    Spacer(modifier = Modifier.height(16.dp))


                    //Sınıf Seçme Bölümü
                    YellowTitle("Sınıf")
                    val classItems = listOf(
                        "Business class", "First Class",
                        "Economy Class"
                    )

                    DropDownList(
                        items = classItems,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Sınıf Seçin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        to = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    //Arama Butonu (Frht Irmk)

                    GradientButton(
                        onClick = {
                            val intent = Intent(context, SearchResultActivity ::class.java).apply {
                                putExtra("from",from)
                                putExtra("to",to)
                                putExtra("numPassenger",adultPassgener)
                            }

                            startActivity(context,intent,null)

                        },
                        text = "Arama"
                    )

                }
            }
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.orange),
        modifier = modifier
    )
}