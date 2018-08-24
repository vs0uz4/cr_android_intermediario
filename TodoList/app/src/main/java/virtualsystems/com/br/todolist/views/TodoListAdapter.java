package virtualsystems.com.br.todolist.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import virtualsystems.com.br.todolist.R;
import virtualsystems.com.br.todolist.entities.Todo;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewerHolder> {

    private Context ctx;
    private List<Todo> todoList;

    public TodoListAdapter(Context ctx, List<Todo> todoList) {
        this.ctx = ctx;
        this.todoList = todoList;
    }

    @Override
    public TodoViewerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoViewerHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_tasks, parent, false));
    }

    @Override
    public void onBindViewHolder(TodoViewerHolder holder, int pos) {
        holder.bindElements(this.ctx, todoList.get(pos));
    }

    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        }

        return todoList.size();
    }
}
