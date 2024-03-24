package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    TextView  tID, tName, tType, tEmail, tLinkedin, tLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* uncomment when need to check variables from database
        tID = (TextView) this.findViewById(R.id.person_id);
        tName = (TextView) this.findViewById(R.id.name);
        tType = (TextView) this.findViewById(R.id.type);
        tEmail = (TextView) this.findViewById(R.id.email);
        tLinkedin = (TextView) this.findViewById(R.id.linkedin);
        tLocation = (TextView) this.findViewById(R.id.location);
        */

        connectSQL sql = new connectSQL();
        sql.execute("SELECT * FROM people"); //input query command here
    }


    public class connectSQL extends AsyncTask<String, Void, List<Map<String, String>>> {
        private static final String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
        private static final String user = "potatoma";
        private static final String password = "potatoma123";

        List<Map<String, String>> queryResult = new ArrayList<Map<String, String>>(); //queryResult is stored in an arraylist consists of maps


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(params[0]);

                int i = 0;
                queryResult.clear();
                while (rs.next())
                {
                    queryResult.add(new HashMap<String, String>());
                    queryResult.get(i).put("ID", rs.getString(1));
                    queryResult.get(i).put("name", rs.getString(2));
                    queryResult.get(i).put("type", rs.getString(3));
                    queryResult.get(i).put("email", rs.getString(4));
                    queryResult.get(i).put("linkedin", rs.getString(5));
                    queryResult.get(i).put("location", rs.getString(6));
                    i++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return queryResult;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result)
        {
            //param: result contains records returned from database
            try
            {
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
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}


