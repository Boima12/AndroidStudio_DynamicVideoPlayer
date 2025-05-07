package com.example.dynamicvideoplayer


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.dynamicvideoplayer.ui.viewModels.DynamicVideoViewModel


@Composable
fun DynamicVideoPlayer(
    modifier: Modifier = Modifier,
    viewModel: DynamicVideoViewModel = viewModel()
) {
    val videoUrl by viewModel.videoUrl.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F3F3))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (videoUrl != null) {
            val context = LocalContext.current
            val player = remember {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
                    prepare()
                    playWhenReady = true
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    player.release()
                }
            }

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                factory = {
                    PlayerView(it).apply {
                        this.player = player
                    }
                }
            )
        } else {
            CircularProgressIndicator()
        }
    }
}