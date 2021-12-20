package com.marah.moblelibrarys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddBooksActivity extends AppCompatActivity {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    private EditText addBookNameET , bookAuthorNameET , bookCreatedYearET , bookPageCountET;
private ImageView addBookImage;
private Button createBookBTN;
//ضايل الحفظ في قاعدة البيانات
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        addBookNameET = findViewById(R.id.addBookNameET);
        bookAuthorNameET = findViewById(R.id.bookAuthorNameET);
        bookCreatedYearET = findViewById(R.id.bookCreatedYearET);
        bookPageCountET = findViewById(R.id.bookPageCountET);
        addBookImage = findViewById(R.id.addBookImage);
        createBookBTN = findViewById(R.id.createBookBTN);
        String bookName = addBookNameET.getText().toString();
        String authorName = bookAuthorNameET.getText().toString();
        String yearOfCreate = bookCreatedYearET.getText().toString();
        String countOfPages = bookPageCountET.getText().toString();
//        int createdYear = Integer.parseInt(yearOfCreate);
//        int pageCount = Integer.parseInt(countOfPages);
        createBookBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookName.length() != 0 && authorName.length() != 0 && yearOfCreate.length() != 0 && countOfPages.length() != 0){
                    Toast.makeText(getApplicationContext(), bookName+" is already added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sorry, You must fill in all fields, verify that your data is correct , plz", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions(AddBooksActivity.this)){
                    chooseImage(AddBooksActivity.this);
                }
            }
        });

    }
    private void chooseImage(Context context){
        final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit" }; // create a menuOption Array
        // create a dialog for showing the optionsMenu
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set the items in builder
        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(optionsMenu[i].equals("Take Photo")){
                    // Open the camera and get the photo
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }
                else if(optionsMenu[i].equals("Choose from Gallery")){
                    // choose from  external storage
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
                else if (optionsMenu[i].equals("Exit")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
    // function to check permission
    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    // Handled permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(AddBooksActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();
                } else if (ContextCompat.checkSelfPermission(AddBooksActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "FlagUp Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    chooseImage(AddBooksActivity.this);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        addBookImage.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                addBookImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }
//    private void selectImage() {
//        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
//        AlertDialog.Builder builder = new AlertDialog.Builder(AddBooksActivity.this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Take Photo"))
//                {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                    startActivityForResult(intent, 1);
//                }
//                else if (options[item].equals("Choose from Gallery"))
//                {
//                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, 2);
//                }
//                else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    public static boolean checkAndRequestPermissions(final Activity context) {
//        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int cameraPermission = ContextCompat.checkSelfPermission(context,
//                Manifest.permission.CAMERA);
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
//        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded
//                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(context, listPermissionsNeeded
//                            .toArray(new String[listPermissionsNeeded.size()]),
//                    REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }
//        return true;
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case REQUEST_ID_MULTIPLE_PERMISSIONS:
//                if (ContextCompat.checkSelfPermission(AddBooksActivity.this,
//                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(),
//                            "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (ContextCompat.checkSelfPermission(AddBooksActivity.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(),
//                            "FlagUp Requires Access to Your Storage.",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    chooseImage(AddBooksActivity.this);
//                }
//                break;
//        }
//    }
//    // function to let's the user to choose image from camera or gallery
//    private void chooseImage(Context context){
//        final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit" }; // create a menuOption Array
//        // create a dialog for showing the optionsMenu
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        // set the items in builder
//        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if(optionsMenu[i].equals("Take Photo")){
//                    // Open the camera and get the photo
//                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(takePicture, 0);
//                }
//                else if(optionsMenu[i].equals("Choose from Gallery")){
//                    // choose from  external storage
//                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(pickPhoto , 1);
//                }
//                else if (optionsMenu[i].equals("Exit")) {
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_CANCELED) {
//            switch (requestCode) {
//                case 0:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
//                        addBookImage.setImageBitmap(selectedImage);
//                    }
//                    break;
//                case 1:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                            if (cursor != null) {
//                                cursor.moveToFirst();
//                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                String picturePath = cursor.getString(columnIndex);
//                                addBookImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//                                cursor.close();
//                            }
//                        }
//                    }
//                    break;
//            }
//        }
//    }
}