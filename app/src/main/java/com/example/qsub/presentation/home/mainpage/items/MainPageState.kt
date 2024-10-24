package com.example.qsub.presentation.home.mainpage.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.qsub.domain.model.DataModel

data class MainPageState(
  val data: List<DataModel> = emptyList(),
  val allUserData: List<DataModel> = emptyList(),

  val tabName: String = "",
  val beforeImage: ImageVector = Icons.Filled.Movie,
  val afterImage: ImageVector = Icons.Filled.Movie,
)

