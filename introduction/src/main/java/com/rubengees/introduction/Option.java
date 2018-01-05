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

package com.rubengees.introduction;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.rubengees.introduction.exception.IntroductionConfigurationException;

/**
 * A bean which contains the data of an option.
 *
 * @author Ruben Gees
 */
public class Option implements Parcelable {

    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {

        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };

    private static final String EXCEPTION_MESSAGE_GET_POSITION = "Don't call the getPosition " +
            "method while constructing the IntroductionBuilder";
    private static final String EXCEPTION_MESSAGE_GET_TITLE = "Don't call the getTitle method " +
            "while constructing the IntroductionBuilder";

    private String title;
    private Integer titleResource;
    private boolean activated;
    private Integer position;

    /**
     * @param title The title of this option.
     */
    public Option(@NonNull String title) {
        this.title = title;
        this.activated = false;
    }

    /**
     * @param title     The title of this option.
     * @param activated makes this option activated or not. If true is passed,
     *                  the checkbox will be checked.
     */
    public Option(@NonNull String title, boolean activated) {
        this.title = title;
        this.activated = activated;
    }

    /**
     * @param titleResource The title as a resource
     */
    public Option(@StringRes int titleResource) {
        this.titleResource = titleResource;
        this.activated = false;
    }

    /**
     * @param titleResource The title as a resource
     * @param activated     makes this option activated or not. If true is passed,
     *                      the checkbox will be checked.
     */
    public Option(@StringRes int titleResource, boolean activated) {
        this.titleResource = titleResource;
        this.activated = activated;
    }

    @SuppressWarnings("WeakerAccess")
    protected Option(Parcel in) {
        this.title = in.readString();
        this.titleResource = (Integer) in.readValue(Integer.class.getClassLoader());
        this.activated = in.readByte() != 0;
        this.position = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public String getTitle() {
        if (title == null && titleResource != null) {
            throw new IntroductionConfigurationException(EXCEPTION_MESSAGE_GET_TITLE);
        }

        return title;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean isActivated) {
        this.activated = isActivated;
    }

    public int getPosition() {
        if (position == null) {
            throw new IntroductionConfigurationException(EXCEPTION_MESSAGE_GET_POSITION);
        }

        return position;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (activated != option.activated) return false;
        if (title != null ? !title.equals(option.title) : option.title != null) return false;
        if (titleResource != null ? !titleResource.equals(option.titleResource) : option.titleResource != null)
            return false;
        return !(position != null ? !position.equals(option.position) : option.position != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (titleResource != null ? titleResource.hashCode() : 0);
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    /**
     * Creates and finds all the needed data. Don't call this Method yourself.
     *
     * @param context  The context.
     * @param position The position of the slide, this option is assigned to.
     */
    void init(@NonNull Context context, @IntRange(from = 0) int position) {
        this.position = position;

        if (titleResource != null) {
            this.title = context.getString(titleResource);
            this.titleResource = null;
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
        dest.writeByte(activated ? (byte) 1 : (byte) 0);
        dest.writeValue(this.position);
    }
}
