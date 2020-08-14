package com.utd.covidtracker.ui.status;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.utd.covidtracker.R;
import com.utd.covidtracker.ui.MyCustomDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    Timer timer;
    TimerTask timerTask;
    private StatusViewModel homeViewModel;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;


    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    public TextView tvStatus,tvLocation,tvDensity,tvRadius,tvInfectedUser,tvSymptomaticUser,tvNormalUser, tvStartDate, tvEndDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(StatusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_status, container, false);

        //accessing available text view fields
         tvStatus = (TextView)root.findViewById(R.id.tvStatus);
         tvStartDate = (TextView)root.findViewById(R.id.tvStartDate);
         tvEndDate = (TextView)root.findViewById(R.id.tvEndDate);
         tvLocation = (TextView)root.findViewById(R.id.tvLocation);
         tvDensity = (TextView)root.findViewById(R.id.tvDensity);
         tvRadius = (TextView)root.findViewById(R.id.tvRadius);
         tvInfectedUser = (TextView)root.findViewById(R.id.tvInfectedUser);
         tvSymptomaticUser = (TextView)root.findViewById(R.id.tvSymptomaticUser);
         tvNormalUser = (TextView)root.findViewById(R.id.tvNormalUser);


        // allDialog();

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        //fetching status data from sharedpreference file
        SharedPreferences sh = this.getContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        //fetching the value for key "status"
        String status = sh.getString("status", "");
        String startDate = sh.getString("startDate", "");
        String endDate = sh.getString("endDate", "");
        String normalUser = sh.getString("normalUser", "0");
        String infectedUser = sh.getString("infectedUser", "0");
        String symptomaticUser = sh.getString("symptomaticUser", "0");

        Log.d("Fragment",status);

        //updating the status textview
        if(status.isEmpty() || status.equals("NORMAL")){
            status="NORMAL";
            tvStatus.setTextColor(Color.GREEN);
            tvStartDate.setText(startDate);
            tvEndDate.setText(endDate);
        }
        else if(status.equals("INFECTED")){
            tvStatus.setTextColor(Color.RED);
            tvStartDate.setText("Quarantine Start Date : "+startDate);
            tvEndDate.setText("Quarantine End Date : "+endDate);

        }
        else if(status.equals("SYMPTOMS")){
            tvStatus.setTextColor(Color.rgb(255,69,0));
            //tvStartDate.setText("Quarantine Start Date : "+startDate);
            //tvEndDate.setText("Quarantine End Date : "+endDate);

        }

        // Setting the fetched data in the EditTexts
        tvStatus.setText(status);
        tvNormalUser.setText(normalUser);
        tvInfectedUser.setText(infectedUser);
        tvSymptomaticUser.setText(symptomaticUser);

        //starting timer
        startTimer();
    }

    @Override
    public void onPause(){
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref"
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        //storing key-value pair in the "MySharedPref" file
        //value is the current status in the status text view
        myEdit.putString("status",tvStatus.getText().toString());
        //myEdit.putString("startDate",tvStartDate.getText().toString());
        //myEdit.putString("endDate",tvEndDate.getText().toString());
        myEdit.putString("normalUser",tvNormalUser.getText().toString());
        myEdit.putString("symptomaticUser",tvSymptomaticUser.getText().toString());
        myEdit.putString("infectedUser",tvInfectedUser.getText().toString());
        myEdit.commit();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        stoptimertask(getView());
    }

    @Override
    public void onStop() {
        super.onStop();
        stoptimertask(getView());

    }

    //function to call location API
    public void locationApiCall(){
        //api service location
        String url = "http://192.168.0.8:8080/covid/locationInfo";
        RequestQueue requestQueue = null;
        // queue for request to process
        if(getActivity()!=null) {
            requestQueue = Volley.newRequestQueue(getActivity());
            //fetching json response
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        // if successful
                        public void onResponse(JSONObject response) {

                            //prints response in log
                            Log.d("Response Request", response.toString());

                            //store response received
                            JSONObject responseObject = response;

                            try {
                                //setting received data to text views
                                tvLocation.setText(response.getString("location"));
                                tvDensity.setText(response.getString("density"));
                                tvRadius.setText("1000");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    //if unsuccessful
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //prints error message in log
                            Log.e("Response Request locationInfo api", error.toString());
                        }
                    });
            //adding request to queue
            requestQueue.add(objectRequest);
        }
    }

    //function to call bluetooth api
    public void bluetoothApiCall(){
        //api service location
        String url = "http://192.168.0.8:8080/covid/bluetooth";

        // queue for request to process
        if(getActivity()!=null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            //fetching json response
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        //if success
                        public void onResponse(JSONObject response) {

                            //prints response in the log
                            Log.e("Response Request bluetooth api", response.toString());

                            JSONObject responseObject = response;
                            try {

                                //fetching status data from sharedpreference file
                                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);

                                //if sharePreference file doesn't exist, create one and put default values
                                if (!sharedPreferences.contains("status")) {
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                                    //updating "MySharedPref" file
                                    myEdit.putString("status", "NORMAL");
                                    myEdit.putString("normalUser", "0");
                                    myEdit.putString("symptomaticUser", "0");
                                    myEdit.putString("infectedUser", "0");
                                    myEdit.commit();

                                }
                                //fetching the user count details from sharedPreference file
                                String normalUser = sharedPreferences.getString("normalUser", "0");
                                String infectedUser = sharedPreferences.getString("infectedUser", "0");
                                String symptomaticUser = sharedPreferences.getString("symptomaticUser", "0");
                              //  String status = sharedPreferences.getString("status", "");

                                //storing the response received from bluetooth api
                                boolean warning = response.getBoolean("warning");
                                boolean alarm = response.getBoolean("alarm");
                                String userDiseaseStatus = response.getString("userdiseaseStatus");

                                //updating normal user count
                                if (userDiseaseStatus.equals("normal") || userDiseaseStatus.equals("NORMAL")) {
                                    normalUser = String.valueOf(Integer.valueOf(normalUser) + 1);
                                }
                                //updating infected user count
                                else if (userDiseaseStatus.equals("infected")) {
                                    infectedUser = String.valueOf(Integer.valueOf(infectedUser) + 1);
                                }
                                //updating symptomatic user count
                                else if (userDiseaseStatus.equals("symptoms")) {
                                    symptomaticUser = String.valueOf(Integer.valueOf(symptomaticUser) + 1);

                                }
                                //show dialog box
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                                if (warning) {
                                    //show warning dialog
                                    callDialog(warning, alarm);
                                    myEdit.putString("status", "Symptoms");

                                }
                                //updating infected user count
                                else if (alarm) {
                                    //show alarm dialog
                                    callDialog(warning, alarm);
                                    myEdit.putString("status", "Symptoms");


                                }
                                // initializing editor for updating sharePreference file
                                //updating "MySharedPref" file
                                myEdit.putString("normalUser", normalUser);
                                myEdit.putString("symptomaticUser", symptomaticUser);
                                myEdit.putString("infectedUser", infectedUser);
                                myEdit.commit();

                                //fetching updated data from sharePreference file
                                String status = sharedPreferences.getString("status", "0");
                                normalUser = sharedPreferences.getString("normalUser", "0");
                                infectedUser = sharedPreferences.getString("infectedUser", "0");
                                symptomaticUser = sharedPreferences.getString("symptomaticUser", "0");

                                //updating textviews
                                tvNormalUser.setText(normalUser);
                                tvInfectedUser.setText(infectedUser);
                                tvSymptomaticUser.setText(symptomaticUser);
                                Log.d("NEW CLASS",status);

                                if(status.isEmpty() || status.equalsIgnoreCase("NORMAL")){
                                    status="NORMAL";
                                    tvStatus.setTextColor(Color.GREEN);
                                    tvStatus.setText(status);
                                   // tvStartDate.setText(startDate);
                                  //  tvEndDate.setText(endDate);
                                }
                                else if(status.equalsIgnoreCase("INFECTED")){
                                    tvStatus.setTextColor(Color.RED);
//                                    tvEndDate.setText("Quarantine End Date : "+endDate);
                                    tvStatus.setText(status);

                                }
                                else if(status.equalsIgnoreCase("SYMPTOMS")){
                                    tvStatus.setTextColor(Color.rgb(255,69,0));
                                    //tvStartDate.setText("Quarantine Start Date : "+startDate);
                                    //tvEndDate.setText("Quarantine End Date : "+endDate);
                                    tvStatus.setText(status);

                                }

                                //   tvStatus.setText(status);
                            //    tvStatus.setTextColor(Color.rgb(255,69,0));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    //if request unsuccessful
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //print error message in log
                            Log.e("Response Request bluetooth api", error.toString());
                        }
                    });
            //adding request to queue
            requestQueue.add(objectRequest);
        }
    }

//function to start timer
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, 10000); //
    }

    //function to stop timer
    public void stoptimertask(View v) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //function to initialize timer
    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //calls for location and bluetooth apis
                        locationApiCall();
                        bluetoothApiCall();
                        }
                });
            }
        };
    }


    public void callDialog(boolean warning, boolean alarm){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new MyCustomDialogFragment();
        Bundle bundle = new Bundle();
        if(warning){
            bundle.putString("title", "Warning");
            bundle.putString("image", "warning");
        }
        else if(alarm){
            bundle.putString("title", "Danger");
            bundle.putString("image", "alarm");
        }

        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "dialog");
    }
}