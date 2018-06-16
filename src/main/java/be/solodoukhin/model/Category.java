package be.solodoukhin.model;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 *
 * Cette classe représente la table TCATEGORIE
 */
public class Category {

    /*
        ID = CODE_CAT
     */
    private final String code;
    /*
        NOM_CAT
     */
    private final String name;
    /*
        FKCATPRINC_CAT
     */
    private Category parentCategory;

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (code != null ? !code.equals(category.code) : category.code != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return parentCategory != null ? parentCategory.equals(category.parentCategory) : category.parentCategory == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parentCategory=" + parentCategory +
                '}';
    }
}
