package ua.studert.coursework.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Model.ProfitTotalModel;
import ua.studert.coursework.Model.SpendingTotalModel;
import ua.studert.coursework.Service.SpendingService;
import ua.studert.coursework.Service.ProfitService;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner commandLineRunner(final ProfitService profitService,final SpendingService spendingService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                profitService.addProfit("Opening balance",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Balance at the beginning", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Salary", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Credit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Deposit interest", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Gifts", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                profitService.addProfit("Sell", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

                spendingService.addSpending("Rental of property", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Communal expenses", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Products", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Alcohol", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Transport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Car", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Gifts", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Sport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Heals", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Other", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                spendingService.addSpending("Credit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

                profitService.addProfitTotal(new ProfitTotalModel("Total incomes",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

                spendingService.addSpendingTotal(new SpendingTotalModel("Total expenses",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

            }
        };
    }
}

//            private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//            @Bean
//            CommandLineRunner initDatabase(EmployeeRepository repository) {
//
//                return args -> {
//            log.info("Preloading " + repository.save(new ProfitEntity("Salary",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Credit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Deposit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Gifts",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Sell",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//       };

