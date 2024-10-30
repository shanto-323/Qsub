package com.example.qsub.presentation.home.mainpage.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qsub.R
import com.example.qsub.domain.model.DataModel
import com.example.qsub.presentation.ui.theme.LightCard

@Composable
fun CardLazyColumn(
  modifier: Modifier = Modifier, allUserData: List<DataModel>, scrollState: LazyListState
) {
  LazyColumn(
    modifier.fillMaxHeight(),
    state = scrollState
  ) {
    items(allUserData.size) { i ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.3f)
          .padding(10.dp, 1.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
      ) {
        if (allUserData[i] != null) {
          MyCard(
            name = allUserData[i].name,
            time = createdTime(allUserData[i].created),
            query = allUserData[i].query
          )
        }
      }
    }
  }
}


@Composable
fun MyCard(
  modifier: Modifier = Modifier,
  name: String = "Profile Name",
  time: String = "JUST NOW",
  query: String = ""
) {
  var isExpended by rememberSaveable {
    mutableStateOf(false)
  }
  var textQuery by rememberSaveable {
    mutableStateOf("")
  }
  textQuery = if (!isExpended && query.length > 100) {
    query.take(100)
  } else {
    query
  }
  Card(
    shape = RoundedCornerShape(8.dp)
  ) {
    Column(
      modifier
        .fillMaxWidth()
        .background(LightCard)
        .padding(18.dp, 12.dp , 14.dp , 22.dp),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start
    ) {
      Text(text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            fontSize = 22.sp, fontFamily = FontFamily(
              Font(R.font.jet_brains_mono_ferd_font_bold, FontWeight.Normal)
            ), letterSpacing = (-1).sp,
            color = Color.Black
          )
        ) {
          append("$name  ")
        }
        withStyle(
          style = SpanStyle(
            fontSize = 8.sp, fontFamily = FontFamily(
              Font(R.font.roboto_bold, FontWeight.Normal)
            ),
            color = Color.Black
          )
        ) {
          append(time)
        }

      })

      val text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(
              Font(R.font.song_myung_regular, FontWeight.Normal)
            ),color = Color.Black
          )
        ) {
          append(textQuery)
        }
        if (query.length > 100 && !isExpended) {
          pushStringAnnotation("more", annotation = "more")
          withStyle(
            style = SpanStyle(
              fontSize = 10.sp,
              fontFamily = FontFamily(
                Font(R.font.vt323_regular, FontWeight.Normal)
              ),
              color = Color.Red,
            )
          ) {
            append("  ..SHOW MORE")
          }
          pop()
        }
        if (isExpended) {
          pushStringAnnotation("less", annotation = "less")
          withStyle(
            style = SpanStyle(
              fontSize = 10.sp,
              fontFamily = FontFamily(
                Font(R.font.vt323_regular, FontWeight.Normal)
              ),
              color = Color.Red,
            )
          ) {
            append("  ..SHOW LESS")
          }
          pop()
        }
      }

      ClickableText(
        text = text,
        onClick = {
          text.getStringAnnotations("more",1 , text.length)
            .firstOrNull()?.let {
              if (it.item == "more"){
                isExpended = true
              }
            }
          text.getStringAnnotations("less",1 , text.length)
            .firstOrNull()?.let {
              if (it.item == "less"){
                isExpended = false
              }
            }
        }
      )
    }
  }
}
