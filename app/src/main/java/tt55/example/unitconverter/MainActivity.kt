package tt55.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tt55.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import android.content.Intent
import android.net.Uri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->                     (
                        UnitConvert()

                        )
                }
            }
        }
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
    }
}
val customStyle=TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 30.sp,
    color = Color.Blue
)



@Composable
fun UnitConvert(){
    var inputValue =   remember { mutableStateOf("") }
    val outputValue = remember { mutableStateOf("") }
    var inputUnit = remember { mutableStateOf("Meters") }
    val outputUnit =  remember { mutableStateOf("Meters") }
    val iExpanded =  remember { mutableStateOf(false) }
    val oExpanded =  remember { mutableStateOf(false) }
    val conversationFactor = remember { mutableDoubleStateOf(1.0) }  // For input unit
    val oConversationFactor = remember { mutableDoubleStateOf(1.0) }


    fun convertUnits() {
        // Elvis operator ?: - handles null values
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversationFactor.value / oConversationFactor.value).roundToInt() / 100.0

        outputValue.value = result.toString()
    }







    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {


        //all the UI element will be hear
        Text("Unit Converter", style = customStyle)
       Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = inputValue.value, onValueChange = { inputValue.value = it
            convertUnits()}
        , label = { Text("ENTER YOUR NUMBER", )})




        Spacer(modifier = Modifier.height(20.dp))
        Row {//row start from hear dad of box
            Box {
                //INPUT BUTTON
                Button(onClick = {iExpanded.value = true}) {
                    Text(text = inputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }

                DropdownMenu(expanded = iExpanded.value , onDismissRequest = {iExpanded.value = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {iExpanded.value=false
                            inputUnit.value ="Centimeters"
                        conversationFactor.value=0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        iExpanded.value=false
                        inputUnit.value ="Meters"
                        conversationFactor.value=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        iExpanded.value=false
                        inputUnit.value ="Feet"
                        conversationFactor.value=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters")}, onClick = {iExpanded.value=false
                        inputUnit.value ="Millimeters"
                        conversationFactor.value=0.001
                        convertUnits()})

                }

            }//last line of box 1


            Spacer(modifier = Modifier.width(16.dp  ))//its make space btween box 1 and 2



            Box {
                Button(onClick = {oExpanded.value=true}) {
                    Text(text = outputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded.value, onDismissRequest = {oExpanded.value=false}) {
                    DropdownMenuItem(text = { Text("Centimeters")}, onClick = {
                        oExpanded.value=false
                        outputUnit.value ="Centimeters"
                        oConversationFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        oExpanded.value=false
                        outputUnit.value ="Meters"
                        oConversationFactor.value=1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        oExpanded.value=false
                        outputUnit.value ="Feet"
                        oConversationFactor.value=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters")}, onClick = { oExpanded.value=false
                        outputUnit.value ="Millimeters"
                        oConversationFactor.value=0.001
                        convertUnits()})

                }

            }//last line of box 2

         } //row last line brekect



        Spacer(modifier = Modifier.height(20.dp))//its use for make space bttween result and seclect
//result text
        Text("Result: ${outputValue.value} ${outputUnit.value}",
            style = MaterialTheme.typography.headlineMedium)


    }

    }






@Preview(showBackground = true)
@Composable
fun UnitConvertPreview(){
    UnitConvert()

}

