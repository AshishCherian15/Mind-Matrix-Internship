package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.model.Topic
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainAppScreen()
                }
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    var showGrid by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { showGrid = !showGrid },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(if (showGrid) "Show Affirmations List" else "Show Courses Grid")
        }

        // Add Modifier.weight(1f) here to make the list/grid fill the screen
        if (showGrid) {
            TopicGrid(
                topicList = Datasource().loadTopics(),
                modifier = Modifier.weight(1f)
            )
        } else {
            AffirmationList(
                affirmationList = Datasource().loadAffirmations(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// --- LIST COMPONENTS ---
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation = affirmation, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier.fillMaxWidth().height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

// --- GRID COMPONENTS ---
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
                Text(text = stringResource(id = topic.nameRes), style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                    Icon(painter = painterResource(R.drawable.ic_grain), contentDescription = null)
                    Text(text = topic.courseCount.toString(), style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
fun TopicGrid(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // Update this line to use the passed modifier:
        modifier = modifier.padding(8.dp)
    ) {
        items(topicList) { topic ->
            TopicCard(topic = topic)
        }
    }
}

// --- PREVIEWS (Fixes your "No Preview Found" error) ---
@Preview(showBackground = true)
@Composable
fun AffirmationsPreview() {
    AffirmationsTheme {
        AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    AffirmationsTheme {
        TopicCard(Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}