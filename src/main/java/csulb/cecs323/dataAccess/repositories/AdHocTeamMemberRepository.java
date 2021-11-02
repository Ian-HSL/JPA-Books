package csulb.cecs323.dataAccess.repositories;

import csulb.cecs323.dataAccess.IRepositories.IAdHocTeamMemberRepository;
import csulb.cecs323.model.AdHocTeamsMember;

import javax.persistence.EntityManager;

public class AdHocTeamMemberRepository extends Repository<AdHocTeamsMember> implements IAdHocTeamMemberRepository {
    public AdHocTeamMemberRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllAdHocTeamMembers";
    }
}
