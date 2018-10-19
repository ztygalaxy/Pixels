/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.markers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.dsandler.apps.markers.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class WaterMark {

    static AlertDialog dialog;
    public static boolean IsDrawing = false;
	static void show(final MarkersActivity activity) {
        IsDrawing = false;
        if(dialog==null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(null);
            builder.setCancelable(true);

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.watermark_box, null);

            Spinner dropdown = (Spinner)layout.findViewById(R.id.spinner1);
            String[] items = new String[]{"80", "160", "240", "320", "400"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);

            Spinner dropdownx = (Spinner)layout.findViewById(R.id.spinnerX);
            String[] itemsx = new String[]{"50", "80", "160", "200", "300", "400", "600", "700", "800"};
            ArrayAdapter<String> adapterx = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, itemsx);
            dropdownx.setAdapter(adapterx);

            Spinner dropdowny = (Spinner)layout.findViewById(R.id.spinnerY);
            String[] itemsy = new String[]{"50", "160", "300", "500", "800", "1000", "1200", "1400", "1600"};
            ArrayAdapter<String> adaptery = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, itemsy);
            dropdowny.setAdapter(adaptery);

            //activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            EditText watertext = (EditText) dialog.findViewById(R.id.watertext);
//            InputMethodManager im = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.showSoftInput(watertext, 0);

            builder.setView(layout);
            builder.setNeutralButton("关闭", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.show();
        }
        else {
            dialog.show();
        }
	}
    static WaterMarkSetting GetWaterMark(final MarkersActivity activity)
    {
        WaterMarkSetting wms = new WaterMarkSetting();
        EditText watertext = (EditText) dialog.findViewById(R.id.watertext);
        wms.WaterMarkText = watertext.getText().toString().trim();
        Spinner dropdown = (Spinner)dialog.findViewById(R.id.spinner1);
        wms.WaterMarkSize =  Integer.parseInt(dropdown.getSelectedItem().toString());
        Spinner dropdownx = (Spinner)dialog.findViewById(R.id.spinnerX);
        wms.WaterMarkX = Integer.parseInt(dropdownx.getSelectedItem().toString());
        Spinner dropdowny = (Spinner)dialog.findViewById(R.id.spinnerY);
        wms.WaterMarkY= Integer.parseInt(dropdowny.getSelectedItem().toString());
        return wms;
    }
    static void HideWaterMark()
    {
        IsDrawing = true;
        dialog.dismiss();
    }
}
