package com.manunimahmud.instafollow.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.databinding.Notification2SampleBinding;
import com.manunimahmud.instafollow.models.CommentModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<CommentModel> list;
    private Context context;

    public CommentAdapter(List<CommentModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification2_sample, parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentModel commentModel = list.get(position);
        holder.binding.profileImage2.setImageResource(commentModel.getImage());
        holder.binding.commentDesc.setText(Html.fromHtml(commentModel.getComment()));
        holder.binding.time.setText(commentModel.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        Notification2SampleBinding binding;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = Notification2SampleBinding.bind(itemView);
        }
    }
}
