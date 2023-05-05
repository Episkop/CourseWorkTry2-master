package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Entity.SpendingTotalEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.SpendingModel;
import ua.studert.coursework.Model.SpendingTotalModel;
import ua.studert.coursework.Repository.ProfitRepository;
import ua.studert.coursework.Repository.ProfitTotalEntityRepository;
import ua.studert.coursework.Repository.SpendingRepository;
import ua.studert.coursework.Repository.SpendingTotalEntityRepository;
import ua.studert.coursework.Service.ServiceInterface.SpendingServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingService implements SpendingServiceInterface {
    private final SpendingRepository spendingRepository;
    private final ProfitTotalEntityRepository profitTotalEntityRepository;
    private final SpendingTotalEntityRepository spendingTotalEntityRepository;
    private final ProfitRepository profitRepository;
    private final ProfitService profitService;

    public SpendingService(SpendingRepository spendingRepository,
                           ProfitTotalEntityRepository profitTotalEntityRepository,
                           SpendingTotalEntityRepository spendingTotalEntityRepository,
                           ProfitRepository profitRepository, ProfitService profitService) {
        this.spendingRepository = spendingRepository;
        this.profitTotalEntityRepository = profitTotalEntityRepository;
        this.spendingTotalEntityRepository = spendingTotalEntityRepository;
        this.profitRepository = profitRepository;
        this.profitService = profitService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpendingModel> getAllSpending() throws DBIsEmptyException {
        List<SpendingModel> modelList = new ArrayList<>();
        List<SpendingEntity> list = spendingRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpendingTotalModel> getAllTotalSpending() throws DBIsEmptyException {
        List<SpendingTotalModel> modelList = new ArrayList<>();
        List<SpendingTotalEntity> list = spendingTotalEntityRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Transactional
    @Override
    public boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                               Double june, Double july, Double august, Double september, Double october, Double november,
                               Double december, Double year) throws AlreadyExistException {
        if (spendingRepository.existsByArticle(article))
            return false;
        SpendingModel model = new SpendingModel(article, january, february, march, april, may, june, july, august,
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
        spendingRepository.save(SpendingEntity.fromModel(model));
        return true;
    }

    @Transactional
    @Override
    public boolean addSpendingTotal(SpendingTotalModel spendingTotalModelTotalModel) {
        if (spendingTotalEntityRepository.existsByArticle(spendingTotalModelTotalModel.getArticle()))
            return false;
        SpendingTotalEntity spendingTotalEntity = SpendingTotalEntity.fromModel(spendingTotalModelTotalModel);
        spendingTotalEntityRepository.save(spendingTotalEntity);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public SpendingModel findByArticle(String article) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Didn`t find article " + spending.getArticle());
        }
        return spending.toModel();
    }

    @Transactional
    @Override
    public boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            spending.setJanuary(january);
        if (february != null)
            spending.setFebruary(february);
        if (march != null)
            spending.setMarch(march);
        if (april != null)
            spending.setApril(april);
        if (may != null)
            spending.setMay(may);
        if (june != null)
            spending.setJune(june);
        if (july != null)
            spending.setJuly(july);
        if (august != null)
            spending.setAugust(august);
        if (september != null)
            spending.setSeptember(september);
        if (october != null)
            spending.setOctober(october);
        if (november != null)
            spending.setNovember(november);
        if (december != null)
            spending.setDecember(december);
        countSum();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                profitService.countSum();
            }
            profitService.balance();
        }
        spendingRepository.sumSpendingLine(january, february, march, april, may, june, july, august,
                september, october, november, december, article);
        countSum();
        profitService.countSum();
        profitService.countSumLine();
        spendingRepository.save(spending);
        return true;
    }

    @Transactional
    @Override
    public void countSum() {
        SpendingTotalEntity ste = spendingTotalEntityRepository.findByArticle("Total expenses");
        Double jan = spendingRepository.totalJan();
        ste.setJanuary(jan);
        Double feb = spendingRepository.totalFeb();
        ste.setFebruary(feb);
        Double mar = spendingRepository.totalMar();
        ste.setMarch(mar);
        Double apr = spendingRepository.totalApr();
        ste.setApril(apr);
        Double ma = spendingRepository.totalMay();
        ste.setMay(ma);
        Double jun = spendingRepository.totalJun();
        ste.setJune(jun);
        Double jul = spendingRepository.totalJul();
        ste.setJuly(jul);
        Double aug = spendingRepository.totalAug();
        ste.setAugust(aug);
        Double sep = spendingRepository.totalSep();
        ste.setSeptember(sep);
        Double oct = spendingRepository.totalOct();
        ste.setOctober(oct);
        Double nov = spendingRepository.totalNov();
        ste.setNovember(nov);
        Double dec = spendingRepository.totalDec();
        ste.setDecember(dec);
        Double su = spendingRepository.totalYear();
        ste.setYear(su);
        spendingTotalEntityRepository.save(ste);
    }

    @Transactional
    @Override
    public void deleteSpending(String article) throws NotFoundException {
        spendingRepository.deleteSpendingEntityByArticle(article);
        countSum();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                profitService.countSum();
            }
            profitService.balance();
        }
    }
}

