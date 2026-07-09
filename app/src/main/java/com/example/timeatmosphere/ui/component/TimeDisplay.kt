package com.example.timeatmosphere.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 大号数字时钟 + 秒数 + 日期
 */
@Composable
fun TimeDisplay(
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier
) {
    // 淡化动画
    val fadeAnim = remember { Animatable(1f) }
    var prevMinute by remember { mutableStateOf(minute) }

    LaunchedEffect(minute) {
        if (minute != prevMinute) {
            fadeAnim.snapTo(0.3f)
            fadeAnim.animateTo(1f, tween(800, easing = FastOutSlowInEasing))
            prevMinute = minute
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.alpha(fadeAnim.value)
    ) {
        // 时分
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = String.format("%02d", hour),
                style = timeTextStyle
            )

            Text(
                text = ":",
                style = timeTextStyle.copy(
                    fontSize = 72.sp
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Text(
                text = String.format("%02d", minute),
                style = timeTextStyle
            )
        }

        // 秒数
        Text(
            text = String.format("%02d", second),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.W500,
                color = Color.White.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                shadow = Shadow(
                    color = Color.White.copy(alpha = 0.2f),
                    blurRadius = 10f
                )
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private val timeTextStyle = TextStyle(
    fontSize = 96.sp,
    fontWeight = FontWeight.W600,
    color = Color.White,
    textAlign = TextAlign.Center,
    letterSpacing = 6.sp,
    shadow = Shadow(
        color = Color.White.copy(alpha = 0.4f),
        blurRadius = 30f
    )
)
