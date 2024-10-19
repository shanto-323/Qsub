package com.example.qsub.presentation.auth.items

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.GMobiledata
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun ItemTextBox(
  modifier: Modifier = Modifier,
  onChange: (String) -> Unit = {},
  keyboardType: KeyboardType = KeyboardType.Text,
  label: String = "EMAIL",
  icon: ImageVector = Icons.Filled.Email,
  primaryColor: Color = Color.White,
  secondaryColor: Color = Color.Black,
  isPassword: Boolean = false,
) {
  var text by rememberSaveable { mutableStateOf("") }
  var passWordVisibility by rememberSaveable { mutableStateOf(false) }

  OutlinedTextField(
    value = text,
    onValueChange = {
      text = it
      onChange(it)
    },
    modifier = Modifier.then(modifier),
    colors = OutlinedTextFieldDefaults.colors(
      focusedContainerColor = primaryColor,
      unfocusedContainerColor = primaryColor,
      focusedBorderColor = Color.Cyan,
      unfocusedBorderColor = secondaryColor
    ),
    suffix = {
      if (!isPassword) {
        ItemText(
          label = "@google.com",
          fontSize = 12,
          fontWeight = FontWeight.SemiBold
        )
      }else null
    },
    singleLine = true,
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType
    ),
    label = {
      ItemText(
        label = label,
        labelColor = secondaryColor,
        fontSize = 12,
        fontWeight = FontWeight.Bold
      )
    },
    leadingIcon = {
      Icon(
        imageVector = icon,
        contentDescription = label,
        tint = secondaryColor
      )
    },
    trailingIcon = {
      if (isPassword) {
        IconButton(onClick = { passWordVisibility = !passWordVisibility }) {
          Icon(
            imageVector = if (!passWordVisibility)Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = label,
            tint = secondaryColor
          )
        }
      }else {
        Icon(
          imageVector = Icons.Filled.GMobiledata,
          contentDescription = label,
          tint = secondaryColor
        )
      }
    },
    visualTransformation = if (isPassword && !passWordVisibility) PasswordVisualTransformation() else VisualTransformation.None
  )
}
