package vodaassistant.haikaroselab.com.vodaassistant.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.staticHelpers.Colors;
import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.ServiceItem;


public class ServiceItemAdapter extends ArrayAdapter<ServiceItem> {

    private Context context;
    private List<ServiceItem> statusItemList;

    private int resource;

    public ServiceItemAdapter(Context context, int resource, List<ServiceItem> serviceItemList){

        super(context,resource, serviceItemList);
        this.context=context;
        this.resource=resource;
        this.statusItemList = serviceItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ServiceItem serviceItem = statusItemList.get(position);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(resource, null);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView detail=(TextView)view.findViewById(R.id.detail);
        TextView letter=(TextView)view.findViewById(R.id.letter);
        LinearLayout lview=(LinearLayout)view.findViewById(R.id.circle_view_background);

        GradientDrawable drawable=(GradientDrawable)lview.getBackground();


        if(serviceItem.getName().equalsIgnoreCase("sms plan")){

        letter.setText("P");

          drawable.setColor(Color.parseColor(Colors.success));

        }else if(serviceItem.getName().equalsIgnoreCase("redirected")){

            letter.setText("R");
            drawable.setColor(Color.parseColor(Colors.themeColor));

        }else if(serviceItem.getName().equalsIgnoreCase("assistant")){

            letter.setText("A");
            drawable.setColor(Color.parseColor(Colors.info));

        }else if(serviceItem.getName().equalsIgnoreCase("my excuse")){

            letter.setText("E");
            drawable.setColor(Color.parseColor(Colors.danger));

        }else if(serviceItem.getName().equalsIgnoreCase("favorites")){

            letter.setText("F");
            drawable.setColor(Color.parseColor(Colors.colorFin));


        }else if(serviceItem.getName().equalsIgnoreCase("info passer")){

            letter.setText("I");
            drawable.setColor(Color.parseColor(Colors.warning));

        }

        name.setText(serviceItem.getName().toUpperCase());
        detail.setText(serviceItem.getDetail());

        return view;
    }

    /**
     * Created by root on 1/7/16.
     */
    public static class PayInfo {

        private String payer;
        private String amount;
        private String date;
        private String receipt;

        public PayInfo(String payer, String amount, String date, String receipt){
            this.amount=amount;
            this.payer=payer;
            this.date=date;
            this.receipt=receipt;
        }

    }
}
