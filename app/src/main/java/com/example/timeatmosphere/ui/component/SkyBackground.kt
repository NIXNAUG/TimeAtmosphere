package com.example.timeatmosphere.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.timeatmosphere.ui.model.TimeAtmosphere

/**
 * 动态天空渐变背景
 * 根据时段平滑过渡颜色，并施加微弱的呼吸动画
 */
@Composable
fun SkyBackground(
    atmosphere: TimeAtmosphere,
    modifier: Modifier = Modifier
) {
    // 颜色过渡动画 — 当 atmosphere 切换时平滑插值
    val animatedColors = remember { Animatable(0f) }

    LaunchedEffect(atmosphere) {
        animatedColors.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
        )
        // 到位后重置以便下次切换
        animatedColors.snapTo(0f)
    }

    // 呼吸动画 — 微弱的亮度波动
    val infiniteTransition = rememberInfiniteTransition(label = "breath")
    val breathAlpha by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathAlpha"
    )

    // 缓存的天空颜色 — 使用当前 atmosphere 的颜色
    val skyColors = remember(atmosphere) { atmosphere.skyColors }

    Canvas(modifier = modifier.fillMaxSize()) {
        // 垂直渐变
        val brush = Brush.verticalGradient(
            colors = skyColors.map { it.copy(alpha = it.alpha * breathAlpha) },
            startY = 0f,
            endY = size.height
        )
        drawRect(brush = brush, size = size)
    }
}
