package com.revbase.zaidanarrafif.presentation.student.quran_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink
import com.revbase.zaidanarrafif.presentation.ui.theme.PlaceholderGrey

@Composable
fun SearchBar(
    barHeight: Dp = 52.dp,
    onSearchButtonClicked: (String) -> Unit
) {
    var searchQuery by remember{ mutableStateOf("") }
    val searchBarConstraintSet = ConstraintSet {
        val searchTextField = createRefFor("searchTextField")
        val searchButton = createRefFor("searchButton")

        constrain(searchTextField) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(searchButton.start, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(barHeight)
        }

        constrain(searchButton) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(searchTextField.end)
            end.linkTo(parent.end)
            width = Dimension.value(barHeight)
            height = Dimension.value(barHeight)
        }
    }
    
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight),
        constraintSet = searchBarConstraintSet
    ) {
        SearchTextField(
            text = searchQuery,
            placeholder = "Cari surat disini yukk" ,
            onTextChanged = {
                searchQuery = it
                if(searchQuery.isEmpty()) {
                    onSearchButtonClicked(searchQuery)
                }
            },
            modifier = Modifier.layoutId("searchTextField")
        )
        Button(
            onClick = { onSearchButtonClicked(searchQuery) },
            modifier = Modifier
                .size(52.dp)
                .layoutId("searchButton"),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    color: Color = LightGrey,
    placeholder: String,
    height: Dp = 52.dp,
    onTextChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .height(height),
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    color = PlaceholderGrey,
                    fontSize = 16.sp,
                    fontFamily = Constant.LATO_FONT_FAMILY
                )
            )
        },
        textStyle = TextStyle(
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = color,
            cursorColor = Pink,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}