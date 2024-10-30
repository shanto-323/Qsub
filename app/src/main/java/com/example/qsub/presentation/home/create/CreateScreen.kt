package com.example.qsub.presentation.home.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Queue
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.R
import com.example.qsub.domain.model.Response
import com.example.qsub.presentation.home.create.items.CreateEvent

@Composable
fun CreateScreen(
  modifier: Modifier = Modifier,
  viewModel: CreateScreenViewModel = hiltViewModel(),
  navigate: () -> Unit
) {

  var query by rememberSaveable { mutableStateOf("") }
  var wordCount by rememberSaveable { mutableIntStateOf(0) }
  var response = viewModel.response

  when (response) {
    is Response.Error -> TODO()
    Response.Loading -> {

    }

    is Response.Success -> {
      if (response.data) {
        navigate()
      }
    }
  }

  Column(
    modifier
      .fillMaxSize()
      .systemBarsPadding()
      .imePadding()
      .background(MaterialTheme.colorScheme.primary),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier.padding(20.dp, 10.dp, 20.dp, 0.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {

      Icon(
        imageVector = Icons.Filled.Clear,
        contentDescription = "Clear",
        tint = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
          .padding(0.dp, 0.dp, 20.dp, 0.dp)
          .size(32.dp)
          .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { navigate() }
          ),
      )

      TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          viewModel.onResponse(CreateEvent.Submit)
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
          defaultElevation = 30.dp,
        )
      ) {
        Text(
          text = "POST",
          style = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
          ),
          modifier = Modifier
            .fillMaxWidth(),
        )
      }
    }

    Spacer(modifier = Modifier.padding(5.dp))
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.fillMaxSize()
    ) {
      if (response == Response.Loading) {
        CircularProgressIndicator(
          modifier = Modifier
            .size(24.dp)
            .align(Alignment.Center),
          color = MaterialTheme.colorScheme.secondary
        )
      }
      Row(
        modifier
          .fillMaxSize()
          .padding(10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
      ) {
        Box(
          modifier
            .fillMaxWidth(0.1f)
            .fillMaxHeight(0.1f),
          contentAlignment = Alignment.BottomEnd
        ) {
          Icon(
            imageVector = Icons.Filled.Queue,
            contentDescription = "Queue",
            modifier.fillMaxSize(),
            tint = MaterialTheme.colorScheme.secondary
          )
        }
        TextField(
          value = query,
          onValueChange = {
            if (it.length <= 500) {
              query = it
              viewModel.onResponse(CreateEvent.Query(it))
              wordCount = it.length
            }
          },
          modifier
            .weight(1f)
            .padding(2.dp, 0.dp),
          colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary

          ),
          textStyle = TextStyle(
            fontSize = 22.sp,
            lineHeight = 32.sp,
            fontFamily = FontFamily(
              Font(R.font.roboto_light, FontWeight.Normal)
            ),
          ),
          placeholder = {
            Text(
              text = "what's on your mind?",
              style = TextStyle(
                fontSize = 20.sp,
              ),
              modifier = Modifier
                .fillMaxWidth(),
            )
          },
          supportingText = {
            Text(
              text = "$wordCount / 500 words",
              color = if (wordCount == 500) Color.Red else Color.Gray,
              style = TextStyle(
                fontSize = 12.sp,
                textAlign = TextAlign.End
              ),
              modifier = Modifier
                .fillMaxWidth(),
            )
          }
        )
      }
    }

  }
}