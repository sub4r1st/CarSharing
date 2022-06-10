package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Order;
import serv.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository reps;

    public List<Order> getOrderByUserId(int userId){
        List<Order> a = reps.findAll();
        List<Order> c = new ArrayList<Order>();
        for (int i = 0; i < a.size(); i++){
            if(a.get(i).getUserId() == userId){
                c.add(a.get(i));
            }
        }
        return c;
    }

    public List<Order> getOrders() {
        return reps.findAll();
    }

    public void createOrder(Order a) {
        reps.save(a);
    }
}
