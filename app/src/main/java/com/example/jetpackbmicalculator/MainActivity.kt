package com.example.jetpackbmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackbmicalculator.ui.theme.JetpackBMICalculatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "BMI計算アプリ",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(20.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                        PinkLabeledTextField(
                            value = viewModel.height,
                            onValueChange = {viewModel.height = it},
                            label = "身長(cm)",
                            placeholder = "170"
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        PinkLabeledTextField(
                            value = viewModel.weight,
                            onValueChange = {viewModel.weight = it},
                            label = "体重(kg)",
                            placeholder = "65"
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        Button(
                            onClick = {viewModel.calcBMI()},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF85F6A))
                        ) {
                            Text(
                                text = "計算する",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "あなたのBMIは${viewModel.bmi}です",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold
                            )

                    }
                }
            }
        }
    }
}

@Composable
fun PinkLabeledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    Column {
        Text(
            text = label,
            color = Color(0xFFF85F6A),
            fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}