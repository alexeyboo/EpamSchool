package travelcompany.common.solutions.utils;

import java.util.List;

public final class CollectionUtils {

<<<<<<< HEAD
    public static <T> T getLast(List<T> list) {
=======
    public static<T>  T getLast(List<T> list) {
>>>>>>> github/master
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(list.size() - 1);
        }
    }
<<<<<<< HEAD

    public static <T> List<T> getPageableData(List<T> list, final int limit, final int offset) {
        if (offset >= list.size()) {
            return list;
        } else {
            int l = offset + limit > list.size() ? list.size() % limit : limit;
            return list.subList(offset, offset + l);
        }
    }
=======
>>>>>>> github/master
}