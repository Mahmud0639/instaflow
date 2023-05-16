package com.manunimahmud.instafollow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.databinding.DashboardRvBinding;
import com.manunimahmud.instafollow.models.DashboardModel;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {
    private Context context;
    private List<DashboardModel> list;

    public DashboardAdapter(Context context, List<DashboardModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_rv,parent,false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        DashboardModel model = list.get(position);
        holder.binding.profilem5.setImageResource(model.getUserImage());
        holder.binding.textName.setText(model.getUserName());
        holder.binding.profession.setText(model.getProfession());
        holder.binding.imageView1.setImageResource(model.getPostImage());
        holder.binding.description.setText(model.getDescription());
        holder.binding.like.setText(model.getLike());
        holder.binding.comment.setText(model.getComment());
        holder.binding.share.setText(model.getShare());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {
        DashboardRvBinding binding;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DashboardRvBinding.bind(itemView);
        }
    }
}
