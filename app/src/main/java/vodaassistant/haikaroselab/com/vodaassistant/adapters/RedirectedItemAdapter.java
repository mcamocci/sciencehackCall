package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.RedirectedItem;


public class RedirectedItemAdapter extends ArrayAdapter<RedirectedItem>{

    private Context context;
    private int resources;
    private List<RedirectedItem> list;

    public RedirectedItemAdapter(Context context, int resources, List<RedirectedItem> objects){

        super(context,resources,objects);
        this.context=context;
        this.resources=resources;
        this.list=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RedirectedItem item=list.get(position);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=(View)inflater.inflate(resources, null);

        TextView phone=(TextView)view.findViewById(R.id.reminder);
        phone.setText(item.getPhone());

        return view;
    }
}
