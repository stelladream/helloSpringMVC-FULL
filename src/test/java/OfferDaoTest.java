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
        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        assertEquals("Test Offer", offer.getName());
    }

    @Test
    @DisplayName("Test2: testGetOfferById")
    public void testGetOfferById() {

        Offer savedOffer = offerDao.getOffer("Test Offer");

        Offer offer = offerDao.getOffer(savedOffer.getId());
        assertNotNull(offer);
        assertEquals(savedOffer.getId(), offer.getId());
    }

    @Test
    @DisplayName("Test3: testGetOffers")
    public void testGetOffers() {

        List<Offer> offers = offerDao.getOffers();
        assertNotNull(offers);
        assertFalse(offers.isEmpty());
    }

    @Test
    @DisplayName("Test4: testInsert")
    public void testInsert() {

        Offer newOffer = new Offer();
        newOffer.setName("New Offer");
        newOffer.setEmail("new@example.com");
        newOffer.setText("This is a new offer");
        offerDao.insert(newOffer);
        assertNotNull(newOffer.getId());

        Offer savedOffer = offerDao.getOffer(newOffer.getId());
        assertNotNull(savedOffer);
        assertEquals("New Offer", savedOffer.getName());
    }

    @Test
    @DisplayName("Test5: testUpdate")
    public void testUpdate() {

        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offer.setText("Updated text");
        offerDao.update(offer);

        Offer updatedOffer = offerDao.getOffer(offer.getId());
        assertNotNull(updatedOffer);
        assertEquals("Updated text", updatedOffer.getText());
    }

    @Test
    @DisplayName("Test6: testDelete")
    public void testDelete() {

        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offerDao.delete(offer.getId());

        Offer deletedOffer = offerDao.getOffer(offer.getId());
        assertNull(deletedOffer);
    }

}
