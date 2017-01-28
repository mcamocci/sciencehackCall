package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.FavoritesItem;
import vodaassistant.haikaroselab.com.vodaassistant.R;

/**
 * Created by root on 6/15/16.
 */
public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.MyHolder> {

    private Context context;
    private List<FavoritesItem> myList;

    public FavoritesRecyclerAdapter(Context context, List<FavoritesItem> favorites){
        this.myList=favorites;
        this.context=context;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        FavoritesItem item=myList.get(position);
        holder.setNecessaryStuffs(item);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.voda_favorte_item,parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CheckBox checkBox;
        private TextView contactName;
        private TextView contactNumber;

        public MyHolder(View view){
            super(view);
            this.checkBox=(CheckBox)view.findViewById(R.id.checkBox);
            this.contactName=(TextView)view.findViewById(R.id.contact_name);
            this.contactNumber=(TextView)view.findViewById(R.id.contact_phone);

        }

        public void setNecessaryStuffs(FavoritesItem item){
            checkBox.setOnClickListener(this);
            contactName.setText(item.getName());
            contactNumber.setText(item.getPhone());
        }

        @Override
        public void onClick(View v) {

            int position=MyHolder.this.getAdapterPosition();
            FavoritesItem item=myList.get(position);

            //logic for adding only if less than five///

            //logic for removing//

        }
    }
}
