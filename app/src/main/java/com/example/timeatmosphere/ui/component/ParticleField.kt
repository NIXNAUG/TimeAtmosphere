package com.example.timeatmosphere.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import com.example.timeatmosphere.ui.model.ParticleType
import kotlin.random.Random

/**
 * 单个粒子
 */
data class Particle(
    var x: Float,
    var y: Float,
    var vx: Float,
    var vy: Float,
    val baseRadius: Float,
    var alpha: Float,
    var alphaDir: Float = 1f,
    val twinkleSpeed: Float = 1f,
    val isShooting: Boolean = false, // 流星模式
    var shootLife: Float = 0f
)

/**
 * 氛围粒子场
 * 根据粒子类型渲染不同行为的光点
 */
@Composable
fun ParticleField(
    particleType: ParticleType,
    modifier: Modifier = Modifier
) {
    val particles = remember {
        mutableStateListOf<Particle>().apply {
            val count = when (particleType) {
                ParticleType.STARRY_NIGHT -> 120
                else -> 70
            }
            repeat(count) { add(createParticle(particleType)) }
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // 更新并绘制每个粒子
        particles.forEach { p ->
            updateParticle(p, w, h, particleType)
            drawParticle(p, particleType)
        }
    }
}

/**
 * 根据粒子类型创建初始粒子
 */
private fun createParticle(type: ParticleType): Particle {
    val x = Random.nextFloat()
    val y = Random.nextFloat()
    return when (type) {
        ParticleType.GOLDEN_DUST -> Particle(
            x = x, y = y,
            vx = (Random.nextFloat() - 0.5f) * 0.0003f,
            vy = -Random.nextFloat() * 0.0004f - 0.0001f,
            baseRadius = 2f + Random.nextFloat() * 3f,
            alpha = Random.nextFloat() * 0.4f + 0.2f,
            twinkleSpeed = 0.5f + Random.nextFloat() * 1.5f
        )

        ParticleType.LAZY_FLOAT -> Particle(
            x = x, y = y,
            vx = (Random.nextFloat() - 0.5f) * 0.0002f,
            vy = -Random.nextFloat() * 0.00015f,
            baseRadius = 1.5f + Random.nextFloat() * 2f,
            alpha = Random.nextFloat() * 0.25f + 0.1f,
            twinkleSpeed = 0.3f + Random.nextFloat()
        )

        ParticleType.WARM_EMBER -> Particle(
            x = x, y = y,
            vx = (Random.nextFloat() - 0.5f) * 0.0004f,
            vy = -Random.nextFloat() * 0.0006f - 0.0002f,
            baseRadius = 2.5f + Random.nextFloat() * 4f,
            alpha = Random.nextFloat() * 0.5f + 0.3f,
            twinkleSpeed = 0.8f + Random.nextFloat() * 2f
        )

        ParticleType.STARRY_NIGHT -> {
            val isShooting = Random.nextFloat() < 0.03f // 3% 概率是流星
            Particle(
                x = x, y = y,
                vx = if (isShooting) Random.nextFloat() * 0.002f + 0.003f else 0f,
                vy = if (isShooting) Random.nextFloat() * 0.001f + 0.002f else 0f,
                baseRadius = if (isShooting) 1f + Random.nextFloat() else 0.8f + Random.nextFloat() * 2.2f,
                alpha = if (isShooting) 1f else Random.nextFloat() * 0.7f + 0.1f,
                twinkleSpeed = 0.2f + Random.nextFloat() * 2f,
                isShooting = isShooting,
                shootLife = if (isShooting) Random.nextFloat() * 0.6f + 0.2f else 0f
            )
        }
    }
}

/**
 * 更新粒子位置和状态
 */
private fun updateParticle(p: Particle, w: Float, h: Float, type: ParticleType) {
    // 位置更新
    p.x += p.vx
    p.y += p.vy

    // 闪烁 alpha
    p.alpha += p.alphaDir * 0.003f * p.twinkleSpeed
    if (p.alpha >= 0.8f || p.alpha <= 0.05f) {
        p.alphaDir *= -1f
    }

    // 边界环绕
    if (p.x < -0.05f) p.x = 1.05f
    if (p.x > 1.05f) p.x = -0.05f
    if (p.y < -0.05f) p.y = 1.05f
    if (p.y > 1.05f) p.y = -0.05f

    // 流星生命周期
    if (p.isShooting) {
        p.shootLife -= 0.0005f
        if (p.shootLife <= 0f) {
            // 重置流星
            p.x = if (p.vx > 0) -0.05f else 1.05f
            p.y = Random.nextFloat() * 0.6f
            p.shootLife = Random.nextFloat() * 0.6f + 0.2f
        }
    }
}

/**
 * 绘制单个粒子
 */
private fun DrawScope.drawParticle(p: Particle, type: ParticleType) {
    val cx = p.x * size.width
    val cy = p.y * size.height
    val alpha = p.alpha.coerceIn(0f, 1f)

    when (type) {
        ParticleType.GOLDEN_DUST -> {
            // 金色光点 + 光晕
            val color = Color(1f, 0.84f, 0.5f, alpha)
            drawCircle(color, p.baseRadius + 1.5f, Offset(cx, cy))
            drawCircle(color.copy(alpha = alpha * 0.3f), p.baseRadius + 5f, Offset(cx, cy))
        }

        ParticleType.LAZY_FLOAT -> {
            val color = Color(1f, 1f, 1f, alpha)
            drawCircle(color, p.baseRadius, Offset(cx, cy))
        }

        ParticleType.WARM_EMBER -> {
            // 暖橙光点
            val color = Color(1f, 0.6f, 0.3f, alpha)
            drawCircle(color, p.baseRadius, Offset(cx, cy))
            drawCircle(color.copy(alpha = alpha * 0.2f), p.baseRadius + 4f, Offset(cx, cy))
        }

        ParticleType.STARRY_NIGHT -> {
            if (p.isShooting) {
                // 流星：拖尾效果
                val trailColor = Color(1f, 1f, 1f, alpha * 0.6f)
                val headColor = Color(1f, 1f, 1f, alpha)
                val angle = kotlin.math.atan2(p.vy, p.vx)
                val trailLen = 25f * alpha
                val startX = cx - kotlin.math.cos(angle) * trailLen
                val startY = cy - kotlin.math.sin(angle) * trailLen
                drawLine(trailColor, Offset(startX, startY), Offset(cx, cy), strokeWidth = 1.5f)
                drawCircle(headColor, 2f, Offset(cx, cy))
            } else {
                // 星星闪烁
                val color = Color(1f, 1f, 1f, alpha)
                drawCircle(color, p.baseRadius, Offset(cx, cy))
                // 十字光芒（亮的星）
                if (alpha > 0.5f && p.baseRadius > 1.5f) {
                    val glowColor = color.copy(alpha = alpha * 0.3f)
                    val glowLen = p.baseRadius * 3f
                    drawLine(glowColor, Offset(cx - glowLen, cy), Offset(cx + glowLen, cy), strokeWidth = 0.5f)
                    drawLine(glowColor, Offset(cx, cy - glowLen), Offset(cx, cy + glowLen), strokeWidth = 0.5f)
                }
            }
        }
    }
}
