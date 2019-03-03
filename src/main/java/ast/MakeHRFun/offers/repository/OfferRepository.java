package ast.MakeHRFun.offers.repository;

import ast.MakeHRFun.offers.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
