package com.example.clock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.navigation.MainNavController
import com.example.clock.ui.theme.ClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainNavController()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavController()
        }
    }
}