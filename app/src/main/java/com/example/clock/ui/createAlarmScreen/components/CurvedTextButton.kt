package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.theme.brightBlue
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.gray
import com.example.clock.ui.theme.montSerratFontFamily

@Composable
fun CurvedTextButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    val shape = RoundedCornerShape(defaultPadding)
    val buttonColour = if (isEnabled) brightBlue else gray

    Button(
        modifier = modifier,
        onClick = {
            onButtonClick()
        },
        shape = shape,
        colors = ButtonDefaults.buttonColors(buttonColour)
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                color = Color.White,
                fontFamily = montSerratFontFamily,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
fun PreviewCurvedTextButton() {
    CurvedTextButton(
        isEnabled = false,
        buttonText = "Save"

    ) {

    }
}