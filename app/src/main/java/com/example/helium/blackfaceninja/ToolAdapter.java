package com.example.helium.blackfaceninja;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by helium on 2018/6/21.
 */

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ViewHolder> implements Jiek{

    private List<Tool> mToolList;


    public void J(String j) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View toolView;
        ImageView toolImage;
        TextView toolName;

        public ViewHolder(View view){
            super(view);
            toolView = view;
            toolImage = (ImageView) view.findViewById(R.id.tool_image);
            toolName = (TextView) view.findViewById(R.id.tool_name);
        }
    }

    public ToolAdapter(List<Tool> toolList){
        mToolList = toolList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tool_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.toolView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Tool tool = mToolList.get(position);
                //Toast.makeText(v.getContext(),"name"+tool.getName(), Toast.LENGTH_SHORT).show();
                //MainActivity.Jump(tool.getName());
                //Intent intent = new Intent(MainActivity.this,PhoneControlActivity.class);
                //startActivity(intent);
            }
        });

        holder.toolImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Tool tool = mToolList.get(position);
                Toast.makeText(v.getContext(),"image"+tool.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tool tool = mToolList.get(position);
        holder.toolImage.setImageResource(tool.getImageId());
        holder.toolName.setText(tool.getName());
    }

    @Override
    public int getItemCount() {
        return mToolList.size();
    }

}

//public class ToolAdapter extends ArrayAdapter<Tool> {
//
//    private int resourceId;
//
//    public ToolAdapter(Context context, int textViewResourceId, List<Tool> objects) {
//        super(context,textViewResourceId, objects);
//        resourceId = textViewResourceId;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent){
//        Tool tool = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        ImageView toolImage = (ImageView)view.findViewById(R.id.tool_image);
//        TextView toolName = (TextView) view.findViewById(R.id.tool_name);
//        toolImage.setImageResource(tool.getImageId());
//        toolName.setText(tool.getName());
//        return view;
//    }
//
//}

