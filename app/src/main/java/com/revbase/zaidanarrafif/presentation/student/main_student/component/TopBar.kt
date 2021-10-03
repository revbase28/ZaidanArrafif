package com.revbase.zaidanarrafif.presentation.student.main_student.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.revbase.zaidanarrafif.R

@Composable
fun TopBar(
    starCount: Int,
    modifier: Modifier = Modifier,
    photoUrl: String = "https://www.seekpng.com/png/detail/349-3499598_portrait-placeholder-placeholder-person.png"
) {
    Row(
        modifier = modifier ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.zaidan_icon),
            contentDescription = "zaidan_icon",
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        StartCount(count = starCount)
        Spacer(modifier = Modifier.width(8.dp))

        val context = LocalContext.current
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(photoUrl)
            .build()
        Image(
            painter = rememberImagePainter(
                request = request,
                imageLoader = imageLoader
            ),
            contentDescription = "Student photo",
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 16.dp, top = 12.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(percent = 50))
        )
    }
}