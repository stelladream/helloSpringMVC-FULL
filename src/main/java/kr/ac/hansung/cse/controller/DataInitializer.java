package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements InitializingBean {

    @Autowired
    private OfferDao offerDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        Offer offer1 = new Offer("Alice","alice@hansung.ac.kr","스프링이 참 재미있네요");
        Offer offer2 = new Offer("Bob","bob@hansung.ac.kr","JPA/Hibernate는 참 편리하네요");
        Offer offer3 = new Offer("Charlie","charlie@hansung.ac.kr","Rest API를 구현합니다.");

        offerDao.insert(offer1);
        offerDao.insert(offer2);
        offerDao.insert(offer3);
    }
}
