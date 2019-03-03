package ast.MakeHRFun.applications.service;

import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.applications.repository.ApplicationRepository;
import ast.MakeHRFun.offers.model.Offer;
import ast.MakeHRFun.offers.service.OfferRequestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.logging.Logger;

@Component
public class ApplicationRequestService {

    private final static Logger logger = Logger.getLogger("ApplicationRequestService");

    private ApplicationRepository repository;
    private OfferRequestService offerRequestService;

    public ApplicationRequestService(ApplicationRepository repository,
                                     OfferRequestService offerRequestService){
        this.repository = repository;
        this.offerRequestService = offerRequestService;
    }

    public Application applyForOffer(Offer offer, String candidateEmail, String resumeText) {
        Application application = new Application(offer, candidateEmail, resumeText);
        logger.info("New application for offer "+offer.getId());
        return repository.save(application);
    }

    public Application inviteCandidate(@PathVariable("id") Long id) {
        logger.info("Inviting candidate based on application "+id);

        Application application = changeStatus(id, Application.Status.INVITED);
        return application;
    }

    public Application rejectCandidate(@PathVariable("id") Long id) {
        logger.info("Rejecting candidate based on application "+id);

        Application application =  changeStatus(id, Application.Status.REJECTED);
        offerRequestService.decreaseApplicationsNumberForOffer(application.getRelatedOffer().getId());
        return application;
    }

    public Application acceptCandidate(@PathVariable("id") Long id) {
        logger.info("Accepting candidate based on application "+id);

        Application application =  changeStatus(id, Application.Status.HIRED);
        offerRequestService.decreaseApplicationsNumberForOffer(application.getRelatedOffer().getId());
        return application;
    }

    private Application changeStatus(Long applicationId, Application.Status status) {
        Application application = repository.getOne(applicationId);
        application.setApplicationStatus(status);
        return repository.save(application);
    }


}
