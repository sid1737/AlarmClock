package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.clock.ui.theme.defaultPadding

@Composable
fun CurvedButtonCloseIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonShape = RoundedCornerShape(defaultPadding)
    val disabledButtonColour = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)

    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(disabledButtonColour)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            tint = Color.White,
            contentDescription = "Close"
        )
    }
}