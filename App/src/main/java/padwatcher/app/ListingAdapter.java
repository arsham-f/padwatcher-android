package padwatcher.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListingAdapter extends ArrayAdapter<Listing>{

    public static class Views {
        TextView title;
        TextView price;
    }
    public ListingAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Listing location = getItem(position);
        Views views;

        if (convertView == null) {
            views = new Views();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listing_list_item, null);
            views.title = (TextView)convertView.findViewById(R.id.title);
            views.price = (TextView)convertView.findViewById(R.id.price);
            convertView.setTag(views);
        } else {
            views = (Views)convertView.getTag();
        }

        views.title.setText(location.displayTitle);
        views.price.setText(location.price);

        return convertView;
    }
}


