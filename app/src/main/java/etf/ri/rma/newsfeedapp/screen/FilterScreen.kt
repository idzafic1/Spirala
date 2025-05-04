package etf.ri.rma.newsfeedapp.screen

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.Alignment

private fun formatDate(timeInMillis: Long): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(Date(timeInMillis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    dateRangePickerState: DateRangePickerState
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(navController: NavController) {
    val homeStateHandle = navController.previousBackStackEntry?.savedStateHandle

    var selectedCategory by remember {
        mutableStateOf(homeStateHandle?.get<String>("selectedCategory") ?: "Sve")
    }
    var textList by remember {
        mutableStateOf(homeStateHandle?.get<List<String>>("unwantedWords") ?: emptyList())
    }
    var enteredText by remember { mutableStateOf("") }

    var showDateRangePicker by remember { mutableStateOf(false) }
    var selectedStartDate by remember {
        mutableStateOf(homeStateHandle?.get<Long>("startDate"))
    }
    var selectedEndDate by remember {
        mutableStateOf(homeStateHandle?.get<Long>("endDate"))
    }
    val dateRangePickerState = rememberDateRangePickerState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val categories = listOf("Sve", "Politika", "Sport", "Nauka/tehnologija", "None")
            val tags = listOf("filter_chip_all", "filter_chip_pol", "filter_chip_spo", "filter_chip_sci", "filter_chip_none")

            categories.forEachIndexed { index, category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) },
                    modifier = Modifier.testTag(tags[index])
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Opseg datuma",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedStartDate != null && selectedEndDate != null) {
                    val startDateStr = formatDate(selectedStartDate!!)
                    val endDateStr = formatDate(selectedEndDate!!)
                    "$startDateStr;$endDateStr"
                } else {
                    "Nije odabran opseg datuma"
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .testTag("filter_daterange_display")
            )
        }

        // Put both date range buttons in the same row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { showDateRangePicker = true },
                modifier = Modifier
                    .weight(1f)
                    .testTag("filter_daterange_button")
            ) {
                Text("Odaberi opseg")
            }

            Button(
                onClick = {
                    selectedStartDate = null
                    selectedEndDate = null
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("filter_daterange_reset_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text("Reset opseg datuma")
            }
        }

        if (showDateRangePicker) {
            DateRangePickerDialog(
                onDismissRequest = { showDateRangePicker = false },
                confirmButton = {
                    Button(
                        onClick = {
                            dateRangePickerState.selectedStartDateMillis?.let { start ->
                                selectedStartDate = start
                            }
                            dateRangePickerState.selectedEndDateMillis?.let { end ->
                                selectedEndDate = end
                            }
                            showDateRangePicker = false
                        }
                    ) {
                        Text("Potvrdi")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDateRangePicker = false }) {
                        Text("Odustani")
                    }
                },
                dateRangePickerState = dateRangePickerState
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nepoželjne riječi",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = enteredText,
                onValueChange = { enteredText = it },
                label = { Text("Unesite nepoželjnu riječ") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("filter_unwanted_input")
            )

            Button(
                onClick = {
                    if (enteredText.isNotBlank()) {
                        if (!textList.any { it.equals(enteredText, ignoreCase = true) }) {
                            textList = textList + enteredText
                        }
                        enteredText = ""
                    }
                },
                modifier = Modifier
                    .testTag("filter_unwanted_add_button")
                    .align(Alignment.CenterVertically)
            ) {
                Text("Dodaj")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .testTag("filter_unwanted_list"),
            colors = androidx.compose.material3.CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                if (textList.isEmpty()) {
                    Text(
                        text = "Nema dodanih riječi",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                } else {
                    LazyColumn {
                        items(textList) { text ->
                            Text(
                                text = text,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Put both action buttons in the same row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.apply {
                        set("updatedCategory", selectedCategory)
                        set("updatedStartDate", selectedStartDate)
                        set("updatedEndDate", selectedEndDate)
                        set("updatedUnwantedWords", textList)
                    }
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("filter_apply_button")
            ) {
                Text("Primijeni filtere")
            }


            Button(
                onClick = {
                    textList = emptyList()
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("filter_unwanted_reset_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text("Obriši sve nepoželjne riječi")
            }

        }
    }
}