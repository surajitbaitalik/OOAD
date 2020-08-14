package com.utd.covidtracker.ui.update;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class UpdateUserFragment extends Fragment {

    private UpdateUserViewModel slideshowViewModel;
    private Context mContext;
    final Calendar myCalendar = Calendar.getInstance();
    EditText startDate, endDate;
    TextView testResult;
    Button update_button;
    Spinner dropdown;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(UpdateUserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_update_user, container, false);

        // accessing textview fields
      //  testResult = (TextView)root.findViewById(R.id.tvTestResult);
        startDate= (EditText) root.findViewById(R.id.tvStartDate);
        endDate= (EditText) root.findViewById(R.id.tvEndDate);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };

        startDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //accessing update button
        update_button = (Button)root.findViewById(R.id.update_button);

        dropdown = (Spinner)root.findViewById(R.id.spinner1);

        String[] items = new String[]{"positive","negative"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        // function on update button
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve the testResult updated
                String status = dropdown.getSelectedItem().toString();

                //updating status of user in sharedpreference file
                // Storing data into SharedPreferences
                sharedPreferences = getContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                // Storing the key and its value  as the data fetched from edittext
                if(status.equals("positive")){
                    myEdit.putString("status","INFECTED");
                    myEdit.putString("endDate",endDate.getText().toString());
                    myEdit.putString("startDate",startDate.getText().toString());

                }
                else if(status.equals("negative")){
                    myEdit.putString("status","NORMAL");
                    myEdit.putString("endDate","");
                    myEdit.putString("startDate","");
                }
                // Once the changes have been made,we need to commit to apply those changes made,
                // otherwise, it will throw an error
                myEdit.commit();
                //posting the user info
                postRequest();

                // returning back to home activity
                getFragmentManager().popBackStack();;

            }
        });
        return root;
    }

//function to call bluetooth api
    private void postRequest(){

        /*//target location
        String url = "http://192.168.0.8:8080/covid/bluetooth";

        // queue for request to process
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //making parameter in json format
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("testResult",testResult.getText().toString());
            object.put("startDate",startDate.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // posting request
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                url,object,
                new Response.Listener<JSONObject>() {
                    @Override
                    //if success
                    public void onResponse(JSONObject response) {
*/
                        //Displaying success message
                        Toast.makeText(getContext().getApplicationContext(),"Update successful",Toast.LENGTH_SHORT).show();
  /*                      //print response in log
                        Log.e("Response post", response.toString());
                    }
                },
                //if unsuccessful
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Displaying failed message
                        Toast.makeText(getContext().getApplicationContext(),"Update failed",Toast.LENGTH_SHORT).show();

                        //print error in log
                        Log.e("Response post", error.toString());
                    }
                }
        )
        /*{
            @Override
            //setting the parameters for request
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("testResult", testResult.getText().toString());
                params.put("startDate",startDate.getText().toString());

                return  params;
            }
        ;
        // adding request to queue
        requestQueue.add(stringRequest);
        }*/
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDate.setText(sdf.format(myCalendar.getTime()));
    }
}