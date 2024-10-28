package com.blucky8649.profile

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import brocallie.shared.feature.profile.generated.resources.Res
import brocallie.shared.feature.profile.generated.resources.ic_brocallie_graphic
import compose.icons.TablerIcons
import compose.icons.tablericons.BrandKotlin
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun ProfilePreview() {
    ProfileScreen(
        name = "BroCallie",
        image = {
            Image(
                modifier = it,
                painter = painterResource(Res.drawable.ic_brocallie_graphic),
                contentDescription = "preview image",
                contentScale = ContentScale.Crop
            )
        },
        type = "Vegetable",
        personality = "Ferocity",
        gender = "Male",
        job = "Vegetable worker",
        age = 13,
        tone = "Bright",
        hobby = "Reading a book",
        onBackPressed = {}
    )

}

@Composable
@Preview
fun NameSpacePreview() {
    NameSpace("Bro Callie")
}

@Composable
@Preview
fun TraitCardPreview() {
    TraitCard(
        title = "Personality",
        content = "Ferocity",
        startIcon = TablerIcons.BrandKotlin
    )
}