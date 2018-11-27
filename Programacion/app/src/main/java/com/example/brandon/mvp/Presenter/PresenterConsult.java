package com.example.brandon.mvp.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.brandon.mvp.Model.Device;
import com.example.brandon.mvp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PresenterConsult {

    Context mContext;
    private static final String TAG = "PresenterConsult";
    ListView ListDevice;
    private List<Device> listDevice = new ArrayList<Device>();
    ArrayAdapter<Device> adapter;

    public PresenterConsult(Context mContext) {
        this.mContext = mContext;
    }

    public void getDatosFirebase(DatabaseReference mDatabase)
    {
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.activity_mostrar);
        dialog.show();
        ListDevice = (ListView)dialog.findViewById(R.id.Lista);
;
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDevice.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Device device = snapshot.getValue(Device.class);
                    listDevice.add(device);
                    adapter = new ArrayAdapter<Device>(mContext,android.R.layout.simple_list_item_1,listDevice);
                    ListDevice.setAdapter(adapter);
                    String id = device.getId();
                    String modelo = device.getModelo();
                    String serie = device.getSerie();
                    String os = device.getOS();
                    String hdd = device.getHdd();
                    String ram = device.getRam();
                    Log.i(TAG,"Datos: " + "\n" + id + "\n"+ modelo + "\n" + serie + "\n" +os + "\n" + hdd + "\n" + ram);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
