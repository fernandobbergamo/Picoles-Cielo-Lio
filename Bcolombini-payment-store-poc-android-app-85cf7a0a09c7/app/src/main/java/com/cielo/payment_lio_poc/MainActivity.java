package com.cielo.payment_lio_poc;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.cielo.payment_lio_poc.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class MainActivity extends BaseActivity {

    private OrderManager orderManager;
    private Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) this.findViewById(R.id.grid_view);

        AdapterGridView adapterGridView = new AdapterGridView(this, productList());
        adapterGridView.setMainActivity(this);
        gridView.setAdapter(adapterGridView);

        order();

    }


    private List<Product> productList() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(0);
        product.setImage(R.drawable.img_bolsa);
        product.setName("Bolsa");
        product.setPrice("120,00");
        products.add(product);
        product = new Product();
        product.setId(1);
        product.setImage(R.drawable.img_camisa);
        product.setName("Camisa");
        product.setPrice("80,00");
        products.add(product);
        product = new Product();
        product.setId(2);
        product.setImage(R.drawable.img_casaco);
        product.setName("Casaco");
        product.setPrice("180,00");
        products.add(product);
        product = new Product();
        product.setId(3);
        product.setImage(R.drawable.img_mochila);
        product.setName("Mochila");
        product.setPrice("80,00");
        products.add(product);
        product = new Product();
        product.setId(4);
        product.setImage(R.drawable.img_sapato);
        product.setName("Mocassim");
        product.setPrice("150,00");
        products.add(product);
        product = new Product();
        product.setId(5);
        product.setImage(R.drawable.img_vestido);
        product.setName("Vestido");
        product.setPrice("65,00");
        products.add(product);

        return products;
    }

    private void order() {
        Credentials credentials = new Credentials("yxyDxmlXXHvK", "vXlqw6BB8OEm");
        orderManager = new OrderManager(credentials, this);

        ServiceBindListener serviceBindListener = new ServiceBindListener() {
            @Override
            public void onServiceBound() {
                order = orderManager.createDraftOrder("Roupas");

                String sku = "2891820317391823";
                String name = "Coca-cola lata";

                int unitPrice = 1;
                int quantity = 1;

                String unityOfMeasure = "UNIDADE";

                order.addItem(sku, name, unitPrice, quantity, unityOfMeasure);

                orderManager.placeOrder(order);
            }

            @Override
            public void onServiceUnbound() {
                // O serviço foi desvinculado
            }
        };

        orderManager.bind(this, serviceBindListener);
    }

    public void orderRequest() {
        PaymentListener paymentListener = new PaymentListener() {
            @Override
            public void onStart() {
                Log.d("MinhaApp", "O pagamento começou.");
            }

            @Override
            public void onPayment(@NotNull Order order) {
                Log.d("MinhaApp", "Um pagamento foi realizado.");
            }

            @Override
            public void onCancel() {
                Log.d("MinhaApp", "A operação foi cancelada.");
            }

            @Override
            public void onError(@NotNull PaymentError paymentError) {
                Log.d("MinhaApp", "Houve um erro no pagamento.");
            }
        };

        String orderId = order.getId();
        long value = order.getPrice();

        orderManager.checkoutOrder(orderId, value, paymentListener);
    }
}
