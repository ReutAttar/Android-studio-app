package com.example.owner.android5778_0920_9377_02.controller;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.owner.android5778_0920_9377_02.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    View view;
    private Button phoneButton;
    private Button emailButton;
    private Button navigationButton;
    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_information, container, false);
        emailButton=(Button)view.findViewById(R.id.emailButton);
        phoneButton=(Button)view.findViewById(R.id.phoneButton);
        navigationButton=(Button)view.findViewById(R.id.navigationButton);
        /**
         clicking the mail button opens the option to send e-mail to the company
         the Intent calls all the possible options to do so
         */
        emailButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:"+emailButton.getText().toString()));
                try {
                    startActivity(Intent.createChooser(mailIntent, "Complete the action with:"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        /**
         clicking the phone button opens the option to call to the company
         the Intent calls all the possible options to do so
         */
        phoneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneButton.getText()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        /**
         clicking the navigation button open map in the exact location of our company
         */
        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri location=Uri.parse("geo:0,0?q="+"beit ha-defus 12");
                intent.setData(location);
                startActivity(intent);
            }
        });
        return view;
    }

}
