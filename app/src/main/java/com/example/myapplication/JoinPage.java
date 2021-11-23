package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class JoinPage extends AppCompatActivity {
    private EditText edtId,edtPassword,edtConfirm,edtEmail;
    private Button btnJoin,btnBack;
    private TextView checkid;
    private DatePicker dtpBirthDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);
        edtId=findViewById(R.id.edtId);
        edtPassword=findViewById(R.id.edtPassWord);
        checkid=findViewById(R.id.tvCheckid);
        edtConfirm=findViewById(R.id.edtConfirm);
        edtEmail=findViewById(R.id.edtEmail);
        btnJoin=findViewById(R.id.btnJoin);
        btnBack=findViewById(R.id.btnBack);
        checkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User").child(edtId.getText().toString().trim());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null){
                            Toast.makeText(JoinPage.this,"ID used",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(JoinPage.this,"ID ok",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinPage.this,LoginPage.class));
            }
        });
        dtpBirthDay=findViewById(R.id.dtpDateOfBirth);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kttt(edtId.getText().toString(),edtPassword.getText().toString(),edtConfirm.getText().toString()
                ,edtEmail.getText().toString())) {
                    String id= edtId.getText().toString().trim();
                    String pw= edtPassword.getText().toString().trim();
                    String cfpw= edtPassword.getText().toString().trim();
                    if(!pw.equals(cfpw)){
                        Toast.makeText(JoinPage.this,"Password not confirm",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User").child(id);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue()==null){
                                HashMap<Object,String> hashMap= new HashMap<>();
                                hashMap.put("id",id);
                                hashMap.put("password",edtPassword.getText().toString().trim());
                                hashMap.put("email",edtEmail.getText().toString().trim());
                                String birthday=dtpBirthDay.getDayOfMonth()+"/"+dtpBirthDay.getMonth()+"/"+dtpBirthDay.getYear();
                                hashMap.put("birthday",birthday);
                                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(JoinPage.this,"Join Successful",Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(JoinPage.this,LoginPage.class);
                                        intent.putExtra("Id",id);
                                        startActivity(intent);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(JoinPage.this,"Join fail",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else {
                                Toast.makeText(JoinPage.this,"duplicate ID, Join fail",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private Boolean kttt(String id, String pw, String cfpw, String email) {
        if(TextUtils.isEmpty(id)||TextUtils.isEmpty(pw)||TextUtils.isEmpty(cfpw)||TextUtils.isEmpty(email)) {
            Toast.makeText(JoinPage.this,"Dont empty information",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}