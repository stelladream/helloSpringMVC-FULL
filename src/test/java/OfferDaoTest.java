import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/dao-context.xml")

public class OfferDaoTest {

    @Autowired
    private OfferDao offerDao;

    @BeforeEach
    public void setUp() {
        Offer offer = new Offer();
        offer.setName("Test Offer");
        offer.setEmail("test@example.com");
        offer.setText("This is a test offer");

        offerDao.insert(offer);
    }

    @Test
    @DisplayName("Test1: testGetOfferByName")
    public void testGetOfferByName() {

        // 이름을 통해 Offer 객체를 가져옴
        Offer offer = offerDao.getOffer("Test Offer");
        // 가져온 Offer 객체가 null이 아닌지 확인
        assertNotNull(offer);
        // 가져온 Offer 객체의 이름이 예상한 값과 일치하는지 확인
        assertEquals("Test Offer", offer.getName());
    }

    @Test
    @DisplayName("Test2: testGetOfferById")
    public void testGetOfferById() {

        // 이름을 통해 Offer 객체를 가져옴
        Offer savedOffer = offerDao.getOffer("Test Offer");
        // ID를 통해 Offer 객체를 가져옴
        Offer offer = offerDao.getOffer(savedOffer.getId());
        // 가져온 Offer 객체가 null이 아닌지 확인
        assertNotNull(offer);
        // 가져온 Offer 객체의 ID가 예상한 값과 일치하는지 확인
        assertEquals(savedOffer.getId(), offer.getId());
    }

    @Test
    @DisplayName("Test3: testGetOffers")
    public void testGetOffers() {

        // 모든 Offer 객체를 가져옴
        List<Offer> offers = offerDao.getOffers();
        // 가져온 Offer 리스트가 null이 아닌지 확인
        assertNotNull(offers);
        // 가져온 Offer 리스트가 비어있지 않은지 확인
        assertFalse(offers.isEmpty());
    }

    @Test
    @DisplayName("Test4: testInsert")
    public void testInsert() {

        // 새로운 Offer 객체 생성
        Offer newOffer = new Offer();
        newOffer.setName("New Offer");
        newOffer.setEmail("new@example.com");
        newOffer.setText("This is a new offer");
        // 새로운 Offer 객체를 데이터베이스에 저장
        offerDao.insert(newOffer);
        // ID가 할당되었는지 확인
        assertNotNull(newOffer.getId());
        // 저장된 Offer 객체를 가져와서 확인
        Offer savedOffer = offerDao.getOffer(newOffer.getId());
        assertNotNull(savedOffer);
        assertEquals("New Offer", savedOffer.getName());
    }

    @Test
    @DisplayName("Test5: testUpdate")
    public void testUpdate() {

        // 저장된 Offer 객체를 가져와서 수정
        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offer.setText("Updated text");
        // 수정된 Offer 객체를 데이터베이스에 업데이트
        offerDao.update(offer);
        // 업데이트된 Offer 객체를 다시 가져와서 확인
        Offer updatedOffer = offerDao.getOffer(offer.getId());
        assertNotNull(updatedOffer);
        assertEquals("Updated text", updatedOffer.getText());
    }

    @Test
    @DisplayName("Test6: testDelete")
    public void testDelete() {
        // 저장된 Offer 객체를 삭제
        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offerDao.delete(offer.getId());
        // 삭제된 Offer 객체를 가져와서 확인
        Offer deletedOffer = offerDao.getOffer(offer.getId());
        assertNull(deletedOffer);
    }
}
