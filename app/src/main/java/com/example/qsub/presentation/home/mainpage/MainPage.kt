package com.example.qsub.presentation.home.mainpage

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.domain.model.DataModel
import com.example.qsub.presentation.auth.items.ItemButton
import com.example.qsub.presentation.home.mainpage.items.MainPageState
import com.example.qsub.presentation.home.mainpage.items.createdTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  viewModel: MainPageViewModel = hiltViewModel(),
  navigateToAuth: () -> Unit
) {
  val list = listOf(
    MainPageState(
      tabName = "Movie",
      beforeImage = Icons.Outlined.Movie,
      afterImage = Icons.Filled.Movie
    ),
    MainPageState(
      tabName = "Bookmarks",
      beforeImage = Icons.Outlined.Bookmarks,
      afterImage = Icons.Filled.Bookmarks
    )
  )
  val pagerState = rememberPagerState { list.size }
  var selectedTabIndex by remember { mutableIntStateOf(0) }

  LaunchedEffect(selectedTabIndex) { pagerState.animateScrollToPage(selectedTabIndex) }
  LaunchedEffect(pagerState.currentPage) { selectedTabIndex = pagerState.currentPage }
  var data = viewModel.state.data
  var allUserData = viewModel.state.allUserData
  LaunchedEffect(viewModel) {
    viewModel.getData()
    viewModel.getAllUserData()
  }
  Column(
    modifier
      .fillMaxSize()
      .background(Color.White)
      .systemBarsPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
  ) {
    HorizontalPager(
      state = pagerState,
      modifier = Modifier
        .fillMaxSize()
        .weight(1f)
    ) { page ->
      Box(
        modifier = Modifier
          .fillMaxHeight(0.8f),
      ) {
        when (page) {
          0 -> {
            LazyColumn(
              modifier.fillMaxHeight()
            ) {
              items(data.size) { i ->
                Column(
                  modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(0.dp, 5.dp, 10.dp, 10.dp)
                ) {
                  Text(
                    text = buildAnnotatedString {
                      withStyle(
                        style = androidx.compose.ui.text.SpanStyle(
                          color = Color.Green,
                          fontSize = 14.sp,
                          fontWeight = FontWeight.ExtraBold
                        )
                      ) {
                        append(data[i].name)
                      }
                    }
                  )
                  if (data[i].query != "") {
                    Text(text = data[i].query, color = Color.DarkGray)
                  }
                }
              }
            }
          }
          1 -> {
            LazyColumn(
              modifier.fillMaxHeight()
            ) {
              items(allUserData.size) { i ->
                Column(
                  modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(0.dp, 5.dp, 10.dp, 10.dp)
                ) {
                  Text(
                    text = buildAnnotatedString {
                      withStyle(
                        style = androidx.compose.ui.text.SpanStyle(
                          color = Color.Green,
                          fontSize = 14.sp,
                          fontWeight = FontWeight.ExtraBold
                        )
                      ) {
                        append(allUserData[i].name)
                      }
                      append(createdTime(allUserData[i].created))
                    }
                  )
                  if (allUserData[i].query != "") {
                    Text(text = allUserData[i].query, color = Color.DarkGray)
                  }
                }
              }
            }
          }
        }
      }
    }

    Row(
      modifier.fillMaxWidth()
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
      FloatingActionButton(onClick = {
        navigateToAuth()
      }) {
        Row(
          modifier
            .weight(1f)
            .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Create,
            contentDescription = "Create",
            modifier.padding(5.dp, 0.dp)
          )
          Text(text = "Add Query")
        }
      }
    }
  }
}