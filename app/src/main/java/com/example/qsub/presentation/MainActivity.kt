package com.example.qsub.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qsub.presentation.auth.login.LoginUi
import com.example.qsub.presentation.navigation.Navigation
import com.example.qsub.presentation.ui.theme.QsubTheme
import com.example.qsub.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel by viewModels<MainActivityViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge(
      statusBarStyle = SystemBarStyle.auto(
        Color.Transparent.hashCode(), Color.Transparent.hashCode()
      ),
      navigationBarStyle = SystemBarStyle.auto(
        Color.Transparent.hashCode(), Color.Transparent.hashCode()
      )
    )
    setContent {
      QsubTheme {
        val authState = viewModel.getAuthState.collectAsState().value
        val navController = rememberNavController()
        Navigation(
          navHostController = navController,
          startDestination = if (authState!!) Constants.AUTH_NAVIGATION else Constants.HOME_NAVIGATION
        )
      }
    }
  }
}



