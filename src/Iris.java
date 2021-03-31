public class Iris {
    protected double sepal_length;
    protected double sepal_width;
    protected double petal_length;
    protected double petal_width;
    protected String species;

    //default constructor
    public Iris() {
        double sepal_length;
        double sepal_width;
        double petal_length;
        double petal_width;
        String species;
    }

    //parametrized constructor
    public Iris(double sepal_length, double sepal_width, double petal_length, double petal_width, String species) {
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;
        this.species = species;
    }
    //copy constructor
    public Iris(Iris i) {
        this.sepal_length = i.sepal_length;
        this.sepal_width = i.sepal_width;
        this.petal_length = i.petal_length;
        this.petal_width = i.petal_width;
    }

    //getters
    public double getSepal_length() {
        return sepal_length;
    }
    public double getSepal_width() {
        return sepal_width;
    }
    public double getPetal_length() {
        return petal_length;
    }
    public double getPetal_width() {
        return petal_width;
    }
    public String getSpecies() {
        return species;
    }
    //setters
    public void setSepal_length(double sepal_length) {
        this.sepal_length = sepal_length;
    }

    public void setSepal_width(double sepal_width) {
        this.sepal_width = sepal_width;
    }
    public void setPetal_length(double petal_length) {
        this.petal_length = petal_length;
    }
    public void setPetal_width(double petal_width) {
        this.petal_width = petal_width;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    //@Override
    public String toString() {
        return("The flower " + species);
    }

//    public int compareTo(Iris Iris2) {
//
//    }
//    boolean isLessThan(Iris Iris2) {
//
//    }
}
