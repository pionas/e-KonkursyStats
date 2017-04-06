package info.e_konkursy.stats.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Adrian Pionka on 2017-04-05.
 */

public class ImageLoader {
    public static void loadImage(Context context, String url, ImageView iv) {
        Picasso.with(context).load(url).into(iv);
    }
}
