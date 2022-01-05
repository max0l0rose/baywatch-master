package software.sagax.baywatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import software.sagax.baywatch.dto.SpeedNameDTO;
import software.sagax.baywatch.model.Lifeguard;

import java.util.List;

@Repository
public interface LifeguardRepository extends JpaRepository<Lifeguard, Long> {

    //TODO Correct the query
    //Get a list of the names of the lifeguards who have faster speed than their bosses
    @Query(value = "SELECT l.name FROM lifeguard l\n" +
                           "LEFT JOIN lifeguard l2 ON l2.id = l.boss_id\n" +
                           "WHERE l.speed > l2.speed", nativeQuery = true)
    List<String> findWhenSpeedGreaterThanBoss();

    //TODO Correct the query
    //Get a list of the names of the fastest lifeguards
    @Query(value = "SELECT l.name FROM lifeguard l,\n" +
                           "(\n" +
                           "    SELECT l2.speed FROM lifeguard l2\n" +
                           "    GROUP BY l2.speed\n" +
                           "    ORDER BY l2.speed DESC\n" +
                           "    LIMIT 1\n" +
                           ") as q2\n" +
                           "WHERE l.speed = q2.speed", nativeQuery = true)
    List<String> findFastestLifeguards();

    //TODO Correct the query
    //Get a list of bosses. The boss is the one who has subordinates
    @Query(value = "SELECT l.name FROM lifeguard l\n" +
                           "WHERE boss_id IS NULL", nativeQuery = true)
    List<String> findBosses();


    // JPA Derived Query Methods.
    @Transactional
    void deleteAllByNameIs(String name);

    @Transactional
    @Modifying
    @Query("update Lifeguard l SET l.speed = :speed WHERE l.name = :name")
    void updateSpeed(@Param("name") String name, @Param("speed") Integer speed);

    @Transactional
    @Query("select l.speed, count(l) from Lifeguard l GROUP BY l.speed")
    List<int[]> groupAndCountBySpeed();

    @Transactional
    @Query("select new software.sagax.baywatch.dto.SpeedNameDTO(l.speed, l.name) " +
                   "from Lifeguard l order by l.speed")
    List<SpeedNameDTO> namesAndSpeeds();
}
