package com.example.timeatmosphere.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeatmosphere.ui.model.TimeAtmosphere

/**
 * 诗意氛围文案
 * 显示当前时段名称 + 引语，切换时有淡入淡出动画
 */
@Composable
fun AtmosphereText(
    atmosphere: TimeAtmosphere,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = atmosphere,
        transitionSpec = {
            (fadeIn(tween(800)) + slideInVertically(tween(800)) { it / 4 }) togetherWith
                (fadeOut(tween(400)) + slideOutVertically(tween(400)) { -it / 4 })
        },
        label = "atmosphereText"
    ) { target ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(horizontal = 32.dp)
        ) {
            // 时段名
            Text(
                text = target.periodName,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    letterSpacing = 8.sp,
                    shadow = Shadow(
                        color = Color.White.copy(alpha = 0.15f),
                        blurRadius = 12f
                    )
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 引语
            Text(
                text = "「${target.quote}」",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.White.copy(alpha = 0.55f),
                    textAlign = TextAlign.Center,
                    letterSpacing = 4.sp,
                    lineHeight = 28.sp,
                    shadow = Shadow(
                        color = Color.White.copy(alpha = 0.1f),
                        blurRadius = 8f
                    )
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 出处
            Text(
                text = "—— ${target.author}",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.White.copy(alpha = 0.35f),
                    textAlign = TextAlign.Center,
                    letterSpacing = 3.sp
                )
            )
        }
    }
}
