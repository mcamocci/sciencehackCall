package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.Pojos.FavoritesItem;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class FavoritesItemAdapter extends ArrayAdapter<FavoritesItem>{

    private Context context;
    private int resources;
    private List<FavoritesItem> list;

    public FavoritesItemAdapter(Context context,int resources,List<FavoritesItem> objects){

        super(context,resources,objects);
        this.context=context;
        this.resources=resources;
        this.list=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FavoritesItem item=list.get(position);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=(View)inflater.inflate(resources, null);

        TextView phone=(TextView)view.findViewById(R.id.phone);
        TextView name=(TextView)view.findViewById(R.id.name);

        phone.setText(item.getPhone());
        name.setText(item.getName());

        return view;
    }
}
