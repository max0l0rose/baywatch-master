package software.sagax.baywatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
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
    @Query(value = "SELECT l.name FROM lifeguard l\n" +
                           "ORDER BY speed DESC", nativeQuery = true)
    List<String> findFastestLifeguards();

    //TODO Correct the query
    //Get a list of bosses. The boss is the one who has subordinates
    @Query(value = "SELECT l.name FROM lifeguard l\n" +
                           "WHERE boss_id IS NULL", nativeQuery = true)
    List<String> findBosses();

}
