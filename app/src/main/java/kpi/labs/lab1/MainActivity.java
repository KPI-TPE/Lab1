package kpi.labs.lab1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;


import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Adapter adapter;
    private List<String> list;
    private double RESULT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        adapter.strings = calculation();
        final GridView gridView = (GridView) findViewById(R.id.mas);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);


        adapter = new Adapter(getApplicationContext(), R.layout.list_item);

        gridView.setAdapter(adapter);

        final TextView result = (TextView) findViewById(R.id.textView);
        result.setText("Result = " + RESULT);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.strings = calculation();
                adapter = new Adapter(getApplicationContext(), R.layout.list_item);
                gridView.setAdapter(adapter);
                result.setText("Result = " + RESULT);
            }
        });


    }

    public String[] calculation() {
        double[] x1 = new double[8];
        double[] x2 = new double[8];
        double[] x3 = new double[8];

        double[] x1n = new double[8];
        double[] x2n = new double[8];
        double[] x3n = new double[8];
        double[] y = new double[8];

        for (int i = 0; i < x1.length; i++) {

            x1[i] = (Math.random() * (20 - 1));
            x2[i] = (Math.random() * (20 - 1));
            x3[i] = (Math.random() * (20 - 1));
        }

        Arrays.sort(x1);
        Arrays.sort(x2);
        Arrays.sort(x3);

        double X01 = (x1[7] + x1[0]) / 2;
        double X02 = (x2[7] + x2[0]) / 2;
        double X03 = (x3[7] + x3[0]) / 2;

        double dx1 = X01 - x1[0];
        double dx2 = X02 - x2[0];
        double dx3 = X03 - x3[0];
        double buf = 0;
        for (int i = 0; i < y.length; i++) {
            y[i] = f(x1[i], x2[i], x3[i]);
            buf += y[i];
        }
        Arrays.sort(y);

        buf /= y.length;
        double res = 0;
        Log.d("Value", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + buf);
        for (int i = 0; i < y.length; i++) {
            if (buf > y[i])
                res = y[i + 1];

        }
        RESULT = res;


        for (int i = 0; i < x1n.length; i++) {

            x1n[i] = (x1[i] - X01) / dx1;
            x2n[i] = (x2[i] - X02) / dx2;
            x3n[i] = (x3[i] - X03) / dx3;
        }


        ArrayList list = new ArrayList();
        for (int i = 0; i < 8; i++) {

            list.add(Integer.toString(i + 1));
            list.add(subString(Double.toString(x1[i])));
            list.add(subString(Double.toString(x2[i])));
            list.add(subString(Double.toString(x3[i])));
            list.add(subString(Double.toString(y[i])));
            list.add(subString(Double.toString(x1n[i])));
            list.add(subString(Double.toString(x2n[i])));
            list.add(subString(Double.toString(x3n[i])));
        }


        list.add("X0");
        list.add(subString(Double.toString(X01)));
        list.add(subString(Double.toString(X02)));
        list.add(subString(Double.toString(X03)));
        list.add("");
        list.add("");
        list.add("");
        list.add("");



        list.add("dx");
        list.add(subString(Double.toString(dx1)));
        list.add(subString(Double.toString(dx2)));
        list.add(subString(Double.toString(dx3)));
        return (String[]) list.toArray(new String[list.size()]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings :
                return true;

            case R.id.about :
                AlertDialog adb = new AlertDialog.Builder(MainActivity.this).create();
                adb.setTitle("About");
                adb.setMessage("Лаборатона робота №1\n" +
                        "Варіант №108\n" +
                        "Виконав студент групи ІО-34\n" +
                        "Дяк Олександр");
                adb.setIcon(R.drawable.alert);
                adb.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public double f(double x1, double x2, double x3) {
        return 3 + 2 * x1 + 5 * x2 + 4 * x3;
    }

    public double dxN(double x0n, double xN) {
        return x0n - xN;
    }


    private String subString(String value) {
        if (value.length() <= 4) {
            return "  " + value + "  ";
        } else {
            return "  " + value.substring(0, 5) + "  ";
        }

    }



}
