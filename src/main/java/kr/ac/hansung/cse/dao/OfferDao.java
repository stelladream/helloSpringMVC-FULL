package kr.ac.hansung.cse.dao;

import kr.ac.hansung.cse.model.Offer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
<<<<<<< HEAD
=======
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> fd17ab99bf44ab80a8bc5456a509a3a60572270b
import java.util.List;

//@Repository
//public class OfferDao {
//
//    private JdbcTemplate jdbcTemplate;  // psa(portable service abstraction, sql(x) api
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    public int getRowCount() {
//        String sqlStatement= "select count(*) from offers";
//        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
//
//    }
//    //query and return a single object
//    public Offer getOffer(String name) {
//
//        String sqlStatement= "select * from offers where name=?";
//        return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name},
//                new RowMapper<Offer>() {
//
//                    @Override
//                    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                        Offer offer= new Offer();
//
//                        offer.setId(rs.getInt("id"));
//                        offer.setName(rs.getString("name"));
//                        offer.setEmail(rs.getString("email"));
//                        offer.setText(rs.getString("text"));
//
//                        return offer;
//                    }
//                });
//    }
//
//    //query and return multiple objects
//    // cRud method
//    public List<Offer> getOffers() {
//
//        String sqlStatement= "select * from offers";
//        return jdbcTemplate.query(sqlStatement, new RowMapper<Offer>() {
//
//            @Override
//            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                Offer offer= new Offer();
//
//                offer.setId(rs.getInt("id"));
//                offer.setName(rs.getString("name"));
//                offer.setEmail(rs.getString("email"));
//                offer.setText(rs.getString("text"));
//
//                return offer;
//            }
//        });
//    }
//
//
//    // Crud method
//    public boolean insert(Offer offer) {
//
//        String name= offer.getName();
//        String email= offer.getEmail();
//        String text = offer.getText();
//
//        String sqlStatement= "insert into offers (name, email, text) values (?,?,?)";
//
//        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text}) == 1);
//    }
//
//    // crUd method
//    public boolean update(Offer offer) {
//
//        int id = offer.getId();
//        String name= offer.getName();
//        String email= offer.getEmail();
//        String text = offer.getText();
//
//        String sqlStatement= "update offers set name=?, email=?, text=? where id=?";
//
//        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text, id}) == 1);
//    }
//
//    //cruD method
//    public boolean delete(int id) {
//        String sqlStatement= "delete from offers where id=?";
//        return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
//    }
//
//}

@Repository
@Transactional
public class OfferDao {  //CRUD

    @PersistenceContext
    private EntityManager entityManager;

    public Offer getOffer(String name) {
        return entityManager.createQuery("SELECT o FROM Offer o WHERE o.name = :name", Offer.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public Offer getOffer(int id) {
        return entityManager.find(Offer.class, id);
    }

    public List<Offer> getOffers() {
        return entityManager.createQuery("SELECT o FROM Offer o", Offer.class)
                .getResultList();
    }
    public void insert(Offer offer) {
        entityManager.persist(offer);
    }

    public void update(Offer offer) {
        entityManager.merge(offer);
    }

    public void delete(int id) {
        Offer offer = entityManager.find(Offer.class, id);
        entityManager.remove(offer);
    }
}

