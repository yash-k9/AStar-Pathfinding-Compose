package com.yashk9.astar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import com.yashk9.astar.ui.theme.AStarTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AStarTheme {
                HomeScreen()
            }
        }
    }
}
