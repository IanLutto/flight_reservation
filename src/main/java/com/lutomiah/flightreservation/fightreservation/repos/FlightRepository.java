package com.lutomiah.flightreservation.fightreservation.repos;

import com.lutomiah.flightreservation.fightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
@Query( value ="select * from Flight where departure_city =:departureCity and arrival_city =:arrivalCity and date_of_departure =:dateOfDeparture ", nativeQuery = true)
    List<Flight> findFlight(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") String departureDate);


//    /**
//     * Find all results of executing a given hibernate query with given params. Each parameter is
//     * substituted in the query.
//     *
//     * @param <T> Actual return type.
//     * @param query Hibernate query constructed to be substituted with the actual params below.
//     * @param params map pair of param name which is simply the field name of the entity and a
//     * search value. The param map maybe empty.
//     * @return List of results or empty list if none is found.
//     */
//    public <T> List<T> fetchWithHibernateQuery(String query, Map<String, Object> params) throws HibernateException;
}
