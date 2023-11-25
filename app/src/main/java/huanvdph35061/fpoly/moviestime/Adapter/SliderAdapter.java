package huanvdph35061.fpoly.moviestime.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import huanvdph35061.fpoly.moviestime.R;
import huanvdph35061.fpoly.moviestime.model.Slide;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<Slide> mlist;

    public SliderAdapter(Context context, List<Slide> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item,null);

        ImageView SlideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_text);
        SlideImg.setImageResource(mlist.get(position).getImage());
        slideText.setText(mlist.get(position).getTitle());

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
