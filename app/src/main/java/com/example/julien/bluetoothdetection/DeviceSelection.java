package com.example.julien.bluetoothdetection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class DeviceSelection extends AppCompatActivity {

    Button scanBtn;
    Button backBtn;
    ListView deviceList;

    private BluetoothAdapter myBT = null;
    private Set pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        scanBtn = (Button) findViewById(R.id.scanBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        deviceList = (ListView) findViewById(R.id.pairedList);

        myBT = BluetoothAdapter.getDefaultAdapter();
        if(myBT == null)
        {
            //Show a mensag. that thedevice has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            //finish apk
            finish();
        }
        else
        {
            if (myBT.isEnabled())
            { }
            else
            {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pairedDevicesList(); //method that will be called
            }
        });

    }

    private void pairedDevicesList()
    {
        pairedDevices = myBT.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(Object bt : pairedDevices)
            {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) bt;
                list.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            //String info = ((TextView) v).getText().toString();
            //String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(DeviceSelection.this, ScanningPage.class);
            //Change the activity.
            //i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
        }
    };
}
