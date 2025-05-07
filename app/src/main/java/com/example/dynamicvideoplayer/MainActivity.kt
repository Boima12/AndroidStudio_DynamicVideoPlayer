package com.example.dynamicvideoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dynamicvideoplayer.ui.theme.DynamicVideoPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicVideoPlayer()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DynamicVideoPlayerTheme {
        DynamicVideoPlayer()
    }
}