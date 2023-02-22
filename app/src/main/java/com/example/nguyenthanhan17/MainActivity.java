package com.example.nguyenthanhan17;

import static com.example.nguyenthanhan17.R.id.swCm_or_Fit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import kotlin.Unit;
import kotlin.text.UStringsKt;

public class MainActivity extends AppCompatActivity {

    String Gender;
    int age=1,weight=1;
    double BIM;
    Switch swCm_or_Fit,swGender;
    TextInputEditText etAge,etWeight,etHeightcm,etHeightm;
    ImageView ivRemoveAge,ivRemoveWeght,ivPlusAge,ivPlusWeight;
    Button btCal;
    TextView tvcm,tvmet;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAge=findViewById(R.id.etAge);
        etWeight=findViewById(R.id.etWeight);
        tvcm=findViewById(R.id.tvcm);
        tvmet=findViewById(R.id.tvmet);
        ivRemoveAge=findViewById(R.id.ivRemoveAge);
        ivRemoveAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (age>=0){
                String a = String.valueOf(age--);
                etAge.setText(a);
                }else {
                    age=0;
                }
            }
        });
        ivPlusAge=findViewById(R.id.ivPlusAge);
       ivPlusAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(age>=0){
                    String a = String.valueOf(age++);
                    etAge.setText(a);
                }
                else {
                    age=0;
                }
            }
        });
        ivRemoveWeght=findViewById(R.id.ivRemoveWeiht);
        ivRemoveWeght.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight>=0){
                    String a = String.valueOf(weight--);
                    etWeight.setText(a);
                }
                else {
                    weight=0;
                }
            }
        });
        ivPlusWeight=findViewById(R.id.ivPlusWeight);
        ivPlusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight>=0){
                    String a = String.valueOf(weight++);
                    etWeight.setText(a);}
                else {
                    weight=0;
                }

            }
        });

        swCm_or_Fit=findViewById(R.id.swCm_or_Fit);
        swCm_or_Fit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if(swCm_or_Fit.isChecked()) {
                  tvcm.setText("in");
                  tvmet.setText("ft");
              }else {
                  tvcm.setText("cm");
                  tvmet.setText("m");
              }

            }
        });
        swGender=findViewById(R.id.swGender);
        etHeightm=findViewById(R.id.etHeightm);
        etHeightcm=findViewById(R.id.etHeightcm);
      btCal = findViewById(R.id.btCal);
      btCal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Gender=getGender();
              age=Integer.parseInt(etAge.getText().toString());
              double BMI = getBIM();
              String message = getTitle(BMI);
              AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
              builder.setTitle("BMI caculate");
              builder.setMessage("Age: "+age+"\n"+"Gender: "+Gender+"\n"+"BMI: "+BMI+"\n"+message);
              builder.setCancelable(false);
              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                  }
              });
              AlertDialog alertDialog = builder.create();
              alertDialog.show();


          }
      });
    }
    String getGender(){
        if (swGender.isChecked()){
            return "Male";
        }else {
            return "Female";
        }
    }
   double getBIM(){
       weight=Integer.parseInt(etWeight.getText().toString());
       double a = Integer.parseInt(etHeightm.getText().toString());
       double b = Integer.parseInt(etHeightcm.getText().toString());

        if (swCm_or_Fit.isChecked()){
            double fttoin=a*12;
            double intomet=(fttoin+b)*0.0254;
            double kq=weight/(intomet*intomet);
            return kq;

        }else{
       double c =(a+(b/100));
       return  weight/(c*c);}
   }
   String getTitle(double BMI)
   {
       if(BMI<18.5){
           return "You have a normal body Underweight";}
       else if (BMI>=18.5 && BMI<24.9){
           return "You have a normal body Normal or Healthy Weigh! Good";}
       else if (BMI>=25&&BMI<29.9){
           return "You have a normal body Overweigh";}
       else{
           return "You have a normal body Obese";}

   }

}