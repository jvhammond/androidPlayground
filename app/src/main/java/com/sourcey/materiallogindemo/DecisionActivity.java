package com.sourcey.materiallogindemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jefhammond on 12/5/16.
 */


public class DecisionActivity extends Activity {
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    Random random = new Random();

    @Bind(R.id.btnShare)
    Button _shareButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);
        Log.d("CameraDemo", "jhammond decision");
        final boolean decision = random.nextBoolean();
        ButterKnife.bind(this);
        TextView e = (TextView) findViewById(R.id.decision);
        ImageView i = (ImageView) findViewById(R.id.imageDecision);

        if (decision == true) {
            e.setText("You've Been APPROVED");
            int id = getResources().getIdentifier("success", "drawable", getPackageName());
            i.setImageResource(id);
        } else {
            e.setText("You've Been DECLINED");
            int id = getResources().getIdentifier("switzer_sm", "drawable", getPackageName());
            i.setImageResource(id);
        }

        _shareButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tweet(decision);
            }
        });

        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.
        /*final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
*/
//        Button capture = (Button) findViewById(R.id.btnCapture);
//        capture.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                // Here, the counter will be incremented each time, and the
//                // picture taken by camera will be stored as 1.jpg,2.jpg
//                // and likewise.
//                /*count++;
//                String file = dir+count+".jpg";
//                File newfile = new File(file);
//                try {
//                    newfile.createNewFile();
//                }
//                catch (IOException e)
//                {
//                }
//
//                Uri outputFileUri = Uri.fromFile(newfile);*/
//
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//
//                startActivityForResult(cameraIntent, 100);
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void tweet(boolean decision) {
        Log.d("decision", "tweetttttt123123123");

        String tweetMsg = "Celebrate good times! I got approved for PayPal Credit!";
        //Uri imageUri = null;
        Uri imageUri = Uri.parse("android.resource://com.sourcey.materiallogindemo/" + R.drawable.switzer_sm);

        if (decision == false) {
            tweetMsg = "I didn't get approved for PayPal Credit";
        }


        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        }

        imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(),
                BitmapFactory.decodeResource(getResources(), R.drawable.switzer_sm), null, null));


        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, tweetMsg);
        tweetIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        tweetIntent.setType("image/*");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                Log.d("decision", "twitter app found");
                resolved = true;
                break;
            }
        }
        if (resolved) {
            startActivity(tweetIntent);
        } else {
            //Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();
            Log.d("CameraDemo", "error tweeting");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("CameraDemo", "jhammond Pic saved");
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("CameraDemo", "Pic saved");

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Decision Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}