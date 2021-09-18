package com.hfad.ww2planesencyclopedia;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    TextView textView;
    TextToSpeech t1;
    boolean isPlaying=false;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        //sound button initialization

        Button sound = (Button) findViewById(R.id.sound);
        sound.setOnClickListener(this);
        final Button narrator = (Button)findViewById(R.id.narrate);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
         public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        narrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = textView.getText().toString();
                //Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
            case R.id.sound: {
                Log.i("sound", "onClick: ");


                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.spitfiresound);
                if (isPlaying) {
                   mediaPlayer.pause();
                    Log.i("sound", "off ");
                } else {
                    mediaPlayer.start();
                    Log.i("sound", "on");
                }
                isPlaying = !isPlaying;
            }
            }
        }
    }



