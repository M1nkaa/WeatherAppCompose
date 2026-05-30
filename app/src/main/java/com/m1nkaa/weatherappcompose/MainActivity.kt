package com.m1nkaa.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    private val API_KEY = "48290f0f8998b978845afd28844fa6d0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                WeatherScreen(apiKey = API_KEY)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    apiKey: String,
    viewModel: WeatherViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var city by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌤 Weather") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (city.trim().isNotEmpty()) {
                            viewModel.fetchWeather(city.trim(), apiKey)
                        }
                    }
                )
            )

            Button(
                onClick = {
                    if (city.trim().isNotEmpty()) {
                        viewModel.fetchWeather(city.trim(), apiKey)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val s = state) {
                is WeatherState.Idle -> {
                    Text(
                        text = "Enter a city name to see the weather",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                is WeatherState.Loading -> {
                    CircularProgressIndicator()
                }
                is WeatherState.Success -> {
                    WeatherCard(data = s.data)
                }
                is WeatherState.Error -> {
                    Text(
                        text = s.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherCard(data: WeatherResponse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = data.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${data.main.temp.toInt()}°C",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = data.weather.firstOrNull()?.description ?: "",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Feels like", color = Color.Gray, fontSize = 12.sp)
                        Text("${data.main.feels_like.toInt()}°C", fontWeight = FontWeight.Medium)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Humidity", color = Color.Gray, fontSize = 12.sp)
                        Text("${data.main.humidity}%", fontWeight = FontWeight.Medium)
                    }
                }
            }
    }
}