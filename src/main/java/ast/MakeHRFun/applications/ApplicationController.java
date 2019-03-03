package ast.MakeHRFun.applications;

import ast.MakeHRFun.applications.model.Application;
import ast.MakeHRFun.applications.service.ApplicationQueryService;
import ast.MakeHRFun.applications.service.ApplicationRequestService;
import ast.MakeHRFun.offers.model.Offer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("applications")
public class ApplicationController {

    private ApplicationQueryService queryService;
    private ApplicationRequestService requestService;


    public ApplicationController(ApplicationQueryService queryService,
                                 ApplicationRequestService requestService){
        this.queryService = queryService;
        this.requestService = requestService;
    }

    //-------- Query

    @GetMapping("/{id}")
    public Application get(@PathVariable(value="id") Long id) {
        return queryService.get(id);
    }

    @GetMapping()
    public List<Application> list(@RequestParam(value="offerId", required = false) Long offerId) {
        return queryService.list(offerId);
    }

    @GetMapping("/count-active")
    public Long countActiveApplications(@RequestParam(value="offerId", required = false) Long offerId) {
        return queryService.countActive(offerId);
    }

    //-------- Request

    public Application apply(Offer offer, String candidateEmail, String resumeText) {
        return requestService.applyForOffer(offer, candidateEmail, resumeText);
    }

    @PostMapping("/{id}/invite")
    public Application invite(@PathVariable(value="id") Long id) {
        return requestService.inviteCandidate(id);
    }

    @PostMapping("/{id}/reject")
    public Application reject(@PathVariable(value="id") Long id) {
        return requestService.rejectCandidate(id);
    }

    @PostMapping("/{id}/accept")
    public Application accept(@PathVariable(value="id") Long id) {
        return requestService.acceptCandidate(id);
    }


}
