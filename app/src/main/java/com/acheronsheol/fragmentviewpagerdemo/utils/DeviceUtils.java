package com.acheronsheol.fragmentviewpagerdemo.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.acheronsheol.fragmentviewpagerdemo.main.AcheronsheolApplication.getAppContext;


public class DeviceUtils {

    private static String Device_Mac = "";
    private static String Device_IMEI = "";
    private static String Device_AndroidID = "";
    private static String Device_OAID = "";


    public static String getIMEI() {
        try {
            if (Device_IMEI.equals("")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {//版本高于或等于android10
                    return "";
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//在10和8之间
                    TelephonyManager telephonyManager = (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getAppContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return "";
                    }
                    Device_IMEI = telephonyManager.getImei();
                    return Device_IMEI;
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//在8和6之间
                    TelephonyManager telephonyManager = (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
                    if (ActivityCompat.checkSelfPermission(getAppContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return "";
                    }
                    Device_IMEI = telephonyManager.getDeviceId();
                    return Device_IMEI;
                } else {//低于android6
                    TelephonyManager telephonyManager = (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
                    Device_IMEI = telephonyManager.getDeviceId();
                    return Device_IMEI;
                }
            } else {
                return Device_IMEI;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getAndroidID(){
        try {
            if(Device_AndroidID.equals("")) {
                Device_AndroidID = Settings.Secure.getString(getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            return Device_AndroidID;

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getOAID(){
        try {
            if(Device_OAID.equals("")) {
                int errorCode = MdidSdkHelper.InitSdk(getAppContext(), true, new IIdentifierListener() {
                        @Override
                        public void OnSupport(boolean b, IdSupplier idSupplier) {
                            if (idSupplier != null && idSupplier.isSupported()) {
                                Device_OAID = idSupplier.getOAID();
                            }
                        }
                    });
                if (errorCode == ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT) {
                    Log.d("DeviceUtils", "getOAID:不支持的设备 ");
                } else if (errorCode == ErrorCode.INIT_ERROR_LOAD_CONFIGFILE) {
                    Log.d("DeviceUtils", "getOAID:加载配置文件出错 ");
                } else if (errorCode == ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT) {
                    Log.d("DeviceUtils", "getOAID:不支持的设备厂商 ");
                } else if (errorCode == ErrorCode.INIT_ERROR_RESULT_DELAY) {
                    Log.d("DeviceUtils", "getOAID:获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程 ");
                } else if (errorCode == ErrorCode.INIT_HELPER_CALL_ERROR) {
                    Log.d("DeviceUtils", "getOAID:反射调用出错 ");
                } else {
                    Log.d("DeviceUtils", "getOAID:获取成功 ");
                }
            }
            return Device_OAID;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getMacAddress() {
        String defaultMac = "02:00:00:00:00:00";
        if(Device_Mac.equals(defaultMac) || Device_Mac.equals("")) {
            Device_Mac = defaultMac;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                Device_Mac = getMacDefault(getAppContext());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Device_Mac = getMacFromFile();
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Device_Mac = getMacFromHardware();
            }
        }
        return Device_Mac;
    }
    private static String getMacDefault(Context context) {
        String mac = "02:00:00:00:00:00";
        if (context == null) {
            return mac;
        }
        WifiManager wifi = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wifi == null) {
            return mac;
        }
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
        }
        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }
    private static String getMacFromFile() {
        String WifiAddress = "02:00:00:00:00:00";
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }
    private static String getMacFromHardware() {
        String mac = "02:00:00:00:00:00";
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return mac;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    public static String getCPUArchitecture() {
        try {
            String os_cpuabi = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream())).readLine();
            if (os_cpuabi.contains("armeabi-v7a")) {
                return "armeabi-v7a";
            } else if (os_cpuabi.contains("arm64-v8a")) {
                return "armeabi-v8a";
            } else if (os_cpuabi.contains("x86")) {
                return "x86";
            } else {
                return "armeabi";
            }
        } catch (Exception e) {
            return "armeabi";
        }
    }

}
