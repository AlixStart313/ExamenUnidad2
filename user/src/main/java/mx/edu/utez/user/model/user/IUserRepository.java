package mx.edu.utez.user.model.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository  extends JpaRepository<UserModel,Long> {
     Optional<UserModel> findByUsername(String userName);
     Optional<UserModel>findByCurp(String curp);

     @Modifying
     @Query(
             value="UPDATE usuarios SET status= :status WHERE id=:id",
             nativeQuery = true
     )
     int updateStatusById(
             @Param("status") Boolean status,
             @Param("id") Long id);







}
