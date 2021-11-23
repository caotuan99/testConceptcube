package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> image;
    private ImageView img;
    private  ImageAdapter imageAdapter;
    private RecyclerView rcvCard;
    private Button btnShuffle,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvCard=findViewById(R.id.rcvCard);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginPage.class));

            }
        });
        btnShuffle=findViewById(R.id.btnShuffle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvCard.setLayoutManager(linearLayoutManager);
        image=new ArrayList<>();
        setImage();
        imageAdapter= new ImageAdapter(image);
        rcvCard.setAdapter(imageAdapter);
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(image);
                imageAdapter= new ImageAdapter(image);
                rcvCard.setAdapter(imageAdapter);

            }
        });
        ItemTouchHelper helper= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                int positionDragged= dragged.getAdapterPosition();
                int positionTarget= target.getAdapterPosition();
                Collections.swap(image,positionDragged,positionTarget);
                imageAdapter.notifyItemMoved(positionDragged,positionTarget);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(rcvCard);

    }
    private  void setImage(){
        image.add(R.drawable.c01);
        image.add(R.drawable.c02);
        image.add(R.drawable.c03);
        image.add(R.drawable.c04);
        image.add(R.drawable.c05);
        image.add(R.drawable.c06);
        image.add(R.drawable.c07);
        image.add(R.drawable.c08);
        image.add(R.drawable.c09);
        image.add(R.drawable.c10);
        image.add(R.drawable.c11);
        image.add(R.drawable.c12);
        image.add(R.drawable.c13);
        image.add(R.drawable.c14);
        //
        image.add(R.drawable.m01);
        image.add(R.drawable.m02);
        image.add(R.drawable.m03);
        image.add(R.drawable.m04);
        image.add(R.drawable.m05);
        image.add(R.drawable.m06);
        image.add(R.drawable.m07);
        image.add(R.drawable.m08);
        image.add(R.drawable.m09);
        image.add(R.drawable.m10);
        image.add(R.drawable.m11);
        image.add(R.drawable.m12);
        image.add(R.drawable.m13);
        image.add(R.drawable.m14);
        image.add(R.drawable.m15);
        image.add(R.drawable.m16);
        image.add(R.drawable.m17);
        image.add(R.drawable.m18);
        image.add(R.drawable.m19);
        image.add(R.drawable.m20);
        image.add(R.drawable.m21);
        //
        image.add(R.drawable.p01);
        image.add(R.drawable.p02);
        image.add(R.drawable.p03);
        image.add(R.drawable.p04);
        image.add(R.drawable.p05);
        image.add(R.drawable.p06);
        image.add(R.drawable.p07);
        image.add(R.drawable.p08);
        image.add(R.drawable.p09);
        image.add(R.drawable.p10);
        image.add(R.drawable.p11);
        image.add(R.drawable.p12);
        image.add(R.drawable.p13);
        image.add(R.drawable.p14);
        //
        image.add(R.drawable.s01);
        image.add(R.drawable.s02);
        image.add(R.drawable.s03);
        image.add(R.drawable.s04);
        image.add(R.drawable.s05);
        image.add(R.drawable.s06);
        image.add(R.drawable.s07);
        image.add(R.drawable.s08);
        image.add(R.drawable.s09);
        image.add(R.drawable.s10);
        image.add(R.drawable.s11);
        image.add(R.drawable.s12);
        image.add(R.drawable.s13);
        image.add(R.drawable.s14);
        //
        image.add(R.drawable.w01);
        image.add(R.drawable.w02);
        image.add(R.drawable.w03);
        image.add(R.drawable.w04);
        image.add(R.drawable.w05);
        image.add(R.drawable.w06);
        image.add(R.drawable.w07);
        image.add(R.drawable.w08);
        image.add(R.drawable.w09);
        image.add(R.drawable.w10);
        image.add(R.drawable.w11);
        image.add(R.drawable.w12);
        image.add(R.drawable.w13);
        image.add(R.drawable.w14);
    }
}