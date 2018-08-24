package virtualsystems.com.br.todolist.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import virtualsystems.com.br.todolist.R;
import virtualsystems.com.br.todolist.entities.Todo;

public class TodoViewerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.todo_title)
    TextView txtTodoTitle;

    @BindView(R.id.todo_description)
    TextView txtTodoDescription;

    @BindView(R.id.todo_id)
    TextView txtTodoId;

    public TodoViewerHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindElements(Context ctx, Todo task) {
        txtTodoId.setId(Integer.parseInt(task.getId().toString()));
        txtTodoTitle.setText(task.getTitle());
        txtTodoDescription.setText(task.getDescription());
    }
}
