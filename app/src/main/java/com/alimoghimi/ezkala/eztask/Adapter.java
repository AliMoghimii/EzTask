package com.alimoghimi.ezkala.eztask;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Adapter extends RecyclerView.Adapter <Adapter.MyViewHolder> {


        private List<Tasks> tasks ;

    Adapter(List<Tasks> tasks){  // Constructor - saveing arraylist from tasks from input

            if (tasks == null) {

            }
            else
            {
                this.tasks = tasks;
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) { //for Recycle View
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_box_for_rview, viewGroup, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

            myViewHolder.bind(tasks.get(position));  // index ha ro set mekhoonee. to view holder

            // position is a index in list.

        }

        @Override
        public int getItemCount() {
            if (tasks == null) {
                return 0 ;
            }
            else
        return tasks.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder{

            AppCompatTextView  TitleTextView ;
            AppCompatTextView DateTextView ;

            MyViewHolder(View itemView){
                super(itemView);
                TitleTextView = itemView.findViewById(R.id.Title); // ref ing massage txt to xml code.
                DateTextView = itemView.findViewById(R.id.Date);
            }

            public void bind(Tasks tasks){  // field ha ro por mikoneee. toye on bind view holder

                TitleTextView.setText(tasks.getTitle());
                DateTextView.setText(tasks.getDate()); // 6 fields must be added. + one field in the Task class complete

            }
        }
    }




