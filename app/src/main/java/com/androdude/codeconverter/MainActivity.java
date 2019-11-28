package com.androdude.codeconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner s1,s2;
    private EditText e1;
    private TextView e2,e3;
    private Button b1,b2;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private Handler h =new Handler();
    BinaryToOther B = new BinaryToOther();
    DecimalToOther D = new DecimalToOther();
    OctalToOther O = new OctalToOther();
    HexadecimalToOther H = new HexadecimalToOther();

    private String t1="",t2="",t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        if(savedInstanceState!=null)
        {
            String data = savedInstanceState.getString("key");
            e1.setText(data);
        }


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5483101987186950/6631348570");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());










        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);adView.setAdUnitId("ca-app-pub-5483101987186950/8442628852");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });














        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbersystem, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(MainActivity.this);
        s2.setAdapter(adapter);
        s2.setOnItemSelectedListener(MainActivity.this);



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("key",e1.getText().toString());
    }

    private void showAds()
{
    h.postDelayed(new Runnable() {
        @Override
        public void run() {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Load the next interstitial.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

            });
        }
    },2000);


}

    private void setUI()
    {
        s1 = findViewById(R.id.spinner1);
        s2 = findViewById(R.id.spinner2);
        e1 = findViewById(R.id.editText1);
        e2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.copy);

    }

    private void copyToClipboard(String text)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied", text);
        clipboard.setPrimaryClip(clip);
    }




    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, int position, long id) {


        if(parent.getId() == R.id.spinner1)
        {
            t1=parent.getItemAtPosition(position).toString();
        }
        else if(parent.getId() == R.id.spinner2)
        {
            t2=parent.getItemAtPosition(position).toString();
        }


        if(t1.equals("Binary") && t2.equals("Decimal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Binary Code");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();

                    if(!t3.isEmpty())
                    {
                        if(e1.length()<=10)
                        {
                            if(B.isBinary(t3))
                            {
                                final int ans = B.binaryToDecimal(Integer.parseInt(t3));
                                e2.setText(String.valueOf(ans));
                                b2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        copyToClipboard(String.valueOf(ans));
                                        Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Binary Input Can Only Have 0 or 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(t1.equals("Binary") && t2.equals("Octal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Binary Code");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t3=e1.getText().toString();

                    if(!t3.isEmpty())
                    {
                        if(e1.length()<=10)
                        {
                            if(B.isBinary(t3))
                            {
                                final int ans = B.convertBinarytoOctal(Integer.parseInt(t3));
                                e2.setText(String.valueOf(ans));
                                b2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        copyToClipboard(String.valueOf(ans));
                                        Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Binary Input Can Only Have 0 or 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(t1.equals("Binary") && t2.equals("Hexadecimal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Binary Code");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();

                    if(!t3.isEmpty())
                    {
                        if(e1.length()<=10)
                        {
                            if(B.isBinary(t3))
                            {
                                final String ans = B.binaryTohexadecimal(Integer.parseInt(t3));
                                e2.setText(ans);
                                b2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        copyToClipboard(String.valueOf(ans));
                                        Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Binary Input Can Only Have 0 or 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(t1.equals("Decimal") && t2.equals("Binary"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Decimal Code");


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(D.isNumber(t3))
                                {
                                    final int ans1 = D.decToBinary(Integer.parseInt(t3));
                                    e2.setText(String.valueOf(ans1));
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans1));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Decimal Input Can Only Have Numbers From 0 To 9", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Decimal") && t2.equals("Octal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Decimal Code");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(D.isNumber(t3))
                                {
                                    final int ans1 = D.toOctal(Integer.parseInt(t3));
                                    e2.setText(String.valueOf(ans1));
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans1));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Decimal Input Can Only Have Numbers From 0 To 9", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Decimal") && t2.equals("Hexadecimal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Decimal Code");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(D.isNumber(t3))
                                {
                                    final String ans = D.convertToHexadecimal(Integer.parseInt(t3));
                                    e2.setText(ans);
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(ans);
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Decimal Input Can Only Have Numbers From 0 To 9", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Octal") && t2.equals("Binary"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Octal Code");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(O.isOctal(t3))
                                {
                                    final long ans = O.convertOctalToBinary(Integer.parseInt(t3));
                                    e2.setText(String.valueOf(ans));
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Octal Input Can Only Have Numbers From 0 To 7", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Octal") && t2.equals("Decimal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Octal Code");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(O.isOctal(t3))
                                {
                                    final long ans = O.octalToDecimal(Integer.parseInt(t3));

                                    e2.setText(String.valueOf(ans));
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Octal Input Can Only Have Numbers From 0 To 7", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }

        else if(t1.equals("Octal") && t2.equals("Hexadecimal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_NUMBER);
            e1.setHint("Enter A Octal Code");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<4)
                            {
                                if(O.isOctal(t3))
                                {
                                    long t = O.convertOctalToBinary(Integer.parseInt(t3));
                                    final int c = B.binaryToDecimal((int) t);
                                    final String ans = D.convertToHexadecimal(c);
                                    e2.setText(ans);
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Octal Input Can Only Have Numbers From 0 To 7", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Hexadecimal") && t2.equals("Binary"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_TEXT); //Change It In HexaDecimal
            e1.setHint("Enter A Hexadecimal Code");
            e1.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<8)
                            {
                                if(H.isHexadecimal(t3))
                                {
                                    final String ans1 = String.valueOf(H.hexadecimalToDecimal(t3));
                                    final int ans = D.decToBinary(Integer.valueOf(ans1));
                                    e2.setText(String.valueOf(ans));
                                    Toast.makeText(MainActivity.this, String.valueOf(ans), Toast.LENGTH_SHORT).show();
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Hexdecimal Input Can Only Have Numbers From 0 To 10 And A to F", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }


        else if(t1.equals("Hexadecimal") && t2.equals("Decimal"))
        {
            e1.setText("");
            e2.setText("");
              e1.setInputType(InputType.TYPE_CLASS_TEXT); //Change It In HexaDecimal
            e1.setHint("Enter A Hexadecimal Code");
            e1.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<8)
                            {
                                if(H.isHexadecimal(t3))
                                {
                                    final String ans = String.valueOf(H.hexadecimalToDecimal(t3));
                                    //final String ans = D.convertToHexadecimal(c);
                                    e2.setText(ans);
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Hexdecimal Input Can Only Have Numbers From 0 To 10 And A to F", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else if(t1.equals("Hexadecimal") && t2.equals("Octal"))
        {
            e1.setText("");
            e2.setText("");
            e1.setInputType(InputType.TYPE_CLASS_TEXT); //Change It In HexaDecimal
            e1.setHint("Enter A Hexadecimal Code");
            e1.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    t3=e1.getText().toString();
                    try {
                        if(!t3.isEmpty())
                        {
                            if(e1.length()<8)
                            {
                                if(H.isHexadecimal(t3))
                                {
                                    final String ans1 = String.valueOf(H.hexadecimalToDecimal(t3));
                                    final int ans = D.toOctal(Integer.parseInt(ans1));
                                    //final String ans = D.convertToHexadecimal(c);
                                    e2.setText(String.valueOf(ans));
                                    b2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            copyToClipboard(String.valueOf(ans));
                                            Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Hexdecimal Input Can Only Have Numbers From 0 To 10 And A to F", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Convertion Overflowed", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        e2.setText("0");
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                copyToClipboard("0");
                                Toast.makeText(MainActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }


        else if(t1 == t2)
        {
            e1.setInputType(InputType.TYPE_CLASS_TEXT);
            e1.setText("");
            e2.setText("");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAds();
                    e1.setText("");
                    Toast.makeText(MainActivity.this, "Same Number System", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
