package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import hk.hku.cs.hkudirectory.dao.UserDao;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mysql-hkudirectory-MainActivity";
    TextView  tID, tName, tType, tEmail, tLinkedin, tLocation;
    EditText txt_UserEmail, txt_UserPW;
    Button btn_Login;

    /*
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            String uemail = txt_UserEmail.getText().toString();
            String upassword = txt_UserPW.getText().toString();

            // Redirect to the Home page
            Intent intent = new Intent(getBaseContext(), HomePageActivity.class);
            startActivity(intent);
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        /*
        btn_Login = (Button)findViewById(R.id.login_button);
        txt_UserEmail = (EditText)findViewById(R.id.email_input);
        txt_UserPW = (EditText)findViewById(R.id.password_input);


        btn_Login.setOnClickListener(this);
        */
        //tentative change for developing the homepage

        /* uncomment when need to check variables from database
        tID = (TextView) this.findViewById(R.id.person_id);
        tName = (TextView) this.findViewById(R.id.name);
        tType = (TextView) this.findViewById(R.id.type);
        tEmail = (TextView) this.findViewById(R.id.email);
        tLinkedin = (TextView) this.findViewById(R.id.linkedin);
        tLocation = (TextView) this.findViewById(R.id.location);
         */


        // connectSQL sql = new connectSQL();
        // sql.execute("SELECT * FROM people"); //input query command here

    }

    public void reg(View view) {
        startActivity(new Intent(getApplicationContext(), register.class));
    }

    public void login(View view) {
        EditText EditTextEmail = findViewById(R.id.email_input);
        EditText EditTextPassword = findViewById(R.id.password_input);

        new Thread() {
            public void run() {
                UserDao userDao = new UserDao();
                int msg = userDao.login(EditTextEmail.getText().toString(), EditTextPassword.getText().toString());
                hand1.sendEmptyMessage(msg);
                if (msg == 1) {
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "Login successfully!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3) {
                Toast.makeText(getApplicationContext(), "Account doesn't exist!", Toast.LENGTH_LONG).show();
            }
        }
    };

    /*
    public class connectSQL extends AsyncTask<String, Void, List<Map<String, String>>> {
        private static final String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
        private static final String user = "potatoma";
        private static final String password = "potatoma123";

        List<Map<String, String>> queryResult = new ArrayList<Map<String, String>>(); //queryResult is stored in an arraylist consists of maps


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(params[0]);

                int i = 0;
                queryResult.clear();
                while (rs.next()) {
                    queryResult.add(new HashMap<String, String>());
                    queryResult.get(i).put("ID", rs.getString(1));
                    queryResult.get(i).put("name", rs.getString(2));
                    queryResult.get(i).put("type", rs.getString(3));
                    queryResult.get(i).put("email", rs.getString(4));
                    queryResult.get(i).put("linkedin", rs.getString(5));
                    queryResult.get(i).put("location", rs.getString(6));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResult;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {
            //param: result contains records returned from database
            try {
                //result.get(int x): get the the x_th record
                //try x=0 and x=1 in this demo.

                String ID = result.get(1).get("ID");
                String name = result.get(1).get("name");
                String type = result.get(1).get("type");
                String email = result.get(1).get("email");
                String linkedin = result.get(1).get("linkedin");
                String location = result.get(1).get("location");

                tID.setText(ID);
                tName.setText(name);
                tType.setText(type);
                tEmail.setText(email);
                tLinkedin.setText(linkedin);
                tLocation.setText(location);
                //tID.setText(SQLQueryResult[0].getString(1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    */
}


