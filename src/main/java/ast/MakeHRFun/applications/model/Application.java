package ast.MakeHRFun.applications.model;

import ast.MakeHRFun.offers.model.Offer;

import javax.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(targetEntity=Offer.class, fetch = FetchType.EAGER)
    private Offer relatedOffer;
    private String candidateEmail;
    private String resumeText;
    private Status applicationStatus;

    public Application(){ };

    public Application(Offer offer, String candidateEmail, String resumeText){
        this.relatedOffer = offer;
        this.candidateEmail = candidateEmail;
        this.resumeText = resumeText;
        this.applicationStatus = Status.APPLIED;
    }

    public Offer getRelatedOffer() {
        return relatedOffer;
    }

    public void setRelatedOffer(Offer relatedOffer) {
        this.relatedOffer = relatedOffer;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public Status getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Status applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Status {
        APPLIED, INVITED, REJECTED, HIRED;
    }
}
