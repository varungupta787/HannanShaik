package com.example.sample.test;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sample on 06-03-2016.
 */
public class FragmentClass extends Fragment implements View.OnClickListener {
    boolean in = false;
    ImageView phone, contact, msg;
    private Context mContext;
    private Data mData;
    private String TAG = "FragmentClass";
    private RecyclerView recyclerView;
    private AdapterClass adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        setRetainInstance(true);
        Log.e(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        String jsonData = getJSONData();
        fillData(jsonData);
        adapter = new AdapterClass(getActivity(), mData.getViewData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(mData.getName());

        phone = (ImageView) view.findViewById(R.id.phone);
        phone.setOnClickListener(this);
        msg = (ImageView) view.findViewById(R.id.message);
        msg.setOnClickListener(this);
        contact = (ImageView) view.findViewById(R.id.contact);
        contact.setOnClickListener(this);

        return view;
    }

    private void fillData(String jsonData) {
        if (jsonData.isEmpty()) {
            return;
        }
        mData = new Data();
        try {
            JSONObject rootObject = new JSONObject(jsonData);
            Log.e(TAG, "fillData : " + this.getClass().getSimpleName() + "rootObject Size: " + rootObject.length());

            mData.setName((String) rootObject.get("name"));
            mData.setPhoneNumber((String) rootObject.get("phone"));
            mData.setPhotoUrl((String) rootObject.get("photo"));
            mData.setEmail((String) rootObject.get("email"));
            mData.setContactUrl((String)rootObject.get("contact_url"));

            if (mData.getPhotoUrl() != null) {
                Data.ViewData viewData = mData.getViewDataInstance();
                viewData.setImageUrl(mData.getPhotoUrl());
                mData.getViewData().add(viewData);
            }

            JSONArray contentObj = (JSONArray) rootObject.getJSONArray("our_story");

            JSONObject jsonObject;
            Data.ViewData viewData;

            for (int i = 0; i < contentObj.length(); i++) {

                jsonObject = (JSONObject) contentObj.get(i);
                viewData = mData.getViewDataInstance();

                viewData.setType((String) jsonObject.get("type"));
                viewData.setTitle((String) jsonObject.get("title"));

                if (jsonObject.has("content")) {
                    viewData.setContent((String) jsonObject.get("content"));
                }
                if (jsonObject.has("image_url")) {
                    viewData.setImageUrl((String) jsonObject.get("image_url"));
                }
                if (jsonObject.has("location_url")) {
                    viewData.setLocationUrl((String) jsonObject.get("location_url"));
                }
                if (jsonObject.has("more_images")) {
                    viewData.setMoreImageURl((String) jsonObject.get("more_images"));
                }

                mData.getViewData().add(viewData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJSONData() {
        String jsonData = "";
        try {
            InputStream is = getActivity().getAssets().open("sample_response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonData;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.equals(phone)) {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + mData.getPhoneNumber()));
            startActivity(intent);
        } else if (v.equals(msg)) {
            Uri uri = Uri.parse("smsto:" + mData.getPhoneNumber());
            intent = new Intent(Intent.ACTION_SENDTO, uri);
            startActivity(intent);
        } else if (v.equals(contact)) {
            intent = new Intent(Intent.ACTION_INSERT);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, mData.getName());
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mData.getPhoneNumber());
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, mData.getEmail());
            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, mData.getContactUrl());
            startActivity(intent);
        }
    }
}
