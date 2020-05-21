package vn.touchspace.example.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 11/24/2016
 */

public class ComponentUtils {

    private static final String TAG = ComponentUtils.class.getSimpleName();

    public static void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager mLayoutManager) {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.hasFixedSize();
    }


    public static void setUpRecyclerViewNested(Context context, RecyclerView recyclerView, boolean isNested) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.hasFixedSize();
        recyclerView.setNestedScrollingEnabled(isNested);
    }

    public static void setupLinearLayoutRecyclerViewWithNestedScrolling(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.hasFixedSize();
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void setupLinearLayoutHorizontalRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.hasFixedSize();
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void setupGridLayoutRecyclerView(Context context, RecyclerView recyclerView, int spanCount) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.hasFixedSize();
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void setUnderLineTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void setUpSpinner(Context context, List<String> list, Spinner spinner) {
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, list);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(genderAdapter);
    }

    public static void setUpPopupMenu(Context context, PopupMenu popup, View anchorView, @MenuRes int menuRes) {
        //Creating the instance of PopupMenu
        popup = new PopupMenu(context, anchorView);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());
    }

}
