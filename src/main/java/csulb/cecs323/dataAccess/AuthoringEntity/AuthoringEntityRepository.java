package csulb.cecs323.dataAccess.AuthoringEntity;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.AuthoringEntity;

import javax.persistence.EntityManager;

public class AuthoringEntityRepository extends Repository<AuthoringEntity> implements IAuthoringEntityRepository {
    public AuthoringEntityRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllAuthoringEntities";
    }
}
