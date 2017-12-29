package com.mk.kilikili.module.common.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mk.kilikili.R;
import com.mk.kilikili.base.AppBarActivity;
import com.mk.kilikili.module.common.presenter.LoginPresenter;
import com.mk.kilikili.module.MainActivity;
import com.mk.kilikili.utils.CommonUtil;
import com.mk.kilikili.utils.PreferenceUtil;
import com.mk.kilikili.utils.ToastUtil;
import com.mk.kilikili.utils.manager.C;
import com.mk.kilikili.utils.manager.NavigationManager;
import com.mk.mvpbase.factory.RequiresPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mr_468 on 2017/3/17.
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends AppBarActivity<LoginPresenter> {

    @BindView(R.id.iv_icon_left)
    ImageView mLeftLogo;

    @BindView(R.id.iv_icon_right)
    ImageView mRightLogo;

    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_password)
    EditText et_password;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getBarLayoutId() {
        return 0;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mLeftLogo.setImageResource(R.drawable.ic_22_hide);
                mRightLogo.setImageResource(R.drawable.ic_33_hide);
            }
        });
        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mLeftLogo.setImageResource(R.drawable.ic_22);
                mRightLogo.setImageResource(R.drawable.ic_33);
            }
        });
    }


    @Override
    public void initToolBar() {
        super.initToolBar();
        mToolbar.setTitle("登录");
    }


    @OnClick(R.id.btn_login)
    void startLogin() {

        boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
        if (!isNetConnected) {
            ToastUtil.ShortToast("当前网络不可用,请检查网络设置");
            return;
        }
        login();
    }

    private void login() {

        String name = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.ShortToast("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.ShortToast("密码不能为空");
            return;
        }

        PreferenceUtil.putBoolean(C.KEY, true);
        NavigationManager.overlay(LoginActivity.this, MainActivity.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
