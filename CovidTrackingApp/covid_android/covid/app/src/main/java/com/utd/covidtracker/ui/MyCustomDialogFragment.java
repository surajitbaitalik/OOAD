package com.utd.covidtracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utd.covidtracker.R;

import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCustomDialogFragment extends DialogFragment {

String header;
String body;
    TextView textView;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            header = getArguments().getString("title","");
            body = getArguments().getString("image","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_custom_dialog, container, false);
        textView = v.findViewById(R.id.dialogText);
        image = v.findViewById(R.id.image);
        textView.setText(header);

        int resId = getResources().getIdentifier(body, "drawable", "com.utd.covidtracker");
        image.setImageResource(resId);
       // image.setImageResource(R.drawable.ic_menu_gallery);


        // Do all the stuff to initialize your custom view

        return v;
    }
}