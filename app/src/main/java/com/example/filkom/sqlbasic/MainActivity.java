package com.example.filkom.sqlbasic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends Activity {
    private DataSourceTamu dataTamu;
    ListView listview;
    EditText fieldNama;
    ArrayAdapter<Tamu> adapter;
    Tamu currentTamu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldNama = (EditText)findViewById(R.id.nama);
        listview = (ListView) findViewById(R.id.listView);

        dataTamu = new DataSourceTamu(this);
        dataTamu.open();

        final List<Tamu> values = dataTamu.semuaTamu();
        adapter = new ArrayAdapter<Tamu>(this,
                android.R.layout.simple_list_item_1, values);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                fieldNama.setText("");
                fieldNama.append(""+adapter.getItem(position));
                currentTamu = adapter.getItem(position);
            }
        });
    }

    public void onClick(View view){
        Tamu tamu = null;
        switch (view.getId()){
            case R.id.tambah:
                String isinama = fieldNama.getText().toString();
                if (isinama.compareTo("") != 0) {
                    tamu = dataTamu.tambahTamu(isinama);
                    adapter.add(tamu);
                    fieldNama.setText("");
                }
                break;
            case R.id.hapus:
                if (currentTamu != null) {
                    dataTamu.hapusTamu(currentTamu);
                    adapter.remove(currentTamu);
                    fieldNama.setText("");
                    currentTamu = null;
                }
                break;
            case R.id.edit:
                if (currentTamu != null){
                    String namabaru = fieldNama.getText().toString();
                    final List<Tamu> newValues = dataTamu.updateTamu(currentTamu, namabaru);
                    adapter = new ArrayAdapter<Tamu>(this,
                            android.R.layout.simple_list_item_1, newValues);

                    listview.setAdapter(adapter);
                    fieldNama.setText("");
                    currentTamu = null;
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

}
