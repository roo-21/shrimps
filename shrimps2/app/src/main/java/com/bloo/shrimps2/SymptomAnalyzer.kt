package com.bloo.shrimps2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SymptomsAnalyser(navController: NavController,sharedViewModel: SharedViewModel){
    val viewModel = viewModel<AccordionViewModel>()
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .background(color = Color.White)

        ) {
            if (viewModel.accordionItems1.isEmpty()) {
                viewModel.accordionItems1.addAll(
                    listOf(
                        AccordionItem(
                            "Antennae/Antennule Related Signs",
                            listOf(
                                CheckboxItem("Antennae Cut"),
                                CheckboxItem("Antennule Cut"),
                                CheckboxItem("Brittle antennae"),
                                CheckboxItem("Necrosis of antennules tip"),
                                CheckboxItem("Necrotic tip of antennae")
                            )
                        ),
                        AccordionItem(
                            "Discoloration",
                            listOf(
                                CheckboxItem("Red/Pink coloration of chelated legs"),
                                CheckboxItem("Red/pink coloration of walking legs"),
                                CheckboxItem("Reddening /pinkish appearance of aody as a whole"),
                                CheckboxItem("Darkened body"),
                                CheckboxItem("Colour Bleaching/pale appearance"),
                                CheckboxItem("Bright yellow markings on body"),
                                CheckboxItem("Melanisation of the whole body"),
                                CheckboxItem("Brownish discoloration of shrimp"),
                                CheckboxItem("Yellowing of cephalothorax"),
                                CheckboxItem("Redness of cephalothorax"),
                                CheckboxItem("Melanisation of cephalothorax")
                            )
                        ),
                        AccordionItem(
                            "Pleopod Abnormalities",
                            listOf(
                                CheckboxItem("Pink/red discoloration of pleopods"),
                                CheckboxItem("Fouling of pleopods"),
                                CheckboxItem("Darkened edges of pleopods"),
                                CheckboxItem("Loss/erosion of pleopods"),
                                CheckboxItem("Fuzzy mat-like appearance of pleopods")
                            )
                        ),
                        AccordionItem(
                            "Uropod Abnormalities",
                            listOf(
                                CheckboxItem("Reddening of Uropods"),
                                CheckboxItem("Fouling of uropod"),
                                CheckboxItem("Degeneration of Uropod"),
                                CheckboxItem("Darkened edges of uropods"),
                                CheckboxItem("Loss/erosion of uropod")
                            )
                        ),
                        AccordionItem(
                            "Eye Abnormalities",
                            listOf(
                                CheckboxItem("Necrosis of eye stalk"),
                                CheckboxItem("Eye damage"),
                                CheckboxItem("Eyeball turns brown")
                            )
                        ),
                        AccordionItem(
                            "Shell/Exoskeleton Abnormalities",
                            listOf(
                                CheckboxItem("White spot(rosette-like) on carapace"),
                                CheckboxItem("Loose shell with space between shell and muscle"),
                                CheckboxItem("Fouling of exoskeleton"),
                                CheckboxItem("Exoskeleton turn grey to dark blue-black"),
                                CheckboxItem("Localized lesions on exoskeleton"),
                                CheckboxItem("Occasional white spots on exoskeleton"),
                                CheckboxItem("Soft shell"),
                                CheckboxItem("Melanised cuticular lesions on exoskeleton"),
                                CheckboxItem("Browning/blackening of exoskeleton"),
                                CheckboxItem("Soft papery /leathery carapace"),
                                CheckboxItem("Blisters on the exoskeleton")
                            )
                        ),
                        AccordionItem(
                            "Gill Abnormalities",
                            listOf(
                                CheckboxItem("Gill necrosis"),
                                CheckboxItem("Darkened Gills"),
                                CheckboxItem("Gills turn white /yellow/brown"),
                                CheckboxItem("Gill fouling"),
                                CheckboxItem("Reddening of Gills"),
                                CheckboxItem("Fuzzy mat like appearance of gills"),
                                CheckboxItem("Accumulation of detritus in gills"),
                                CheckboxItem("Black coloration of gills")
                            )
                        ),
                        AccordionItem(
                            "Rostrum Abnormalities",
                            listOf(
                                CheckboxItem("Bent rostrum"),
                                CheckboxItem("Rostrum deformed to one side")
                            )
                        ),
                        AccordionItem(
                            "Abnormalities in the Abdominal Region",
                            listOf(
                                CheckboxItem("White spot on epidermis(junction of abdominal plates)"),
                                CheckboxItem("Bamboo shaped abdomen"),
                                CheckboxItem("Necrotic distal abdominal segment"),
                                CheckboxItem("Enlarged abdominal segment"),
                                CheckboxItem("Abdomen turns milky white"),
                                CheckboxItem("Bent abdominal segments"),
                                CheckboxItem("Opacity of abdominal muscle"),
                                CheckboxItem("Cloudy abdominal musculature"),
                                CheckboxItem("White patches on abdominal muscle"),
                                CheckboxItem("Spongy abdomen"),
                                CheckboxItem("Flaccid abdominal muscle")
                            )
                        ),
                        AccordionItem(
                            "Tail/Telson Abnormalities",
                            listOf(
                                CheckboxItem("Opacity of tail muscle"),
                                CheckboxItem("Reddening of telson"),
                                CheckboxItem("Degeneration of telson"),
                                CheckboxItem("Discoloration of telson"),
                                CheckboxItem("White appearance of tail fan"),
                                CheckboxItem("Necrotic telson")
                            )
                        ),
                        AccordionItem(
                            "Other Abnormalities on Shrimp Body",
                            listOf(
                                CheckboxItem("Mottled appearance"),
                                CheckboxItem("Fouling of whole body"),
                                CheckboxItem("Floating moults on water surface"),
                                CheckboxItem("Death during moulting"),
                                CheckboxItem("Luminescence"),
                                CheckboxItem("Loss/erosion of appendages"),
                                CheckboxItem("Fuzzy mat like appearance of the whole body"),
                                CheckboxItem("Outward growth of fungal filaments from the body"),
                                CheckboxItem("White necrotic areas in muscle")
                            )
                        )
                    )
                )
            }
            if (viewModel.accordionItems2.isEmpty()){
                viewModel.accordionItems2.addAll(
                    listOf(
                        AccordionItem(
                            "Growth Abnormalities",
                            listOf(
                                CheckboxItem("Reduced Growth"),
                                CheckboxItem("Abnormal growth"),
                                CheckboxItem("Stunted growth")
                            )
                        ),
                        AccordionItem(
                            "Abnormalities in Swimming Behaviour",
                            listOf(
                                CheckboxItem("Erratic /Abnormal swimming"),
                                CheckboxItem("Slow swimming"),
                                CheckboxItem("Swimming on sides"),
                                CheckboxItem("Swimming near water surface"),
                                CheckboxItem("Swim with head poised near water surface"),
                                CheckboxItem("Abnormal slow swimming")
                            )
                        ),
                        AccordionItem(
                            "Abnormalities in Feeding Habits",
                            listOf(
                                CheckboxItem("Reduced Feeding"),
                                CheckboxItem("Feed voraciously for 2-3 days and stop feeding abruptly")
                            )
                        ),
                        AccordionItem(
                            "Other Behavioural Abnormalities",
                            listOf(
                                CheckboxItem("Cannibalism"),
                                CheckboxItem("Poor FCR"),
                                CheckboxItem("Lethargy"),
                                CheckboxItem("Reduced preening"),
                                CheckboxItem("Size variation/ irregular size"),
                                CheckboxItem("Gathering around the edges"),
                                CheckboxItem("Moribound shrimp sink to the bottom and die"),
                                CheckboxItem("Internal mortality"),
                                CheckboxItem("Restlessness"),
                                CheckboxItem("Irrited behaviour")
                            )
                        ),
                    )
                )
            }
            if(viewModel.accordionItems3.isEmpty()) {
                viewModel.accordionItems3.addAll(
                    listOf(
                        AccordionItem(
                            "Pancreas Related Abnormalities",
                            listOf(
                                CheckboxItem("Hypertrophy of Hepatopancreas"),
                                CheckboxItem("Hepatopancreas Turn Yellow"),
                                CheckboxItem("Hepatopancreatic Lesions"),
                                CheckboxItem("Exceptionally Soft Hepatopancreas"),
                                CheckboxItem("Swelling of Hepatopancreas"),
                                CheckboxItem("Atrophy of Hepatopancreas"),
                                CheckboxItem("Hepatopancreatic Necrosis"),
                                CheckboxItem("Vermiform Gregarine Bodies in Hepatopancreas"),
                                CheckboxItem("Reddish Yellow Hepatopancreas"),
                                CheckboxItem("Pale Hepatopancreas Due to Pigment Loss"),
                                CheckboxItem("Massive Degeneration of Hepatopancreas"),
                                CheckboxItem("Hepatopancreas Does Not Squash Easily Between Thumb and Forefinger"),
                                CheckboxItem("Necrosis of Hepatopancreas")
                            )
                        ),
                        AccordionItem(
                            "Heart Muscle Abnormalities",
                            listOf(
                                CheckboxItem("Heart Muscle Damage"),
                                CheckboxItem("Necrosis of Heart Muscle")
                            )
                        ),
                        AccordionItem(
                            "Stomach Abnormalities",
                            listOf(
                                CheckboxItem("Empty Stomach"),
                                CheckboxItem("Pale Stomach"),
                                CheckboxItem("Localized Stomach Infection"),
                                CheckboxItem("Vermiform Gregarine Bodies in Stomach")
                            )
                        ),
                        AccordionItem(
                            "Gut Related Abnormalities",
                            listOf(
                                CheckboxItem("Empty Gut"),
                                CheckboxItem("Mid Gut with White to Yellow-Golden Content"),
                                CheckboxItem("Whitish Intestine/Opaque White Gut"),
                                CheckboxItem("White Midgut Lines"),
                                CheckboxItem("Detachment of Midgut Epithelium"),
                                CheckboxItem("Necrosis of Gut Epithelium"),
                                CheckboxItem("Discontinuous Gut Contents"),
                                CheckboxItem("Distended Mid Gut Junction and Mid Gut")
                            )
                        ),
                        AccordionItem(
                            "Abnormalities in Faecal Matter",
                            listOf(
                                CheckboxItem("Faecal Matter in the Form of Fluid"),
                                CheckboxItem("Floating Fecal Strings on Water Surface"),
                                CheckboxItem("White Feces with Microsporidian Spores")
                            )
                        )
                    )
                )
            }
            Accordion(viewModel = viewModel)
        }

        Box(
            modifier= Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = Color.White)
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                    onClick = {
                        viewModel.resetCheckboxes(viewModel)
                    }
                ) {
                    Text(text = "Reset Selection")
                }
                Button(modifier= Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                    onClick = {

                        val labelSet = viewModel.getCheckedLabels()
                        sharedViewModel.setLabelSet(labelSet)
                        viewModel.resetCheckboxes(viewModel)
                        //println(labelSet)
                        navController.navigate("ResultScreen")
                    }
                ) {
                    Text(text = "Analyze")
                }
            }
        }
    }
}

