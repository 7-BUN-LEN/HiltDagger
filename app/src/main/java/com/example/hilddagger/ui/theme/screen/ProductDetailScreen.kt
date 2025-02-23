package com.example.hilddagger.ui.theme.screen

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.hilddagger.data.product.Products
import com.example.hilddagger.ui.theme.HildDaggerTheme

class ProductDetailScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HildDaggerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Retrieve the Products object directly
                    val productObject = intent.getParcelableExtra<Products>("productObject")

                    // Pass the product object to the composable
                    ProductDetail(productObject)
                }
            }
        }
    }

    @Composable
    fun ProductDetail(products: Products?) {
        val context = LocalContext.current
        val act = LocalContext.current as Activity
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { act.finish() },
                    modifier = Modifier
                        .background(color = Color(0x8DE7E1E1), shape = CircleShape)
                        .clip(CircleShape)
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Row(
                    modifier = Modifier
                        .width(70.dp)
                        .background(color = Color(0x8DE7E1E1), shape = RoundedCornerShape(8.dp))
                        .padding(3.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = products?.rating?.rate.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Image(
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )
                }
            }

            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(250.dp),
                painter = rememberAsyncImagePainter(
                    model = products?.image,
                    contentScale = ContentScale.Crop
                ),
                contentDescription = "Product Image"
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color(0x8DE7E1E1))
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x8DE7E1E1)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(15.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            products?.let {
                                Text(
                                    text = it.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Image(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.Red),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0x75F44336), shape = RoundedCornerShape(20.dp))
                                        .padding(10.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        products?.let {
                            Text(
                                text = it.description,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White, shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                        .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(top = 30.dp, bottom = 30.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        onClick = {
                            Toast.makeText(context, "Successfully added to cart", Toast.LENGTH_SHORT).show()
                        },
                    ) {
                        Text(text = "Add to Cart", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
