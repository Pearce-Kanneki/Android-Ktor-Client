package com.kanneki.testktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kanneki.testktor.data.remote.PostService
import com.kanneki.testktor.data.remote.dto.PostResponse
import com.kanneki.testktor.ui.theme.TestKtorTheme

class MainActivity : ComponentActivity() {

    private val service = PostService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                }
            )

            TestKtorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                Text(text = it.title, style = MaterialTheme.typography.h6)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }
            }
        }
    }
}

