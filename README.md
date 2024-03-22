# 7506SmartAppGroupProject
## MySQL Database Related
### Credentials

URL: nuc.hkumars.potatoma.com:3306

Web Management: http://nuc.hkumars.potatoma.com/phpmyadmin

Database name: comp7506

Username: root or potatoma

Password: potatoma123
### Android Implementation
Class ```connectSQL``` is implemented with necessary credentials included. It connects to MySQL database and fetch requested records from it in a new thread, so that no front-end operations are blocked.

#### Usage:

in ```onCreate```:
```
connectSQL sql = new connectSQL(); //instantiate sql object
sql.execute("SQL query here") //for instance, sql.execute("SELECT * FROM people")；
```

in ```onPostExecute```, records returned are stored in ```List<Map<String, String>> result``` param, which is an arraylist containing multiple maps. You might want to implement what to do with records returned in this callback function.

Use ```result.get(index)``` to find certain row, and use ```result.get(index).get(column_name) ```to further navigate to certain attribute.

![](./images/Untitled%202.jpg)
![](./images/Untitled%203.jpg)
