package com.rubengees.introduction;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.rubengees.introduction.exception.IntroductionConfigurationException;
import com.rubengees.introduction.interfaces.CustomViewBuilder;

/**
 * A bean which contains the data of a slide.
 *
 * @author Ruben Gees
 */
public class Slide implements Parcelable {

    public static final Creator<Slide> CREATOR = new Creator<Slide>() {
        @Override
        public Slide createFromParcel(Parcel source) {
            return new Slide(source);
        }

        @Override
        public Slide[] newArray(int size) {
            return new Slide[size];
        }
    };

    private static final String EXCEPTION_COLOR_MESSAGE = "You must add a color to each slide";

    private int position;
    private String title;
    private Integer titleResource;
    private Float titleSize;
    private String description;
    private Integer descriptionResource;
    private Float descriptionSize;
    private Integer imageResource;
    private Integer color;
    private Integer colorResource;
    private Option option;
    private CustomViewBuilder customViewBuilder;

    public Slide() {
    }

    protected Slide(Parcel in) {
        this.position = in.readInt();
        this.title = in.readString();
        this.titleResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.titleSize = (Float) in.readValue(Float.class.getClassLoader());
        this.description = in.readString();
        this.descriptionResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descriptionSize = (Float) in.readValue(Float.class.getClassLoader());
        this.imageResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.color = (Integer) in.readValue(Integer.class.getClassLoader());
        this.colorResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.option = in.readParcelable(Option.class.getClassLoader());
        this.customViewBuilder = (CustomViewBuilder) in.readSerializable();
    }

    /**
     * Sets the title of this Slide.
     * If a title resource was given before, it will be overridden.
     *
     * @param title The title.
     * @return The current instance.
     */
    @NonNull
    public Slide withTitle(@Nullable String title) {
        this.title = title;
        this.titleResource = null;
        this.customViewBuilder = null;

        return this;
    }

    /**
     * Sets the title resource for this Slide.
     * If a title was given before, it will be overridden.
     *
     * @param titleResource The title resource.
     * @return The current instance.
     */
    @NonNull
    public Slide withTitle(@StringRes int titleResource) {
        this.titleResource = titleResource;
        this.title = null;
        this.customViewBuilder = null;

        return this;
    }

    /**
     * Sets the description of this slide.
     * If a description resource was given before, it will be overridden.
     *
     * @param description The description.
     * @return The current instance.
     */
    @NonNull
    public Slide withDescription(@Nullable String description) {
        this.description = description;
        this.descriptionResource = null;
        this.option = null;
        this.customViewBuilder = null;

        return this;
    }

    /**
     * Sets the description resource of this slide.
     * If a description was given before, it will be overridden.
     *
     * @param descriptionResource The description resource.
     * @return The current instance.
     */
    @NonNull
    public Slide withDescription(@StringRes int descriptionResource) {
        this.descriptionResource = descriptionResource;
        this.description = null;
        this.option = null;
        this.customViewBuilder = null;

        return this;
    }

    @NonNull
    public Slide withTitleSize(@Nullable Float size) {
        this.titleSize = size;

        return this;
    }

    @NonNull
    public Slide withDescriptionSize(@Nullable Float size) {
        this.descriptionSize = size;

        return this;
    }

    /**
     * Sets the image resource of this slide.
     *
     * @param imageResource The image resource.
     * @return The current instance.
     */
    @NonNull
    public Slide withImage(@DrawableRes int imageResource) {
        this.imageResource = imageResource;
        this.customViewBuilder = null;

        return this;
    }

    /**
     * Sets the background color of this slide.
     * If a color resource was given before, it will be overridden.
     *
     * @param color The color.
     * @return The current instance.
     */
    @SuppressWarnings("unused")
    @NonNull
    public Slide withColor(@ColorInt int color) {
        this.color = color;
        this.colorResource = null;

        return this;
    }

