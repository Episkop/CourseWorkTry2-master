package ua.studert.coursework.Service.ServiceInterface;

import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Model.SpendingModel;
import ua.studert.coursework.Model.UserModel;

import java.util.List;

public interface UserServiceInterface {

    void addUser(UserModel userModel, List<ProfitModel> profitModels, List<SpendingModel> spendingModels) throws AlreadyExistException;

    public List<UserModel> getAllUsers() throws DBIsEmptyException;

    UserModel getUserByEmail(String email) throws NotFoundException;

    void delete(List<Long> idList);



}
