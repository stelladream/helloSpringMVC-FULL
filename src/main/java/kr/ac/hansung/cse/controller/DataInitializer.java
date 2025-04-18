package kr.ac.hansung.cse.controller;

import jakarta.annotation.PostConstruct;
import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// vm optons: -Dspring.profiles.active=dev,
// dev: 개발 환경
//test: 테스트 환경
//prod: 운영 환경
@Profile("dev")
@Component
public class DataInitializer {

    @Autowired
    private OfferDao offerDao;

    @PostConstruct
    public void init() {
        offerDao.insert(new Offer("Alice", "alice@hansung.ac.kr", "스프링이 참 재미있네요"));
        offerDao.insert(new Offer("Bob", "bob@hansung.ac.kr", "JPA/Hibernate는 참 편리하네요"));
        offerDao.insert(new Offer("Charlie", "charlie@hansung.ac.kr", "Rest API를 구현합니다."));
    }
}
