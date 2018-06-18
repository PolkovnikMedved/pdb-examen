package be.solodoukhin.model.enumeration;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public enum Step {
    ENTREE,
    PLAT,
    DESSERT,
    AUCCUNE;

    public static Step getFromCode(String code)
    {
        if (code == null)
        {
            return Step.AUCCUNE;
        }
        switch (code)
        {
            case "E":
                return Step.ENTREE;
            case "P":
                return Step.PLAT;
            case "D":
                return Step.DESSERT;
            default:
                return Step.AUCCUNE;
        }
    }

    public static String getCode(Step step)
    {
        if(step == null || step.equals(Step.AUCCUNE)) return null;

        return step.name().substring(0,1);
    }
}
