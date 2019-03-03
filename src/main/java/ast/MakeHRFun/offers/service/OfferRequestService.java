package ast.MakeHRFun.offers.service;

import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.applications.repository.ApplicationRepository;
import ast.MakeHRFun.offers.model.Offer;
import ast.MakeHRFun.offers.repository.OfferRepository;
import org.springframework.stereotype.Component;

@Component
public class OfferRequestService {

    private OfferRepository repository;
    private ApplicationRepository applicationRepository;


    public OfferRequestService(OfferRepository repository,
                               ApplicationRepository applicationRepository){
        this.repository = repository;
        this.applicationRepository = applicationRepository;
    }

    public Offer createOffer(Offer offer) {
        return repository.save(offer);
    }

    public Application applyForOffer(Long offerId, Application application) {
        Offer offer = repository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer with id "+offerId+" not found"));
        offer.setNumberOfActiveApplications(offer.getNumberOfActiveApplications() + 1);
        repository.save(offer);

        application.setRelatedOffer(offer);
        application.setApplicationStatus(Application.Status.APPLIED);
        return applicationRepository.save(application);
    }

    public void decreaseApplicationsNumberForOffer(Long offerId){
        Offer offer = repository.getOne(offerId);
        offer.setNumberOfActiveApplications(offer.getNumberOfActiveApplications() - 1);
        repository.save(offer);
    }

}
