package ast.MakeHRFun.applications;

import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.offers.OfferRepository;
import ast.MakeHRFun.offers.model.Offer;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("applications")
public class ApplicationController {

    private final static Logger logger = Logger.getLogger("ApplicationController");

    private ApplicationRepository repository;
    private OfferRepository offerRepository;

    public ApplicationController(ApplicationRepository repository, OfferRepository offerRepository){
        this.repository = repository;
        this.offerRepository = offerRepository;
    }
    //-------- Query

    @GetMapping("/{id}")
    public Application getApplication(@PathVariable(value="id") Long id) {
        return repository.getOne(id);
    }

    @GetMapping()
    public List<Application> listApplications(@RequestParam(value="offerId", required = false) Long offerId) {
        if(offerId == null){
            return repository.findAll();
        } else {
            return repository.findAllForOffer(offerId);
        }

    }

    @GetMapping("/count-active")
    public Long countActiveApplications(@RequestParam(value="offerId", required = false) Long offerId) {
        if(offerId == null){
            return repository.countActive();
        } else {
            return repository.countActiveForOffer(offerId);
        }
    }

    //-------- Request

    public Application apply(Offer offer, String candidateEmail, String resumeText) {
        Application application = new Application(offer, candidateEmail, resumeText);
        logger.info("New application for offer "+offer.getId());
        return repository.save(application);
    }

    @PostMapping("/{id}/invite")
    public Application invite(@PathVariable(value="id") Long id) {
        logger.info("Inviting candidate based on application "+id);

        Application application = changeStatus(id, Application.Status.INVITED);
        return application;
    }

    @PostMapping("/{id}/reject")
    public Application reject(@PathVariable(value="id") Long id) {
        logger.info("Rejecting candidate based on application "+id);

        Application application =  changeStatus(id, Application.Status.REJECTED);
        decreaseApplicationsNumberForOffer(application.getRelatedOffer().getId());
        return application;
    }

    @PostMapping("/{id}/accept")
    public Application accept(@PathVariable(value="id") Long id) {
        logger.info("Accepting candidate based on application "+id);

        Application application =  changeStatus(id, Application.Status.HIRED);
        decreaseApplicationsNumberForOffer(application.getRelatedOffer().getId());
        return application;
    }

    private Application changeStatus(Long applicationId, Application.Status status) {
        Application application = repository.getOne(applicationId);
        application.setApplicationStatus(status);
        return repository.save(application);
    }

    private void
    decreaseApplicationsNumberForOffer(Long offerId){
        Offer offer = offerRepository.getOne(offerId);
        offer.setNumberOfActiveApplications(offer.getNumberOfActiveApplications() - 1);
        offerRepository.save(offer);
    }

}
