package ast.MakeHRFun.offers.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String jobTitle;
    private Date startDate;
    private int numberOfActiveApplications;

    public Offer(){ }

    public Offer(String jobTitle, Date startDate){
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.numberOfActiveApplications = 0;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getNumberOfActiveApplications() {
        return numberOfActiveApplications;
    }

    public void setNumberOfActiveApplications(int numberOfActiveApplications) {
        this.numberOfActiveApplications = numberOfActiveApplications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
