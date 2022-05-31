package com.eozsahin.accordion.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eozsahin.accordion.ui.theme.*

data class AccordionModel(
    val header: String,
    val columns: List<Row>
) {
    data class Row(
        val security: String,
        val stockPrice: String
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Accordion(modifier: Modifier = Modifier, model: AccordionModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        AccordionHeader(title = model.header, isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = Color.White,
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
    val degrees = if (isExpanded) 180f else 0f

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray200),
        elevation = 8.dp,
        modifier = Modifier.clickable { onTapped() }
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, Modifier.weight(1f), style = accordionHeaderStyle, color = Gray600)
            Surface(shape = CircleShape, color = LightBlue900.copy(alpha = 0.65f)) {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    contentDescription = "arrow-down",
                    modifier = Modifier.rotate(degrees),
                    tint = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
fun AccordionRow(
    model: AccordionModel.Row = AccordionModel.Row("AAPL", "$328.89")
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(model.security, Modifier.weight(1f), style = bodyBold, color = MedGray3)
        Surface(color = Green500, shape = RoundedCornerShape(8.dp), elevation = 2.dp) {
            Text(
                text = model.stockPrice,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                style = tags,
                color = Color.White
            )
        }
    }
}