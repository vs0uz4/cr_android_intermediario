package virtualsystems.com.br.todolist.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import virtualsystems.com.br.todolist.entities.Todo;

public class CRUDHelperImpl implements CRUDHelper<Todo> {

    private DbHandler handler = null;

    private static final String TABLE_NAME = "tb_tasks";

    public CRUDHelperImpl(DbHandler handler) {
        this.handler = handler;
    }

    @Override
    public void create(Todo entity) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", entity.getTitle());
        values.put("description", entity.getDescription());

        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    @Override
    public Todo findById(int id) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {"id", "title", "description"}, "id =?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Todo task = new Todo(cursor.getLong(0), cursor.getString(1), cursor.getString(2));

        return task;
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<Todo>();

        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Todo task = new Todo(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                list.add(task);
            } while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public int update(Todo entity) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", entity.getTitle());
        values.put("description", entity.getDescription());

        return db.update(TABLE_NAME, values, " id = ?", new String[] { String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(Todo entity) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        db.delete(TABLE_NAME, " id = ?", new String[] { String.valueOf(entity.getId()) } );
        db.close();
    }
}
