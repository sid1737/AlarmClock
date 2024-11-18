package com.example.clock.ui.createAlarmScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.clock.ui.createAlarmScreen.components.AlarmNameComponent
import com.example.clock.ui.createAlarmScreen.components.AlarmTimeComponent
import com.example.clock.ui.createAlarmScreen.components.CurvedButtonCloseIcon
import com.example.clock.ui.createAlarmScreen.components.CurvedTextButton
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.utilities.AlarmUtility.isValid24HourFormatTime

@Composable
fun CreateAlarmScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CreateAlarmViewModel
) {
    var isSaveButtonActive by remember {
        mutableStateOf(false)
    }

    var alarmName by remember {
        mutableStateOf("Work")
    }

    var timeData by remember {
        mutableStateOf("")
    }
    
    var alarmDescription by remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = defaultPadding, end = defaultPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(defaultPadding)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CurvedButtonCloseIcon(
                    onClick = {
                        navController.navigateUp()
                    }
                )
                CurvedTextButton(
                    isEnabled = isSaveButtonActive,
                    buttonText = "Save",
                ) {
                    if (isSaveButtonActive) {
                        val alarmData = viewModel.createAlarmObject(
                            time = timeData,
                            alarmDescription = alarmDescription,
                            alarmName = alarmName
                        )
                        viewModel.onEvent(CreateAlarmEvents.OnSaveButtonClick(alarmData))
                        navController.navigateUp()
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(defaultPadding)
            )
            AlarmTimeComponent(
                onValidInput = { time ->
                    timeData = time
                    isSaveButtonActive = isValid24HourFormatTime(time) && alarmName.isNotBlank()
                },
                onCalculatedRemainingTime = { remainingTimeString ->
                    alarmDescription = remainingTimeString
                }
            )
            Spacer(
                modifier = Modifier
                    .height(defaultPadding)
            )
            AlarmNameComponent(
                alarmName = alarmName,
                setAlarmName = {
                    alarmName = it
                }
            )
        }
    }
}

@Preview
@Composable
fun CreateAlarmScreenPreview() {
    CreateAlarmScreen(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}