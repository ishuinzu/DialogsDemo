package com.ishuinzu.dialogsdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private Button btnShowDialog;
    private String[] items = {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4"
    };
    private boolean[] checkedItems = {
            true,
            false,
            false,
            false
    };
    private int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowDialog = findViewById(R.id.btnShowDialog);

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show Simple Dialog
                //showSimpleDialog();
                //showAlertDialog();
                //showSingleChoiceDialogBox();
                //showMultipleCheckedItemsDialog();
                showFullScreenDialog();
            }
        });
    }

    private void showFullScreenDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FullScreenDialogFragment screenDialogFragment = new FullScreenDialogFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(android.R.id.content, screenDialogFragment).addToBackStack(null).commit();
    }

    private void showMultipleCheckedItemsDialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        dialogBuilder.setTitle("Title");
        dialogBuilder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                dialog.dismiss();
            }
        });
        //Show Dialog
        dialogBuilder.show();
    }

    private void showSingleChoiceDialogBox() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        dialogBuilder.setTitle("Title");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setSingleChoiceItems(items, selectedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedItem = which;
                Toast.makeText(MainActivity.this, "Selected Item : " + items[selectedItem], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //Create Alert Dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showAlertDialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        dialogBuilder.setTitle("Title");
        dialogBuilder.setMessage("Supporting content.");
        dialogBuilder.setCancelable(false);
        //Set Neutral Button Click Listener
        dialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Dismiss", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //Set Negative Button Click Listener
        dialogBuilder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Decline", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //Set Positive Button Click Listener
        dialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Accept", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //Create Alert Dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showSimpleDialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        dialogBuilder.setTitle("Title");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //On Item Click Listener
                Toast.makeText(MainActivity.this, "Clicked Item : " + items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //Creating Alert Dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}