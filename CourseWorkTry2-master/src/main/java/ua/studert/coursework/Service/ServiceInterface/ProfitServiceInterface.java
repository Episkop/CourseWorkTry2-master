package ua.studert.coursework.Service.ServiceInterface;

import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Model.ProfitTotalModel;

import java.util.List;

public interface ProfitServiceInterface {
    List<ProfitModel> getAllProfit() throws DBIsEmptyException;

    List<ProfitTotalModel> getAllTotalProfit() throws DBIsEmptyException;

    boolean addProfit(String article, Double january, Double february, Double march, Double april, Double may,
                      Double june, Double july, Double august, Double september, Double october, Double november,
                      Double december, Double year) throws AlreadyExistException;


    boolean addProfitTotal(ProfitTotalModel profitTotalModel);

    ProfitModel findByArticle(String article) throws NotFoundException;

    boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                         Double june, Double july, Double august, Double september, Double october, Double november,
                         Double december, Double sum) throws NotFoundException;

    void countSumLine();

    void countSum();

    void balance();

    boolean startUpCapital(String article, Double january, Double february, Double march, Double april, Double may,
                           Double june, Double july, Double august, Double september, Double october, Double november,
                           Double december, Double year) throws NotFoundException;

    void deleteProfit(String article) throws NotFoundException;

}
