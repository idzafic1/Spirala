package etf.ri.rma.newsfeedapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Unique color variable names to avoid conflicts
private val SpiralaDarkBackground = Color(0xFF121212)
private val SpiralaChipBackgroundDark = Color(0xFF2D2D2D)
private val SpiralaChipTextDark = Color(0xFFEEEEEE)
private val SpiralaLightBackground = Color(0xFFFAFAFA)

private val DarkColorScheme = darkColorScheme(
    background = SpiralaDarkBackground,
    surface = SpiralaChipBackgroundDark,
    onSurface = SpiralaChipTextDark
)

private val LightColorScheme = lightColorScheme(
    background = SpiralaLightBackground
)

@Composable
fun SpiralaCopyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}