package ast.MakeHRFun.offers;

import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.offers.model.Offer;
import ast.MakeHRFun.offers.service.OfferQueryService;
import ast.MakeHRFun.offers.service.OfferRequestService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {

    private OfferQueryService queryService;
    private OfferRequestService requestService;


    public OfferController(OfferQueryService queryService,
                           OfferRequestService requestService){
        this.queryService = queryService;
        this.requestService = requestService;
    }

    //-------- Query

    @GetMapping("/{id}")
    public Offer get(@PathVariable(value="id") Long id) {
        return queryService.get(id);
    }

    @GetMapping()
    public List<Offer> listOffers() {
        return queryService.list();
    }

    //-------- Request

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Offer create(@RequestBody Offer offer) {
        return requestService.createOffer(offer);
    }

    @PostMapping("/{offerId}/apply")
    public Application apply(@PathVariable(value="offerId") Long offerId,
                             @RequestBody Application application) {
        return requestService.applyForOffer(offerId, application);
    }


}
