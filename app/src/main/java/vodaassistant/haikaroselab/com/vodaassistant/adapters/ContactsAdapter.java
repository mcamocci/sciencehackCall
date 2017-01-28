package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.Pojos.ContactsItem;
import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.AssistantActivity;

public class ContactsAdapter extends ArrayAdapter<ContactsItem> {


    private Context context;
    private int resource;
    private List<ContactsItem> contacts;

    public ContactsAdapter(Context context,int resource,List<ContactsItem> objects){

        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.contacts=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactsItem item=contacts.get(position);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(AssistantActivity.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(resource,null);

        TextView name=(TextView)view.findViewById(R.id.contact_name);
        TextView phone=(TextView)view.findViewById(R.id.contact_phone);

        phone.setText(item.getPhone());
        name.setText(item.getName());


        return view;
    }
}
