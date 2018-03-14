package com.zackmatthews.mvp_sample.views;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zackmatthews.mvp_sample.R;
import com.zackmatthews.mvp_sample.models.GenericItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    private static GenericItem item;
    public @BindView(R.id.tv_details_title) TextView title;
    public @BindView(R.id.details_circleImg) CircleImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        if(item != null){
            refreshLayout();
        }
    }

    public static void setItem(GenericItem _item){
        item = _item;
    }

    protected void refreshLayout(){
        title.setText(item.getTitle());
        Bitmap bmp = item.getImg();
        if(bmp != null){
            img.setImageBitmap(bmp);
        }
        else if(GenericItem.useDefaultImg){
            img.setImageResource(GenericItem.defaultImgRes);
        }
    }

}
