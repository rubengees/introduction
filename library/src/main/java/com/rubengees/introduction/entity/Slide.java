/*
 *   Copyright 2015 Ruben Gees
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.rubengees.introduction.entity;

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

/**
 * A bean which contains the data of a slide.
 *
 * @author Ruben Gees
 */
public class Slide implements Parcelable {

    public static final Parcelable.Creator<Slide> CREATOR = new Parcelable.Creator<Slide>() {
        public Slide createFromParcel(Parcel source) {
            return new Slide(source);
        }

        public Slide[] newArray(int size) {
            return new Slide[size];
        }
    };
    private String title;
    private Integer titleResource;
    private String description;
    private Integer descriptionResource;
    private Integer imageResource;
    private Integer color;
    private Integer colorResource;
    private Option option;

    public Slide() {

    }

    protected Slide(Parcel in) {
        this.title = in.readString();
        this.titleResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readString();
        this.descriptionResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imageResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.color = (Integer) in.readValue(Integer.class.getClassLoader());
        this.colorResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.option = in.readParcelable(Option.class.getClassLoader());
    }

    /**
     * Sets the title of this Slide.
     * If a title resource was given before, it will be overridden.
     *
     * @param title The title.
     * @return The current instance.
     */
    public Slide withTitle(@Nullable String title) {
        this.title = title;
        this.titleResource = null;

        return this;
    }

    /**
     * Sets the title resource for this Slide.
     * If a title was given before, it will be overridden.
     *
     * @param titleResource The title resource.
     * @return The current instance.
     */
    public Slide withTitleResource(@StringRes int titleResource) {
        this.titleResource = titleResource;
        this.title = null;

        return this;
    }

    /**
     * Sets the description of this slide.
     * If a description resource was given before, it will be overridden.
     *
     * @param description The description.
     * @return The current instance.
     */
    public Slide withDescription(@Nullable String description) {
        this.description = description;
        this.descriptionResource = null;
        this.option = null;

        return this;
    }

    /**
     * Sets the description resource of this slide.
     * If a description was given before, it will be overridden.
     *
     * @param descriptionResource The description resource.
     * @return The current instance.
     */
    public Slide withDescriptionResource(@StringRes int descriptionResource) {
        this.descriptionResource = descriptionResource;
        this.description = null;
        this.option = null;

        return this;
    }

    /**
     * Sets the image resource of this slide.
     *
     * @param imageResource The image resource.
     * @return The current instance.
     */
    public Slide withImageResource(@Nullable @DrawableRes Integer imageResource) {
        this.imageResource = imageResource;

        return this;
    }

    /**
     * Sets the background color of this slide.
     * If a color resource was given before, it will be overridden.
     *
     * @param color The color.
     * @return The current instance.
     */
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
    public Slide withOption(@NonNull Option option) {
        this.option = option;
        this.description = null;
        this.descriptionResource = null;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }

    public Integer getColor() {
        return color;
    }

    public Option getOption() {
        return option;
    }

    /**
     * Creates and retrieves all the needed data. Don't call this Method yourself.
     *
     * @param context  The Context.
     * @param position The position of this slide.
     */
    public void init(@NonNull Context context, @IntRange(from = 0) int position) {
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeValue(this.titleResource);
        dest.writeString(this.description);
        dest.writeValue(this.descriptionResource);
        dest.writeValue(this.imageResource);
        dest.writeValue(this.color);
        dest.writeValue(this.colorResource);
        dest.writeParcelable(this.option, 0);
    }
}
