package com.eozsahin.accordion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eozsahin.accordion.ui.theme.*
import com.eozsahin.accordion.ui.theme.Green300
import kotlin.math.exp


val modelTechStock = AccordionModel(
    header = "Technology Stocks",
    columns = mutableListOf(
        AccordionModel.Row(security = "AAPL", "$328.00"),
        AccordionModel.Row(security = "GOOGL", "$328.00"),
        AccordionModel.Row(security = "NFLX", "$198.00"),
        AccordionModel.Row(security = "META", "$200.00"),
        AccordionModel.Row(security = "TSLA", "$769.16"),
    )
)

val modelEnergyStock = AccordionModel(
    header = "Energy Stocks",
    columns = mutableListOf(
        AccordionModel.Row(security = "MPC", "$96.56"),
        AccordionModel.Row(security = "CVX", "179.71"),
        AccordionModel.Row(security = "DVN", "$77.13"),
        AccordionModel.Row(security = "XOM", "$98.48"),
        AccordionModel.Row(security = "XPRO", "$13.86"),
    )
)

val modelDividendStocks = AccordionModel(
    header = "Dividend Stocks",
    columns = mutableListOf(
        AccordionModel.Row(security = "JNJ", "$178.83"),
        AccordionModel.Row(security = "PG", "$148.63"),
        AccordionModel.Row(security = "KO", "$64.12"),
        AccordionModel.Row(security = "BAC", "$37.42"),
        AccordionModel.Row(security = "MA", "$333.33"),
    )
)


class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccordionTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Markets") }
                        )
                    }
                ) {
                    Surface(Modifier.fillMaxSize(), color = Gray50) {
                        Column(Modifier.fillMaxSize()) {
                            Accordion(model = modelTechStock)
                            Accordion(model = modelEnergyStock)
                            Accordion(model = modelDividendStocks)
                        }
                    }
                }
            }
        }
    }
}

data class AccordionModel(
    val header: String,
    val columns: List<Row>
) {
    data class Row(
        val security: String,
        val stockPrice: String
    )
}

@ExperimentalAnimationApi
@Composable
fun Accordion(modifier: Modifier = Modifier, model: AccordionModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        AccordionHeader(title = model.header, isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = White,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Gray200),
                elevation = 8.dp,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                LazyColumn {
                    items(model.columns) { row ->
                        AccordionRow(row)
                        Divider(color = Gray200, thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AccordionHeader(
    title: String = "Header",
    isExpanded: Boolean = false,
    onTapped: () -> Unit = {}
) {
    var degrees = if (isExpanded) 180f else 0f


    Surface(
        color = White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray200),
        elevation = 8.dp,
        modifier = Modifier.clickable { onTapped() }
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, Modifier.weight(1f), style = Typography.h6.copy(fontWeight = FontWeight.Bold), color = MedGray3)

            Surface(shape = CircleShape, color = MedGray3) {
                Icon(Icons.Outlined.ArrowDropDown, "arrow-down", Modifier.rotate(degrees), tint = Color.White)
            }
        }
    }

}

@Preview
@Composable
fun AccordionRow(model: AccordionModel.Row = AccordionModel.Row("AAPL", "$328.89")) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        Text(model.security, Modifier.weight(1f), style = bodyBold, color = MedGray3)
        Surface(color = Green300, shape = RoundedCornerShape(8.dp)) {
            Text(model.stockPrice, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp), style = bodyBold, color = White)
        }
    }
}

