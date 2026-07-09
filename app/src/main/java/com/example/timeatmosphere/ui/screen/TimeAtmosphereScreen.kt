package com.example.timeatmosphere.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timeatmosphere.ui.component.AtmosphereText
import com.example.timeatmosphere.ui.component.ParticleField
import com.example.timeatmosphere.ui.component.SkyBackground
import com.example.timeatmosphere.ui.component.TimeDisplay
import com.example.timeatmosphere.ui.model.getTimeAtmosphere
import kotlinx.coroutines.delay
import java.util.Calendar

/**
 * 横屏主屏幕 — 左侧时间 + 右侧氛围文案
 */
@Composable
fun TimeAtmosphereScreen() {
    var currentHour by remember { mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    var currentMinute by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }
    var currentSecond by remember { mutableStateOf(Calendar.getInstance().get(Calendar.SECOND)) }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Calendar.getInstance()
            currentHour = now.get(Calendar.HOUR_OF_DAY)
            currentMinute = now.get(Calendar.MINUTE)
            currentSecond = now.get(Calendar.SECOND)
            delay(500L)
        }
    }

    val atmosphere = getTimeAtmosphere(currentHour)

    Box(modifier = Modifier.fillMaxSize()) {
        // 底层：动态天空渐变
        SkyBackground(atmosphere = atmosphere)

        // 中层：氛围粒子
        ParticleField(particleType = atmosphere.particleType)

        // 顶层：横屏左右布局
        Row(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // 左侧：时间
            TimeDisplay(
                hour = currentHour,
                minute = currentMinute,
                second = currentSecond,
                modifier = Modifier.weight(0.55f)
            )

            // 右侧：氛围文案
            AtmosphereText(
                atmosphere = atmosphere,
                modifier = Modifier.weight(0.45f)
            )
        }
    }
}
