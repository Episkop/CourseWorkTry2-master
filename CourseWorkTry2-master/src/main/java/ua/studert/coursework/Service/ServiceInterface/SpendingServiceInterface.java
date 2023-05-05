package ua.studert.coursework.Service.ServiceInterface;

import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.SpendingModel;
import ua.studert.coursework.Model.SpendingTotalModel;

import java.util.List;

public interface SpendingServiceInterface {

    List<SpendingModel> getAllSpending() throws DBIsEmptyException;

    List<SpendingTotalModel> getAllTotalSpending() throws DBIsEmptyException;

    boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double sum) throws AlreadyExistException;

    boolean addSpendingTotal(SpendingTotalModel spendingTotalModelTotalModel);

    SpendingModel findByArticle(String article) throws NotFoundException;

    boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                           Double june, Double july, Double august, Double september, Double october, Double november,
                           Double december) throws NotFoundException;

    void countSum();

    void deleteSpending(String article) throws NotFoundException;
}
