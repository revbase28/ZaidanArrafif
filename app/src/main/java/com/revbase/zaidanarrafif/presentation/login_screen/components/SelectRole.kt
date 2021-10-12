package com.revbase.zaidanarrafif.presentation.login_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun SelectRole() {
    var isTeacherRoleSelected by remember {
        mutableStateOf(false)
    }
    var isStudentRoleSelected by remember {
        mutableStateOf(false)
    }
    
    Row(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardRoleOption(
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2784/2784461.png",
            modifier = Modifier.weight(1F),
            isSelected = isStudentRoleSelected,
            onSelect = {
                isStudentRoleSelected = true
                isTeacherRoleSelected = false
            },
            text = "Masuk sebagai siswa"
        )
        Spacer(modifier = Modifier.width(16.dp))
        CardRoleOption(
            imageUrl = "https://cdn-icons-png.flaticon.com/512/2784/2784488.png",
            modifier = Modifier.weight(1F),
            isSelected = isTeacherRoleSelected,
            onSelect = {
                isStudentRoleSelected = false
                isTeacherRoleSelected = true
            },
            text = "Masuk sebagai guru"

        )
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CardRoleOption(
    imageUrl:String,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onSelect: ()->Unit,
    text: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = LightGrey,
        border = if(isSelected) {
            BorderStroke(width = 2.dp, color = Pink)
        } else null,
        onClick = onSelect
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = rememberImagePainter(data = imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp))

                Text(
                    text = text,
                    style = androidx.compose.ui.text.TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)

                )
            }
        }
    }
}