
package Mine;

public class Permissions extends Licenses{

    public Permissions() {
        super(5);
    }
     @Override
    public boolean registerDays(int hours,Employee employee){
        if (employee.getPermissions() + hours <= maximunDays){
            employee.setPermissions(employee.getPermissions() + hours);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int hours, Employee employee){
        if(employee.getPermissions() < maximunDays){
            return registerDays(hours,employee);
        }
        else{
            return false;
        }
    }
}
