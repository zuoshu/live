package com.oneguy.live.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oneguy.live.R;
import com.oneguy.live.base.BaseActivity;
import com.oneguy.live.control.LiveTransformation;
import com.oneguy.live.control.Webservice;
import com.oneguy.live.control.callback.ItemListCallback;
import com.oneguy.live.control.callback.RoomCallback;
import com.oneguy.live.control.wsdl.OperationResult;
import com.oneguy.live.model.bean.Item;
import com.oneguy.live.model.bean.Room;
import com.oneguy.live.view.adapter.ProgrammeItemAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;

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

    private Room currentRoom;
    private List<Item> futureItemList;
    ProgrammeItemAdapter itemAdapter;

    String token;

    ExtRoomCallback roomCallback;
    ExtItemListCallback itemListCallback;

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

        loadData();
    }

    private void loadData() {
        loadThumbnail();
    }

    private void loadThumbnail() {
        Webservice.getInstance().getCurrentRoom(token, roomCallback);
    }

    private void loadItems() {
        Webservice.getInstance().getFutureItems(token,itemListCallback);
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
                .resizeDimen(R.dimen.book_cover_width, R.dimen.book_cover_height)
                .centerCrop()
                .into(thumbnailImage);
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
