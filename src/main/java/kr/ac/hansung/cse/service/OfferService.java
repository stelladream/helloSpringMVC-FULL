package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// RestController -> Service -> DAO
@Service
public class OfferService {

    //service -> dao
    @Autowired
    private OfferDao offerDao;

    public List<Offer> getAllOffers() {
        return offerDao.getOffers();
    }

    public Offer getOfferById(int id) {
        return offerDao.getOffer(id);
    }

    public void insertOffer(Offer offer) {
        offerDao.insert(offer);
    }

    public void updateOffer(Offer offer) {
        offerDao.update(offer);
    }

    public void deleteOfferById(int id) {
        offerDao.delete(id);
    }
}
