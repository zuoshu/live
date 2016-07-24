package com.oneguy.live.control.util;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.oneguy.live.App;
import com.oneguy.live.R;
import com.squareup.picasso.Transformation;


/**
 * Created by ZuoShu on 12/15/15.
 */
public class LiveTransformation {
    public static Transformation newCoverTransformation() {
        return new RoundedTransformationBuilder()
                .borderWidth(App.getInstance().getResources().getDimensionPixelSize(R.dimen.cover_border_width))
                .borderColor(App.getInstance().getResources().getColor(R.color.cover_border_color))
                .cornerRadius(App.getInstance().getResources().getDimension(R.dimen.cover_radius))
                .oval(false)
                .build();
    }

    public static Transformation newAvatarTransformation() {
        return new RoundedTransformationBuilder()
                .borderWidth(App.getInstance().getResources().getDimensionPixelSize(R.dimen.cover_border_width))
                .borderColor(App.getInstance().getResources().getColor(R.color.avatar_border_color))
                .oval(true)
                .build();
    }
}
