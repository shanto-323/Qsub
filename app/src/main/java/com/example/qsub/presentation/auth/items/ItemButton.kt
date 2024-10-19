package com.example.qsub.presentation.auth.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  containerColor: Color = Color.White,
  labelColor: Color = Color.Black,
  label: String = "Button",
  fontSize: Int = 16,
  fontWeight: FontWeight = FontWeight.Normal,
  fontStyle: FontStyle = FontStyle.Normal,
  borderStroke: BorderStroke = BorderStroke(2.dp, Color.Black),
  shape : Int = 10
) {
  Button(
    onClick = onClick,
    modifier,
    colors = ButtonDefaults.buttonColors(
      containerColor = containerColor
    ),
    border = borderStroke,
    shape = RoundedCornerShape(shape.dp)
  ) {
    ItemText(
      label = label,
      labelColor = labelColor,
      fontSize = fontSize,
      fontWeight = fontWeight,
      fontStyle = fontStyle
    )
  }
}


