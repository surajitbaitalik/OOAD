package com.utd.covidtracker.ui.disease;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.utd.covidtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseInfoFragment extends Fragment {

    private DiseaseInfoViewModel galleryViewModel;

    BarChart bar;
    BarChart bar2;
    BarChart bar3;

    Spinner dropdown;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(DiseaseInfoViewModel.class);
        View view = inflater.inflate(R.layout.fragment_disease, container, false);
        dropdown = (Spinner)view.findViewById(R.id.spinner1);

        String[] items = new String[]{"Dallas", "New York", "San Jose"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //"position" argument gives the index of item selected
                //call post request
                postRequest(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bar = (BarChart)view.findViewById(R.id.bar_daily);
        bar2 = (BarChart)view.findViewById(R.id.bar_2);
        bar3 = (BarChart)view.findViewById(R.id.bar_3);

        return view;
    }
    //function to call bluetooth api
    private void postRequest(int position){
        //target location
        String url = "http://192.168.0.8:8080/covid/diseaseInfo";

        // queue for request to process
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("position",position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                url,object,
                new Response.Listener<JSONObject>() {
                    @Override
                    //if successful
                    public void onResponse(JSONObject response) {
                        //prints log message
                        Log.e("Response post  diease api", response.toString());

                        JSONObject mainObj = response;
                        // sample(mainObj);
                        try {
                            createBar1(response.getJSONArray("daily deaths"));
                            createBar2(response.getJSONArray("newcases"));
                            createBar3(response.getJSONArray("recoveries"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                //if unsuccessfull
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Displaying fail message
                        Toast.makeText(getContext().getApplicationContext(),"DiseaseInfo api call failed",Toast.LENGTH_SHORT).show();

                        //prints error message in log
                        Log.e("Response post diease api", error.toString());
                    }
                }
        );

        requestQueue.add(jsonRequest);
    }

    private void sample(JSONObject mainObj){

    }
    
    private void createBar3(JSONArray itemArray) throws JSONException {

        List<BarEntry> entries = new ArrayList<>();

        for (int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            int number = objects.getInt("number");
            String date = objects.getString("date");
            entries.add(new BarEntry(i, number,date));
        }

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        int[] colors = new int[itemArray.length()];
        Arrays.fill(colors,Color.rgb(51, 204, 51));
        bSet.setColors(colors);

        ArrayList<String> barFactors = new ArrayList<>();

        for(int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            barFactors.add(objects.getString("date"));
            System.out.println("date : "+objects.getString("date"));
        }

        XAxis xAxis = bar.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        BarData data = new BarData(bSet);
        data.setBarWidth(0.9f); // set custom bar width
        data.setValueTextSize(12);
        Description description = new Description();
        description.setTextColor(R.color.colorPrimary);
        description.setText("All values in marks");
        bar3.setDescription(description);
        bar3.setData(data);
        bar3.setFitBars(true); // make the x-axis fit exactly all bars
        bar3.invalidate(); // refresh
        bar3.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        Legend l = bar3.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        // l.setTypeface(font);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        List<LegendEntry> lentries = new ArrayList<>();

        LegendEntry entry = new LegendEntry();
        entry.formColor = Color.GREEN;
        entry.label = "Recoveries";
        lentries.add(entry);

        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        l.setCustom(lentries);
    }

    private void createBar2(JSONArray itemArray) throws JSONException {

        List<BarEntry> entries = new ArrayList<>();

        for (int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            int number = objects.getInt("number");
            String date = objects.getString("date");
            entries.add(new BarEntry(i, number,date));
        }

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        int[] colors = new int[itemArray.length()];
        Arrays.fill(colors,Color.rgb(255, 178, 102));
        bSet.setColors(colors);

        ArrayList<String> barFactors = new ArrayList<>();
        for(int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            barFactors.add(objects.getString("date"));
            System.out.println("date : "+objects.getString("date"));
        }

        XAxis xAxis = bar.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        BarData data = new BarData(bSet);
        data.setBarWidth(0.9f); // set custom bar width
        data.setValueTextSize(12);
        Description description = new Description();
        description.setTextColor(R.color.colorPrimary);
        description.setText("All values in marks");
        bar2.setDescription(description);
        bar2.setData(data);
        bar2.setFitBars(true); // make the x-axis fit exactly all bars
        bar2.invalidate(); // refresh
        bar2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        Legend l = bar2.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        List<LegendEntry> lentries = new ArrayList<>();
        LegendEntry entry = new LegendEntry();
        entry.formColor = Color.rgb(255, 178, 102);
        entry.label = "New Cases";
        lentries.add(entry);

        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        l.setCustom(lentries);
    }

    private void createBar1(JSONArray itemArray) throws JSONException {

        List<BarEntry> entries = new ArrayList<>();
        for (int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            int number = objects.getInt("number");
            String date = objects.getString("date");
            entries.add(new BarEntry(i, number,date));
        }

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        int[] colors = new int[itemArray.length()];
        Arrays.fill(colors,Color.rgb(255, 0, 0));
        bSet.setColors(colors);

        ArrayList<String> barFactors = new ArrayList<>();
        for(int i=0; i<itemArray.length(); i++){
            JSONObject objects = itemArray.optJSONObject(i);
            barFactors.add(objects.getString("date"));
            System.out.println("date : "+objects.getString("date"));
        }

        XAxis xAxis = bar.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        BarData data = new BarData(bSet);
        data.setBarWidth(0.9f); // set custom bar width
        data.setValueTextSize(12);
        Description description = new Description();
        description.setTextColor(R.color.colorPrimary);
        description.setText("All values in marks");
        bar.setDescription(description);
        bar.setData(data);
        bar.setFitBars(true); // make the x-axis fit exactly all bars
        bar.invalidate(); // refresh
        bar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        Legend l = bar.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        List<LegendEntry> lentries = new ArrayList<>();

        LegendEntry entry = new LegendEntry();
        entry.formColor = Color.rgb(255,0,0);
        entry.label = "Daily Deaths";
        lentries.add(entry);

        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        l.setCustom(lentries);
    }
}