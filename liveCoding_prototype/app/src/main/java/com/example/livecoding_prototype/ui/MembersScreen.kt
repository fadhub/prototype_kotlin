package com.example.livecoding_prototype.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NameListScreen() {

    var name by rememberSaveable { mutableStateOf("") }


    val names = rememberSaveable { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Entrer un prénom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                if (name.isNotBlank()) {
                    names.add(name)
                    name = ""
                }
            }
        ) {
            Text("Ajouter")
        }

        Spacer(modifier = Modifier.height(16.dp))


        names.forEach { n ->
            Text(
                text = "👤 $n",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNameListScreen() {
    MaterialTheme {
        NameListScreen()
    }
}
