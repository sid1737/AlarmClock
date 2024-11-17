package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clock.R
import com.example.clock.ui.theme.createAlarmTextColour
import com.example.clock.ui.theme.montSerratFontFamily
import com.example.clock.ui.theme.smallTextSize
import com.example.clock.ui.theme.standardTextSize

@Composable
fun AlarmNameComponent(
    modifier: Modifier = Modifier,
    alarmName: String = "Work",
    setAlarmName: (String) -> Unit
) {
    val context = LocalContext.current

    var openDialog by remember {
        mutableStateOf(false)
    }

    var alarmNameData by remember {
        mutableStateOf(alarmName)
    }

    Box(
        modifier = modifier
            .clickable {
                openDialog = true
            }
            .fillMaxWidth()
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = context.getString(R.string.alarm_name),
                style = TextStyle(
                    fontFamily = montSerratFontFamily,
                    fontSize = standardTextSize,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                text = alarmNameData,
                style = TextStyle(
                    fontFamily = montSerratFontFamily,
                    fontSize = smallTextSize,
                    fontWeight = FontWeight.Normal,
                    color = createAlarmTextColour
                )
            )
        }
    }

    if (openDialog) {
        AlertDialogComponent(
            setAlarmName = {
                alarmNameData = it
                openDialog = false
                setAlarmName(alarmName)
            },
            dismissAlarm = {
                openDialog = false
            },
        )
    }
}

@Preview
@Composable
fun PreviewAlarmNameComponent() {
    AlarmNameComponent(
        alarmName = "demo",
        setAlarmName = {

        }
    )
}