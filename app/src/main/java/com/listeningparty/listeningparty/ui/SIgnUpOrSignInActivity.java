package com.listeningparty.listeningparty.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.listeningparty.listeningparty.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SIgnUpOrSignInActivity extends Activity {

    @BindView(R.id.activity_auth_fullname)
    EditText fullname;

    @BindView(R.id.activity_auth_password_confirmation)
    EditText passwordConfirmation;

    @BindView(R.id.activity_auth_submit)
    Button submitButton;

    @BindView(R.id.activity_auth_forgotpassword)
    TextView forgotPassword;

    @BindView(R.id.activity_auth_orsocialmedia)
    TextView orSocialAuth;

    @BindView(R.id.activity_auth_signin_title)
    TextView signInTitle;

    @BindView(R.id.activity_auth_signup_title)
    TextView signUpTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_sign_in);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.deep_purple_500));
    }


    @OnClick({R.id.activity_auth_signup_title, R.id.activity_auth_signin_title})
    public void toggleSignInUp(TextView textView) {
        if (textView.getText().equals("SIGN IN")) {

            signInTitle.setTextColor(getResources().getColor(R.color.colorAccent));
            signUpTitle.setTextColor(getResources().getColor(R.color.darker_gray));
            submitButton.setText(R.string.sign_in);
            orSocialAuth.setText(R.string.or_social_signin);
            fullname.setVisibility(View.GONE);
            passwordConfirmation.setVisibility(View.GONE);
            forgotPassword.setVisibility(View.VISIBLE);

        } else if (textView.getText().equals("SIGN UP")) {

            signUpTitle.setTextColor(getResources().getColor(R.color.colorAccent));
            signInTitle.setTextColor(getResources().getColor(R.color.darker_gray));
            submitButton.setText(R.string.sign_up);
            orSocialAuth.setText(R.string.or_social_signup);
            fullname.setVisibility(View.VISIBLE);
            passwordConfirmation.setVisibility(View.VISIBLE);
            forgotPassword.setVisibility(View.GONE);

        }
    }

    @OnClick(R.id.activity_auth_submit)
    public void onSubmit(){
        startActivity(new Intent(this, MainActivity.class));
    }

}
