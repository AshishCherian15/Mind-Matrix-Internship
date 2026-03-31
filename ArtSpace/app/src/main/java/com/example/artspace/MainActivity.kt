package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    // 1. Identify and create states for dynamic elements
    var currentArtwork by remember { mutableIntStateOf(1) }

    val imageResource = when (currentArtwork) {
        1 -> R.drawable.artspace4 // Replace with your image names
        2 -> R.drawable.artspace5
        else -> R.drawable.artspace6
    }

    val titleResource = when (currentArtwork) {
        1 -> "Starry Night"
        2 -> "Mona Lisa"
        else -> "The Persistence of Memory"
    }

    val artistResource = when (currentArtwork) {
        1 -> "Vincent van Gogh (1889)"
        2 -> "Leonardo da Vinci (1503)"
        else -> "Salvador Dalí (1931)"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- Artwork Wall Section ---
        Surface(
            modifier = Modifier.weight(1f).padding(16.dp),
            shadowElevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = titleResource,
                modifier = Modifier.padding(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Artwork Descriptor Section ---
        Card {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = titleResource, fontSize = 24.sp, fontWeight = FontWeight.Light)
                Text(text = artistResource, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Display Controller Section (Buttons) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    // Logic for Previous button
                    if (currentArtwork > 1) currentArtwork-- else currentArtwork = 3
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    // Logic for Next button
                    if (currentArtwork < 3) currentArtwork++ else currentArtwork = 1
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}