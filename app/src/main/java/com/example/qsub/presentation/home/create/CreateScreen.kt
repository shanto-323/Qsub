package com.example.qsub.presentation.home.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Queue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.presentation.home.create.items.CreateEvent

@Composable
fun CreateScreen(
  modifier: Modifier = Modifier,
  viewModel: CreateScreenViewModel = hiltViewModel(),
  navigate: () -> Boolean
) {

  var query by rememberSaveable {
    mutableStateOf("")
  }

  Column(
    modifier
      .fillMaxSize()
      .systemBarsPadding(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier.padding(20.dp, 0.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      IconButton(onClick = {
        navigate()
      }) {
        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
      }
      Box(modifier = modifier.weight(1f))
      TextButton(onClick = { viewModel.onResponse(CreateEvent.Submit) }) {
        Text(text = "POST")
      }
    }
    Spacer(modifier = Modifier.padding(10.dp))
    Row(
      modifier
        .weight(1f)
        .padding(10.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(
        imageVector = Icons.Filled.Queue,
        contentDescription = "Queue",
        modifier
          .fillMaxWidth(0.1f)
          .aspectRatio(1f)
      )
      TextField(
        value = query,
        onValueChange = {
          query = it
          viewModel.onResponse(CreateEvent.Query(it))
        },
        modifier
          .weight(1f)
          .padding(5.dp, 0.dp)
      )
    }

  }
}