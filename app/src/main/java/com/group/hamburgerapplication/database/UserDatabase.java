package com.group.hamburgerapplication.database;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.group.hamburgerapplication.entity.User;

public class UserDatabase {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private static User user = new User();

    public static void setUser(User user) {
        UserDatabase.user = user;
    }

    public static User getUser() {
        return user;
    }
    public static void writeNewUser(String id, String name, String email, String phone, String address, String password) {
        User user = new User(id, name, email, phone, address, password);
        mDatabase.child("user").child(id).setValue(user);
    }
    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public static boolean checkLogin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null;
    }
//    public static void getUserByEmail(String email){
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user");
//        Query query = usersRef.orderByChild("email").equalTo(email);
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                User user1 = snapshot.getValue(User.class);
//                if(user1!=null){
//
//                    setUser(user1);
//                }
//                else {
//
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//    }
public static void getUserByEmail(String email, Context context, ChildEventListener childEventListener) {
    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user");
    Query query = usersRef.orderByChild("email").equalTo(email);
    query.addChildEventListener(childEventListener);
}

//    public static void getUserByEmail(String email, Context context , ProgressBar progressBar) {
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user");
//        Query query = usersRef.orderByChild("email").equalTo(email);
//        progressBar.setVisibility(View.VISIBLE);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot ) {
//                progressBar.setVisibility(View.INVISIBLE);
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                        String userId = userSnapshot.getKey();
//                        String name = userSnapshot.child("name").getValue(String.class);
//                        String userEmail = userSnapshot.child("email").getValue(String.class);
//                        String phoneNumber = userSnapshot.child("phone").getValue(String.class);
//                        String address = userSnapshot.child("address").getValue(String.class);
//                        String password = userSnapshot.child("password").getValue(String.class);
//                        User user1 = new User(userId,name,userEmail,phoneNumber,address,password);
//                        if(user1.getEmail()!=null){
//                            setUser(user1);
//                        }
//                        // Gọi hàm xử lý thông tin người dùng
//                        // ForgotPasswordActivity forgotPasswordActivity = new ForgotPasswordActivity();
//                        // forgotPasswordActivity.setUser(user);
//                    }
//                } else {
//                    Ultils.displayToast(context, "Email không tồn tại trong hệ thống");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("firebase", "Error getting user by email: " + databaseError.getMessage());
//            }
//        });
//    }

}