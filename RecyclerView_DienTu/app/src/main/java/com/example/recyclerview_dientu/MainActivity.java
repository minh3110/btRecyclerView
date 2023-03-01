package com.example.recyclerview_dientu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public class data
    {
        public int Src_image;
        public String name;
        public String content;
        public data(int src_image, String name, String content)
        {
            this.Src_image = src_image;
            this.name = name;
            this.content = content;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<data> Data = new ArrayList<>();

        //Thêm dữ liệu
        int id_bus = getResources().getIdentifier("ic_baseline_bus_alert_24", "drawable", getPackageName());
        int id_noi = getResources().getIdentifier("ic_baseline_child_friendly_24", "drawable", getPackageName());
        int id_runner = getResources().getIdentifier("ic_baseline_directions_run_24", "drawable", getPackageName());
        Data.add(new data(id_bus, "Xe buýt", "Xe buýt thì đi nhanh hay dừng giữa các trạm   "));
        Data.add(new data(id_noi, "Xe nôi", "Xe nôi thì đi chậm cần có người đẩy"));
        Data.add(new data(id_runner, "Xe chạy bộ", "Xe này thì lúc nhanh lúc chậm theo sức khỏe của người chạy"));

        MyAdapter adapter = new MyAdapter(Data);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        List<data> mData;

        public MyAdapter(List<data> Data)
        {
            mData = Data;
        }
        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder(v);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            data d = mData.get(position);
            holder.imageView_image.setImageResource(d.Src_image);
            holder.textView_name.setText(d.name);
            holder.textView_content.setText(d.content);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("resID", d.Src_image);
                    bundle.putString("name", d.name);
                    bundle.putString("content", d.content);

                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView_image;
            TextView textView_name;
            TextView textView_content;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView_image = itemView.findViewById(R.id.imageView_image);
                textView_name = itemView.findViewById(R.id.textView_name);
                textView_content = itemView.findViewById(R.id.textView_content);
            }
        }
    }
}