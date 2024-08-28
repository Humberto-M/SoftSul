import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText searchInput;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrderRepository.init(this);

        Order order1 = new Order("Cliente A", "2023-10-01", "Pendente");
        Order order2 = new Order("Cliente B", "2023-10-02", "Entregue");
        OrderRepository.insertOrder(order1);
        OrderRepository.insertOrder(order2);

        listView = findViewById(R.id.orderListView);
        searchInput = findViewById(R.id.searchInput);
        orderList = OrderRepository.getOrders(); // Método fictício para obter pedidos

        orderAdapter = new OrderAdapter(this, orderList);
        listView.setAdapter(orderAdapter);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                orderAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Order selectedOrder = orderList.get(position);
            Intent intent = new Intent(MainActivity.this, OrderDetailActivity.class);
            intent.putExtra("ORDER_ID", selectedOrder.getId());
            startActivity(intent);
        });
    }
}
