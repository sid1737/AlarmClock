package com.example.clock.ui.createAlarmScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.montSerratFontFamily
import com.example.clock.ui.theme.standardTextSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogComponent(
    setAlarmName : (String) -> Unit,
    dismissAlarm: () -> Unit
) {
    val context = LocalContext.current

    var textData by remember {
        mutableStateOf("")
    }

    BasicAlertDialog(
        onDismissRequest = {
            dismissAlarm()
        },
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(defaultPadding)
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
                Spacer(
                    modifier = Modifier.height(6.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textData,
                        onValueChange = { newData ->
                            textData = newData
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                        )
                    )
                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )
                    CurvedTextButton(
                        isEnabled = textData.isNotBlank(),
                        buttonText = context.getString(R.string.save_button_text)
                    ) {
                        setAlarmName(textData)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAlertDialog() {
    AlertDialogComponent(
        setAlarmName = {

        }
    ) { }
}