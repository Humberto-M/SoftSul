import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {
    private EditText deliveryDateInput, statusInput;
    private Button saveButton;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        deliveryDateInput = findViewById(R.id.deliveryDateInput);
        statusInput = findViewById(R.id.statusInput);
        saveButton = findViewById(R.id.saveButton);

        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        order = OrderRepository.getOrderById(orderId); // Método fictício para obter pedido

        deliveryDateInput.setText(order.getDeliveryDate());
        statusInput.setText(order.getStatus());

        saveButton.setOnClickListener(v -> {
            order.setDeliveryDate(deliveryDateInput.getText().toString());
            order.setStatus(statusInput.getText().toString());
            OrderRepository.updateOrder(order); // Método fictício para atualizar pedido
            finish();
        });
    }
}
