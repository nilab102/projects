package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public class ooboard extends AppCompatActivity {
    private ScreenSlidePagerAdapter screenSlidePagerAdapter;
    private static final int Num_pages=3;
    private ViewPager viewPager;
    ImageButton imageButton;
    int count=0;
    boolean flag =true;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        viewPager=findViewById(R.id.pager);
        screenSlidePagerAdapter =new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(screenSlidePagerAdapter);
        String k =MemoryData.getName(ooboard.this);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dref = db.getReference().child("user");
        if(k.isEmpty()==true) {
            while (flag) {
                Date currentTime = Calendar.getInstance().getTime();
                str = currentTime.toString() ;

                Query checkUsers = dref.equalTo(str);
                MemoryData.saveName(str,ooboard.this);
            }

        }
 /*       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ooboard.this,HomeBar.class));


            }
        },6500);
*/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBordingFragment1 tab1= new OnBordingFragment1();
                    return tab1;
                case 1:
                    OnBordingFragment2 tab2= new OnBordingFragment2();
                    return tab2;
                case 2:
                    OnBordingFragment3 tab3= new OnBordingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return Num_pages ;
        }
    }
}