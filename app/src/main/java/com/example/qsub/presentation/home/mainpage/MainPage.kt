package com.example.qsub.presentation.home.mainpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.presentation.auth.items.ItemButton

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  viewModel: MainPageViewModel = hiltViewModel(),
  navigateToAuth: () -> Unit
) {
  Column(
    modifier
      .fillMaxSize()
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    ItemButton(
      onClick = {
        viewModel.signOut()
      },
      borderStroke = BorderStroke(1.dp, Color.Black),
      shape = 4,
      modifier = Modifier.fillMaxWidth(0.6f),
      label = "SIGN OUT"
    )
    ItemButton(
      onClick = {
        viewModel.putData()
      },
      borderStroke = BorderStroke(1.dp, Color.Black),
      shape = 4,
      modifier = Modifier.fillMaxWidth(0.6f),
      label = "PUT DATA"
    )
    FloatingActionButton(onClick = {
      navigateToAuth()
    }) {
      Row(
        modifier.weight(1f).padding(5.dp).fillMaxWidth(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ){
        Icon(imageVector = Icons.Filled.Create, contentDescription = "Create",modifier.padding(5.dp,0.dp))
        Text(text = "Add Query")
      }
    }
  }
}