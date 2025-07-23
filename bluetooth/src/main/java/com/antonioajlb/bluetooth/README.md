#Modulo Bluetooth

Para usar este modulo deberas incluir en tu manifest los siguientes permisos

    <uses-permission android:name="android.permission.BLUETOOTH" android:maxSdkVersion="30"/>
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN"
        android:usesPermissionFlags="neverForLocation"/>
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" android:maxSdkVersion="30"/>
    
    <uses-feature android:name="android.hardware.bluetooth"/>