package ast.MakeHRFun.applications.repository;

import ast.MakeHRFun.applications.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT app FROM Application app WHERE app.relatedOffer.id = ?1 and app.applicationStatus in ('APPLIED', 'INVITED')")
    List<Application> findActiveForOffer(Long offerId);

    @Query("SELECT app FROM Application app WHERE app.relatedOffer.id = ?1")
    List<Application> findAllForOffer(Long offerId);

    @Query("SELECT count(app.id) FROM Application app WHERE app.relatedOffer.id = ?1 and app.applicationStatus in ('APPLIED', 'INVITED')")
    Long countActiveForOffer(Long offerId);

    @Query("SELECT count(app.id) FROM Application app WHERE app.applicationStatus in ('APPLIED', 'INVITED')")
    Long countActive();

}
