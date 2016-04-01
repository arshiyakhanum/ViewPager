package com.arshiya.viewpager;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by akhanumx on 3/28/2016.
 */
public class SlideFragment extends Fragment {

    private static final String TAG = SlideFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.view_pager_item, container, false);
        int pos = getArguments().getInt("slide_num");
        updateUI(pos, view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                Toast.makeText(getActivity().getApplicationContext(),  "Clicked slide " + tag, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void updateUI(int pos, View view) {
        Log.d(TAG, "pos : " + pos);
        String message = "";
        Drawable drawable = getResources().getDrawable(R.drawable.rectangle_amber);
        int tag = 0;

        switch (pos) {
            case 0:
                message = "Slide 0";
                tag = 0;
                drawable = getResources().getDrawable(R.drawable.rectangle_amber);
                break;

            case 1:
                message = "Slide 1";
                tag = 1;
                drawable = getResources().getDrawable(R.drawable.rectangle_blue);
                break;

            case 2:
                message = "Slide 2";
                tag = 2;
                drawable = getResources().getDrawable(R.drawable.rectangle_cyan);
                break;

            case 3:
                message = "Slide 3";
                tag = 3;
                drawable = getResources().getDrawable(R.drawable.rectangle_red);
                break;

            case 4:
                message = "Slide 4";
                tag = 4;
                drawable = getResources().getDrawable(R.drawable.rectangle_green);
                break;

            default:
                Log.d(TAG, "default");
        }

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(message);

        view.setTag(tag);

//        ImageView screen = (ImageView) view.findViewById(R.id.screen);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.view_pager_item_holder);
        relativeLayout.setBackground(drawable);

    }

    public static SlideFragment newInstance(int pos) {
        SlideFragment slideFragment = new SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("slide_num", pos);
        slideFragment.setArguments(bundle);
        return slideFragment;
    }
}
