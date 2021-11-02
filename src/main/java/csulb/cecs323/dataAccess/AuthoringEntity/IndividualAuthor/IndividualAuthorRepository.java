package csulb.cecs323.dataAccess.AuthoringEntity.IndividualAuthor;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.IndividualAuthor;

import javax.persistence.EntityManager;

public class IndividualAuthorRepository extends Repository<IndividualAuthor> implements IIndividualAuthorRepository {
    public IndividualAuthorRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllIndividualAuthors";
    }
}
