/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mine;

public class PaternityLicense extends Licenses {

    public PaternityLicense() {
        super( 15);
    }
    
    @Override
    public boolean registerDays(int days,Employee employee){
        if (employee.getDaysTakenPaternity() + days <= maximunDays){
            employee.setDaysTakenPaternity(employee.getDaysTakenPaternity() + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int days, Employee employee){
        if(employee.getDaysTakenPaternity() < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
}
