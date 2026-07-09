package com.example.timeatmosphere.ui.model

import androidx.compose.ui.graphics.Color

/**
 * 时间段氛围数据类
 */
data class TimeAtmosphere(
    val periodName: String,
    val periodNameEn: String,
    val skyColors: List<Color>,
    val particleType: ParticleType,
    val quote: String,
    val author: String
)

enum class ParticleType {
    GOLDEN_DUST,    // 金色晨光尘埃
    LAZY_FLOAT,     // 白天慵懒光点
    WARM_EMBER,     // 傍晚暖橙余烬
    STARRY_NIGHT    // 夜间星空闪烁
}

/**
 * 根据当前小时数获取对应的氛围配置
 */
fun getTimeAtmosphere(hour: Int): TimeAtmosphere = when (hour) {
    in 5..6 -> TimeAtmosphere(
        periodName = "黎明",
        periodNameEn = "Dawn",
        skyColors = listOf(
            Color(0xFF1A0533), // 深紫
            Color(0xFF3D1E6D), // 紫
            Color(0xFFD97B93), // 粉
            Color(0xFFF4A460)  // 金
        ),
        particleType = ParticleType.GOLDEN_DUST,
        quote = "晨光熹微，万物初醒",
        author = "破晓时分"
    )

    in 7..9 -> TimeAtmosphere(
        periodName = "早晨",
        periodNameEn = "Morning",
        skyColors = listOf(
            Color(0xFFF4A460), // 金
            Color(0xFFFAD6A5), // 浅金
            Color(0xFF87CEEB), // 天蓝
            Color(0xFFB0E0E6)  // 浅蓝
        ),
        particleType = ParticleType.GOLDEN_DUST,
        quote = "一日之计在于晨",
        author = "朝阳初升"
    )

    in 10..16 -> TimeAtmosphere(
        periodName = "白天",
        periodNameEn = "Daytime",
        skyColors = listOf(
            Color(0xFF1E90FF), // 湛蓝
            Color(0xFF4DA8FF), // 亮蓝
            Color(0xFF87CEEB), // 天蓝
            Color(0xFFC9E8FF)  // 浅蓝白
        ),
        particleType = ParticleType.LAZY_FLOAT,
        quote = "阳光正好，微风不燥",
        author = "晴空万里"
    )

    in 17..19 -> TimeAtmosphere(
        periodName = "傍晚",
        periodNameEn = "Sunset",
        skyColors = listOf(
            Color(0xFFFF6B35), // 橙
            Color(0xFFF4845F), // 浅橙
            Color(0xFFD97B93), // 粉
            Color(0xFF6B3FA0)  // 紫
        ),
        particleType = ParticleType.WARM_EMBER,
        quote = "落日余晖，待你而归",
        author = "晚霞满天"
    )

    in 20..22 -> TimeAtmosphere(
        periodName = "晚间",
        periodNameEn = "Evening",
        skyColors = listOf(
            Color(0xFF0D1B3E), // 深蓝
            Color(0xFF1B2D5C), // 蓝
            Color(0xFF3D1E6D), // 紫蓝
            Color(0xFF1A0A2E)  // 暗紫
        ),
        particleType = ParticleType.STARRY_NIGHT,
        quote = "夜幕降临，万家灯火",
        author = "华灯初上"
    )

    else -> TimeAtmosphere( // 23-4
        periodName = "深夜",
        periodNameEn = "Late Night",
        skyColors = listOf(
            Color(0xFF050814), // 墨黑
            Color(0xFF0A1628), // 深蓝黑
            Color(0xFF0D1B3E), // 深蓝
            Color(0xFF0A0A2E)  // 暗紫黑
        ),
        particleType = ParticleType.STARRY_NIGHT,
        quote = "万籁俱寂，星河长明",
        author = "夜深人静"
    )
}
