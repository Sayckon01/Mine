
package Mine;

public class TemporaryLicense extends Licenses {

    public TemporaryLicense() {
        super(4);
    }
    
    @Override
    public boolean registerDays(int days,Employee employee){
        if (employee.getDaysTaken() + days <= maximunDays){
             employee.setDaysTaken(employee.getDaysTaken() + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int days, Employee employee){
        if(employee.getDaysTaken() < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
    
}
