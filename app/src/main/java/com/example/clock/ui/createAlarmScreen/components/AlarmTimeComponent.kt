package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clock.ui.theme.brightBlue
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.largeTextSize
import com.example.clock.ui.theme.montSerratFontFamily
import com.example.clock.ui.theme.screenBackground
import com.example.clock.ui.theme.smallTextSize

@Composable
fun AlarmTimeComponent(
    hours: String = "12",
    minutes: String = "00",
    modifier: Modifier = Modifier,
) {
    var hourPart by remember {
        mutableStateOf(hours)
    }

    var minutesPart by remember {
        mutableStateOf(minutes)
    }

    val textStyle = TextStyle(
        fontFamily = montSerratFontFamily,
        fontSize = largeTextSize,
        color = if (hourPart.toInt() == 0 && minutesPart.toInt() == 0) Color.Gray else brightBlue,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold
    )


    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(defaultPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(screenBackground)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = hourPart,
                        onValueChange = { newHours ->
                            hourPart = newHours
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        textStyle = textStyle,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = screenBackground,
                            unfocusedContainerColor = screenBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Text(
                    text = ":",
                    fontFamily = montSerratFontFamily,
                    fontStyle = FontStyle.Normal,
                    fontSize = smallTextSize
                )
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(screenBackground)
                        .weight(1f)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = minutesPart,
                        onValueChange = { newMins ->
                            minutesPart = newMins
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        textStyle = textStyle,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = screenBackground,
                            unfocusedContainerColor = screenBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text ="Alarm in 7h",
                fontSize = smallTextSize,
                fontFamily = montSerratFontFamily,
                fontWeight = FontWeight.Thin,

            )
        }
    }
}

@Preview
@Composable
fun PreviewAlarmComponent() {
    AlarmTimeComponent(
        hours = "00",
        minutes = "00"
    )
}