package com.example.foodapp_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp_2.Domain.FoodDomain;
import com.example.foodapp_2.Helper.ManagementCart;
import com.example.foodapp_2.R;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder=1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle(){
        //How to pass an object from one activity to another on Android
        object= (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder++;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1) {
                    numberOrder--;
                    numberOrderTxt.setText(String.valueOf(numberOrder));
                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }
    private void initView(){
        addToCartBtn=(TextView) findViewById(R.id.addToCartBtn);
        titleTxt=(TextView) findViewById(R.id.titleTxt);
        feeTxt=(TextView) findViewById(R.id.priceTxt);
        descriptionTxt=(TextView) findViewById(R.id.descriptionTxt);
        plusBtn=(ImageView) findViewById(R.id.plusBtn);
        minusBtn=(ImageView) findViewById(R.id.minusBtn);
        picFood=(ImageView) findViewById(R.id.picFood);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
    }
}