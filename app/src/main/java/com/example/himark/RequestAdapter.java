package com.example.himark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder{
    TextView title,category,manager,rdate,state;


    public ViewHolder(View itemView){
        super(itemView);
        title=itemView.findViewById(R.id.title);
        category=itemView.findViewById(R.id.category);
        manager=itemView.findViewById(R.id.manager);
        rdate=itemView.findViewById(R.id.rdate);
        state=itemView.findViewById(R.id.state);
    }

}

public class RequestAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private int resourceId;
    private List<PaymentVO>dataList;

    public RequestAdapter(Context context, int resourceId, List<PaymentVO> dataList){
        this.context=context;
        this.resourceId=resourceId;
        this.dataList=dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(resourceId,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PaymentVO pvo = dataList.get(position);
        holder.title.setText(pvo.getTitle());
        holder.category.setText(pvo.getCategory());
        holder.manager.setText(pvo.getManagerId());
        holder.rdate.setText(pvo.getRdate());
        holder.state.setText(pvo.getState());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

