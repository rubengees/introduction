package com.rubengees.introductionsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rubengees.introduction.IntroductionActivity;
import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.IntroductionConfiguration;
import com.rubengees.introduction.common.NumberIndicatorManager;
import com.rubengees.introduction.common.ZoomOutPageTransformer;
import com.rubengees.introduction.entity.Option;
import com.rubengees.introduction.entity.Slide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntroductionBuilder.INTRODUCTION_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            String result = "User chose: ";

            for (Option option : data.<Option>getParcelableArrayListExtra(IntroductionActivity.
                    OPTION_RESULT)) {
                result += option.getPosition() + (option.isActivated() ? " enabled" : " disabled");
            }

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }

    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide().withTitle("Title").withDescription("Description").
                withColorResource(R.color.green).withImageResource(R.drawable.image1));
        result.add(new Slide().withTitle("Gif").withDescription("This is a Gif")
                .withColorResource(R.color.indigo).withImageResource(R.drawable.image3));
        result.add(new Slide().withTitle("Option").withOption(new Option("This is an option", true))
                .withColorResource(R.color.orange).withImageResource(R.drawable.image2));

        return result;
    }

    public void onDefaultClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides()).introduceMyself();
    }

    public void onCustomAnimatorsClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides())
                .withPageTransformer(new ZoomOutPageTransformer()).introduceMyself();
    }

    public void onCustomIndicatorClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides())
                .withIndicatorManager(new NumberIndicatorManager()).introduceMyself();
    }

    public void onCustomButtonsClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides())
                .withPreviousButtonEnabled(false).introduceMyself();
    }

    public void onNoIndicatorClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides()).withIndicatorEnabled(false)
                .introduceMyself();
    }

    public void onPortraitOnlyClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides())
                .withForcedOrientation(IntroductionBuilder.ORIENTATION_PORTRAIT).introduceMyself();
    }

    public void onFullscreenClick(View view) {
        new IntroductionBuilder(this).withSlides(generateSlides())
                .withStyle(IntroductionBuilder.STYLE_FULLSCREEN).introduceMyself();
    }

    public void OnRequestPermissionClick(View view) {
        List<Slide> slides = generateSlides();

        slides.add(0, new Slide().withTitle("Permission Request")
                .withDescription("You can request permissions with the listener")
                .withColorResource(R.color.purple));
        new IntroductionBuilder(this).withSlides(slides)
                .withOnSlideChangedListener(new IntroductionConfiguration.OnSlideChangedListener() {
                    @Override
                    public void onSlideChanged(int from, int to) {
                        if (from == 0 && to == 1) {
                            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        12);
                            }
                        }
                    }
                }).introduceMyself();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 12) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission was successfully granted!", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
