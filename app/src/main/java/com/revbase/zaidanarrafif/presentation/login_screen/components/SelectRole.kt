package com.revbase.zaidanarrafif.presentation.login_screen.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

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
            .height(152.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardRoleOption(
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

@ExperimentalMaterialApi
@Composable
fun CardRoleOption(
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
            Text(text = text, textAlign = TextAlign.Center)
        }
    }
}