package com.example.realisation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs


@Composable
fun ParkingScreen() {
    val backgroundColor = Color(0xFFE3F2FD)
    val cardColor = Color(0xFFF9F9F9)
    val buttonColor = Color(0xFFCC7697)
    val accentColor = Color(0xFFDEA0B7)

    var start by rememberSaveable { mutableStateOf("") }
    var end by rememberSaveable { mutableStateOf("") }
    var mode by rememberSaveable { mutableStateOf("proportionnel") }
    var result by rememberSaveable { mutableStateOf("") }
    var details by rememberSaveable { mutableStateOf(listOf<String>()) }
    val history = rememberSaveable { mutableStateListOf<String>() }

    val tranches = listOf(
        Tranche("Nuit", 0, 479, 4.0),
        Tranche("Jour", 480, 1139, 8.0),
        Tranche("Soir", 1140, 1439, 6.0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))
        Text(" Parking Fee", fontSize = 26.sp, color = Color.Black)
        Spacer(Modifier.height(15.dp))

        OutlinedTextField(
            value = start,
            onValueChange = { start = it },
            label = { Text("Heure début (HH:MM)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor, // Lon l'cadre melli m'clicki
                focusedLabelColor = accentColor    // Lon l'label melli m'clicki
            )
        )
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = end,
            onValueChange = { end = it },
            label = { Text("Heure fin (HH:MM)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                focusedLabelColor = accentColor
            )
        )

        Spacer(Modifier.height(20.dp))

        // Mode de calcul
        Text("Mode de calcul :", fontSize = 18.sp, color = Color.Black)
        Row(verticalAlignment = Alignment.CenterVertically) {
            // --- TEBDIL 2: Zidna 'colors' l'RadioButton ---
            RadioButton(
                selected = (mode == "proportionnel"),
                onClick = { mode = "proportionnel" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = accentColor // Lon melli l'radio ikon mkhtar
                )
            )
            Text("Proportionnel")
            Spacer(Modifier.width(16.dp))
            RadioButton(
                selected = (mode == "heure"),
                onClick = { mode = "heure" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = accentColor
                )
            )
            Text("Heure entamée")
        }

        Spacer(Modifier.height(20.dp))

        // Bouton Calculer
        Button(
            onClick = {
                if (start.isNotBlank() && end.isNotBlank()) {
                    val (total, det) = Calculator.calculate(
                        start,
                        end,
                        tranches,
                        proportional = (mode == "proportionnel")
                    )
                    details = det
                    result = "Total : ${"%.2f".format(total)} MAD"

                    // Durée totale
                    val s = Calculator.toMinutes(start)
                    var e = Calculator.toMinutes(end)
                    if (e < s) e += 1440
                    val duration = e - s
                    val h = duration / 60
                    val m = duration % 60

                    history.add(0, "$start–$end → ${"%.2f".format(total)} MAD")
                    if (history.size > 3) history.removeAt(history.lastIndex)

                    details = listOf("Durée totale : ${h}h ${m}min") + details
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White
            )
        ) {
            Text("CALCULER")
        }

        Spacer(Modifier.height(20.dp))

        // Résultats
        if (result.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cardColor)
                    .padding(12.dp)
            ) {
                details.forEach { Text(it) }
                Text(result, fontSize = 20.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Historique
        Text("Derniers calculs :", fontSize = 18.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardColor)
                .padding(10.dp)
        ) {
            history.forEach { Text(it) }
        }
    }
}

