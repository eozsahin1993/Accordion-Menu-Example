package com.eozsahin.accordion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eozsahin.accordion.ui.components.AccordionGroup
import com.eozsahin.accordion.ui.components.AccordionModel
import com.eozsahin.accordion.ui.theme.AccordionTheme


val modelTechStocks = AccordionModel(
    header = "Technology Stocks",
    rows = mutableListOf(
        AccordionModel.Row(security = "AAPL", "$328.00"),
        AccordionModel.Row(security = "GOOGL", "$328.00"),
        AccordionModel.Row(security = "NFLX", "$198.00"),
        AccordionModel.Row(security = "META", "$200.00"),
        AccordionModel.Row(security = "TSLA", "$769.16"),
    )
)

val modelEnergyStocks = AccordionModel(
    header = "Energy Stocks",
    rows = mutableListOf(
        AccordionModel.Row(security = "MPC", "$96.56"),
        AccordionModel.Row(security = "CVX", "179.71"),
        AccordionModel.Row(security = "DVN", "$77.13"),
        AccordionModel.Row(security = "XOM", "$98.48"),
        AccordionModel.Row(security = "XPRO", "$13.86"),
    )
)

val modelDividendStocks = AccordionModel(
    header = "Dividend Stocks",
    rows = mutableListOf(
        AccordionModel.Row(security = "JNJ", "$178.83"),
        AccordionModel.Row(security = "PG", "$148.63"),
        AccordionModel.Row(security = "KO", "$64.12"),
        AccordionModel.Row(security = "BAC", "$37.42"),
        AccordionModel.Row(security = "MA", "$333.33"),
    )
)

val group = listOf(modelTechStocks, modelEnergyStocks, modelDividendStocks)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccordionTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Markets") }
            )
        }
    ) {
        AccordionGroup(modifier = Modifier.padding(top = 8.dp), group = group)
    }
}

