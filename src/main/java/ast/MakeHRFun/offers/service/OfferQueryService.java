package ast.MakeHRFun.offers.service;

import ast.MakeHRFun.offers.model.Offer;
import ast.MakeHRFun.offers.repository.OfferRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class OfferQueryService {

    private OfferRepository repository;

    public OfferQueryService(OfferRepository repository){
        this.repository = repository;
    }

    public Offer get(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Offer with id "+id+" not found"));
    }

    public List<Offer> list() {
        return repository.findAll();
    }
}
