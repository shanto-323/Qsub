package com.example.qsub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.qsub.presentation.auth.login.LoginUi
import com.example.qsub.presentation.auth.signup.SignUpUi
import com.example.qsub.presentation.home.create.CreateScreen
import com.example.qsub.presentation.home.mainpage.MainScreen
import com.example.qsub.utils.Constants

@Composable
fun Navigation(
  navHostController: NavHostController = rememberNavController(),
  startDestination: String = Constants.AUTH_NAVIGATION
) {

  NavHost(
    navController = navHostController,
    startDestination = startDestination,
  ) {
    navigation(
      startDestination = Constants.SIGN_IN_SCREEN,
      route = Constants.AUTH_NAVIGATION
    ) {
      composable(route = Constants.SIGN_IN_SCREEN) {
        LoginUi(
          navigateToSignUp = {
            navHostController.navigate(Constants.SIGNUP_SCREEN)
          },
          authComplete = {
            navHostController.navigate(Constants.HOME_NAVIGATION) {
              popUpTo(Constants.AUTH_NAVIGATION) {
                inclusive = true
              }
            }
          }
        )
      }
      composable(route = Constants.SIGNUP_SCREEN) {
        SignUpUi(
          navigateBack = {
            navHostController.popBackStack()
          },
          authComplete = {
            navHostController.navigate(Constants.HOME_NAVIGATION) {
              popUpTo(Constants.AUTH_NAVIGATION) {
                inclusive = true
              }
            }
          }
        )
      }
    }

    navigation(
      startDestination = Constants.HOME_SCREEN,
      route = Constants.HOME_NAVIGATION
    ) {
      composable(route = Constants.HOME_SCREEN) {
        MainScreen(
          navigateToAuth = {
            navHostController.navigate(Constants.CREATE_SCREEN)
          }
        )
      }
      composable(route = Constants.CREATE_SCREEN) {
        CreateScreen(
          navigate = {
            navHostController.navigate(Constants.HOME_NAVIGATION){
              popUpTo(Constants.HOME_SCREEN) {
                inclusive = true
              }
            }
          }
        )
      }
    }
  }
}