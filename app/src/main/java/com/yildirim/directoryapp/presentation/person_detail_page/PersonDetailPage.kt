package com.yildirim.directoryapp.presentation.person_detail_page
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yildirim.directoryapp.entity.Persons
import com.yildirim.directoryapp.presentation.components.PhoneField
import com.yildirim.directoryapp.presentation.components.UpdateOutlinedTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PersonDetailPage(navController: NavController,getPerson:Persons) {
    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonTelephone = remember { mutableStateOf("") }
    val viewModel: PersonDetailPageViewModel = viewModel()

    LaunchedEffect(key1 = true){
        tfPersonName.value = getPerson.person_name
        tfPersonTelephone.value = getPerson.person_telephone
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Page")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
            )
            UpdateOutlinedTextField(
                value = tfPersonName.value,
                onValueChange = {tfPersonName.value = it},
                label = { Text(text = "Person Name") }
            )
            Spacer(modifier = Modifier.size(30.dp))
            PhoneField(
                phone = tfPersonTelephone.value,
                label = { Text(text = "Person Telephone") },
                onPhoneChanged = {tfPersonTelephone.value = it}
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick = {
                    val personName = tfPersonName.value
                    val personTelephone = tfPersonTelephone.value
                    viewModel.update(getPerson.person_id, personName, personTelephone)
                    navController.navigate("main_page")
                },
                modifier = Modifier.size(250.dp,50.dp)) {
                Text(text = "UPDATE")
            }
        }
    }
}
