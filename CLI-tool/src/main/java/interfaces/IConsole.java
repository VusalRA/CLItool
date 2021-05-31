package interfaces;

import java.text.ParseException;
import java.util.List;

public interface IConsole<T> {

    void add() throws ParseException;

    void delete();

    void update();

    List<T> getList();

    List<T> findByName(String name);

}
