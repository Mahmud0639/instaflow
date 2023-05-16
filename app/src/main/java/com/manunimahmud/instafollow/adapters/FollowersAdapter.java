package com.manunimahmud.instafollow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.databinding.FollowersSampleBinding;
import com.manunimahmud.instafollow.models.FollowersModel;

import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder> {
    private Context context;
    private List<FollowersModel> list;

    public FollowersAdapter(Context context, List<FollowersModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.followers_sample, parent,false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerViewHolder holder, int position) {
        FollowersModel model = list.get(position);
        holder.binding.followerPic.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FollowerViewHolder extends RecyclerView.ViewHolder {
        FollowersSampleBinding binding;

        public FollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = FollowersSampleBinding.bind(itemView);
        }
    }
}