    /**
     * Sets the background color resource of this slide.
     * If a color was given before, it will be overridden.
     *
     * @param colorResource The color resource.
     * @return The current instance.
     */
    @NonNull
    public Slide withColorResource(@ColorRes int colorResource) {
        this.colorResource = colorResource;
        this.color = null;

        return this;
    }

    /**
     * Sets the option of this slide.
     * Overrides a description or description resource.
     *
     * @param option The option.
     * @return The current instance.
     */
    @NonNull
    public Slide withOption(@Nullable Option option) {
        this.option = option;
        this.description = null;
        this.descriptionResource = null;
        this.customViewBuilder = null;

        return this;
    }

    public Slide withCustomViewBuilder(@Nullable CustomViewBuilder customViewBuilder) {
        this.customViewBuilder = customViewBuilder;
        this.title = null;
        this.titleResource = null;
        this.imageResource = null;
        this.option = null;
        this.description = null;
        this.descriptionResource = null;

        return this;
    }

    public int getPosition() {
        return position;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public Integer getImageResource() {
        return imageResource;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public Integer getColor() {
        return color;
    }

    @Nullable
    public Option getOption() {
        return option;
    }

    @Nullable
    public Float getTitleSize() {
        return titleSize;
    }

    @Nullable
    public Float getDescriptionSize() {
        return descriptionSize;
    }

    @Nullable
    public CustomViewBuilder getCustomViewBuilder() {
        return customViewBuilder;
    }

    /**
     * Creates and retrieves all the needed data. Don't call this Method yourself.
     *
     * @param context  The Context.
     * @param position The position of this slide.
     */
    void init(@NonNull Context context, @IntRange(from = 0) int position) {
        this.position = position;

        Resources resources = context.getResources();

        if (titleResource != null) {
            title = resources.getString(titleResource);
            titleResource = null;
        }

        if (descriptionResource != null) {
            description = resources.getString(descriptionResource);
            descriptionResource = null;
        }

        if (colorResource != null) {
            color = ContextCompat.getColor(context, colorResource);
            colorResource = null;
        }

        if (option != null) {
            option.init(context, position);
        }

        if (color == null) {
            throw new IntroductionConfigurationException(EXCEPTION_COLOR_MESSAGE);
        }
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slide slide = (Slide) o;

        if (position != slide.position) return false;
        if (title != null ? !title.equals(slide.title) : slide.title != null) return false;
        if (titleResource != null ? !titleResource.equals(slide.titleResource) : slide.titleResource != null)
            return false;
        if (description != null ? !description.equals(slide.description) : slide.description != null)
            return false;
        if (descriptionResource != null ? !descriptionResource.equals(slide.descriptionResource) : slide.descriptionResource != null)
            return false;
        if (imageResource != null ? !imageResource.equals(slide.imageResource) : slide.imageResource != null)
            return false;
        if (color != null ? !color.equals(slide.color) : slide.color != null) return false;
        if (colorResource != null ? !colorResource.equals(slide.colorResource) : slide.colorResource != null)
            return false;
        if (option != null ? !option.equals(slide.option) : slide.option != null) return false;
        return customViewBuilder != null ? customViewBuilder.equals(slide.customViewBuilder) : slide.customViewBuilder == null;
    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (titleResource != null ? titleResource.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (descriptionResource != null ? descriptionResource.hashCode() : 0);
        result = 31 * result + (imageResource != null ? imageResource.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (colorResource != null ? colorResource.hashCode() : 0);
        result = 31 * result + (option != null ? option.hashCode() : 0);
        result = 31 * result + (customViewBuilder != null ? customViewBuilder.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
        dest.writeString(this.title);
        dest.writeValue(this.titleResource);
        dest.writeValue(this.titleSize);
        dest.writeString(this.description);
        dest.writeValue(this.descriptionResource);
        dest.writeValue(this.descriptionSize);
        dest.writeValue(this.imageResource);
        dest.writeValue(this.color);
        dest.writeValue(this.colorResource);
        dest.writeParcelable(this.option, flags);
        dest.writeSerializable(this.customViewBuilder);
    }
}
