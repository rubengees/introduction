package com.rubengees.introductionsample;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rubengees.introduction.IntroductionActivity;
import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.Option;
import com.rubengees.introduction.Slide;
import com.rubengees.introduction.interfaces.OnSlideListener;
import com.rubengees.introduction.style.FullscreenStyle;
import com.rubengees.introductionsample.transformer.ColorPageTransformer;
import com.rubengees.introductionsample.transformer.DepthPageTransformer;
import com.rubengees.introductionsample.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final OnSlideListener DEFAULT_ON_SLIDE_LISTENER = new OnSlideListener() {
        @Override
        public void onSlideInit(int position, @Nullable TextView title, @NonNull ImageView image,
                                @Nullable TextView description) {
            if (position % 3 == 1) {
                Glide.with(image.getContext())
                        .load(R.drawable.image3)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(image);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IntroductionBuilder.INTRODUCTION_REQUEST_CODE && resultCode == RESULT_OK) {
            StringBuilder result = new StringBuilder("User chose: ");

            for (Option option : data.<Option>getParcelableArrayListExtra(IntroductionActivity.OPTION_RESULT)) {
                result.append(option.getPosition()).append(option.isActivated() ? " enabled" : " disabled");
            }

            //noinspection ConstantConditions
            Snackbar.make(findViewById(R.id.root), result.toString(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 12 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Permission was granted successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onDefaultClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .introduceMyself();
    }

    public void onZoomOutClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withPageTransformer(new ZoomOutPageTransformer())
                .introduceMyself();
    }

    public void onDepthClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withPageTransformer(new DepthPageTransformer())
                .introduceMyself();
    }

    public void onColorTransformationClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withPageTransformer(new ColorPageTransformer())
                .introduceMyself();
    }

    public void onCustomIndicatorClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withIndicatorManager(new NumberIndicatorManager())
                .introduceMyself();
    }

    public void onCustomButtonsClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withPreviousButtonEnabled(false)
                .introduceMyself();
    }

    public void onNoIndicatorClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withIndicatorEnabled(false)
                .introduceMyself();
    }

    public void onPortraitOnlyClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withForcedOrientation(IntroductionBuilder.ORIENTATION_PORTRAIT)
                .introduceMyself();
    }

    public void onFullscreenClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withStyle(new FullscreenStyle())
                .introduceMyself();
    }

    public void onTextFromResourcesClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateResourceSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withSkipEnabled(R.string.skip)
                .introduceMyself();
    }

    public void onLongStringsClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateLongStringsSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .introduceMyself();
    }

    public void onBackPressClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withAllowBackPress(true)
                .introduceMyself();
    }

    public void onRequestPermissionClick(View view) {
        List<Slide> slides = generateSlides();

        slides.add(0, new Slide()
                .withTitle("Permission Request")
                .withDescription("You can request permissions with the listener")
                .withColorResource(R.color.purple)
        );

        new IntroductionBuilder(this).withSlides(slides).withOnSlideListener(new OnSlideListener() {
            @Override
            public void onSlideChanged(int from, int to) {
                if (from == 0 && to == 1) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{WRITE_EXTERNAL_STORAGE}, 12);
                    } else {
                        Toast.makeText(MainActivity.this, "Permission is already granted",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onSlideInit(int position, @Nullable TextView title, @NonNull ImageView image,
                                    @Nullable TextView description) {
                if (position == 2) {
                    Glide.with(image.getContext())
                            .load(R.drawable.image3)
                            .into(image);
                }
            }
        }).introduceMyself();
    }

    public void onAsynchronousClick(View view) {
        List<Slide> slides = new ArrayList<>();

        slides.add(0, new Slide()
                .withTitle("Asynchronous")
                .withDescription("The next image will be loaded asynchronously")
                .withColorResource(R.color.purple)
        );

        slides.add(1, new Slide()
                .withTitle("Asynchronous")
                .withDescription("This image was loaded asynchronously")
                .withColorResource(R.color.indigo)
        );

        new IntroductionBuilder(this)
                .withSlides(slides)
                .withOnSlideListener(new OnSlideListener() {
                    @Override
                    public void onSlideInit(int position, @Nullable TextView title, @NonNull ImageView image,
                                            @Nullable TextView description) {
                        if (position == 1) {
                            Glide.with(image.getContext())
                                    .load(R.drawable.image3)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(image);
                        }
                    }
                }).introduceMyself();
    }

    public void onManySlidesClick(View view) {
        List<Slide> slides = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            slides.addAll(generateSlides());
        }

        new IntroductionBuilder(this)
                .withSlides(slides)
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .introduceMyself();
    }

    public void onSkipClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withSkipEnabled("Skip")
                .introduceMyself();
    }

    public void onFullscreenImageClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(Collections.singletonList(new Slide()
                        .withColorResource(R.color.green)
                ))
                .withOnSlideListener(new OnSlideListener() {
                    @Override
                    public void onSlideInit(int position, @Nullable TextView title, @NonNull ImageView image,
                                            @Nullable TextView description) {
                        Glide.with(image.getContext())
                                .load(R.drawable.image3)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(image);
                    }
                })
                .introduceMyself();
    }

    public void onTypefaceClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(generateSlides())
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .withTitleTypeface(Typeface.MONOSPACE)
                .withDescriptionTypeface(Typeface.SERIF)
                .introduceMyself();
    }

    public void onTextSizeClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(Collections.singletonList(new Slide()
                        .withTitle("small text")
                        .withDescription("BIG TEXT")
                        .withTitleSize(12f)
                        .withDescriptionSize(60f)
                        .withColorResource(R.color.green)))
                .withOnSlideListener(DEFAULT_ON_SLIDE_LISTENER)
                .introduceMyself();
    }

    public void onCustomViewClick(View view) {
        new IntroductionBuilder(this)
                .withSlides(
                        new Slide()
                                .withCustomViewBuilder((inflater, parent) -> inflater
                                        .inflate(R.layout.layout_custom_1, parent, false))
                                .withColorResource(R.color.cyan),
                        new Slide()
                                .withCustomViewBuilder((inflater, parent) -> inflater
                                        .inflate(R.layout.layout_custom_2, parent, false))
                                .withColorResource(R.color.green)
                ).introduceMyself();
    }

    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide()
                .withTitle("Title")
                .withDescription("Description")
                .withColorResource(R.color.green)
                .withImage(R.drawable.image1)
        );

        result.add(new Slide()
                .withTitle("Gif")
                .withDescription("This is a Gif")
                .withColorResource(R.color.indigo)
        );

        result.add(new Slide()
                .withTitle("Option")
                .withOption(new Option("This is an option", true))
                .withColorResource(R.color.orange)
                .withImage(R.drawable.image2)
        );

        return result;
    }

    private List<Slide> generateResourceSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide()
                .withTitle(R.string.slide_title)
                .withDescription(R.string.slide_description)
                .withColorResource(R.color.green)
                .withImage(R.drawable.image1)
        );

        result.add(new Slide()
                .withTitle(R.string.slide_gif)
                .withDescription(R.string.slide_gif_description)
                .withColorResource(R.color.indigo)
        );

        result.add(new Slide()
                .withTitle(R.string.slide_option)
                .withOption(new Option(R.string.slide_option_description, true))
                .withColorResource(R.color.orange)
                .withImage(R.drawable.image2)
        );

        return result;
    }

    private List<Slide> generateLongStringsSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide()
                .withTitle("This is a veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeery " +
                        "long title")
                .withDescription("This is an even looooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                        "oooooooooooooooonger description")
                .withColorResource(R.color.green)
                .withImage(R.drawable.image1));

        result.add(new Slide()
                .withTitle("This is a veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeery " +
                        "long title")
                .withOption(new Option("This is a veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                        "eeeeeeeeeeeeeery long option"))
                .withColorResource(R.color.orange)
                .withImage(R.drawable.image2)
        );

        return result;
    }
}
