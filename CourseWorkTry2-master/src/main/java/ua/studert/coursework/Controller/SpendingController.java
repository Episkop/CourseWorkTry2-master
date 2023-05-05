package ua.studert.coursework.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Service.ProfitService;
import ua.studert.coursework.Service.SpendingService;

@RestController
@RequestMapping("/spending")
public class SpendingController {
    private SpendingService spendingService;
    private ProfitService profitService;

    public SpendingController(SpendingService spendingService, ProfitService profitService) {
        this.spendingService = spendingService;
        this.profitService = profitService;
    }


    @GetMapping
    public ResponseEntity getAllSpending() {
        try {
            return ResponseEntity.ok(spendingService.getAllSpending());
        } catch (DBIsEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Data Base is empty!");
        }
    }

    @GetMapping("/one")
    public ResponseEntity getOne(@RequestParam String article) {
        try {
            return ResponseEntity.ok(spendingService.findByArticle(article));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found!");
        }
    }

    @GetMapping("/total")
    public ResponseEntity getTotal() {
        try {
            return ResponseEntity.ok(spendingService.getAllTotalSpending());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveProfit(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                     @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                     @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                     @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                     @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                     @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                     @RequestParam(required = false) Double december, @RequestParam(required = false) Double sum) {
        try {
            return ResponseEntity.ok(spendingService.addSpending(article, january, february, march, april, may, june, july, august,
                    september, october, november, december, sum));
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Line didn`t save");
        }

    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                 @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                 @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                 @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                 @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                 @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                 @RequestParam(required = false) Double december) {
        try {
            spendingService.updateSpending(article, january, february, march, april, may, june, july, august,
                    september, october, november, december);
            return ResponseEntity.ok("Success!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @DeleteMapping("/delete/{article}")
    public ResponseEntity delete(@PathVariable String article) {
        try {
            spendingService.deleteSpending(article);
            return ResponseEntity.ok("Successes! The line was deleted! " + article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Line not found!");
        }
    }
}

