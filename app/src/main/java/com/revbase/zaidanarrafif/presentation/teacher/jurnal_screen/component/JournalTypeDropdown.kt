package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JournalTypeDropdown(
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false)}
    val dropdownItems = listOf("Kegiatan", "Ibadah")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Jurnal " + dropdownItems[selectedIndex],
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
            onDismissRequest = { expanded = false }
        ) {
            dropdownItems.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedIndex = index
                    onClick(s)
                }) {
                    Text(text = "Jurnal $s")
                }
            }
        }
    }

}