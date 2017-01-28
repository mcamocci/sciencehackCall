package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Random;

import vodaassistant.haikaroselab.com.vodaassistant.staticHelpers.Colors;
import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.ServiceItem;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.AssistantActivity;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.FavoritesActivity;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.InformationManagerActivity;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.RedirectedActivity;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.StatusActivity;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.TextPlanActivity;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskItemViewHolder> {
    private Context context;
    private List<ServiceItem> list;

    public TaskAdapter(Context context, List<ServiceItem> list){

        this.context=context;
        this.list=list;
    }

    @Override
    public TaskAdapter.TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
        TaskItemViewHolder holder=new TaskItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.TaskItemViewHolder holder, int position) {
        ServiceItem item=list.get(position);
        holder.setTaskItemViewHolderItems(item);
        holder.setClick();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView taskName;
        private LinearLayout container;
        private TextView taskDetails;
        private TextView taskLetter;
        private Context context;
        private View bigView;
        private LinearLayout circleView;

        public TaskItemViewHolder(View view){
            super(view);
            this.taskName=(TextView)view.findViewById(R.id.name);
            this.taskDetails=(TextView)view.findViewById(R.id.detail);
            this.taskLetter=(TextView)view.findViewById(R.id.letter);
            this.container=(LinearLayout)view.findViewById(R.id.container);
            this.context=view.getContext();
            this.circleView=(LinearLayout)view.findViewById(R.id.circle_view_background);
            this.bigView =view;

        }

        public void setClick(){
            this.bigView.setOnClickListener(this);
            this.taskLetter.setOnClickListener(this);
            this.taskDetails.setOnClickListener(this);
            this.taskName.setOnClickListener(this);
            this.container.setOnClickListener(this);
        }
        public void setTaskItemViewHolderItems(ServiceItem item){
                 GradientDrawable gradientDrawable=(GradientDrawable)circleView.getBackground();
                int randomInt =new Random().nextInt(18);
                gradientDrawable.setColor(Color.parseColor(Colors.colorsMaterial[randomInt]));
                this.taskLetter.setText(item.getName().substring(0,1).toUpperCase());
                this.taskName.setText(item.getName().toUpperCase());
                this.taskDetails.setText(item.getDetail());
        }
        @Override
        public void onClick(View v) {
            int position=TaskAdapter.TaskItemViewHolder.this.getAdapterPosition();
            ServiceItem item=list.get(position);
            if (item.getName().equalsIgnoreCase("SMS plan")) {
                Intent intent = new Intent(context, TextPlanActivity.class);
                context.startActivity(intent);
            } else if (item.getName().equalsIgnoreCase("my excuse")) {
                Intent intent = new Intent(context,StatusActivity.class);
                context.startActivity(intent);
            } else if (item.getName().equalsIgnoreCase("assistant")) {
                Intent intent = new Intent(context, AssistantActivity.class);
                context.startActivity(intent);
            } else if (item.getName().equalsIgnoreCase("Redirected")) {
                Intent intent = new Intent(context, RedirectedActivity.class);
                context.startActivity(intent);
            } else if (item.getName().equalsIgnoreCase("favorites")) {
                Intent intent = new Intent(context, FavoritesActivity.class);
                context.startActivity(intent);
            } else if (item.getName().equalsIgnoreCase("info passer")) {
                Intent intent = new Intent(context, InformationManagerActivity.class);
                context.startActivity(intent);
            }
        }


    }
}
