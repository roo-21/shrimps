package com.bloo.shrimps2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            //goes to page with symptoms
            navController.navigate("SymptomsAnalyser")
        }) {
            Text(text = "Disease Investigator")
        }
        Button(onClick = {
            navController.navigate("DiseaseDB")
        }){
            //for going to page with Diseases information
            Text(text="Coming Soon (Or Not)")
        }
    }
}