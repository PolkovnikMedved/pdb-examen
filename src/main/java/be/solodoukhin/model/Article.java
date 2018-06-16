package be.solodoukhin.model;

import be.solodoukhin.model.enumeration.Step;

import java.util.Optional;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 *
 * Cette classe représente la table TARTICLE
 * Elle possède:
 *  - le lien vers la table TCATEGORIE
 *  - un constructeur par défaut
 *  - un champ "nullable" qui se traduit en Java 8 par un Optional.ofNullable(this.description) pour
 *      que l'application soit NullPointer Safe.
 *  - méthodes hashCode et equals autmatiquement générées par Intellij
 */
public class Article {

    /*
        ID = CODE_ART
     */
    private final String code;
    /*
        NOM_ART
     */
    private String name;
    /*
        DESCRIPTION_ART
     */
    private String description;
    /*
        CALORIE_ART
     */
    private Integer calories;
    /*
        PRIX_ART
     */
    private Double price;
    /*
        DISPO_ART
     */
    private Boolean isAvailable;
    /*
        FK_ETAPE_ART
     */
    private Step step;
    /*
        FKCATEGORIE_ART
     */
    private Category category;

    public Article(String code, String name, String description, Integer calories, Double price, Boolean isAvailable, Step step, Category category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.price = price;
        this.isAvailable = isAvailable;
        this.step = step;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (code != null ? !code.equals(article.code) : article.code != null) return false;
        if (name != null ? !name.equals(article.name) : article.name != null) return false;
        if (description != null ? !description.equals(article.description) : article.description != null) return false;
        if (calories != null ? !calories.equals(article.calories) : article.calories != null) return false;
        if (price != null ? !price.equals(article.price) : article.price != null) return false;
        if (isAvailable != null ? !isAvailable.equals(article.isAvailable) : article.isAvailable != null) return false;
        if (step != article.step) return false;
        return category != null ? category.equals(article.category) : article.category == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (calories != null ? calories.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", step=" + step +
                ", category=" + category +
                '}';
    }
}
