package com.zycg.stop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zycg.stop.ui.MainScreen
import com.zycg.stop.ui.theme.ZhongYanStopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZhongYanStopTheme {
                MainScreen()
            }
        }
    }
}
