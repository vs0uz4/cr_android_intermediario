package virtualsystems.com.br.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        DbHandler handler = new DbHandler(this);
        CRUDHelperImpl db = new CRUDHelperImpl(handler);

        /*
        *
        * Create and findAll
        *
         */

        // Usando For Normal
        /*
          db.create(new Test("Vitor"));
          List<Test> list = db.findAll();

          for (int i = 0; i < list.size(); i++) {
            Toast.makeText(this, list.get(i).getId() + " - " + list.get(i).getName(), Toast.LENGTH_SHORT).show();
          }
        */

        // Usando For in
        // Achei Muito mais Interessante e Legível a Forma a Seguir
        /*
          for (Test tst: list) {
            Toast.makeText(this, tst.getId() + " - " + tst.getName(), Toast.LENGTH_SHORT).show();
          }
        */


        /*
        *
        * findById
        *
         */

        // Test test = db.findById(1);
        // Toast.makeText(this, test.getId() + " - " + test.getName(), Toast.LENGTH_SHORT).show();


        /*
        *
        * update
        *
         */

        //Test test = db.findById(1);
        //test.setName("Vitor Souza");

        //db.update(test);
        //test = db.findById(1);
        //Toast.makeText(this, test.getId() + " - " + test.getName(), Toast.LENGTH_SHORT).show();

        /*
        *
        * delete
        *
         */

        Test test = db.findById(1);
        db.delete(test);

        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);

        mainActivity.putExtra("KEY", 10);

        startActivity(mainActivity);
    }

}
