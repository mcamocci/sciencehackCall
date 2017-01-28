package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class MembersItemAdapter extends ArrayAdapter<MemberItem>{

    private Context context;
    private int resources;
    private List<MemberItem> list;

    public MembersItemAdapter(Context context, int resources, List<MemberItem> objects){

        super(context,resources,objects);
        this.context=context;
        this.resources=resources;
        this.list=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MemberItem item=list.get(position);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=(View)inflater.inflate(resources, null);



        TextView phone=(TextView)view.findViewById(R.id.number);
        TextView group=(TextView)view.findViewById(R.id.group);

        phone.setText(item.getPhone());
        group.setText("Group: "+item.getGroup());

        return view;
    }
}
