package com.bloo.shrimps2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ResultScreen(navController: NavController,apiService: ApiService,sharedViewModel: SharedViewModel){
    val labelSet by sharedViewModel.labelSetFlow.collectAsState(setOf())
    var response by remember { mutableStateOf<ApiResponse?>(null) }


    println(labelSet)
    LaunchedEffect(labelSet){
        println("LaunchedEffect executed")
        if(labelSet.size in 1..5) {
            sendRequest(apiService, labelSet.toList()) {
                response = it
            }
        }
    }

    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors=CardDefaults.cardColors(
                    containerColor = Color(0xFFD3D3D3)
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Observed Symptoms", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp))
                    labelSet.forEach { 
                        Text(text = it, modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (response != null && response!!.diseases.isEmpty()) {
                // Display "Not found" message
                Text("Not found",modifier=Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Bold)
            }
            if (response != null) {
                // Display diseases and corresponding symptoms
                response?.diseases?.forEachIndexed { index, disease ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors=CardDefaults.cardColors(
                            containerColor = Color(0xFFD3D3D3)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Disease: $disease", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp))
                            response?.symptoms?.getOrNull(index)?.forEach { symptom ->
                                Text(text = symptom, modifier = Modifier.padding(start = 16.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                // Loading or placeholder content while waiting for the response
                Column(
                    modifier=Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("Number of checked symptoms must be 1-5.",modifier=Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Bold)
                }
            }
        }
    }

}


private fun sendRequest(apiService: ApiService, values: List<String>, onResponse: (ApiResponse) -> Unit) {

    val requestBody = DiseaseRequest(symptoms = values)

    val call = apiService.getDisease(requestBody)


    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                response.body()?.let {diseaseResponse ->
                    println("Disease: ${diseaseResponse.diseases}")
                    println("Symptoms: ${diseaseResponse.symptoms}")



                    // Call the provided lambda with the response
                    onResponse(diseaseResponse)
                }
            } else {
                // In case of an error, create a dummy ApiResponse with a default value
                //val dummyResponse = ApiResponse(result = -1) // You can choose an appropriate default value
                //  onResponse(dummyResponse)
                println("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            // Handle failure
            println("Request failed: ${t.message}")

            // In case of failure, create a dummy ApiResponse with a default value
            // val dummyResponse = ApiResponse(result = -1) // You can choose an appropriate default value
            //onResponse(dummyResponse)
        }
    })
}