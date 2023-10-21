
package Mine;

public abstract class Licenses {
    protected int maximunDays;

    public Licenses(int maximunDays) {
        this.maximunDays = maximunDays;
    }


    public int getMaximunDays() {
        return maximunDays;
    }

    public void setMaximunDays(int maximunDays) {
        this.maximunDays = maximunDays;
    }
    
    public abstract boolean registerDays(int days, Employee employee);
    
    public abstract boolean extend(int days, Employee employee);
    
}
