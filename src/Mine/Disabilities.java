
package Mine;

public class Disabilities extends Licenses {
    
    public Disabilities() {
        super(20);
    }
    
    @Override
    public boolean registerDays(int days,Employee employee){
        if (employee.getDisabilities() + days <= maximunDays){
            employee.setDisabilities(employee.getDisabilities()  + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int days, Employee employee){
        if(employee.getDisabilities()  < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
}
