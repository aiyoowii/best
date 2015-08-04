package com.tianji.android.best.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.tianji.android.best.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cegrano on 2015/1/20.
 * 联系人管理类
 */
public class ContactManager {

    /**
     * 联系人的ID*
     */
    private static final int PHONES_CONTACT_ID_INDEX = 0;

    /**
     * 联系人显示名称*
     */
    private static final int PHONES_DISPLAY_NAME_INDEX = 1;

    /**
     * 电话号码*
     */
    private static final int PHONES_NUMBER_INDEX = 2;

    /**
     * 头像ID*
     */
    private static final int PHONES_PHOTO_ID_INDEX = 3;

    /**
     * 是否有电话号码*
     */
//    private static final int PHONES_HAS_NUMBER_INDEX = 3;

    private static ArrayList<Contact> contactsCache;

    public static ArrayList<Contact> getContacts(Activity context) {
        if (contactsCache != null)
            return contactsCache;
        contactsCache = getLocalContacts(context);
        return contactsCache;
    }

    /**
     * 设置通讯录列表
     *
     * @param contactsCache 应该是从服务器拿到的列表结果
     */
    public static void setContactsCache(ArrayList<Contact> contactsCache) {
        ContactManager.contactsCache = contactsCache;
    }

    /**
     * @param context context
     * @return 本地通讯录列表
     */
    public static ArrayList<Contact> getLocalContacts(Activity context) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        if (context == null)
            return contacts;
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
        };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '1'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
//        CursorLoader cursorLoader = new CursorLoader(context, uri, projection, selection, selectionArgs, sortOrder);
//        Cursor cursor = cursorLoader.loadInBackground();
        Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(PHONES_CONTACT_ID_INDEX);
                Cursor cur1 = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                if (cur1.moveToNext()) {
                    //to get the contact names
                    String name = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    Log.e("Name :", name);
                    String email = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                    Log.e("Email", email);
                } else
                    contacts.add(new Contact(id, cursor.getString(PHONES_DISPLAY_NAME_INDEX), ""
                            , cursor.getString(PHONES_NUMBER_INDEX), cursor.getLong(PHONES_PHOTO_ID_INDEX)));

                cur1.close();
            }
            cursor.close();
        }
        Collections.sort(contacts);
        return contacts;
    }

    public static String getLocalContactsEmail(Activity activity) {
        StringBuilder names = new StringBuilder();
        ContentResolver cr = activity.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cur1 = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                while (cur1.moveToNext()) {
                    //to get the contact names
                    String name = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    Log.e("Name :", name);
                    String email = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                    Log.e("Email", email);
                    if (email != null) {
                        names.append(email).append(",");
                    }
                }
                cur1.close();
            }
        }
        cur.close();
        return names.toString();
    }

    /**
     * @param context   context
     * @param contactId 本地通讯录id
     * @param photoId   本地通讯录照片id
     * @return 本地通讯录照片
     */
    public static Bitmap getContactPhoto(Context context, long contactId, long photoId) {
        if (photoId > 0) {
            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri);
            return BitmapFactory.decodeStream(input);
        } else {
            return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
    }

    public static Uri getContactPhoto(String contactId, long photoId) {
        if (photoId > 0) {
            return ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));
        } else {
            return new Uri.Builder().build();
        }
    }

    public static Bitmap getContactPhoto(Context context, String contactId, long photoId) {
        return getContactPhoto(context, Long.parseLong(contactId), (photoId));
    }

}
