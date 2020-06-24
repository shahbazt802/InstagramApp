package com.floydd.instagramapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTab extends Fragment {
    private EditText edtprofilename, edtprofilebio, edtprofileprofession, edtprofilehobbies, edtprofilefs;
    private Button btnupdateinfo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTab newInstance(String param1, String param2) {
        ProfileTab fragment = new ProfileTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtprofilename = view.findViewById(R.id.edtProfileName);
        edtprofilebio = view.findViewById(R.id.edtProfileBio);
        edtprofileprofession = view.findViewById(R.id.edtprofileProfession);
        edtprofilehobbies = view.findViewById(R.id.edtProfileHobbies);
        edtprofilefs = view.findViewById(R.id.edtProfilefs);
        btnupdateinfo = view.findViewById(R.id.btnUpdateInfo);
        final ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser.get("ProfileName")==null){
            edtprofilename.setText("");
            edtprofilebio.setText("");
            edtprofileprofession.setText("");
            edtprofilehobbies.setText("");
            edtprofilefs.setText("");

        }
        else {
            edtprofilename.setText(parseUser.get("ProfileName") + "");
            edtprofilebio.setText(parseUser.get("ProfileBio") + "");
            edtprofileprofession.setText(parseUser.get("ProfileProfession") + "");
            edtprofilehobbies.setText(parseUser.get("ProfileHobbies") + "");
            edtprofilefs.setText(parseUser.get("ProfileFavoriteSports") + "");
        }
        btnupdateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("ProfileName", edtprofilename.getText().toString());
                parseUser.put("ProfileBio", edtprofilebio.getText().toString());
                parseUser.put("ProfileProfession", edtprofileprofession.getText().toString());
                parseUser.put("ProfileHobbies", edtprofilehobbies.getText().toString());
                parseUser.put("ProfileFavoriteSports", edtprofilefs.getText().toString());
                final ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("Updading Info");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(), "INFO UPDATED", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });
        return view;
    }


}