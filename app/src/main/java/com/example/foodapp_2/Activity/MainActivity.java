package com.example.foodapp_2.Activity;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp_2.Adapter.CategoryAdapter;
import com.example.foodapp_2.Adapter.PopularAdapter;
import com.example.foodapp_2.Domain.CategoryDomain;
import com.example.foodapp_2.Domain.FoodDomain;
import com.example.foodapp_2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleViewCategory();
        recycleViewPopular();
        bottomNavigation();
    }

        private void clickaddedsoon(ImageView btn){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "This will be added soon", Toast.LENGTH_SHORT).show();
                }
            });
        }
        private void bottomNavigation() {
            ImageView cart = (ImageView) findViewById(R.id.cartBtn); //Not there yet
            LinearLayout homeBtn = findViewById(R.id.homeBtn);
            ImageView support = findViewById(R.id.support);
            ImageView user = findViewById(R.id.user);
            ImageView settings = findViewById(R.id.settings);
            clickaddedsoon(support);
            clickaddedsoon(user);
            clickaddedsoon(settings);



            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, CartListActivity.class));
                }
            });

            homeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
            });
        }
    private void recycleViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }
    private void recycleViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza", "pop_1", "Slices pepperonni, mozzerella cheese, ground black pepper, pizza sauce", 9.76));
        foodList.add(new FoodDomain("Cheese Burger", "pop_2", "beef, Guda cheese pepperonni, mozzerella cheese, burger sauce", 7.56));
        foodList.add(new FoodDomain("vegeatables pizza", "pop_3", "Slices pizza, vegeatables, ground black pepper, pizza sauce", 7.70));
        foodList.add(new FoodDomain("Pepperoni pizza", "pop_1", "Slices pepperonni, mozzerella cheese, ground black pepper, pizza sauce", 9.76));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}