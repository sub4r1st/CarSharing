package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Auto;
import serv.models.Order;
import serv.repositories.AutoRepository;
import serv.repositories.OrderRepository;

import java.util.List;

@Service
public class AutoService {
    @Autowired
    private AutoRepository reps;

    @Autowired
    private OrderRepository order;

    public List<Auto> getAutos() {
        return reps.findAll();
    }

    public void createAuto(Auto a) {
        reps.save(a);
    }

    public void delete(int id) {
        List<Order> b = order.findAll();
        for (int i = 0; i < b.size(); i++){
            if(b.get(i).getAuto().getId() == id){
                order.deleteById(b.get(i).getId());
            }
        }
        reps.delete(reps.findById(id).get());
    }

    public Auto getAutoById(int AutoId) {
        return reps.findById(AutoId).get();
    }

    public int getPrice(int autoId){return reps.findById(autoId).get().getPrice();}
}
