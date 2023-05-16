package com.manunimahmud.instafollow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.adapters.CommentAdapter;
import com.manunimahmud.instafollow.databinding.FragmentNotification2Binding;
import com.manunimahmud.instafollow.models.CommentModel;

import java.util.ArrayList;
import java.util.List;

public class Notification2Fragment extends Fragment {
    FragmentNotification2Binding binding;
    private CommentAdapter adapter;
    private List<CommentModel> list;


    public Notification2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotification2Binding.inflate(inflater, container, false);

        list = new ArrayList<>();
        list.clear();
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        list.add(new CommentModel(R.drawable.my_image,"<b>Sabbir Ahmed</b> mentioned you in a comment","3 minutes ago"));
        list.add(new CommentModel(R.drawable.my_image3,"<b>A.J.M Joha</b> mentioned you in a comment","1 hour ago"));
        list.add(new CommentModel(R.drawable.my_image,"<b>Jubair Hossain</b> mentioned you in a comment","7 minutes ago"));
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        list.add(new CommentModel(R.drawable.my_image2,"<b>Jannatul Ferdous</b> mentioned you in a comment","just now"));
        binding.notificationRV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.notificationRV.setHasFixedSize(true);
        adapter = new CommentAdapter(list,getContext());
        adapter.notifyDataSetChanged();
        binding.notificationRV.setAdapter(adapter);

        return binding.getRoot();
    }
}