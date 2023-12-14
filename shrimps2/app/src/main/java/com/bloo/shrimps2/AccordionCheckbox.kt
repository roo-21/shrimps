package com.bloo.shrimps2

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

data class AccordionItem(val title: String , val checkboxes: List<CheckboxItem>,var expanded:Boolean = false)
data class CheckboxItem(val label: String, var checked: MutableState<Boolean> = mutableStateOf(false))


class AccordionViewModel : ViewModel(){
    val accordionItems1 =  mutableStateListOf<AccordionItem>()
    val accordionItems2 =  mutableStateListOf<AccordionItem>()
    val accordionItems3 =  mutableStateListOf<AccordionItem>()

    fun resetCheckboxes(viewModel: AccordionViewModel) {
        accordionItems1.forEach { item ->
            item.checkboxes.forEach { checkboxItem ->
                checkboxItem.checked.value = false
            }
        }

        accordionItems2.forEach { item ->
            item.checkboxes.forEach { checkboxItem ->
                checkboxItem.checked.value = false
            }
        }

        accordionItems3.forEach { item ->
            item.checkboxes.forEach { checkboxItem ->
                checkboxItem.checked.value = false
            }
        }
    }

    fun getCheckedLabels(): Set<String> {
        val checkedLabels = mutableSetOf<String>()

        accordionItems1.flatMap { it.checkboxes }
            .filter { it.checked.value }
            .forEach {
                checkedLabels.add(it.label)
            }

        accordionItems2.flatMap { it.checkboxes }
            .filter { it.checked.value }
            .forEach {
                checkedLabels.add(it.label)
            }

        accordionItems3.flatMap { it.checkboxes }
            .filter { it.checked.value }
            .forEach {
                checkedLabels.add(it.label)
            }

        return checkedLabels
    }
}

@Composable
fun Accordion(
    viewModel: AccordionViewModel=AccordionViewModel(),
    modifier: Modifier = Modifier
) {
    val accordionItems1 = viewModel.accordionItems1
    val accordionItems2 = viewModel.accordionItems2
    val accordionItems3 = viewModel.accordionItems3

    Column(modifier = modifier ) {

        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text="External Manifestations",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        accordionItems1.forEach { item ->
            var isExpanded by remember { mutableStateOf(false) }

            Column(
                Modifier.border(1.dp, Color.LightGray)
            ){
                Row(
                    modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(24.dp)
                        .height(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier= Modifier.size(24.dp)
                    )
                }

                if (isExpanded) {
                    CheckboxList(checkboxItems = item.checkboxes)
                }
            }
            Spacer(Modifier.height(4.dp))
        }

        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text="Behavioural Changes",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        accordionItems2.forEach { item ->
            var isExpanded by remember { mutableStateOf(false) }

            Column(
                Modifier.border(1.dp, Color.LightGray)
            ){
                Row(
                    modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(24.dp)
                        .height(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier= Modifier.size(24.dp)
                    )
                }


                if (isExpanded) {
                    CheckboxList(checkboxItems = item.checkboxes)
                }
            }
            Spacer(Modifier.height(4.dp))
        }

        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text="Internal Manifestations",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        accordionItems3.forEach { item ->
            var isExpanded by remember { mutableStateOf(false) }

            Column(
                Modifier.border(1.dp, Color.LightGray)
            ){
                Row(
                    modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(24.dp)
                        .height(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier= Modifier.size(24.dp)
                    )
                }


                if (isExpanded) {
                    CheckboxList(checkboxItems = item.checkboxes)
                }
            }
            Spacer(Modifier.height(4.dp))
        }
    }
}


@Composable
fun CheckboxList(checkboxItems: List<CheckboxItem>) {
    checkboxItems.forEach { checkboxItem ->
        var isChecked = checkboxItem.checked.value

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(width = 1.dp, color = Color.LightGray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { newChecked ->
                    isChecked = newChecked // Update the remembered state immediately
                    checkboxItem.checked.value = newChecked // Update the data state
                }
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = checkboxItem.label)
        }
    }
}