package virtualsystems.com.br.todolist.activities;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import virtualsystems.com.br.todolist.R;
import virtualsystems.com.br.todolist.database.CRUDHelper;
import virtualsystems.com.br.todolist.database.CRUDHelperImpl;
import virtualsystems.com.br.todolist.database.DbHandler;
import virtualsystems.com.br.todolist.entities.Todo;

public class AddActivity extends AppCompatActivity {

    private DbHandler handler = null;

    @BindView(R.id.btn_create_task)
    Button btnCreateTask;

    @BindView(R.id.txt_task_title)
    EditText txtTitle;

    @BindView(R.id.txt_task_description)
    EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.handler = new DbHandler(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_task)
    public void save(View view) {
        CRUDHelper db = new CRUDHelperImpl(this.handler);

        try {
            db.create(new Todo(txtTitle.getText().toString(), txtDescription.getText().toString()));
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            Toast.makeText(this, "Task Created!", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
