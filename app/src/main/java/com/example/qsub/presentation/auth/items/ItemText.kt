package com.example.qsub.presentation.auth.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun ItemText(
  labelColor: Color = Color.Black,
  label: String = "Button",
  fontSize: Int = 16,
  fontWeight: FontWeight = FontWeight.Normal,
  fontStyle: FontStyle = FontStyle.Normal
) {
  Text(
    text = label,
    style = TextStyle(
      color = labelColor,
      fontSize = fontSize.sp,
      fontWeight = fontWeight,
      fontStyle = fontStyle,
    )
  )
}