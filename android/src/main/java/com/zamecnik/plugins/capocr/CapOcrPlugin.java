package com.zamecnik.plugins.capocr;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapOcr")
public class CapOcrPlugin extends Plugin {

    private CapOcr implementation = new CapOcr();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void detectText(PluginCall call) {
        String imageBase64 = call.getString("imageBase64");

        JSObject ret = new JSObject();
        ret.put("value", implementation.detectText(imageBase64));
        call.resolve(ret);
    }
}
