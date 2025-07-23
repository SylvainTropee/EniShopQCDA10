package com.example.eni_shop.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {  EniShopTopBarTitle () })
}


@Composable
fun EniShopTopBarTitle(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "ENI SHOP",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "ENI-SHOP",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp
        )
    }
}

@Preview
@Composable
private fun Preview() {
    EniShopTopBarTitle()
}