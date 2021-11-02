package csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeamMember;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.AdHocTeam.AdHocTeamsMember;

import javax.persistence.EntityManager;

public class AdHocTeamMemberRepository extends Repository<AdHocTeamsMember> implements IAdHocTeamMemberRepository {
    public AdHocTeamMemberRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllAdHocTeamMembers";
    }
}
