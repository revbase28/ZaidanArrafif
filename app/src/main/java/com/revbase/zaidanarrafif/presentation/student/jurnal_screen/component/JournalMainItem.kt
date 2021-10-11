package com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.DarkGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey

@ExperimentalCoilApi
@Composable
fun JournalMainItem(
    journalType: String,
    imageUrl: String,
    progress: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth().height(IntrinsicSize.Max)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.7f))
            {
                Text(
                    text = journalType,
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),

                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = progress,
                    softWrap= true,
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    onClick = onClick,
                    modifier = Modifier.width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        Blue
                    )
                ) {
                    Text(text = "Isi Jurnal", style = TextStyle(color = Color.White))

                }

            }
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(32.dp))

}