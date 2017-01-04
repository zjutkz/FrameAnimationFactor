package com.zjutkz.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zjutkz.frameanimationlib.AnimationFactor;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    private AnimationFactor factor;

    private int[] mAnimFrames = { R.drawable.refresh_10000, R.drawable.refresh_10001, R.drawable.refresh_10002,
            R.drawable.refresh_10003, R.drawable.refresh_10004, R.drawable.refresh_10005,
            R.drawable.refresh_10006, R.drawable.refresh_10007, R.drawable.refresh_10008,
            R.drawable.refresh_10009, R.drawable.refresh_10010, R.drawable.refresh_10011,
            R.drawable.refresh_10012, R.drawable.refresh_10013, R.drawable.refresh_10014,
            R.drawable.refresh_10015, R.drawable.refresh_10016, R.drawable.refresh_10017,
            R.drawable.refresh_10018, R.drawable.refresh_10019, R.drawable.refresh_10020,
            R.drawable.refresh_10021, R.drawable.refresh_10022, R.drawable.refresh_10023,
            R.drawable.refresh_10024, R.drawable.refresh_10025, R.drawable.refresh_10026,
            R.drawable.refresh_10027, R.drawable.refresh_10028, R.drawable.refresh_10029,
            R.drawable.refresh_10030, R.drawable.refresh_10031, R.drawable.refresh_10032,
            R.drawable.refresh_10033, R.drawable.refresh_10034, R.drawable.refresh_10035,
            R.drawable.refresh_10036, R.drawable.refresh_10037, R.drawable.refresh_10038,
            R.drawable.refresh_10039, R.drawable.refresh_10040, R.drawable.refresh_10041,
            R.drawable.refresh_10042, R.drawable.refresh_10043, R.drawable.refresh_10044,
            R.drawable.refresh_10045, R.drawable.refresh_10046, R.drawable.refresh_10047,
            R.drawable.refresh_10048, R.drawable.refresh_10049, R.drawable.refresh_10050,
            R.drawable.refresh_10051, R.drawable.refresh_10052, R.drawable.refresh_10053,
            R.drawable.refresh_10054, R.drawable.refresh_10055, R.drawable.refresh_10056,
            R.drawable.refresh_10057, R.drawable.refresh_10058, R.drawable.refresh_10059,
            R.drawable.refresh_10060, R.drawable.refresh_10061, R.drawable.refresh_10062,
            R.drawable.refresh_10063, R.drawable.refresh_10064, R.drawable.refresh_10065,
            R.drawable.refresh_10066, R.drawable.refresh_10067, R.drawable.refresh_10068,
            R.drawable.refresh_10069, R.drawable.refresh_10070, R.drawable.refresh_10071,
            R.drawable.refresh_10072, R.drawable.refresh_10073, R.drawable.refresh_10074,
            R.drawable.refresh_10075};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.iv);
        factor = AnimationFactor.Builder
                .newBuilder()
                .setAnimFrames(mAnimFrames)
                .setImageView(iv)
                .build();
    }

    public void start(View view){
        if(!factor.isStarted()){
            factor.start();
        }
    }

    public void stop(View view){
        if(factor.isStarted()){
            factor.stop();
        }
    }

    public void reset(View view){
        factor.reset();
    }
}
