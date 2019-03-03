package ast.MakeHRFun.applications.service;

import ast.MakeHRFun.applications.repository.ApplicationRepository;
import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.offers.repository.OfferRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class ApplicationQueryService {

    private ApplicationRepository repository;

    public ApplicationQueryService(ApplicationRepository repository){
        this.repository = repository;
    }

    public Application get(@PathVariable("id") Long id) {
        return repository.getOne(id);
    }

    public List<Application> list(@RequestParam(value = "offerId", required = false) Long offerId) {
        if(offerId == null){
            return repository.findAll();
        } else {
            return repository.findAllForOffer(offerId);
        }
    }

    public Long countActive(@RequestParam(value = "offerId", required = false) Long offerId) {
        if(offerId == null){
            return repository.countActive();
        } else {
            return repository.countActiveForOffer(offerId);
        }
    }

}
