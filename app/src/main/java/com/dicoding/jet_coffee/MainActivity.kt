package com.dicoding.jet_coffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.jet_coffee.model.BottomBarItem
import com.dicoding.jet_coffee.model.Menu
import com.dicoding.jet_coffee.model.dummyBestSellerMenu
import com.dicoding.jet_coffee.model.dummyCategory
import com.dicoding.jet_coffee.model.dummyMenu
import com.dicoding.jet_coffee.ui.components.CategoryItem
import com.dicoding.jet_coffee.ui.components.HomeSection
import com.dicoding.jet_coffee.ui.components.MenuItem
import com.dicoding.jet_coffee.ui.components.Search
import com.dicoding.jet_coffee.ui.components.SectionText
import com.dicoding.jet_coffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetCoffeeApp()
                }
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Scaffold(bottomBar = { BottomBar() }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Banner()
            HomeSection(title = stringResource(id = R.string.section_category)) {
                CategoryRow()
            }
            HomeSection(title = stringResource(id = R.string.section_favorite_menu)) {
                MenuRow(items = dummyMenu)
            }
            HomeSection(title = stringResource(id = R.string.section_best_seller_menu)) {
                MenuRow(items = dummyBestSellerMenu)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(180.dp)
        )
        Search()
    }
}

@Composable
fun CategoryRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory }) {
            CategoryItem(category = it)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    JetCoffeeTheme {
        CategoryRow()
    }
}

@Composable
fun MenuRow(modifier: Modifier = Modifier, items: List<Menu>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(items, key = { it.title }) {
            MenuItem(menu = it)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MenuRowPreview() {
    JetCoffeeTheme {
        MenuRow(items = dummyMenu)
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = Modifier) {
        val navigationItems = listOf(
            BottomBarItem(title = stringResource(R.string.menu_home), icon = Icons.Default.Home),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )

        navigationItems.map {
            NavigationBarItem(
                selected = it.title == navigationItems[0].title,
                onClick = { /*TODO*/ },
                label = {
                    Text(text = it.title)
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = "")
                }
            )
        }
    }
}