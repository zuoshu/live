package com.oneguy.live.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oneguy.live.R;
import com.oneguy.live.base.BaseActivity;
import com.oneguy.live.control.util.LiveTransformation;
import com.oneguy.live.control.Webservice;
import com.oneguy.live.control.callback.ItemListCallback;
import com.oneguy.live.control.callback.RoomCallback;
import com.oneguy.live.control.util.MiscUtil;
import com.oneguy.live.control.util.ViewUtil;
import com.oneguy.live.control.wsdl.OperationResult;
import com.oneguy.live.model.bean.Item;
import com.oneguy.live.model.bean.Room;
import com.oneguy.live.view.adapter.ProgrammeItemAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZuoShu on 6/28/16.
 */
public class LiveHallActivity extends BaseActivity {
    @Bind(R.id.item_list)
    public ListView itemListView;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.thumbnail)
    ImageView thumbnailImage;

    @Bind(R.id.thumbnail_image_layout)
    View thumbnailImageLayout;

    private Room currentRoom;
    private List<Item> futureItemList;
    ProgrammeItemAdapter itemAdapter;

    String token;

    ExtRoomCallback roomCallback;
    ExtItemListCallback itemListCallback;

    int thumbnailWidth;
    int thumbnailHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_hall);
    }

    @Override
    protected void onInitialize(Bundle savedInstanceState) {
        super.onInitialize(savedInstanceState);
        token = getIntent().getStringExtra("token");
        itemAdapter = new ProgrammeItemAdapter(this);
        itemListView.setAdapter(itemAdapter);

        roomCallback = new ExtRoomCallback();
        itemListCallback = new ExtItemListCallback();

        calculateSize();
        loadData();
//        goPlayActivity("rtmp://live.hkstv.hk.lxdns.com/live/hks");
    }

    private void calculateSize() {
        thumbnailWidth = (int) (MiscUtil.getScreenWidth(this) * (4.5 / 11.5)
                - getResources().getDimensionPixelSize(R.dimen.thumbnail_layout_margin) * 2
                - getResources().getDimensionPixelSize(R.dimen.thumbnail_content_margin) * 2
        );
        thumbnailHeight = thumbnailWidth;
    }

    private void loadData() {
        loadThumbnail();
    }

    private void loadThumbnail() {
        Webservice.getInstance().getCurrentRoom(token, roomCallback);
    }

    private void loadItems() {
        Webservice.getInstance().getFutureItems(token, itemListCallback);
    }

    private void setRoomData() {
        title.setText(currentRoom.getTitle());
        Transformation coverTransformation = LiveTransformation.newCoverTransformation();
        RequestCreator creator = TextUtils.isEmpty(currentRoom.getThumbnail())
                ? Picasso.with(getContext()).load(R.drawable.image_default_cover)
                : Picasso.with(getContext()).load(currentRoom.getThumbnail());
        creator.placeholder(R.drawable.image_default_cover)
                .error(R.drawable.image_default_cover)
                .transform(coverTransformation)
                .resizeDimen(R.dimen.thumbnail_width, R.dimen.thumbnail_width)
                .centerCrop()
                .into(thumbnailImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        ViewUtil.fixSize(thumbnailImage, thumbnailWidth, thumbnailHeight);
                        ViewUtil.fixSize(thumbnailImageLayout, thumbnailWidth, thumbnailHeight);
                    }

                    @Override
                    public void onError() {
                        ViewUtil.fixSize(thumbnailImage, thumbnailWidth, thumbnailHeight);
                        ViewUtil.fixSize(thumbnailImageLayout, thumbnailWidth, thumbnailHeight);
                    }
                });
    }

    private void goPlayActivity(String url) {
        url = "rtmp://v588109a8.live.126.net/live/7173e16bfadf4fc5bd01aee9e18b8ed6";
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("media_type", "livestream");
        intent.putExtra("decode_type", "software");
        intent.putExtra("videoPath", url);
        startActivity(intent);
    }

    @OnClick(R.id.thumbnail_layout)
    public void onThumbnailClick(View v) {
        goPlayActivity(currentRoom.getUrl());
    }

    private class ExtRoomCallback extends RoomCallback {

        @Override
        public void Starting() {
            showLoading();
        }

        @Override
        protected void onSuccess(Room room) {
            LiveHallActivity.this.currentRoom = room;
            setRoomData();
        }

        @Override
        protected void onFinish(OperationResult result) {
            loadItems();
        }
    }

    private class ExtItemListCallback extends ItemListCallback {

        @Override
        protected void onSuccess(List<Item> itemList) {
            futureItemList = itemList;
            itemAdapter.setData(itemList);
            itemAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onFinish(OperationResult result) {
            super.onFinish(result);
            if (currentRoom == null && (futureItemList == null || futureItemList.size() == 0)) {
                showEmpty();
            } else {
                showContent();
            }
        }
    }
}
