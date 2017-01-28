package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.Group;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;
import vodaassistant.haikaroselab.com.vodaassistant.R;


public class GroupsItemAdapter extends ArrayAdapter<Group>{

    private Context context;
    private int resources;
    private ArrayList<Group> list=null;

    public GroupsItemAdapter(Context context, int resources, ArrayList<Group> objects){

        super(context,resources,objects);
        this.context=context;
        this.resources=resources;
        this.list=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Group group=list.get(position);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=(View)inflater.inflate(resources, null);

        ArrayList<MemberItem> memberItems=new DatabaseHelper(context).getGroupMember(group.getName().toLowerCase());
        int counts=memberItems.size();
        TextView groupName=(TextView)view.findViewById(R.id.name);
        TextView groupSize=(TextView)view.findViewById(R.id.count);
        groupName.setText(group.getName());
        groupSize.setText("Members Count: "+counts);

        return view;
    }
}
