package com.company.application.data.client.repository;

import com.company.application.data.client.entity.ClientEntity;
import com.company.application.domain.clientlist.data.ClientOverview;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientOverview> findClientOverviewById(int id);

    List<ClientOverview> findAllClientOverviewsBy();

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "address",
                    "contactPersons",
                    "projects"
            }
    )
    @Query(
            "select c from ClientEntity c where c.id = :id"
    )
    Optional<ClientEntity> findClientByIdAndFetchEagerly(@Param("id") Integer id);

    @Modifying
    @Query(
            "update ClientEntity set name = :name, " +
                    "representative = :representative, " +
                    "email = :email, " +
                    "phone = :phone where id = :id"
    )
    int updateClientOverview(@Param("id") int id,
                                 @Param("name") String name,
                                 @Param("representative") String representative,
                                 @Param("email") String email,
                                 @Param("phone") String phone);
}
