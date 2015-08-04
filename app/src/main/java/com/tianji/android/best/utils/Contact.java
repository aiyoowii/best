package com.tianji.android.best.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by cegrano on 2015/3/4.
 */
public class Contact implements Comparable<Contact> {
    String id;
    String name;
    String email;
    String phone;
    long photo;

    private String sortId;

    public Contact() {
    }

    public Contact(String id, String name, String email, String phone, long photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public long getPhoto() {
        return photo;
    }

    public String getSortId() {
        if (TextUtils.isEmpty(sortId))
            sortId = CharacterParser.getInstance().getSpelling(this.getName()).toUpperCase().substring(0, 1);
        if (!sortId.matches("[A-Z]"))
            sortId = "#";
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    @Override
    public int compareTo(@NonNull Contact another) {
        if (this == another) return -1;

        String mPinyin = CharacterParser.getInstance().getSpelling(this.getName()).toUpperCase();
        String mId = mPinyin.substring(0, 1);
        String anotherPinyin = CharacterParser.getInstance().getSpelling(another.getName()).toUpperCase();
        String anotherId = anotherPinyin.substring(0, 1);
        if (!mId.matches("[A-Z]"))
            mId = "#";
        if (!anotherId.matches("[A-Z]"))
            anotherId = "#";
        this.setSortId(mId);
        if ("★".equals(mId)
                || "#".equals(mId)) {
            return -1;
        } else if ("#".equals(anotherId)
                || "★".equals(anotherId)) {
            return 1;
        } else {
            return mPinyin.compareTo(anotherPinyin);
        }
    }
}
