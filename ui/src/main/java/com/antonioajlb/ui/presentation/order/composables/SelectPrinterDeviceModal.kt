package com.antonioajlb.ui.presentation.order.composables

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antonioajlb.bluetooth.AndroidBluetoothController
import com.example.ui.R

@Composable
fun SelectPrinterDeviceModal(
    onDeviceSelected: (BluetoothDevice) -> Unit,
    onDismissRequest: () -> Unit
) {

    val bluetoothController = AndroidBluetoothController(
        context = LocalContext.current
    )

    var scanning by remember {
        mutableStateOf(false)
    }

    val devices by bluetoothController.scannedDevices.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val permissionGranted = remember { mutableStateOf(false) }

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        permissionGranted.value = result.all { it.value }
    }

    LaunchedEffect(Unit) {
        val missing = permissions.any {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing) {
            permissionLauncher.launch(permissions)
        } else {
            permissionGranted.value = true
        }
    }

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
    ) {
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(width = 3.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                Button(
                    onClick = {
                        if(permissionGranted.value){
                            bluetoothController.startDiscovery()
                            scanning = true
                        }else{
                            permissionLauncher.launch(permissions)
                        }
                    }
                ){
                    Text(stringResource(R.string.start_scan))
                }

                Button(
                    onClick = {
                        if(permissionGranted.value){
                            bluetoothController.stopDiscovery()
                            scanning = false
                        }else{
                            permissionLauncher.launch(permissions)
                        }
                    }
                ){
                    Text(stringResource(R.string.stop_scan))
                }

            }
            Text(stringResource(R.string.select_device_to_continue))
            LazyColumn(
                modifier = Modifier
                    .heightIn(min = 225.dp, max = 300.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(devices){
                    DeviceItem(
                        device = it,
                        onClick = {
                            onDeviceSelected(it)
                        }
                    )
                }
                if(devices.isEmpty() && scanning ){
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(stringResource(R.string.scanning_devices))
                            Text(stringResource(R.string.assert_printer_on))
                            CircularProgressIndicator()

                        }
                    }
                }
            }
            Button(
                onClick = {
                    onDismissRequest()
                }
            ){
                Text(stringResource(R.string.continue_without_ticket))
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            bluetoothController.stopDiscovery()
        }
    }
}