package com.example.courses_app

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.privacysandbox.ads.adservices.topics.Topic
import com.example.courses.data.DataSource.topics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        window.statusBarColor = android.graphics.Color.BLACK
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        window.statusBarColor = android.graphics.Color.BLACK
        setContent {
            CoursesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .background(Color.Black),
//                    color = MaterialTheme.colorScheme.background,
                ) {
                    AllCourseGrid()
                }
            }
        }
    }
}

@Composable
fun TopicCardItems(topic: com.example.courses.model.Topic, modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        Image(
            painter = painterResource(id = topic.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.width(12.dp)) // Spacer between image and column

        // Column for text
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            // Topic title
            Text(
                text = stringResource(id = topic.name),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Row for decorative icon and course count
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_grain), // Decorative icon
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),

                )

                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text

                Text(
                    text = topic.availableCourses.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
fun AllCourseGridPreview() {
    CoursesTheme {
        AllCourseGrid()
    }
}

@Preview(showBackground = true)
@Composable
fun AllCourseGrid() {
    CoursesTheme {
        val topic =
            com.example.courses.model.Topic(R.string.photography, 321, R.drawable.photography)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Defines the number of columns in the grid
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp), // Padding around the grid
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp), // Spacing between columns
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp) // Spacing between rows
        ) {
            items(topics) { topic ->
                TopicCardItems(topic = topic)
            }
        }
        }
    }

@Composable
fun CoursesTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

