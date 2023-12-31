package com.covenant.noteapp.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.covenant.noteapp.components.TransparentTextField
import com.covenant.noteapp.data.NoteTable
import com.covenant.noteapp.viewmodel.NoteViewModel
import java.time.Instant
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavHostController, viewModel: NoteViewModel) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = { Text(text = "") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back arrow"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    viewModel.addNote(
                        NoteTable(
                            id = 0,
                            header = viewModel.header.text,
                            body = viewModel.body.text,
                            dateCreated = Instant.now(),
                            isDeleted = false,
                            isPinned = false,
                        )
                    )
                    Toast.makeText(context, "Note saved!", Toast.LENGTH_LONG).show()
                    viewModel.clearContents()
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Save Note"
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Transparent
            )
        )

        LazyColumn(content = {
            item {
                TransparentTextField(
                    label = "Title",
                    textValue = viewModel.header.text,
                    onValueChange = {newHeader ->
                        viewModel.updateHeader(newHeader)
                    },
                    labelSize = 24.sp,
                    labelWeight = FontWeight.SemiBold,
                    labelFont = FontFamily.Monospace,
                    modifier = Modifier.fillMaxWidth()

                )

                TransparentTextField(
                    label = "Note",
                    textValue = viewModel.body.text,
                    onValueChange = {newBody ->
                        viewModel.updateBody(newBody)
                    },
                    labelSize = 15.sp,
                    labelWeight = FontWeight.Normal,
                    labelFont = FontFamily.Monospace,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                )
            }
        })


    }
}
