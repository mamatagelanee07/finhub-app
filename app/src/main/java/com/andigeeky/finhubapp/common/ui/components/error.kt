package com.andigeeky.finhubapp.common.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContentErrorView(
    message: String? = null,
    buttonText: String? = null,
    click: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            message?.let {
                Text(text = it, textAlign = TextAlign.Center)
                TextButton(onClick = click) {
                    Text(text = buttonText.orEmpty())
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentErrorPreview() {
    ContentErrorView(
        message = "There was an error while loading data",
        buttonText = "Try Again",
        click = {}
    )
}
