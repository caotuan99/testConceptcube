package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    Button btnJoin,btnLogin;
    EditText edtId,edtPassword;
    Boolean check=false;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnJoin=findViewById(R.id.btnJoin);
        edtId=findViewById(R.id.edtId);
        edtPassword=findViewById(R.id.edtPassWord);
        btnLogin=findViewById(R.id.btnLogin);
        progressDialog= new ProgressDialog(this);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,JoinPage.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtId.getText().toString().trim())||TextUtils.isEmpty(edtPassword.getText().toString().trim())){
                    Toast.makeText(LoginPage.this,"Dont empty information",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Login");
                progressDialog.show();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User").child(edtId.getText().toString().trim());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue()==null){
                            progressDialog.dismiss();
                            Toast.makeText(LoginPage.this,"Id or password not correct",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user=snapshot.getValue(User.class);
                            if(user.getPassword().equals(edtPassword.getText().toString().trim())){
                                progressDialog.dismiss();

                                Toast.makeText(getApplication(),"Login Sucessfull",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPage.this,MainActivity.class));
                            }
                            else {
                                progressDialog.dismiss();

                                Toast.makeText(LoginPage.this, "Id or password not correct", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginPage.this, "Login Fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        Intent intent= this.getIntent();
        if(intent!=null){
        String id=intent.getStringExtra("Id");
        edtId.setText(id);
        }
    }
}