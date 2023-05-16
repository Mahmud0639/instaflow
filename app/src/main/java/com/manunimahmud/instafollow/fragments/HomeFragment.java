package com.manunimahmud.instafollow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.adapters.DashboardAdapter;
import com.manunimahmud.instafollow.adapters.StoryAdapter;
import com.manunimahmud.instafollow.databinding.FragmentHomeBinding;
import com.manunimahmud.instafollow.models.DashboardModel;
import com.manunimahmud.instafollow.models.StoryModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private StoryAdapter adapter;
    private List<StoryModel> list;

    private List<DashboardModel> dashboardModels;
    private DashboardAdapter dashboardAdapter;


    public HomeFragment() {
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        list = new ArrayList<>();
        list.clear();

        list.add(new StoryModel(R.drawable.fr4,R.drawable.fr5,"Salam Hossain"));
        list.add(new StoryModel(R.drawable.fr8,R.drawable.fr10,"Fatema Akter"));
        list.add(new StoryModel(R.drawable.fr11,R.drawable.fr12,"Sadik Hossain"));
        list.add(new StoryModel(R.drawable.fr12,R.drawable.fr14,"Nasima Begum"));
        list.add(new StoryModel(R.drawable.fr4,R.drawable.fr5,"Salam Hossain"));
        list.add(new StoryModel(R.drawable.fr8,R.drawable.fr10,"Fatema Akter"));
        list.add(new StoryModel(R.drawable.fr11,R.drawable.fr12,"Sadik Hossain"));
        list.add(new StoryModel(R.drawable.fr12,R.drawable.fr14,"Nasima Begum"));

        binding.storyRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new StoryAdapter(getContext(),list);
        adapter.notifyDataSetChanged();
        binding.storyRV.setAdapter(adapter);

        // Code for dashboard
        dashboardModels = new ArrayList<>();
        dashboardModels.clear();
        dashboardModels.add(new DashboardModel(R.drawable.fr10,R.drawable.fr5,"Mahmud Islam","Android Developer","404","120","97",
                "I am Android Developer and I always try to learn a new thing."));
        dashboardModels.add(new DashboardModel(R.drawable.fr8,R.drawable.fr12,"Nasima Begum","House wife","905","335","93",
                "Nasima Begum is the mother of Mahmudul Islam. She always helps her son and her family always. He always expect everything from her creator."));
        binding.dashBoardRV.setLayoutManager(new LinearLayoutManager(getContext()));
        dashboardAdapter = new DashboardAdapter(getContext(),dashboardModels);
        dashboardAdapter.notifyDataSetChanged();
        binding.dashBoardRV.setAdapter(dashboardAdapter);


        return binding.getRoot();
    }
}