package com.example.studentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterStudents extends RecyclerView.Adapter<AdapterStudents.HolderStudent> {

    private List<Student> listStudents;
    private Context context;
    Listener listener;

    public AdapterStudents(Context context, List<Student> students){
        this.context = context;
        listStudents = students;
    }

    public void setListStudents(List<Student> students){
        listStudents = students;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public HolderStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new HolderStudent(inflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStudent holder, final int position) {
        holder.bind(listStudents.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) listener.clickPosition(position);
            }
        });

    }

    public interface Listener{
        void clickPosition(int position);
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }



    public class HolderStudent extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tvFullName;

        public void bind(Student student){
            tvFullName.setText(student.getFirstName()+ " " + student.getLastName());
        }

        public HolderStudent(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_student,parent,false));
            imageView = itemView.findViewById(R.id.image_student);
            tvFullName = itemView.findViewById(R.id.text_full_name);
        }
    }
}
