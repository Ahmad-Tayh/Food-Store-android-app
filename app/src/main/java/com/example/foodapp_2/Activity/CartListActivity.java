package com.example.foodapp_2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp_2.Adapter.CartListAdapter;
import com.example.foodapp_2.Helper.ManagementCart;
import com.example.foodapp_2.Interface.ChangeNumberItemsListener;
import com.example.foodapp_2.R;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, checkbtn;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart = new ManagementCart(this);
        initView();
        initList();
        checkoutClick();
        CalculateCart();
        bottomNavigation();
    }

        private void bottomNavigation(){
            ImageView cart = (ImageView) findViewById(R.id.cartBtn); //Not there yet
            LinearLayout homeBtn = findViewById(R.id.homeBtn);

            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(CartListActivity.this, CartListActivity.class));
                }
            });

            homeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(CartListActivity.this, MainActivity.class));
                }
            });
        }
        private void checkoutClick(){
            checkbtn.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(CartListActivity.this, "Your order will be ready in 20 minutes :) ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    private  void initView(){
        recyclerViewList = findViewById(R.id.cartView);
        totalFeeTxt = (TextView) findViewById(R.id.totalFeeTxt);
        taxTxt = (TextView) findViewById(R.id.taxTxt);
        deliveryTxt = (TextView) findViewById(R.id.deliveryTxt);
        totalTxt = (TextView) findViewById(R.id.totalTaxTxt);
        emptyTxt = (TextView) findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
        checkbtn = (TextView) findViewById(R.id.checkbtn);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }
}