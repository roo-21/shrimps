package com.bloo.shrimps2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bloo.shrimps2.ui.theme.MyApplicationTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val apiService: ApiService = createApiService()

    private fun createApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://172.16.75.22:5000/api/") // http://192.168.1.36:5000 //"http://172.16.75.22:5000/api/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home" ){
                    composable("home"){
                        HomeScreen(navController)
                    }
                    composable("SymptomsAnalyser"){
                        SymptomsAnalyser(navController,sharedViewModel)
                    }
                    composable("DiseaseDB"){
                        DiseaseDB(navController)
                    }
                    composable("ResultScreen"){
                        ResultScreen(navController,apiService,sharedViewModel)
                    }
                }
            }
        }
    }
}