package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.gray

@Composable
fun CurvedButtonCloseIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonShape = RoundedCornerShape(defaultPadding)

    Button(
        onClick = {
            onClick()
        },
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = gray,
            contentColor = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            tint = Color.White,
            contentDescription = "Close"
        )
    }
}

@Preview
@Composable
fun PreviewCurvedButtonCloseIcon() {
    CurvedButtonCloseIcon(
        onClick = {}
    )
}