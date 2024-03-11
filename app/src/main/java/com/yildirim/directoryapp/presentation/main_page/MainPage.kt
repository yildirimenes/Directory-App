package com.yildirim.directoryapp.presentation.main_page
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.yildirim.directoryapp.R
import com.yildirim.directoryapp.entity.Persons
import com.yildirim.directoryapp.presentation.components.DeleteAlertDialog


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {
    val isSearch = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    var isDeleteDialogVisible by remember { mutableStateOf(false) }
    var personToDelete: Persons? by remember { mutableStateOf(null) }
    val defaultController = remember { mutableStateOf(false) }
    val viewModel: MainPageViewModel = viewModel()
    val personList = viewModel.personList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.loadPersons()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if(isSearch.value){
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                viewModel.search(it)
                            },
                            label = { Text(text = "Ara") },

                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                            )
                        )
                    }else{
                        Text(text = "Persons")
                    }
                },
                actions = {
                    if(isSearch.value){
                        IconButton(onClick = {
                            isSearch.value = false
                            tf.value = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.close_image),
                                contentDescription = "",tint = Color.Black)
                        }
                    }else{
                        IconButton(onClick = {
                            isSearch.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.search_image),
                                contentDescription = "",tint = Color.Black)
                        }
                    }
                }
            )
        },
        content = {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 70.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
                ){
                items(
                    count = personList.value!!.count(),
                    itemContent = {
                        val person = personList.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()) {
                            Row(modifier = Modifier.clickable {
                                val personJson = Gson().toJson(person)
                                navController.navigate("person_detail_page/${personJson}")
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${person.person_name} - ${person.person_telephone}")
                                    /*
                                    IconButton(onClick = {
                                        viewModel.delete(person.person_id)
                                    }) {
                                        Icon(painter = painterResource(id = R.drawable.delete_image),
                                            contentDescription = "",tint = Color.Gray)
                                    }*/
                                    DeleteAlertDialog(
                                        isDeleteDialogVisible = isDeleteDialogVisible,
                                        onDismiss = {
                                            isDeleteDialogVisible = false
                                            personToDelete = null
                                        },
                                        onConfirm = {
                                            personToDelete?.person_id?.let { id ->
                                                viewModel.delete(id)
                                            }
                                            isDeleteDialogVisible = false
                                            defaultController.value = true
                                        }
                                    )
                                    IconButton(onClick = {
                                        personToDelete = person
                                        isDeleteDialogVisible = true
                                    }) {
                                        Icon(painter = painterResource(id = R.drawable.delete_image),
                                            contentDescription = "",tint = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("person_register_page") },
                containerColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "",tint = Color.White)
                }
            )
        }
    )
}
