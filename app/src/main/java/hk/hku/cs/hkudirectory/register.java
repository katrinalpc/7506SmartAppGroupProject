package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hk.hku.cs.hkudirectory.dao.UserDao;
import hk.hku.cs.hkudirectory.entity.User;

public class register extends AppCompatActivity {
    private static final String TAG = "mysql-hkudirectory-register";
    EditText userEmail = null;
    EditText userPassword = null;

    EditText userName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.email_reginput);
        userPassword = findViewById(R.id.password_reginput);
        userName = findViewById(R.id.name_reginput);
    }

    public void register(View view) {
        String userEmail1 = userEmail.getText().toString();
        String userPassword1 = userPassword.getText().toString();
        String userName1 = userName.getText().toString();

        User user = new User();

        user.setUserEmail(userEmail1);
        user.setUserPassword(userPassword1);
        user.setUserName(userName1);
        user.setUserType(1);

        new Thread() {
            public void run() {
                int msg = 0;
                UserDao userDao = new UserDao();
                User u = userDao.findUser(user.getUserEmail());
                if (u != null) {
                    msg = 1;
                }
                else {
                    boolean flag = userDao.register(user);
                    if (flag) {
                        msg = 2;
                    }
                }
                hand.sendEmptyMessage(msg);
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "Register failed!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "This account is already exist, try another one", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), "Register successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("a","register");
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        }
    };
}