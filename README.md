# 🌤️ TimeAtmosphere · 时间氛围感

> 一个沉浸式的 Android 氛围时钟应用 —— 随时间流转，天空变色，粒子飞舞，诗意浮现。

<p align="center">
  <img src="docs/dawn.png" width="45%" alt="黎明" />
  &nbsp;&nbsp;
  <img src="docs/night.png" width="45%" alt="深夜" />
</p>

---

## ✨ 体验

**TimeAtmosphere** 不是普通时钟。它根据一天中的时间，实时切换六种氛围场景——每一段时光都有独特的天空渐变色、漂浮粒子、和一句中文诗句。

| 时段 | 时间 | 天空 | 粒子 | 引语 |
|:---|:---|:---|:---|:---|
| 🌅 **黎明** Dawn | 5:00 – 6:59 | 深紫 → 粉 → 金 | 金色晨光尘埃 | 晨光熹微，万物初醒 |
| ☀️ **早晨** Morning | 7:00 – 9:59 | 金 → 浅金 → 天蓝 | 金色晨光尘埃 | 一日之计在于晨 |
| 🌞 **白天** Daytime | 10:00 – 16:59 | 湛蓝 → 亮蓝 → 浅蓝白 | 白色慵懒光点 | 阳光正好，微风不燥 |
| 🌇 **傍晚** Sunset | 17:00 – 19:59 | 橙 → 粉 → 紫 | 暖橙余烬 | 落日余晖，待你而归 |
| 🌙 **晚间** Evening | 20:00 – 22:59 | 深蓝 → 紫蓝 → 暗紫 | 星空 + 流星 | 夜幕降临，万家灯火 |
| 🌌 **深夜** Late Night | 23:00 – 4:59 | 墨黑 → 深蓝黑 → 暗紫黑 | 星空 + 流星 | 万籁俱寂，星河长明 |

### 动态细节

- **天空呼吸动画** —— 渐变色以 4 秒周期微弱波动，仿佛天空在呼吸
- **时段切换平滑过渡** —— 进入新时段时，天空颜色在 2 秒内平滑渐变
- **粒子系统**：
  - 金色尘埃（黎明/早晨）：向上飘浮的金色光点，带光晕
  - 慵懒光点（白天）：缓慢上浮的白色小光点
  - 暖橙余烬（傍晚）：上升的暖橙色火烬粒子
  - 星空（晚间/深夜）：闪烁星星 + 十字光芒 + 3% 概率出现流星拖尾
- **时钟分钟切换淡化** —— 每分钟切换时有 800ms 的淡入动画
- **文案淡入淡出** —— 时段切换时，诗句以滑动 + 淡入方式过渡
- **横屏全屏体验** —— 强制横屏，边到边沉浸显示，屏幕常亮

---

## 🧱 技术栈

| 类别 | 技术 |
|:---|:---|
| 语言 | Kotlin 2.0.10 |
| UI 框架 | Jetpack Compose (BOM 2024.08) |
| 设计系统 | Material 3 |
| 最低 SDK | Android 8.0 (API 26) |
| 目标 SDK | Android 14 (API 34) |
| 构建工具 | Gradle 8.5 + AGP 8.5.2 |

核心依赖：`Compose Canvas`（天空 / 粒子绘制）、`Compose Animation`（所有动画效果）、`Material 3`（深色主题）。

---

## 📁 项目结构

```
TimeAtmosphere/
├── app/
│   ├── build.gradle.kts                          # App 模块构建配置
│   └── src/main/
│       ├── AndroidManifest.xml                   # 强制横屏、全屏主题
│       ├── java/com/example/timeatmosphere/
│       │   ├── MainActivity.kt                   # 🚀 入口：边到边 + 屏幕常亮
│       │   └── ui/
│       │       ├── model/
│       │       │   └── TimeAtmosphere.kt          # 数据模型 + getTimeAtmosphere(hour)
│       │       ├── theme/
│       │       │   └── Theme.kt                   # Material 3 暗色主题
│       │       ├── component/
│       │       │   ├── SkyBackground.kt           # 动态天空渐变背景
│       │       │   ├── ParticleField.kt           # 粒子系统（120+ 粒子）
│       │       │   ├── AtmosphereText.kt          # 时段名 + 诗句动画
│       │       │   └── TimeDisplay.kt             # HH:MM : SS 大号时钟
│       │       └── screen/
│       │           └── TimeAtmosphereScreen.kt    # 主屏幕：左时钟 + 右文案
│       └── res/
│           ├── values/
│           │   ├── strings.xml                   # app_name = "时间氛围感"
│           │   └── themes.xml                    # 全屏 + 透明状态栏主题
│           ├── drawable/                         # 启动图标 (vector)
│           └── mipmap-anydpi-v26/                # 自适应图标
├── build.gradle.kts                              # 根构建脚本
├── settings.gradle.kts                           # 仓库镜像（腾讯云 / 阿里云）
└── gradle/
    ├── libs.versions.toml                        # 版本目录
    └── wrapper/                                  # Gradle Wrapper
```

---

## 🚀 构建 & 运行

### 前提

- Android Studio Hedgehog (2023.1) 或更新版本
- JDK 17
- Android SDK 34

### 步骤

```bash
# 1. 克隆项目
git clone <your-repo-url> && cd TimeAtmosphere

# 2. 用 Android Studio 打开项目根目录

# 3. 同步 Gradle → Run（选择模拟器或真机）
```

> 💡 项目已配置腾讯云 / 阿里云 Maven 镜像（[settings.gradle.kts](settings.gradle.kts)），国内构建无需代理。

### 安装

```bash
# 编译 Debug APK
./gradlew assembleDebug

# APK 路径：app/build/outputs/apk/debug/app-debug.apk
```

---

## 🎯 设计理念

> 时间不只是数字。天空的颜色、空气里浮动的光尘、那一刻心里的句子——把它们揉在一起，才是你感受到的「这一秒」。

- **时间 → 颜色**：用自然界一天中天空的色相变化映射到每个时段
- **时间 → 运动**：不同时段的粒子行为不同——早晨的尘埃积极向上飘，白天的光点慵懒漫步，夜晚的星星静静闪烁
- **时间 → 文字**：每个时段配一句中文诗词/短句，增强意境感

---

## 📄 许可

MIT License

---

*Made with ☕ and Compose Canvas*
