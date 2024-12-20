package com.example.qsub.presentation.auth.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.presentation.auth.items.ItemButton
import com.example.qsub.presentation.auth.items.ItemText
import com.example.qsub.presentation.auth.items.ItemTextBox
import com.example.qsub.presentation.auth.state.UiEvent

@Composable
fun SignUpUi(
  modifier: Modifier = Modifier,
  viewModel: SIgnUpViewModel = hiltViewModel(),
  navigateBack: () -> Boolean,
  authComplete: () -> Unit
) {
  var email by rememberSaveable { mutableStateOf("" + "@gmail.com") }
  var password by rememberSaveable { mutableStateOf("") }

  Column(
    modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.primary)
      .imePadding()
      .padding(20.dp, 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    ItemText(
      label = "SIGN UP",
      labelColor = MaterialTheme.colorScheme.secondary,
      fontSize = 32,
      fontWeight = FontWeight.ExtraBold,
      fontStyle = FontStyle.Italic
    )
    Spacer(modifier = Modifier.padding(20.dp))
    ItemTextBox(
      modifier = Modifier.fillMaxWidth(),
      onChange = {
        email = it
        viewModel.onEvent(UiEvent.EmailChanged(it))
      },
      keyboardType = KeyboardType.Email
    )
    ItemTextBox(
      modifier = Modifier.fillMaxWidth(),
      onChange = {
        password = it
        viewModel.onEvent(UiEvent.PasswordChanged(it))
      },
      keyboardType = KeyboardType.Password,
      icon = Icons.Filled.Lock,
      label = "PASSWORD",
      isPassword = true
    )
    Spacer(modifier = Modifier.padding(5.dp))
    ItemButton(
      onClick = {
        viewModel.onEvent(UiEvent.EnterClicked)
      },

      label = "ENTER",
      borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
      shape = 4,
      modifier = Modifier.fillMaxWidth(0.6f),
      labelColor = MaterialTheme.colorScheme.secondary,
      containerColor = MaterialTheme.colorScheme.primary
    )

    Spacer(modifier = Modifier.padding(5.dp))
    ItemButton(
      onClick = {
        navigateBack()
      },
      label = "SIGN IN NOW",
      borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
      shape = 4,
      modifier = Modifier.fillMaxWidth(0.6f),
      labelColor = MaterialTheme.colorScheme.secondary,
      containerColor = MaterialTheme.colorScheme.primary
    )
  }
}