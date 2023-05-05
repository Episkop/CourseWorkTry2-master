package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.studert.coursework.Entity.ProfitTotalEntity;

public interface ProfitTotalEntityRepository extends JpaRepository<ProfitTotalEntity, Long> {

    boolean existsByArticle (String article);

    ProfitTotalEntity findByArticle (String article);


}