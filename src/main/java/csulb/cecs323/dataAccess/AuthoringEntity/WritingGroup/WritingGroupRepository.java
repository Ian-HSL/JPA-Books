package csulb.cecs323.dataAccess.AuthoringEntity.WritingGroup;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.WritingGroup;

import javax.persistence.EntityManager;

public class WritingGroupRepository extends Repository<WritingGroup> implements IWritingGroupRepository {
    public WritingGroupRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllWritingGroups";
    }
}
