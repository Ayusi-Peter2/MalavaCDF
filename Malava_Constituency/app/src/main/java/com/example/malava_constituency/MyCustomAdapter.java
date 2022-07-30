package com.example.malava_constituency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder>{
    private final List<Student> student;
    public MyCustomAdapter(List<Student> student)
    {
        this.student=student;

    }


    @NonNull
    @Override
    public MyCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_of_students,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomAdapter.ViewHolder holder, int position) {
        //final Student student=student[position];
        //student=new ArrayList<>();
        holder.name.setText(student.get(position).getName());
        holder.course.setText(student.get(position).getCourse());
        holder.institution.setText(student.get(position).getInsitution());


    }

    @Override
    public int getItemCount() {
        return student.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView course;
        public TextView name;
        ConstraintLayout layout1;
        public TextView institution;

        public ViewHolder(View view)
        {
            super(view);
            course=view.findViewById(R.id.course);
            name= view.findViewById(R.id.name);
            institution= view.findViewById(R.id.institution);
            layout1= view.findViewById(R.id.layout);
        }



    }
}





