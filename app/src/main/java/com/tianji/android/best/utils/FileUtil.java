package com.tianji.android.best.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static String encoding;
    /**
     * The default buffer size to use for
     * {@link #copyLarge(java.io.InputStream, java.io.OutputStream)}
     * and
     * {@link #copyLarge(java.io.Reader, java.io.Writer)}
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    public static String PATH = android.os.Environment.getExternalStorageDirectory() + "/best_cache";

    public static boolean deleteFile(String path) {
        File f = new File(path);
        return f.exists() && f.delete();
    }

    /**
     * Copy chars from a <code>Reader to a Writer.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader.
     * <p/>
     * Large streams (over 2GB) will return a chars copied value of
     * <code>-1 after the copy has completed since the correct
     * number of chars cannot be returned as an int. For large streams
     * use the <code>copyLarge(Reader, Writer) method.
     *
     * @param input  the <code>Reader to read from
     * @param output the <code>Writer to write to
     * @return the number of characters copied, or -1 if > Integer.MAX_VALUE
     * @throws NullPointerException if the input or output is null
     * @throws java.io.IOException          if an I/O error occurs
     * @since Commons IO 1.1
     */
    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * Copy bytes from an <code>InputStream to an
     * <code>OutputStream.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream.
     * <p/>
     * Large streams (over 2GB) will return a bytes copied value of
     * <code>-1 after the copy has completed since the correct
     * number of bytes cannot be returned as an int. For large streams
     * use the <code>copyLarge(InputStream, OutputStream) method.
     *
     * @param input  the <code>InputStream to read from
     * @param output the <code>OutputStream to write to
     * @return the number of bytes copied, or -1 if > Integer.MAX_VALUE
     * @throws NullPointerException if the input or output is null
     * @throws java.io.IOException          if an I/O error occurs
     * @since Commons IO 1.1
     */
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * Copy bytes from an <code>InputStream to chars on a
     * <code>Writer using the default character encoding of the platform.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream.
     * <p/>
     * This method uses {@link java.io.InputStreamReader}.
     *
     * @param input  the <code>InputStream to read from
     * @param output the <code>Writer to write to
     * @throws NullPointerException if the input or output is null
     * @throws java.io.IOException          if an I/O error occurs
     * @since Commons IO 1.1
     */
    public static void copy(InputStream input, Writer output)
            throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }


    /**
     * Copy bytes from a large (over 2GB) <code>InputStream to an
     * <code>OutputStream.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream.
     *
     * @param input  the <code>InputStream to read from
     * @param output the <code>OutputStream to write to
     * @return the number of bytes copied
     * @throws NullPointerException if the input or output is null
     * @throws java.io.IOException          if an I/O error occurs
     * @since Commons IO 1.3
     */
    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    /**
     * Copy chars from a large (over 2GB) <code>Reader to a Writer.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader.
     *
     * @param input  the <code>Reader to read from
     * @param output the <code>Writer to write to
     * @return the number of characters copied
     * @throws NullPointerException if the input or output is null
     * @throws java.io.IOException          if an I/O error occurs
     * @since Commons IO 1.3
     */
    public static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    /**
     * Get the contents of an <code>InputStream as a String
     * using the default character encoding of the platform.
     * <p/>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream.
     *
     * @param input the <code>InputStream to read from
     * @return the requested String
     * @throws NullPointerException if the input is null
     * @throws java.io.IOException          if an I/O error occurs
     */
    public static String toString(InputStream input) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw);
        return sw.toString();
    }

    public static int deleteAllFiles(File file) {
        if (file == null)
            return 0;

        if (file.isDirectory()) {
            int result = 0;
            File[] subs = file.listFiles();
            for (File sub : subs) {
                result += deleteAllFiles(sub);
            }

            result += file.delete() ? 1 : 0;
            return result;
        } else {
            Log.i(TAG, file.getName() + " deleted");
            return file.delete() ? 1 : 0;
        }
    }

    public static long calculateFileSize(File file) {
        if (file == null)
            return 0;
        long result = 0;
        if (file.isDirectory()) {
            for (File sub : file.listFiles())
                result += calculateFileSize(sub);
        } else {
            result = file.length();
        }
        return result;
    }

    /**
     * 转换文件大小 *
     */
    public static String formatFileSize(long fileSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = fileSize + " B";
        } else if (fileSize < 1048576) {
            fileSizeString = decimalFormat.format((double) fileSize / 1024) + " KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = decimalFormat.format((double) fileSize / 1048576) + " MB";
        } else {
            fileSizeString = decimalFormat.format((double) fileSize / 1073741824) + " GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小 *
     */
    public static String formatCacheFileSize(long fileSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String fileSizeString = "";
        double temp;
        if (fileSize < 1000) {
            fileSizeString = fileSize + " B";
        } else if (fileSize < 1000000) {
            temp = Math.ceil(fileSize / 10d);
            fileSizeString = decimalFormat.format(temp / 100) + " KB";
        } else if (fileSize < 1000000000) {
            temp = Math.ceil(fileSize / 10000d);
            fileSizeString = decimalFormat.format(temp / 100) + " MB";
        } else {
            temp = Math.ceil(fileSize / 10000000d);
            fileSizeString = decimalFormat.format(temp / 100) + " GB";
        }
        return fileSizeString;
    }

    public static boolean copyFile(String src, String target) {
        File s = new File(src);
        File t = new File(target);
        if (!s.exists())
            return false;

        if (t.exists())
            t.delete();

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(s);
            output = new FileOutputStream(t);
            byte[] b = new byte[1024];
            while (input.read(b) != -1) {
                output.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (input != null)
                    input.close();
                if (output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean isSdCardOK() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getFilePathByUri(Activity activity, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = activity.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        return actualimagecursor.getString(actual_image_column_index);
    }

    public static void saveData(String cacheKey, String data) {
        if (TextUtils.isEmpty(data)) {
            return;
        }
        FileOutputStream outputStream;
        ByteArrayInputStream inputStream;
        Log.d(TAG, "save:" + cacheKey + "\n" + data);
        try {
            // outputStream = new FileOutputStream(new
            // File(HttpUtil.PATH_AVATAR,
            // Md5Util.md5(cacheKey)));
            outputStream = new FileOutputStream(new File(PATH, cacheKey));
            byte[] bytes = data.getBytes();
            inputStream = new ByteArrayInputStream(bytes);

            copy(inputStream, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromDisk(String key) {
        String result = "";
        InputStream inputStream;
        try {
            // inputStream = new FileInputStream(new File(HttpUtil.PATH_AVATAR,
            // Md5Util.md5(key)));
            inputStream = new FileInputStream(new File(PATH, key));
            result = toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
