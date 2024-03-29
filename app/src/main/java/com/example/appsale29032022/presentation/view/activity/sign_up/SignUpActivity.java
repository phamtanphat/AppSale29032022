package com.example.appsale29032022.presentation.view.activity.sign_up;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.appsale29032022.R;
import com.example.appsale29032022.common.SpannedCommon;
import com.example.appsale29032022.common.StringCommon;
import com.example.appsale29032022.data.model.User;
import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.presentation.view.activity.sign_in.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText inputName, inputEmail, inputPassword, inputPhone, inputAddress;
    LinearLayout btnSignUp, layoutLoading;
    SignUpViewModel signUpViewModel;
    TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initial();
        setTextRegister();
        observerData();
        event();
    }
    private void event() {
        btnSignUp.setOnClickListener(view -> {
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            String name = inputName.getText().toString();
            String phone = inputPhone.getText().toString();
            String address = inputAddress.getText().toString();

            if (StringCommon.isValidEmail(email) && !password.isEmpty() && !name.isEmpty() && !address.isEmpty() && phone.matches("[+-]?\\d*(\\.\\d+)?")) {
                signUpViewModel.signUp(email, password,name,phone,address);
            }
        });
    }
    private void observerData() {
        signUpViewModel.getResourceUser().observe(this, new Observer<AppResource<User>>() {
            @Override
            public void onChanged(AppResource<User> userAppResource) {
                switch (userAppResource.status) {
                    case SUCCESS:
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.alpha_fade_in, R.anim.alpha_fade_out);
                        break;
                    case LOADING:
                        layoutLoading.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        Toast.makeText(SignUpActivity.this, userAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private void initial() {
        inputName = findViewById(R.id.textEditName);
        inputEmail = findViewById(R.id.textEditEmail);
        inputPassword = findViewById(R.id.textEditPassword);
        inputPhone = findViewById(R.id.textEditPhone);
        inputAddress = findViewById(R.id.textEditLocation);
        btnSignUp = findViewById(R.id.sign_up);
        layoutLoading = findViewById(R.id.layout_loading);
        tvRegister = findViewById(R.id.text_view_login);

        signUpViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SignUpViewModel(SignUpActivity.this);
            }
        }).get(SignUpViewModel.class);
    }

    private void setTextRegister() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("Already have an account? ");
        builder.append(SpannedCommon.setClickColorLink("Login", this, () -> {
            finish();
            overridePendingTransition(R.anim.alpha_fade_in, R.anim.alpha_fade_out);
        }));
        tvRegister.setText(builder);
        tvRegister.setHighlightColor(Color.TRANSPARENT);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

}