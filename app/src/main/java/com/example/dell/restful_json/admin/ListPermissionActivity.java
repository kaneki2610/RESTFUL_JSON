package com.example.dell.restful_json.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPermissionActivity extends AppCompatActivity {

    ListView listPermission;
    Toolbar toolbar;
    JSONArray jsonArrayPermission;
    JSONObject jsonObject = null;
    ArrayList arrPermisson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_permission);
        listPermission = (ListView) findViewById(R.id.listPermission);
        toolbar=findViewById(R.id.toolbarAddPer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        arrPermisson = new ArrayList<>();

        Permission p = new Permission(ListPermissionActivity.this);
        jsonArrayPermission = p.getAllPermission();
        for(int i=0; i<jsonArrayPermission.length(); i++)
        {
            try {
                jsonObject = jsonArrayPermission.getJSONObject(i);
                arrPermisson.add(jsonObject.get("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrPermisson);
        listPermission.setAdapter(adapter);


  /*      listPermission.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListPermissionActivity.this);
                alert.setTitle("DELETE");
                alert.setIcon(R.mipmap.ic_launcher);
                alert.setMessage("Are you sure to delete?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Permission p = new Permission(ListPermissionActivity.this);
                        try {
                            JSONObject obj = (JSONObject) jsonArrayPermission.get(position);
                            //Toast.makeText(ListProduct.this, obj.get("id")+"", Toast.LENGTH_SHORT).show();
                            p.deletePermission(Integer.parseInt(obj.get("id")+""));
                            arrPermisson.remove(position);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

                return false;
            }
        });*/
    }
    // menu add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                Intent i = new Intent(ListPermissionActivity.this, AddPermissionActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
