package com.group.hamburgerapplication.ultil;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group.hamburgerapplication.common.Validate;
import com.group.hamburgerapplication.database.UserDatabase;
import com.group.hamburgerapplication.entity.User;


public class FuncHelper {
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static boolean validateEmail(String a, Context context) {

        if (Validate.CheckNull(a)) {
            Toast.makeText(context, "Vui lòng không bỏ trống email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (Validate.checkEmail(a)) {
                Toast.makeText(context, "Vui lòng nhập email hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public static boolean validateName(String a, Context context) {
        if (Validate.CheckNull(a)) {
            Toast.makeText(context, "Vui lòng không bỏ trống tên", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String a, Context context) {
        if (Validate.CheckNull(a)) {
            Toast.makeText(context, "Vui lòng không bỏ trống địa chỉ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String a, Context context) {
        if (Validate.CheckNull(a)) {
            Toast.makeText(context, "Vui lòng không bỏ trống mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (Validate.checkPassword(a)) {
                Toast.makeText(context, "Vui lòng nhập mật khẩu trên 8 ký tự", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public static boolean validatePhone(String a, Context context) {
        if (Validate.CheckNull(a)) {
            Toast.makeText(context, "Vui lòng không bỏ trống số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (Validate.checkPhone(a)) {
                Toast.makeText(context, "Vui lòng nhập số điện thoại hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
