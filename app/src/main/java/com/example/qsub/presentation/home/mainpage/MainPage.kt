package com.example.qsub.presentation.home.mainpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.PrivateConnectivity
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.PrivateConnectivity
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qsub.R
import com.example.qsub.presentation.home.mainpage.items.CardLazyColumn
import com.example.qsub.presentation.home.mainpage.items.MainPageState
import com.example.qsub.presentation.ui.theme.DarkCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  viewModel: MainPageViewModel = hiltViewModel(),
  navigateToAuth: () -> Unit
) {
  val list = listOf(
    MainPageState(
      tabName = "All",
      beforeImage = Icons.Outlined.AllInclusive,
      afterImage = Icons.Filled.AllInclusive
    ),
    MainPageState(
      tabName = "Only Me",
      beforeImage = Icons.Outlined.PrivateConnectivity,
      afterImage = Icons.Filled.PrivateConnectivity
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

  val lazyListState = rememberLazyListState()
  var fabVisible by remember { mutableStateOf(true) }

  LaunchedEffect(lazyListState) {
    snapshotFlow { lazyListState.firstVisibleItemIndex }
      .collect { index ->
        fabVisible = index == 0
      }
  }

  Scaffold(
    floatingActionButton = {
      if (fabVisible) {
        FloatingActionButton(
          onClick = {
            navigateToAuth()
          },
          modifier.padding(10.dp),
          containerColor = DarkCard,
          contentColor = Color.Black,
          elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 30.dp,
          )
        ) {
          Row(
            modifier
              .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Create,
              contentDescription = "Create",
              modifier.padding(end = 5.dp)
            )
            Text(text = "Add Query")
          }
        }
      }
    },
    content = {
      Column(
        modifier
          .fillMaxSize()
          .padding(it)
          .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .padding(30.dp, 0.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Start
        ) {
          Text(
            text = "QSUB",
            style = TextStyle(
              color = MaterialTheme.colorScheme.onTertiary,
              fontSize = 32.sp,
              fontWeight = FontWeight.ExtraBold,
              fontFamily = FontFamily.Serif

            )
          )
          Box(
            modifier.weight(1f),
            contentAlignment = Alignment.BottomEnd
          ) {
            IconButton(
              onClick = { viewModel.signOut() },
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
              ) {
                Text(
                  text = " SIGN OUT  ",
                  style = TextStyle(
                    fontSize = 12.sp, fontFamily = FontFamily(
                      Font(R.font.roboto_light, FontWeight.Normal)
                    ),
                    color = MaterialTheme.colorScheme.secondary
                  )
                )
                Icon(
                  imageVector = Icons.Filled.Output,
                  contentDescription = "SignOut",
                  tint = MaterialTheme.colorScheme.secondary,
                )
              }
            }
          }
        }
        TabRow(
          selectedTabIndex = selectedTabIndex,
          modifier = Modifier
            .fillMaxWidth(),
          containerColor = MaterialTheme.colorScheme.background,
          contentColor = MaterialTheme.colorScheme.secondary,
          divider = {
            Canvas(modifier = Modifier.fillMaxWidth()) {
              drawLine(
                color = Color.Green,
                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                strokeWidth = 5f
              )
            }
          }
        ) {
          list.forEachIndexed { index, state ->
            Tab(
              text = {
                Text(state.tabName)
              },
              selected = index == selectedTabIndex,
              onClick = {
                selectedTabIndex = index
              },
              icon = {
                Icon(
                  imageVector = if (selectedTabIndex == index) state.afterImage else state.beforeImage,
                  contentDescription = null
                )
              }
            )
          }
        }
        HorizontalPager(
          state = pagerState,
          modifier = Modifier
            .fillMaxSize()
        ) { page ->
          when (page) {
            0 -> {
              CardLazyColumn(allUserData = allUserData, scrollState = lazyListState)
            }

            1 -> {
              CardLazyColumn(allUserData = data, scrollState = lazyListState)
            }
          }
        }
      }
    }
  )
}