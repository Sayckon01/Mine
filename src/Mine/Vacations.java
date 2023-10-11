/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mine;

/**
 *
 * @author Alex
 */
public class Vacations extends Licenses{

    public Vacations() {
        super(15);
    }
    
    @Override
    public boolean registerDays(int days,Employee employee){
        if (employee.getVacations() + days <= maximunDays){
            employee.setVacations(employee.getVacations() + days);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean extend(int days, Employee employee){
        if(employee.getVacations() < maximunDays){
            return registerDays(days,employee);
        }
        else{
            return false;
        }
    }
}
