package com.example.farahasmaabobakermohamed.activite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farahasmaabobakermohamed.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class paiementActivity extends AppCompatActivity implements PaymentResultListener {

    TextView subtotal,discount,shipping,total;
    Button paiementbtn;
    double amount=0.0;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);


        amount=getIntent().getDoubleExtra("amount",0.0);

        subtotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView17);
        discount.setText("10"+"%");
        discount.getText().toString();
        shipping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);
        paiementbtn=findViewById(R.id.pay_btn);
        subtotal.setText(amount+"DJF");

        paiementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //paiementmethode();
                Toast.makeText(paiementActivity.this,"Paiement reussi",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void paiementmethode(){
        Checkout checkout = new Checkout();

        final Activity activity = paiementActivity.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "My E-Commerce App");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            // Currency type
            options.put("currency", "USD");
            //double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
            amount = amount * 100;
            //amount
            options.put("amount", amount);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "developer.kharag@gmail.com");
            //contact
            preFill.put("contact", "7489347378");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
    }

}