package com.antonioajlb.ui.presentation.order.composables

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.ui.R

@Composable
fun DeviceItem(
    device: BluetoothDevice,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
            .padding(8.dp)
    ) {
//        Text(
//            modifier = Modifier
//                .fillMaxWidth(),
//            text = if (ActivityCompat.checkSelfPermission(
//                    LocalContext.current,
//                    Manifest.permission.BLUETOOTH_CONNECT
//                ) == PackageManager.PERMISSION_GRANTED && device.name != null) device.name else stringResource(
//                R.string.unknown)
//        )
    }
}