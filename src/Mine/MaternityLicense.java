
package Mine;

public class MaternityLicense extends Licenses{

    public MaternityLicense() {
        super(180);
    }
    
    @Override
    public boolean registerDays(int days,Employee employee){
        if (employee.getDaysTakenMaternity() + days <= maximunDays){
            employee.setDaysTakenMaternity(employee.getDaysTakenMaternity() + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int days, Employee employee){
        if(employee.getDaysTakenMaternity() < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
}
