package ua.studert.coursework.Entity;

import lombok.Getter;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Model.StartUpModel;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "profit")
public class ProfitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name ="article",nullable = false)
    private String article;
    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;
    private Double year;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public ProfitEntity(String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double year) {
        this.article = article;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.year = year;

    }

    public ProfitEntity() {
    }

    public static ProfitEntity of(String article, Double january, Double february, Double march, Double april, Double may,
                                          Double june, Double july, Double august, Double september, Double october, Double november,
                                          Double december, Double year){
        return new ProfitEntity(article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
    }

    public static ProfitEntity fromModel(StartUpModel startUpModel) {
        return ProfitEntity.of(startUpModel.getArticle(),startUpModel.getJanuary(),startUpModel.getFebruary(),startUpModel.getMarch(),startUpModel.getApril(),
                startUpModel.getMay(), startUpModel.getJune(),startUpModel.getJuly(), startUpModel.getAugust(), startUpModel.getSeptember(), startUpModel.getOctober(),
                startUpModel.getNovember(), startUpModel.getDecember(), startUpModel.getYear());
    }

    public ProfitModel toModel() {
        return ProfitModel.of(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }

    public static ProfitEntity fromModel (ProfitModel model){
        return ProfitEntity.of(model.getArticle(),model.getJanuary(),model.getFebruary(),model.getMarch(),model.getApril(),
                model.getMay(), model.getJune(),model.getJuly(), model.getAugust(), model.getSeptember(), model.getOctober(),
                model.getNovember(), model.getDecember(), model.getYear());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
