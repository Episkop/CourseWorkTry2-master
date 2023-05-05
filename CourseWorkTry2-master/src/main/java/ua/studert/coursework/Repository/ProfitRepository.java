package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.studert.coursework.Entity.ProfitEntity;

public interface ProfitRepository extends JpaRepository<ProfitEntity, Long> {
    boolean existsByArticle (String article);
    ProfitEntity findByArticle (String article);
    void deleteProfitEntityByArticle (String article);

//    @Query("UPDATE ProfitEntity e SET e.february =(SELECT t.january + g.january from ProfitTotalEntity t, SpendingTotalEntity g)")
//    Double restForNewMonth();

    @Query("SELECT e.january - p.january from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForFebruary();
    @Query("SELECT e.february - p.february from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForMarch();
    @Query("SELECT e.march - p.march from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForApril();
    @Query("SELECT e.april - p.april from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForMay();
    @Query("SELECT e.may - p.may from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForJune();
    @Query("SELECT e.june - p.june from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForJuly();
    @Query("SELECT e.july - p.july from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForAugust();
    @Query("SELECT e.august - p.august from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForSeptember();
    @Query("SELECT e.september - p.september from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForOctober();
    @Query("SELECT e.october - p.october from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForNovember();
    @Query("SELECT e.november - p.november from ProfitTotalEntity e, SpendingTotalEntity p" )
    Double restForDecember();
//    @Query("SELECT e.december - p.december from ProfitTotalEntity e, SpendingTotalEntity p" )
//    Double restForJanuary();
    @Modifying
    @Query("UPDATE ProfitEntity e SET e.year = e.january + e.february + e.march + e.april + e.may + " +
            "e.june + e.july + e.august + e.september + e.october + e.november + e.december WHERE e.article = e.article and not e.article = 'Balance at the beginning'")
    void sumProfitLine();

    @Query("SELECT sum (e.january) from ProfitEntity e ")
    Double totalJan();
    @Query("SELECT sum (e.february) from ProfitEntity e ")
    Double totalFeb();
    @Query("SELECT sum (e.march) from ProfitEntity e ")
    Double totalMar();
    @Query("SELECT sum (e.april) from ProfitEntity e ")
    Double totalApr();
    @Query("SELECT sum (e.may) from ProfitEntity e ")
    Double totalMay();
    @Query("SELECT sum (e.june) from ProfitEntity e ")
    Double totalJun();
    @Query("SELECT sum (e.july) from ProfitEntity e ")
    Double totalJul();
    @Query("SELECT sum (e.august) from ProfitEntity e ")
    Double totalAug();
    @Query("SELECT sum (e.september) from ProfitEntity e ")
    Double totalSep();
    @Query("SELECT sum (e.october) from ProfitEntity e ")
    Double totalOct();
    @Query("SELECT sum (e.november) from ProfitEntity e ")
    Double totalNov();
    @Query("SELECT sum (e.december) from ProfitEntity e ")
    Double totalDec();
    @Query("SELECT sum (e.year) from ProfitEntity e ")
    Double totalYear();

}