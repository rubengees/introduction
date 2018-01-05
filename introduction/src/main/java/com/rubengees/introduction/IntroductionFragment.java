package com.rubengees.introduction;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.view.View.GONE;

/**
 * A Fragment which displays a single Slide.
 *
 * @author Ruben Gees
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class IntroductionFragment extends Fragment {

    private static final String BUNDLE_SLIDE = "introduction_slide";

    private Slide slide;
    private View root;

    public static IntroductionFragment newInstance(@NonNull Slide slide) {
        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();

        args.putParcelable(BUNDLE_SLIDE, slide);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.slide = getSafeArguments().getParcelable(BUNDLE_SLIDE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = initViews(inflater, container);

        getIntroductionActivity().getStyle().applyStyleOnFragmentView(this, root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.requestApplyInsets(root);
    }

    @NonNull
    private View initViews(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.introduction_fragment, container, false);
        ViewGroup contentContainer = root.findViewById(R.id.introduction_fragment_content_container);

        if (slide.getCustomViewBuilder() == null) {
            contentContainer.addView(initDefaultViews(inflater, container));
        } else {
            contentContainer.addView(slide.getCustomViewBuilder().buildView(inflater, container));
        }

        root.setBackgroundColor(slide.getColor());
        root.setTag(slide.getPosition());

        return root;
    }

    @NonNull
    private View initDefaultViews(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.introduction_fragment_content, container, false);
        ViewGroup descriptionContainer = root.findViewById(R.id.introduction_fragment_content_description_container);

        TextView title = root.findViewById(R.id.introduction_fragment_content_title);
        ImageView image = root.findViewById(R.id.introduction_fragment_content_image);
        TextView description = null;

        if (slide.getTitle() != null) {
            title.setText(slide.getTitle());
            title.setMaxLines(getLineCountForTitle());
            title.setTypeface(IntroductionConfiguration.getInstance().getTitleTypeface());

            if (slide.getTitleSize() != null) {
                title.setTextSize(COMPLEX_UNIT_DIP, slide.getTitleSize());
            }
        } else {
            title.setVisibility(GONE);
            title = null;
        }

        if (slide.getDescription() == null && slide.getOption() != null) {
            ColorStateList buttonColors = ContextCompat.getColorStateList(getSafeContext(), android.R.color.white);
            CheckBox option = (CheckBox) inflater.inflate(R.layout.introduction_fragment_option,
                    descriptionContainer, false);

            option.setText(slide.getOption().getTitle());
            option.setChecked(slide.getOption().isActivated());
            option.setOnCheckedChangeListener((buttonView, isChecked) -> slide.getOption().setActivated(isChecked));

            CompoundButtonCompat.setButtonTintList(option, buttonColors);

            descriptionContainer.addView(option);
            description = option;
        } else if (slide.getDescription() != null) {
            description = (TextView) inflater.inflate(R.layout.introduction_fragment_description,
                    descriptionContainer, false);

            description.setText(slide.getDescription());
            descriptionContainer.addView(description);
        } else {
            descriptionContainer.setVisibility(GONE);
        }

        if (description != null) {
            description.setMaxLines(getLineCountForDescription());
            description.setTypeface(IntroductionConfiguration.getInstance().getDescriptionTypeface());

            if (slide.getDescriptionSize() != null) {
                description.setTextSize(COMPLEX_UNIT_DIP, slide.getDescriptionSize());
            }
        }

        if (slide.getImageResource() != null) {
            image.setImageResource(slide.getImageResource());
        }

        IntroductionConfiguration.getInstance().callOnSlideInit(slide.getPosition(), title, image, description);

        return root;
    }

    private int getLineCountForTitle() {
        return getSafeActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ? 2 : 3;
    }

    private int getLineCountForDescription() {
        return getSafeActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ? 2 : 4;
    }

    public IntroductionActivity getIntroductionActivity() {
        return (IntroductionActivity) getActivity();
    }

    @NonNull
    private Bundle getSafeArguments() {
        Bundle result = getArguments();

        if (result == null) {
            throw new AssertionError("context is null");
        }

        return result;
    }

    @NonNull
    private Context getSafeContext() {
        Context result = getContext();

        if (result == null) {
            throw new AssertionError("context is null");
        }

        return result;
    }

    @NonNull
    private Context getSafeActivity() {
        Activity result = getActivity();

        if (result == null) {
            throw new AssertionError("activity is null");
        }

        return result;
    }
}
