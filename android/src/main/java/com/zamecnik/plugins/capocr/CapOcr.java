package com.zamecnik.plugins.capocr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.getcapacitor.Plugin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;


public class CapOcr extends Plugin {
    private String resultText;
    private String resultJsonString;


    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public String detectText(String imageBase64) {
        final AtomicReference<String> resultTextRef = new AtomicReference<>();

        InputImage image = InputImage.fromBitmap(convertBase64ToBitmap(imageBase64), 0);
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        Task<Text> result = recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    String resultText = visionText.getText();
                    Log.i("resultText: ", resultText);
                    resultTextRef.set(resultText);
                    synchronized (resultTextRef) {
                        resultTextRef.notify();
                    }
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                    synchronized (resultTextRef) {
                        resultTextRef.notify();
                    }
                });

        synchronized (resultTextRef) {
            try {
                resultTextRef.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultTextRef.get();
    }



    public String detectData(String imageBase64) throws Exception {
        throw new Exception("Not yet implemented!");

        /*
        InputImage image = InputImage.fromBitmap(convertBase64ToBitmap(imageBase64), 0);
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        Task<Text> result = recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    JSONObject resultJson = new JSONObject();
                    JSONArray blocksJsonArray = new JSONArray();

                    try {
                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                            JSONObject blockJson = new JSONObject();
                            JSONArray linesJsonArray = new JSONArray();

                            for (Text.Line line : block.getLines()) {
                                JSONObject lineJson = new JSONObject();
                                lineJson.put("text", line.getText());
                                linesJsonArray.put(lineJson);
                            }

                            blockJson.put("text", block.getText());
                            blockJson.put("lines", linesJsonArray);
                            blocksJsonArray.put(blockJson);
                        }

                        resultJson.put("text", visionText.getText());
                        resultJson.put("blocks", blocksJsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.i("resultJson", resultJson.toString());
                    this.resultJsonString = resultJson.toString();
                })
                .addOnFailureListener(Throwable::printStackTrace);

        try {
            Tasks.await(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return this.resultJsonString;

         */
    }


    public Bitmap convertBase64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
