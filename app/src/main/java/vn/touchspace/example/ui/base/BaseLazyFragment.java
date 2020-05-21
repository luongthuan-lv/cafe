package vn.touchspace.example.ui.base;

import android.util.Log;

public abstract class BaseLazyFragment extends BaseFragment {

    private static final String TAG = BaseLazyFragment.class.getSimpleName();
    protected boolean isVisible;

    /**
     * to judge if is visible to user
     *
     * @param isVisibleToUser
     * hihi
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            this.isVisible = true;
            onVisible();
        } else {
            this.isVisible = false;
            onInvisible();
        }

    }

    /**
     * when the  fragment is not visible to user
     */
    protected void onInvisible() {
        Log.i(TAG, "onInvisible: ");
    }

    /**
     * when the fragment is visible to user
     * <p>
     * lazyload
     */
    protected void onVisible() {
        Log.i(TAG, "onVisible: ");
        lazyLoad();
    }

    protected abstract void lazyLoad();
}
