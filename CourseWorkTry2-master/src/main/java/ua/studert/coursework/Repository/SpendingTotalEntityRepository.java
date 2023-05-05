package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.studert.coursework.Entity.ProfitTotalEntity;
import ua.studert.coursework.Entity.SpendingTotalEntity;

public interface SpendingTotalEntityRepository extends JpaRepository<SpendingTotalEntity, String> {

    boolean existsByArticle (String article);

    SpendingTotalEntity findByArticle (String article);
}