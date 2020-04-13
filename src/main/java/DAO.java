import java.util.ArrayList;
import java.util.Map;

public interface DAO<T> {

    void add(T object);

    T get(int id);

    ArrayList<T> getAll();

    void update(T object, Map<String, Object> params);

    void remove(T object);
}