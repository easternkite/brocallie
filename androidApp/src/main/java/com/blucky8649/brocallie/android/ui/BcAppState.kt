package com.blucky8649.brocallie.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Stable
class BcAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
)

@Composable
fun rememberBcAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(navController, coroutineScope) {
    BcAppState(navController, coroutineScope)
}