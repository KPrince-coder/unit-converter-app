/**
 * @date Thursday, March 7, 2024
 * @location APC hostel room
 */
package com.example.firstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapplication.ui.theme.FirstApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun UnitConverter() {
    @Composable
    fun Spacer(height: Int = 0, padding: Int = 0) = Spacer(
        modifier = Modifier
            .height(height.dp)
            .padding(
                horizontal
                = padding.dp
            )
    )

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("meter") }
    var outputUnit by remember { mutableStateOf("meter") }
    var outputExpand by remember { mutableStateOf(false) }
    var inputExpand by remember { mutableStateOf(false) }
    val inputConversionFactor =
        remember { mutableFloatStateOf(1.0F) }
    val outputConversionFactor =
        remember { mutableFloatStateOf(1.0F) }

    fun convertUnit() {
        val inputValueFloat = inputValue.toFloatOrNull() ?: 0.0F
        val result =
            (inputValueFloat * inputConversionFactor.floatValue * 100.0F /
                    outputConversionFactor.floatValue)
                .roundToInt() / 100.0F

        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(20)
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            placeholder = { Text(text = "Enter value") })

        Spacer(20)

        Row {
            Column {
                // Row {
                //     val context = LocalContext.current
                //     val counter = remember {
                //         mutableIntStateOf(0)
                //     }
                //     val value =
                //         when {
                //             counter.intValue < 0 -> {
                //                 counter.intValue++
                //                 "Clicks can't be negative"
                //             }
                //
                //             counter.intValue == 0 -> "No clicks"
                //             counter.intValue == 1 -> "1 click"
                //             else -> "${counter.intValue} clicks"
                //         }
                //
                //     Button(onClick = {
                //         counter.intValue++
                //         Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
                //     }) {
                //         Text(text = "Counter ++")
                //     }
                //     //     spacing
                //     Spacer(20, 10)
                //
                //     Button(onClick = {
                //         counter.intValue--
                //
                //         Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
                //     }) {
                //         Text(text = "Counter --")
                //     }
                // }
                // //     spacing
                // Spacer(20)
                Row {
                    // input box
                    Box {
                        // input button
                        Button(onClick = { inputExpand = true }) {
                            Text(inputUnit)
                            Icon(
                                if (inputExpand) Icons.Default.KeyboardArrowUp
                                else Icons.Default.KeyboardArrowDown,
                                contentDescription =
                                if (outputExpand) "Arrow up" else "Arrow down",
                                modifier = Modifier.scale(0.75F)
                            )
                            DropdownMenu(expanded = inputExpand, onDismissRequest = {
                                inputExpand = false
                            }) {
                                DropdownMenuItem(
                                    text = { Text("centimeter") },
                                    onClick = {
                                        inputUnit = "centimeter"
                                        inputExpand = false
                                        inputConversionFactor.floatValue = 0.01F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("meter") }, onClick = {
                                        inputUnit = "meter"
                                        inputExpand = false
                                        inputConversionFactor.floatValue = 1.0F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("feet") }, onClick = {
                                        inputUnit = "feet"
                                        inputExpand = false
                                        inputConversionFactor.floatValue = 0.3048F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("millimeter") }, onClick = {
                                        inputUnit = "millimeter"
                                        inputExpand = false
                                        inputConversionFactor.floatValue = 0.001F
                                        convertUnit()
                                    }
                                )
                            }
                        }
                    }
                    Spacer(padding = 12)
                    // output box
                    Box {
                        // output button
                        Button(onClick = { outputExpand = true }) {
                            Text(outputUnit)
                            Icon(
                                if (outputExpand) Icons.Default.KeyboardArrowUp
                                else Icons.Default.KeyboardArrowDown,
                                contentDescription =
                                if (outputExpand) "Arrow up" else "Arrow down",
                                modifier = Modifier.scale(0.75F)
                            )
                            DropdownMenu(expanded = outputExpand, onDismissRequest = {
                                outputExpand = false
                            }) {
                                DropdownMenuItem(
                                    text = { Text("centimeter") }, onClick = {
                                        outputUnit = "centimeter"
                                        outputExpand = false
                                        outputConversionFactor.floatValue = 0.01F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("meter") }, onClick = {
                                        outputUnit = "meter"
                                        outputExpand = false
                                        outputConversionFactor.floatValue = 1.0F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("feet") }, onClick = {
                                        outputUnit = "feet"
                                        outputExpand = false
                                        outputConversionFactor.floatValue = 0.3048F
                                        convertUnit()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("millimeter") }, onClick = {
                                        outputUnit = "millimeter"
                                        outputExpand = false
                                        outputConversionFactor.floatValue = 0.001F
                                        convertUnit()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(height = 20)
        Text(
            text = "Result: $outputValue ${
                when (outputValue) {
                    "" -> ""
                    1.0.toString() -> outputUnit
                    else -> outputUnit + "s"
                }
            }",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Monospace,
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() = UnitConverter()