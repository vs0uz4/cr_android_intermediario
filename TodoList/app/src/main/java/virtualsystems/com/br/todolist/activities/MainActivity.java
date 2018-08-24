package virtualsystems.com.br.todolist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import virtualsystems.com.br.todolist.R;
import virtualsystems.com.br.todolist.database.CRUDHelper;
import virtualsystems.com.br.todolist.database.CRUDHelperImpl;
import virtualsystems.com.br.todolist.database.DbHandler;
import virtualsystems.com.br.todolist.entities.Todo;
import virtualsystems.com.br.todolist.views.TodoListAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Todo> tasks = new ArrayList<Todo>();
    private TodoListAdapter todoAdapter;

    private DbHandler handler = null;
    private CRUDHelper db;

    @BindView(R.id.fab_new_task)
    FloatingActionButton btnFabNewTask;

    @BindView(R.id.todo_list)
    RecyclerView rvTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.handler = new DbHandler(this);
        this.db = new CRUDHelperImpl(this.handler);

        ButterKnife.bind(this);
        populateRecyclerView();
    }

    @OnClick(R.id.fab_new_task)
    public void onClick(View view) {
        Intent addTask = new Intent(this, AddActivity.class);
        startActivity(addTask);
    }

    private void populateRecyclerView() {
        tasks = db.findAll();
        todoAdapter = new TodoListAdapter(this, tasks);
        rvTodoList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTodoList.setItemAnimator(new DefaultItemAnimator());
        rvTodoList.setAdapter(todoAdapter);
        setItemAnimation();
    }

    private void setItemAnimation(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteTask(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvTodoList);
    }

    private void deleteTask(final int pos) {
        new AlertDialog.Builder(this)
                .setTitle("TodoList")
                .setMessage("Delete Task?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int btn) {
                        dialog.dismiss();
                        deleteTaskFromDatabase(pos);
                    }
                })
                .setNegativeButton("No", ((dialog, btn) -> {
                    dialog.dismiss();
                    populateRecyclerView();
                }))
                .show();
    }

    private void deleteTaskFromDatabase(int pos) {
        Todo task = tasks.get(pos);
        this.db.delete(task);
        tasks.remove(pos);
        populateRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
