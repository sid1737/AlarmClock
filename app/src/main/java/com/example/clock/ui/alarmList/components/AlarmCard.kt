package com.example.clock.ui.alarmList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.theme.brightBlue
import com.example.clock.ui.theme.cardCornerRadius
import com.example.clock.ui.theme.cardElevation
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.extraLargeTextSize
import com.example.clock.ui.theme.largeTextSize
import com.example.clock.ui.theme.montSerratFontFamily
import com.example.clock.ui.theme.paddingExtraSmall
import com.example.clock.ui.theme.paddingSmall
import com.example.clock.ui.theme.smallTextSize
import com.example.clock.ui.theme.verticalSpacerHeightMedium
import com.example.clock.ui.theme.verticalSpacerHeightSmall

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier,
    alarmTitle: String,
    alarmTime: String,
    isDayTime: Boolean,
    alarmDescription: String
) {
    var isAlarmActive by remember {
        mutableStateOf(true)
    }

    Card(
        modifier = modifier
            .padding(defaultPadding)
            .fillMaxWidth()
            .shadow(
                elevation = cardElevation,
                shape = RoundedCornerShape(cardCornerRadius)
            ),
        shape = RoundedCornerShape(cardCornerRadius),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingSmall)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = alarmTitle,
                    fontSize = largeTextSize,
                    fontFamily = montSerratFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = paddingExtraSmall)
                )
                Switch(
                    checked = isAlarmActive,
                    onCheckedChange = {
                        isAlarmActive = !isAlarmActive
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = brightBlue.copy(0.3f),
                        checkedTrackColor = brightBlue,
                        uncheckedBorderColor = Color.Transparent,
                        checkedBorderColor = Color.Transparent
                    ),
                )
            }
            Spacer(
                modifier = Modifier.height(verticalSpacerHeightSmall)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = alarmTime,
                    fontSize = extraLargeTextSize,
                    fontFamily = montSerratFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = if (isDayTime) "AM" else "PM",
                    fontSize = largeTextSize,
                    fontFamily = montSerratFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Spacer(
                modifier = Modifier.height(verticalSpacerHeightMedium)
            )
            Text(
                text = alarmDescription,
                fontSize = smallTextSize,
                fontFamily = montSerratFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    AlarmCard(
        alarmTitle = "Wake Up",
        alarmTime = "10:00",
        isDayTime = true,
        alarmDescription = "Alarm in 30 min"
    )
}