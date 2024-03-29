package com.example.appsale29032022.common;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import java.util.regex.Pattern;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.appsale29032022.R;
import com.airbnb.lottie.animation.content.Content;
import java.text.DecimalFormat;

public class StringCommon {
    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
        public static String formatCurrency(int number) {
            return new DecimalFormat("#,###").format(number);
    }
}