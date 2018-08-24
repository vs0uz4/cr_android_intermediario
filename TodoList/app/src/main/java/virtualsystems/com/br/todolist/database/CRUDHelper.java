package virtualsystems.com.br.todolist.database;

import java.util.List;

public interface CRUDHelper<T> {
    public void create(T entity);
    public T findById(int id);
    public List<T> findAll();
    public int update(T entity);
    public void delete(T entity);
}
