import android.content.Context;
import java.util.ArrayList;

public class OrderRepository {
    private static DatabaseHelper dbHelper;

    public static void init(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public static ArrayList<Order> getOrders() {
        return dbHelper.getAllOrders();
    }

    public static void insertOrder(Order order) {
        dbHelper.insertOrder(order);
    }

    public static void updateOrder(Order order) {
        dbHelper.updateOrder(order);
    }

    public static Order getOrderById(int id) {
        ArrayList<Order> orders = getOrders();
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }
}