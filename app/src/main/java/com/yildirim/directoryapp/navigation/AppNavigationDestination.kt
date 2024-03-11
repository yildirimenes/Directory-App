package com.yildirim.directoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.yildirim.directoryapp.entity.Persons
import com.yildirim.directoryapp.presentation.main_page.MainPage
import com.yildirim.directoryapp.presentation.person_detail_page.PersonDetailPage
import com.yildirim.directoryapp.presentation.person_register_page.PersonRegisterPage

@Composable
fun PageController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_page") {
        composable("main_page"){
            MainPage(navController = navController)
        }
        composable("person_register_page"){
            PersonRegisterPage(navController = navController)
        }
        composable("person_detail_page/{person}", arguments = listOf(
            navArgument("person"){ type = NavType.StringType }
        )){
            val json = it.arguments?.getString("person")
            val objects = Gson().fromJson(json,Persons::class.java)
            PersonDetailPage(navController = navController,objects)
        }
    }
}