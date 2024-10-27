package com.blucky8649.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import brocallie.shared.feature.profile.generated.resources.Res
import brocallie.shared.feature.profile.generated.resources.feature_contact_detail_title
import brocallie.shared.feature.profile.generated.resources.ic_brocallie_graphic
import com.blucky8649.designsystem.BcTopAppBar
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiGraphBuilder
import compose.icons.TablerIcons
import compose.icons.tablericons.BrandKotlin
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
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
        voice = "A husky voice",
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