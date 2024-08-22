package com.example.barcodecamera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    Button btn_scan;
    TextView txt_result, txt_result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_result = findViewById(R.id.Scan_txt);
        txt_result2 = findViewById(R.id.Scan_txt2);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v -> {
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    // launching camera activity
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result != null) {
            // handle result
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            //set title
           /* builder.setTitle("Scan Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            }).show();*/
            txt_result.setText(result.getContents());
            //if contents are 6161112520047 then it will show the twiga 200pgs
            if (result.getContents().equals("6161112520047")) {
                txt_result2.setText("Twiga 200pgs");
            }
            //if contents are 6161112520054 then it will show the twiga 100pgs
            else if (result.getContents().equals("6161110631752")) {
                txt_result2.setText("Twiga 100pgs");
            }
            else{txt_result2.setText("item Not in the record");}
            // clear list after each scan


        }
    });
}