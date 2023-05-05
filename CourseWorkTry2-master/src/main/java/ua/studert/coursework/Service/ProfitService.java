package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.ProfitEntity;
import ua.studert.coursework.Entity.ProfitTotalEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Model.ProfitTotalModel;
import ua.studert.coursework.Repository.ProfitRepository;
import ua.studert.coursework.Repository.ProfitTotalEntityRepository;
import ua.studert.coursework.Service.ServiceInterface.ProfitServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitService implements ProfitServiceInterface {

    private final ProfitRepository profitRepository;
    private final ProfitTotalEntityRepository profitTotalEntityRepository;

    public ProfitService(ProfitRepository profitRepository, ProfitTotalEntityRepository profitTotalEntityRepository) {
        this.profitRepository = profitRepository;
        this.profitTotalEntityRepository = profitTotalEntityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfitModel> getAllProfit() throws DBIsEmptyException {
        List<ProfitModel> modelList = new ArrayList<>();
        List<ProfitEntity> list = profitRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfitTotalModel> getAllTotalProfit() throws DBIsEmptyException {
        List<ProfitTotalModel> modelList = new ArrayList<>();
        List<ProfitTotalEntity> list = profitTotalEntityRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }
    @Transactional
    @Override
    public boolean addProfit(String article, Double january, Double february, Double march, Double april, Double may,
                             Double june, Double july, Double august, Double september, Double october, Double november,
                             Double december, Double year) throws AlreadyExistException {
        if (profitRepository.existsByArticle(article))
            return false;
        ProfitModel model = new ProfitModel(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
        if (january == null)
            model.setJanuary(0.0);
        if (february == null)
            model.setFebruary(0.0);
        if (march == null)
            model.setMarch(0.0);
        if (april == null)
            model.setApril(0.0);
        if (may == null)
            model.setMay(0.0);
        if (june == null)
            model.setJune(0.0);
        if (july == null)
            model.setJuly(0.0);
        if (august == null)
            model.setAugust(0.0);
        if (september == null)
            model.setSeptember(0.0);
        if (october == null)
            model.setOctober(0.0);
        if (november == null)
            model.setNovember(0.0);
        if (december == null)
            model.setDecember(0.0);
        if (year == null)
            model.setYear(year);
        profitRepository.save(ProfitEntity.fromModel(model));

        return true;
    }

    @Transactional
    @Override
    public boolean addProfitTotal(ProfitTotalModel profitTotalModel) {
        if (profitTotalEntityRepository.existsByArticle(profitTotalModel.getArticle()))
            return false;
        ProfitTotalEntity profitTotalEntity = ProfitTotalEntity.fromModel(profitTotalModel);
        profitTotalEntityRepository.save(profitTotalEntity);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfitModel findByArticle(String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Didn`t find article " + profit.getArticle());
        }
        return profit.toModel();
    }

    @Transactional
    @Override
    public boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                                Double june, Double july, Double august, Double september, Double october, Double november,
                                Double december, Double year) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if ("Balance at the beginning".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if ("Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            profit.setJanuary(january);
        if (february != null)
            profit.setFebruary(february);
        if (march != null)
            profit.setMarch(march);
        if (april != null)
            profit.setApril(april);
        if (may != null)
            profit.setMay(may);
        if (june != null)
            profit.setJune(june);
        if (july != null)
            profit.setJuly(july);
        if (august != null)
            profit.setAugust(august);
        if (september != null)
            profit.setSeptember(september);
        if (october != null)
            profit.setOctober(october);
        if (november != null)
            profit.setNovember(november);
        if (december != null)
            profit.setDecember(december);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum();
            }
            balance();
        }
        countSumLine();
        profitRepository.save(profit);
        return true;
    }

    @Transactional
    @Override
    public void countSumLine(){
        profitRepository.sumProfitLine();
        countSum();
    }

    @Transactional
    @Override
    public void countSum() {
        ProfitTotalEntity pte = profitTotalEntityRepository.findByArticle("Total incomes");
        Double jan = profitRepository.totalJan();
        pte.setJanuary(jan);
        Double feb = profitRepository.totalFeb();
        pte.setFebruary(feb);
        Double mar = profitRepository.totalMar();
        pte.setMarch(mar);
        Double apr = profitRepository.totalApr();
        pte.setApril(apr);
        Double ma = profitRepository.totalMay();
        pte.setMay(ma);
        Double jun = profitRepository.totalJun();
        pte.setJune(jun);
        Double jul = profitRepository.totalJul();
        pte.setJuly(jul);
        Double aug = profitRepository.totalAug();
        pte.setAugust(aug);
        Double sep = profitRepository.totalSep();
        pte.setSeptember(sep);
        Double oct = profitRepository.totalOct();
        pte.setOctober(oct);
        Double nov = profitRepository.totalNov();
        pte.setNovember(nov);
        Double dec = profitRepository.totalDec();
        pte.setDecember(dec);
        Double su = profitRepository.totalYear();
        pte.setYear(su);
        profitTotalEntityRepository.save(pte);
    }

    @Transactional
    @Override
    public void balance() {
        ProfitEntity profitRest = profitRepository.findByArticle("Balance at the beginning");
        Double february = profitRepository.restForFebruary();
        if (profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() == 0 || profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() != 0) ;
        else
            profitRest.setFebruary(february);
        Double march = profitRepository.restForMarch();
        if (profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() == 0 || profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() != 0) ;
        else
            profitRest.setMarch(march);
        Double april = profitRepository.restForApril();
        if (profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() == 0 || profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() != 0) ;
        else
            profitRest.setApril(april);
        Double may = profitRepository.restForMay();
        if (profitRest.getApril() == 0 && may == 0 && profitRest.getMay() == 0 || profitRest.getApril() == 0 && may == 0 && profitRest.getMay() != 0) ;
        else
            profitRest.setMay(may);
        Double june = profitRepository.restForJune();
        if (profitRest.getMay() == 0 && june == 0 && profitRest.getJune() == 0 || profitRest.getMay() == 0 && june == 0 && profitRest.getJune() != 0) ;
        else
            profitRest.setJune(june);
        Double july = profitRepository.restForJuly();
        if (profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() == 0 || profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() != 0) ;
        else
            profitRest.setJuly(july);
        Double august = profitRepository.restForAugust();
        if (profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() == 0 || profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() != 0) ;
        else
            profitRest.setAugust(august);
        Double september = profitRepository.restForSeptember();
        if (profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() == 0 || profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() != 0) ;
        else
            profitRest.setSeptember(september);
        Double october = profitRepository.restForOctober();
        if (profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() == 0 || profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() != 0) ;
        else
            profitRest.setOctober(october);
        Double november = profitRepository.restForNovember();
        if (profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() == 0 || profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() != 0) ;
        else
            profitRest.setNovember(november);
        Double december = profitRepository.restForDecember();
        if (profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() == 0 || profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() != 0) ;
        else
            profitRest.setDecember(december);
        profitRepository.save(profitRest);
    }

    //    Сохранение в "Opening balance"
    @Transactional
    @Override
    public boolean startUpCapital(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year) throws NotFoundException {
        ProfitEntity profitEntity = profitRepository.findByArticle("Opening balance");
        if (profitEntity == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            profitEntity.setJanuary(january);
        if (february != null)
            profitEntity.setFebruary(february);
        if (march != null)
            profitEntity.setMarch(march);
        if (april != null)
            profitEntity.setApril(april);
        if (may != null)
            profitEntity.setMay(may);
        if (june != null)
            profitEntity.setJune(june);
        if (july != null)
            profitEntity.setJuly(july);
        if (august != null)
            profitEntity.setAugust(august);
        if (september != null)
            profitEntity.setSeptember(september);
        if (october != null)
            profitEntity.setOctober(october);
        if (november != null)
            profitEntity.setNovember(november);
        if (december != null)
            profitEntity.setDecember(december);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum();
            }
            balance();
        }
        countSumLine();
        profitRepository.save(profitEntity);
        return true;
    }

    @Transactional
    @Override
    public void deleteProfit(String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if ("Balance at the beginning".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        }
        if ("Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        }
        profitRepository.deleteProfitEntityByArticle(article);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum();
            }
            balance();
        }
        countSumLine();
    }
}
