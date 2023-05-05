package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ua.studert.coursework.Configuration.AppConfig;
import ua.studert.coursework.Entity.ProfitEntity;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Entity.UserEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Model.SpendingModel;
import ua.studert.coursework.Model.UserModel;
import ua.studert.coursework.Repository.ProfitRepository;
import ua.studert.coursework.Repository.SpendingRepository;
import ua.studert.coursework.Repository.UserRepository;
import ua.studert.coursework.Service.ServiceInterface.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  implements UserServiceInterface {
    private final UserRepository userRepository;
    private final ProfitRepository profitRepository;
    private final SpendingRepository spendingRepository;

    public UserService(UserRepository userRepository,
                       ProfitRepository profitRepository,
                       SpendingRepository spendingRepository) {
        this.userRepository = userRepository;
        this.profitRepository = profitRepository;
        this.spendingRepository = spendingRepository;
    }
    @Transactional
    @Override
    public void addUser(UserModel userModel, List<ProfitModel> profitModels, List<SpendingModel> spendingModels) throws AlreadyExistException {
        if (userRepository.existsByEmail(userModel.getEmail()))
            throw new AlreadyExistException("Such User " + userModel.getUsername() + " exist");
           // return; // do nothing

        UserEntity user = UserEntity.fromModel(userModel);

        profitModels.forEach((x) -> {
            ProfitEntity profit = ProfitEntity.fromModel(x);
            user.addProfitEntity(profit);
        });

        spendingModels.forEach((x) -> {
            SpendingEntity spending = SpendingEntity.fromModel(x);
            user.addSpendingEntity(spending);
        });

        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserModel> getAllUsers() throws DBIsEmptyException {
        List<UserModel> modelList = new ArrayList<>();
        List<UserEntity> list = userRepository.findAll();
        if(list.isEmpty()){
            throw new DBIsEmptyException("Data Base is empty");
        }
        list.forEach(x ->modelList.add(x.toModel()));
        return modelList;
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel getUserByEmail(String email) throws NotFoundException {
        UserModel userModel = new UserModel();
        UserEntity user = userRepository.findByEmail(email);
        if(email.isEmpty()){
            throw new NotFoundException("User don`t find");
        }
        return user.toModel();
    }
//    @Transactional
//    public boolean registration(UserEntity userEntity) throws AlreadyExistException {
//        if(userRepository.existsByUsername(userEntity.getUsername())){
//            throw new AlreadyExistException("Such " + userEntity.getUsername() + "is already exist");
//        }
//        userRepository.save(userEntity);
//        return true;
//    }
//    @Transactional
//    @Override
//    public boolean addUser(String username, String email) throws AlreadyExistException {
//        if(userRepository.existsByUsername(username)) {
//            throw new AlreadyExistException("Such " + username + "is already exist");
//        }
//        UserEntity user = new UserEntity(username,email);
//         userRepository.save(user);
//         return true;
//    }

    @Transactional
    @Override
    public void delete(List<Long> idList) {
        idList.forEach(profitRepository::deleteById);
        idList.forEach(spendingRepository::deleteById);
    }

//    @Transactional
//    @Override
//    public void deleteUsers(List<Long> ids) {
//        ids.forEach(id -> {
//            Optional<UserEntity> user = userRepository.findById(id);
//            user.ifPresent(u -> {
//                if ( ! AppConfig.ADMIN.equals(u.getUsername())) {
//                    userRepository.deleteById(u.getId());
//                }
//            });
//        });
//    }

}
