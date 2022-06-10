package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Owner;
import serv.repositories.OwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository reps;

    public List<Owner> getOwners() {
        return reps.findAll();
    }

    public void createOwner(Owner a) {
        reps.save(a);
    }

    public List <Owner> getOwnersByUserId(int userId){
        List<Owner> a = reps.findAll();
        List<Owner> c = new ArrayList<Owner>();
        for (int i = 0; i < a.size(); i++){
            if(a.get(i).getUserId() == userId){
                c.add(a.get(i));
            }
        }
        return c;
    }

    public Owner getOwnerById(int ownerId) {
        return reps.findById(ownerId).get();
    }
}
