package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.common_component.ErrorText
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun TextFieldWithDropDown(
    label: String,
    list: List<String>,
    text: String,
    onTextChanged: (String) -> Unit,
    onIconCliked: () -> Unit,
    onDismiss: () -> Unit,
    onDropdownMenuClicked: (String) -> Unit,
    expanded: Boolean = false,
    hasError: Boolean = false
) {
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "contentDescription",
                    modifier = Modifier
                        .clickable {
                            onIconCliked()
                        }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                cursorColor = Pink,
                focusedLabelColor = Pink,
                errorLabelColor = Color.Red,
                errorBorderColor = Color.Red
            ),
            textStyle = TextStyle(
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 16.sp
            ),
            isError = hasError
        )
        if (hasError) {
            Spacer(modifier = Modifier.height(4.dp))
            ErrorText(errorText = "Nama surah harus diisi")
        }
        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .width(
                        with(LocalDensity.current) {
                            textfieldSize.width.toDp()
                        }
                    )
                    .shadow(elevation = 1.dp)
            ) {
                items(list) { label ->
                    DropdownMenuItem(
                        onClick = {
                            onDropdownMenuClicked(label)
                        }
                    ) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}