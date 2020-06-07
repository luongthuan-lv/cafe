package truong.myapp.catam.utils.recycler;

import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by GNUD on 07/09/2017.
 */

public class SetupRvUtils {

    public static void setupLinearLayoutRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void setupLinearLayoutHorizontalRecyclerView(Context context, RecyclerView recyclerView) {
        // setLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
//        recyclerView.setNestedScrollingEnabled(false);

        SnapHelper startSnapHelper = new StartSnapHelper();
        recyclerView.setOnFlingListener(null);
        startSnapHelper.attachToRecyclerView(recyclerView);
    }

    public static void setupLinearLayoutWithDividerRecyclerView(Context context, RecyclerView recyclerView) {
        // setLayoutManager

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration DividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                llm.getOrientation());
        recyclerView.addItemDecoration(DividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(llm);
    }

    public static void setupStaggeredGridRecyclerView(RecyclerView recyclerView, int column) {
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public static void setupGridLayoutRecyclerView(Context context, RecyclerView recyclerView, int column) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, column);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
    }

//    public static void runAnimationFromRight(final RecyclerView recyclerView) {
//        if (recyclerView != null) {
//            final Context context = recyclerView.getContext();
//            final LayoutAnimationController controller =
//                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_rignt);
//
//            recyclerView.setLayoutAnimation(controller);
//            recyclerView.getAdapter().notifyDataSetChanged();
//            recyclerView.scheduleLayoutAnimation();
//        }
//    }
//
//    public static void runAnimationBottom(final RecyclerView recyclerView) {
//        if (recyclerView != null) {
//            final Context context = recyclerView.getContext();
//            final LayoutAnimationController controller =
//                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);
//
//            recyclerView.setLayoutAnimation(controller);
//            recyclerView.getAdapter().notifyDataSetChanged();
//            recyclerView.scheduleLayoutAnimation();
//        }
//    }
}
