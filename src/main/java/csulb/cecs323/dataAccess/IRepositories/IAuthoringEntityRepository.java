package csulb.cecs323.dataAccess.IRepositories;

import csulb.cecs323.model.AdHocTeam;
import csulb.cecs323.model.AuthoringEntity;
import csulb.cecs323.model.IndividualAuthors;
import csulb.cecs323.model.WritingGroup;

import java.util.List;

public interface IAuthoringEntityRepository extends IGenericRepository<AuthoringEntity> {
    List<AdHocTeam> getAllAdHocTeams();

    List<IndividualAuthors> getAllIndividualAuthors();

    List<WritingGroup> getAllWritingGroups();
}
