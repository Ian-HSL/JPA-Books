package csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeam;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.AdHocTeam.AdHocTeam;

import javax.persistence.EntityManager;

public class AdHocTeamRepository extends Repository<AdHocTeam> implements IAdHocTeamRepository {
    public AdHocTeamRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllAdHocTeams";
    }
}
