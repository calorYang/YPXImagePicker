package com.ypx.imagepicker.activity.multi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.R;
import com.ypx.imagepicker.bean.PickerSelectConfig;
import com.ypx.imagepicker.bean.PickerUiConfig;
import com.ypx.imagepicker.data.MultiPickerData;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;
import com.ypx.imagepicker.helper.launcher.PLauncher;
import com.ypx.imagepicker.presenter.IMultiPickerBindPresenter;
import com.ypx.imagepicker.utils.PStatusBarUtil;

import java.util.ArrayList;

/**
 * Description: 多选页
 * <p>
 * Author: peixing.yang
 * Date: 2019/2/21
 */
public class MultiImagePickerActivity extends FragmentActivity {
    public static final String INTENT_KEY_SELECT_CONFIG = "PickerSelectConfig";
    public static final String INTENT_KEY_UI_CONFIG = "IMultiPickerBindPresenter";
    public static final String INTENT_KEY_CURRENT_INDEX = "currentIndex";
    public static final String INTENT_KEY_CURRENT_IMAGE = "currentImage";
    public static final int REQ_CAMERA = 1431;

    public static void intent(Activity activity,
                              PickerSelectConfig selectConfig,
                              IMultiPickerBindPresenter presenter,
                              final OnImagePickCompleteListener listener) {
        Intent intent = new Intent(activity, MultiImagePickerActivity.class);
        intent.putExtra(MultiImagePickerActivity.INTENT_KEY_SELECT_CONFIG, selectConfig);
        intent.putExtra(MultiImagePickerActivity.INTENT_KEY_UI_CONFIG, presenter);
        PLauncher.init(activity).startActivityForResult(intent, new PLauncher.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                if (resultCode == ImagePicker.REQ_PICKER_RESULT_CODE &&
                        data.hasExtra(ImagePicker.INTENT_KEY_PICKERRESULT) && listener != null) {
                    ArrayList list = (ArrayList) data.getSerializableExtra(ImagePicker.INTENT_KEY_PICKERRESULT);
                    listener.onImagePickComplete(list);
                    MultiPickerData.instance.clear();
                }
            }
        });
    }

    private MultiImagePickerFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker_activity_fragment_wrapper);
        if (getIntent() == null || !getIntent().hasExtra(INTENT_KEY_SELECT_CONFIG)
                || !getIntent().hasExtra(INTENT_KEY_UI_CONFIG)) {
            finish();
            return;
        }
        PickerSelectConfig selectConfig = (PickerSelectConfig) getIntent().getSerializableExtra(INTENT_KEY_SELECT_CONFIG);
        IMultiPickerBindPresenter presenter = (IMultiPickerBindPresenter) getIntent().getSerializableExtra(INTENT_KEY_UI_CONFIG);
        if (selectConfig == null || presenter == null) {
            finish();
            return;
        }
        View mStatusBar = findViewById(R.id.mStatusBar);
        PickerUiConfig uiConfig = presenter.getUiConfig(this);
        if (uiConfig != null && uiConfig.isImmersionBar()) {
            mStatusBar.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mStatusBar.getLayoutParams();
            params.height = PStatusBarUtil.getStatusBarHeight(this);
            mStatusBar.setBackgroundColor(uiConfig.getTitleBarBackgroundColor());
            PStatusBarUtil.setStatusBar(this, Color.TRANSPARENT, true,
                    PStatusBarUtil.isDarkColor(uiConfig.getTitleBarBackgroundColor()));
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
        fragment = ImagePicker.withMultiFragment(presenter)
                .setPickerSelectConfig(selectConfig)
                .pickWithFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (fragment != null && fragment.isImageSetShow()) {
            return;
        }
        super.onBackPressed();
        MultiPickerData.instance.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fragment != null) {
            fragment.onTakePhotoResult(requestCode, resultCode);
        }
    }
}
