package com.example.assessment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Subject_Adapter extends RecyclerView.Adapter<Subject_Adapter.Viewholder> {
    public Subject_Adapter(List<Subject_model> subject_modelList) {
        this.subject_modelList = subject_modelList;
    }

    private List<Subject_model> subject_modelList;
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_items,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(subject_modelList.get(position).getImageurl(),subject_modelList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return subject_modelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        private CircleImageView imageView;
        private TextView title;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            title=itemView.findViewById(R.id.title);
        }
        private void setData(String url, String title) {
            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent chapterintent= new Intent(itemView.getContext(),Chapters.class);
                    chapterintent.putExtra("title",title);
                    itemView.getContext().startActivity(chapterintent);
                }
            });
        }
    }

}
