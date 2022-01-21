package com.example.project11.activity;

import static android.os.Build.VERSION.SDK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project11.R;
import com.example.project11.adapters.MainAdapter;
import com.example.project11.models.Member;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends BaseActivity {

    private Context context;
    private RecyclerView recyclerView;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        refreshAdapter(prepareMemberList());

        getDeviceID1();

        fetchDeviceInfo();

        if (isEmulator()) throw new IllegalStateException();

        Log.d("checkIsPhone", "onCreate: " + checkIsPhone());

        checkIsPhone2();

        isTablet(this);
    }

    void initViews() {
        context = this;
        recyclerView = findViewById(R.id.main_recycler_view);
    }

    private void fetchDeviceInfo() {
        String uniquePseudoID = "35" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;

        String serial = Build.getRadioVersion();
        String uuid = new UUID(uniquePseudoID.hashCode(), serial.hashCode()).toString();
        String brand = Build.BRAND;
        String modeling = Build.MODEL;
        String version = Build.VERSION.RELEASE;
        Log.e("fetchDeviceInfo", "fetchDeviceInfo: \n " +
                "\n uuid is : " + uuid +
                "\n brand is: " + brand +
                "\n model is: " + modeling +
                "\n version is: " + version);
    }

    void getDeviceID1() {
        @SuppressLint("HardwareIds") String android_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
        Log.d("getDeviceID1", "onCreate: " + android_id);
    }

    public void openDetailsActivity(Member member, int position) {
        Intent intent = new Intent(context, DetailsActivity.class);
        startActivity(intent);

    }

    void refreshAdapter(ArrayList<Member> members) {
        MainAdapter mainAdapter = new MainAdapter(this, members);
        recyclerView.setAdapter(mainAdapter);
    }

    public boolean isGenymotionEmulator(String buildManufacturer) {
        return buildManufacturer != null &&
                (buildManufacturer.contains("Genymotion") || buildManufacturer.equals("unknown"));
    }

    public boolean buildModelContainsEmulatorHints(String buildModel) {
        return buildModel.startsWith("sdk")
                || "google_sdk".equals(buildModel)
                || buildModel.contains("Emulator")
                || buildModel.contains("Android SDK");
    }

    private boolean isEmulator() {
        boolean check = (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("sdk_gphone64_arm64")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator");

        Log.d("isEmulator", "isEmulator: " + check);
        return check;
    }

    public Boolean IsVM() {
        String radioVersion = android.os.Build.getRadioVersion();
        return radioVersion == null || radioVersion.isEmpty() || radioVersion.equals("1.0.0.0");
    }

    public Boolean IsVM2() {
        return android.os.Build.getRadioVersion().length() == 0;
    }

    public static boolean isEmulator(Context context) {
        @SuppressLint("HardwareIds") final String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        return Build.PRODUCT.contains(SDK)
                || Build.HARDWARE.contains("GOLDFISH")
                || Build.HARDWARE.contains("RANCHU")
                || androidId == null;
    }

    private boolean isEmulator2() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator");
    }

    String checkIsPhone() {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            return "Tablet";
        } else {
            return "Mobile";
        }
    }

    void checkIsPhone2() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches >= 6.5) {
            // 6.5inch device or bigger
            Log.d("checkIsPhone2", "checkIsPhone2: " + "TABLET");
        } else {
            // smaller device
            Log.d("checkIsPhone2", "checkIsPhone2: " + "PHONE");
        }
    }

    public static boolean isTablet(Context ctx) {
        boolean check = (ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        Log.d("isTablet", "isTablet: " + "checked: " + check);
        return check;
    }
}

