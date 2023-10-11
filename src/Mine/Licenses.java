
package Mine;

public class Licenses {
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

    public boolean registerDays(int days,Employee employee){
        if (employee.getDaysTaken() + days <= maximunDays){
             employee.setDaysTaken(employee.getDaysTaken() + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean extend(int days, Employee employee){
        if(employee.getDaysTaken() < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
    
}
