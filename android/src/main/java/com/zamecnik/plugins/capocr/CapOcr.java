package com.zamecnik.plugins.capocr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;


public class CapOcr {


    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public String detectText(String imageBase64) {
        // Code for text detection using imageBase64 goes here
        Log.i("Text Detection", imageBase64);
        return imageBase64;
    }
}
