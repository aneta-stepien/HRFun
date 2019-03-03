package ast.MakeHRFun.offers;

import ast.MakeHRFun.applications.ApplicationController;
import ast.MakeHRFun.applications.ApplicationRepository;
import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.offers.model.Offer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {

    private OfferRepository repository;
    private ApplicationRepository applicationRepository;

    public OfferController(OfferRepository repository, ApplicationRepository applicationRepository){
        this.repository = repository;
        this.applicationRepository = applicationRepository;
    }

    //-------- Query

    @GetMapping("/{id}")
    public Offer get(@PathVariable(value="id") Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Offer with id "+id+" not found"));
    }

    @GetMapping()
    public List<Offer> listOffers() {
        return repository.findAll();
    }

    //-------- Request

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Offer create(@RequestBody Offer offer) {
        return repository.save(offer);
    }

    @PostMapping("/{offerId}/apply")
    public Application apply(@PathVariable(value="offerId") Long offerId,
                                     @RequestParam(value="candidateEmail") String candidateEmail,
                                     @RequestParam(value="resumeText") String resumeText) {
        Offer offer = repository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer with id "+offerId+" not found"));
        offer.setNumberOfActiveApplications(offer.getNumberOfActiveApplications() + 1);
        repository.save(offer);

        Application application = new Application(offer, candidateEmail, resumeText);
        return applicationRepository.save(application);
    }

}
