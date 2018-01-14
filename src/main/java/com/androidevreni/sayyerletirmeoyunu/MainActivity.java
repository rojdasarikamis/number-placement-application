package com.androidevreni.sayyerletirmeoyunu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    Random rnd;
    String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", ""};

    int bosPos = 15, rasgele;
    String gecici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (GridView) findViewById(R.id.gridview);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers);
        grid.setAdapter(adapter);

        rnd = new Random();
        for (int i = 0; i < 15; i++) {
            rasgele = rnd.nextInt(16);
            gecici = numbers[rasgele];
            numbers[rasgele] = numbers[i];
            numbers[i] = gecici;

            if (numbers[i] == "")
                bosPos = i;
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // hızlı bitirmek için bu if bloğunu kaldırın :-), bir deneyin derim.
                if ((bosPos == (pos + 1)) || (bosPos == (pos - 1)) || (bosPos == (pos - 4)) || (bosPos == (pos + 4))) {

                    numbers[bosPos] = (String) ((TextView) view).getText();
                    numbers[pos] = "";
                    bosPos = pos;

                    ((ArrayAdapter) grid.getAdapter()).notifyDataSetChanged();

                    oyunBittiMi();

                }
            }

        });
    }

    private void oyunBittiMi() {
        for (int i = 0; i < 15; i++) {
            if (!Integer.toString(i + 1).equals((String) grid.getItemAtPosition(i).toString()))
                return;
        }

        Toast msg = Toast.makeText(getApplicationContext(), "TEBRİKLER OYUN BİTTİ!!!!", Toast.LENGTH_SHORT);
        msg.show();

    }

}
