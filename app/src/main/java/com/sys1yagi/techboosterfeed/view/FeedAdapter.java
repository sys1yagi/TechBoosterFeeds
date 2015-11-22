package com.sys1yagi.techboosterfeed.view;

import com.sys1yagi.techboosterfeed.databinding.ViewFeedBinding;
import com.sys1yagi.techboosterfeed.model.Feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FeedAdapter extends ArrayAdapter<Feed> {

    public FeedAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initializeViewIfNeeded(convertView);

        Feed feed = getItem(position);
        ViewFeedBinding binding = (ViewFeedBinding) convertView.getTag();
        binding.title.setText(feed.getTitle());
        binding.description.setText(feed.getDescription());

        return convertView;
    }

    View initializeViewIfNeeded(View convertView) {
        if (convertView == null) {
            ViewFeedBinding binding = ViewFeedBinding.inflate(LayoutInflater.from(getContext()));
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }
        return convertView;
    }
}
