package csulb.cecs323.dataAccess.repositories;

import csulb.cecs323.dataAccess.IRepositories.IAuthoringEntityRepository;
import csulb.cecs323.model.AdHocTeam;
import csulb.cecs323.model.AuthoringEntity;
import csulb.cecs323.model.IndividualAuthors;
import csulb.cecs323.model.WritingGroup;

import javax.persistence.EntityManager;
import java.util.List;

public class AuthoringEntityRepository extends Repository<AuthoringEntity> implements IAuthoringEntityRepository {
    String getAllWritingGroupQueryName = "GetAllWritingGroups";
    String getAllIndividualAuthorsQueryName = "GetAllIndividualAuthors";
    String getAllAdHocTeamsQueryName = "GetAllAdHocTeams";

    public AuthoringEntityRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllAuthoringEntities";
    }

    public List<AdHocTeam> getAllAdHocTeams(){
        return manager.createNamedQuery(getAllAdHocTeamsQueryName).getResultList();
    }

    public List<WritingGroup> getAllWritingGroups(){
        return manager.createNamedQuery(getAllWritingGroupQueryName).getResultList();
    }

    public List<IndividualAuthors> getAllIndividualAuthors(){
        return manager.createNamedQuery(getAllIndividualAuthorsQueryName).getResultList();
    }
}
