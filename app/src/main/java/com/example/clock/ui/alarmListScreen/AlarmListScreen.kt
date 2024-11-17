package com.example.clock.ui.alarmListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.clock.R
import com.example.clock.ui.alarmListScreen.components.AlarmCard
import com.example.clock.ui.alarmListScreen.components.SwipeToDeleteContainer
import com.example.clock.ui.navigation.Screen
import com.example.clock.ui.theme.brightBlue
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.largeTextSize
import com.example.clock.ui.theme.montSerratFontFamily
import com.example.clock.ui.theme.paddingExtraLarge

@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AlarmListViewModel
) {
    val alarms = viewModel.listOfAlarmData.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateAlarmScreen.route)
                },
                shape = CircleShape,
                containerColor = brightBlue
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->

        if (alarms.value.isEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(
                        top = defaultPadding,
                        start = defaultPadding,
                        end = defaultPadding
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    painter = painterResource(R.drawable.ic_alarm_clock_blue),
                    tint = brightBlue,
                    contentDescription = null,
                )
                Spacer(
                    modifier = Modifier.height(defaultPadding)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "It's empty! Add the first alarm so you don't miss an important moment!"
                )
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(
                        top = defaultPadding,
                        start = defaultPadding,
                        end = defaultPadding
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Your Alarms",
                    fontFamily = montSerratFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = largeTextSize
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = paddingExtraLarge,
                        )
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    items(alarms.value,
                        key = { it.id}) { alarm ->
                        SwipeToDeleteContainer(
                            item = alarm,
                            onDelete = {
                                alarms.value.toMutableStateList() -= alarm
                                viewModel.onEvent(AlarmListEvents.DeleteAlarm(alarm))
                            }
                        ) {
                            AlarmCard(
                                alarm = alarm,
                                onClick = {
                                    viewModel.onEvent(
                                        AlarmListEvents.DisableAlarm(
                                            alarm.copy(isAlarmActive = !alarm.isAlarmActive)
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AlarmListScreenPreview() {
    AlarmListScreen(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}