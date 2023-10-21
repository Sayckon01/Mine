package Mine;


import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
        //Menus
        String mainMenu = """
                                            MENU
                            1. Ingreso de jornada laboral
                            2. Ingreso de una novedad
                            3. Salir.
                            """;
        String secondMenu = """
                                            Ingreso de una novedad
                            1. Licencias
                            2. Incapacidades
                            3. Vacaciones
                            4. Permisos
                            5. Atras.
                            """;
        String licenseMenu = """
                                             Solicitar licencias
                             1. Licencia temporal.
                             2. Licencia de maternidad.
                             3. Licencia de paternidad.
                             4. Cancelar
                             """;
        
        int option = 0;
        int days,hours,optionLicense,optionSec;
        String name,id;
        boolean employeeFound,licenseIsValid;
        
        //Array to save employees as if it were a database
        Employee[] employees = new Employee[3];
        
        //Creation of 3 employees in the array //1 year worked = 240 days, working 40 hours per week
        employees[0] = new Employee("carlos", "perez","maSCulino", "101010", 1920);
        employees[1] = new Employee("saRa", "osSa","Femenino", "101020", 2000);
        employees[2] = new Employee("Santiago", "Smith","maSCulino", "101030", 2120);
        
        //License creation
        MaternityLicense maternityLicense = new MaternityLicense();
        TemporaryLicense temporaryLicense = new TemporaryLicense();
        PaternityLicense paternityLicense = new PaternityLicense();
        Disabilities disabilities = new Disabilities();
        Vacations vacations = new Vacations();
        Permissions permissions = new Permissions();
        
        //Main program loop
        while(option!=3){
            //main menu
            option = Integer.parseInt(JOptionPane.showInputDialog(mainMenu));
            switch(option){
                case 1 -> {
                    //enter work day
                    employeeFound = false;
                    name = JOptionPane.showInputDialog("              Ingreso de jornada\nIngrese el nombre del empleado: ");
                    name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                    id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": ");
                    for(Employee employee: employees){
                        if(employee.getName().equals(name) && employee.getId().equals(id)){
                            employeeFound = true;
                            employee.showPersonalInformation();
                            String startDate = JOptionPane.showInputDialog("              Ingreso de jornada\nIngrese la fecha de inicio\nFormato valido dd/mm/aaaa ,sin espacios\nEjemplos valido: 28/02/2023");
                            String startTime = JOptionPane.showInputDialog("              Ingreso de jornada\nIngrese la hora de inicio\n Formato valido (24H): hh:mm ,sin espacios\nEjemplo valido: 23:00");
                            
                            String endDate = JOptionPane.showInputDialog("              Ingreso de jornada\nIngrese la fecha de finalizacion\nFormato valido dd/mm/aaaa ,sin espacios\nEjemplo valido: 01/03/2023");
                            String endTime = JOptionPane.showInputDialog("              Ingreso de jornada\nIngrese la hora de finalizacion\n Formato valido (24H): hh:mm ,sin espacios\nEjemplo valido: 08:24\n(Solo se permite ingresos de jornadas minimas de 8 horas)");
                            boolean validEntry = employee.enterHoursWorked(startTime, startDate, endTime, endDate);
                            if(validEntry){
                                JOptionPane.showMessageDialog(null, "Registro de jornada exitoso!\n a continuacion veras tus datos actualizados.");
                                employee.showPersonalInformation();
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "el registro de jornada NO tuvo exito!\nVerifique la informacion que esta ingresando\nDeben ser minimo 8 horas y las fechas no pueden exceder 1 dia");
                                break;
                            }
                        }
                    }
                    if(!employeeFound){
                        JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                        break;
                    }
                    break;
                    
                }
                case 2 -> {//enter a news
                    
                    //show news menu
                    optionSec = Integer.parseInt(JOptionPane.showInputDialog(secondMenu));

                    switch (optionSec){
                        case 1 -> {//1. Licenses
                               optionLicense = Integer.parseInt(JOptionPane.showInputDialog(licenseMenu));
                               switch (optionLicense){
                                   case 1://Temporary license
                                       //employee income
                                        name = JOptionPane.showInputDialog("Ingreso de una licencia temporal\nIngrese el nombre del empleado: ");
                                        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                                        id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                                        employeeFound = false;

                                        //for each to search for the employee
                                        for(Employee employee: employees){
                                            if(employee.getName().equals(name) && employee.getId().equals(id)){
                                                employeeFound = true;
                                                employee.showPersonalInformation();
                                                int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar licencia\n2. Prorrogar Licencia"));
                                                switch(choice){
                                                    case 1:
                                                        if(employee.getDaysTaken()==0){
                                                            days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de licencia temporal (maximo 04 dias )?"));
                                                            licenseIsValid = temporaryLicense.registerDays(days,employee);
                                                            if(licenseIsValid){
                                                                JOptionPane.showMessageDialog(null, "La solicitud de licencia ha tenido exito\ntienes una licencia temporal activa de " + days + " dias");
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Error! El limite licencia temporal son 4 dias maximo \nDebes solicitar vacaciones");
                                                            }
                                                            break;
                                                        }
                                                        else{
                                                            if(employee.getDaysTaken() != 4){
                                                                JOptionPane.showMessageDialog(null, "Error! Ya has solicitado la licencia temporal, actualmente has tomado " + employee.getDaysTaken() + " dias de 04, si deseas tomar una parte o la totalidad de " + (4-employee.getDaysTaken()) + " dias que quedan disponibles, debes prorrogar la licencia temporal");
                                                                break;
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getDaysTaken() + "  dias, la licencia temporal es de maximo 04 dias, debes solicitar vacaciones");
                                                                break;
                                                            }
                                                        }
                                                    case 2:
                                                        if(employee.getDaysTaken()!=4 && employee.getDaysTaken()>0){
                                                            days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea prorrogar la licencia temporal (maximo  " + (4-employee.getDaysTaken()) + " dias )?"));
                                                            licenseIsValid = temporaryLicense.extend(days,employee);
                                                            if(licenseIsValid){
                                                                JOptionPane.showMessageDialog(null, "La solicitud de licencia ha tenido exito\ntienes una licencia temporal activa de " + days + " dias");
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDaysTaken() + " dias de licencia temporal\nDebes solicitar vacaciones");
                                                            }
                                                            break;
                                                        }
                                                        else if(employee.getDaysTaken()==0){
                                                            JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar la licencia temporal, ya que nunca has activado una, debes solicitar la licencia temporal.");
                                                            break;
                                                        }
                                                        else{
                                                            JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas la licencia temporal, Debes solicitar vacaciones");
                                                            break;
                                                        }
                                                    default: 
                                                        JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                                }
                                                
                                                
                                            }
                                        }
                                        if(!employeeFound){
                                            JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                                            break;
                                        }
                                        break;
                                       
                                   case 2://Maternity license
                                        //Entering employee name and ID
                                        name = JOptionPane.showInputDialog("Ingreso de una licencia de maternidad\nIngrese el nombre del empleado: ");
                                        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                                        id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                                        employeeFound = false;

                                        for(Employee employee: employees){
                                            if(employee.getName().equals(name) && employee.getId().equals(id)){
                                                employeeFound = true;
                                                boolean genderIsValid = (employee.getGender().equals("Femenino"));
                                                
                                                if(genderIsValid){
                                                    employee.showPersonalInformation();
                                                    int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar licencia\n2. Prorrogar Licencia"));
                                                    switch(choice){
                                                        case 1:
                                                            if(employee.getDaysTakenMaternity() == 0){
                                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de la licencia de maternidad (maximo 180 dias )?"));
                                                                licenseIsValid = maternityLicense.registerDays(days,employee);

                                                                if(licenseIsValid){
                                                                    JOptionPane.showMessageDialog(null, "La solicitud de licencia de maternidad ha tenido exito\ntienes una licencia de maternidad activa activa por " + days + " dias.");
                                                                    break;
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! El limite de la licencia de maternidad son 180 dias maximo");
                                                                    break;
                                                                }
                                                            }
                                                            else{
                                                                if(employee.getDaysTakenMaternity() != 180){
                                                                    JOptionPane.showMessageDialog(null, "Error! Ya has solicitado la licencia de maternidad, actualmente has tomado " + employee.getDaysTakenMaternity() + " dias de 180, si deseas tomar una parte o la totalidad de " + (180-employee.getDaysTakenMaternity()) + " dias que quedan disponibles, debes prorrogar la licencia de maternidad");
                                                                    break;
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getDaysTakenMaternity() + "  dias, la licencia de maternidad es de maximo 180 dias");
                                                                    break;
                                                                }
                                                            }
                                                        case 2:
                                                            if(employee.getDaysTakenMaternity() != 180 && employee.getDaysTakenMaternity()> 0){
                                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea prorrogar la licencia de maternidad (maximo  " + (180-employee.getDaysTakenMaternity()) + " dias )?"));
                                                                licenseIsValid = maternityLicense.extend(days,employee);
                                                                if(licenseIsValid){
                                                                    JOptionPane.showMessageDialog(null, "La prorroga de licencia ha tenido exito\ntienes una licencia de maternidad activa de " + days + " dias");
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDaysTakenMaternity() + " dias de licencia de maternidad.");
                                                                }
                                                                break;
                                                            }
                                                            else if(employee.getDaysTakenMaternity()==0){
                                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar la licencia de maternidad, ya que nunca has activado una, debes solicitarla.");
                                                                break;
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas la licencia de maternidad.");
                                                                break;
                                                            }
                                                    default: 
                                                        JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                                        break;
                                                    }
                                                    
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Error! Genero del empleado incorrecto\npara solicitar licencia de maternidad debe ser genero femenino");
                                                    break;
                                                }
                                            }
                                        }
                                        if(!employeeFound){
                                            JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                                            break;
                                        }
                                        break;
                                   case 3://Paternity license
                                        //Entering employee name and ID
                                        name = JOptionPane.showInputDialog("Ingreso de una licencia de paternidad\nIngrese el nombre del empleado: ");
                                        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                                        id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                                        employeeFound = false;

                                        for(Employee employee: employees){
                                            if(employee.getName().equals(name) && employee.getId().equals(id)){
                                                employeeFound = true;
                                                boolean genderIsValid = (employee.getGender().equals("Masculino"));
                                                
                                                if(genderIsValid){
                                                    employee.showPersonalInformation();
                                                    int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar licencia\n2. Prorrogar Licencia"));
                                                    switch(choice){
                                                        case 1:
                                                            if(employee.getDaysTakenPaternity() == 0){
                                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de la licencia de paternidad (maximo 15 dias )?"));
                                                                licenseIsValid = paternityLicense.registerDays(days,employee);

                                                                if(licenseIsValid){
                                                                    JOptionPane.showMessageDialog(null, "La solicitud de licencia de paternidad ha tenido exito\ntienes una licencia de paternidad activa por " + days + " dias.");
                                                                    break;
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! El limite de la licencia de paternidad son 15 dias maximo");
                                                                    break;
                                                                }
                                                            }
                                                            else{
                                                                if(employee.getDaysTakenPaternity() != 15){
                                                                    JOptionPane.showMessageDialog(null, "Error! Ya has solicitado la licencia de paternidad, actualmente has tomado " + employee.getDaysTakenPaternity() + " dias de 15\n si deseas tomar una parte o la totalidad de " + (15-employee.getDaysTakenPaternity()) + " dias que quedan disponibles, debes prorrogar la licencia de paternidad");
                                                                    break;
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getDaysTakenPaternity() + "  dias, la licencia de paternidad es de maximo 15 dias");
                                                                    break;
                                                                }
                                                            }
                                                        case 2:
                                                            if(employee.getDaysTakenPaternity() != 15 && employee.getDaysTakenPaternity()> 0){
                                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea prorrogar la licencia de paternidad (maximo  " + (15-employee.getDaysTakenPaternity()) + " dias )?"));
                                                                licenseIsValid = paternityLicense.extend(days,employee);
                                                                if(licenseIsValid){
                                                                    JOptionPane.showMessageDialog(null, "La prorroga de licencia ha tenido exito\ntienes una licencia de paternidad activa de " + days + " dias");
                                                                }
                                                                else{
                                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDaysTakenPaternity() + " dias de licencia de paternidad.");
                                                                }
                                                                break;
                                                            }
                                                            else if(employee.getDaysTakenPaternity()==0){
                                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar la licencia de paternidad, ya que nunca has activado una, debes solicitarla.");
                                                                break;
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas la licencia de paternidad.");
                                                                break;
                                                            }
                                                    default: 
                                                        JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                                        break;
                                                    }
                                                    
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Error! Genero del empleado incorrecto\npara solicitar Licencia de paternidad debe ser genero masculino");
                                                    break;
                                                }
                                            }
                                        }
                                        
                                        if(!employeeFound){
                                            JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                                            break;
                                        }
                                        break;
                                   case 4://Cancel
                                       break;

                                   default: //Invalid
                                       JOptionPane.showMessageDialog(null, optionLicense +" No es una opcion valida");
                                       break;
                               }
                        }
                        case 2 -> {//2. disabilitties
                            name = JOptionPane.showInputDialog("Ingreso de una incapacidad\nIngrese el nombre del empleado: ");
                            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                            id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                            employeeFound = false;

                            for(Employee employee: employees){
                                if(employee.getName().equals(name) && employee.getId().equals(id)){
                                    employeeFound = true;
                                    
                                    employee.showPersonalInformation();
                                    int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar incapacidad\n2. Prorrogar incapacidad"));
                                    switch(choice){
                                        case 1:
                                            if(employee.getDisabilities()==0){
                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de incapacidad (maximo 20 dias )?"));
                                                licenseIsValid = disabilities.registerDays(days,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de incapacidad ha tenido exito\ntienes una incapacidad activa de " + days + " dias");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! El limite de incapacidad son 20 dias maximo.");
                                                }
                                                break;
                                            }
                                            else{
                                                if(employee.getDaysTaken() != 20){
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has solicitado la incapacidad, actualmente has tomado " + employee.getDisabilities() + " dias de 20,\n si deseas tomar una parte o la totalidad de " + (20-employee.getDisabilities()) + " dias que quedan disponibles, debes prorrogar la incapacidad");
                                                    break;
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getDisabilities() + "  dias, la incapacidad es de maximo 20 dias");
                                                    break;
                                                }
                                            }
                                        case 2:
                                            if(employee.getDisabilities()!= 20 && employee.getDisabilities() > 0){
                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea prorrogar la incapacidad (maximo  " + (20-employee.getDisabilities()) + " dias )?"));
                                                licenseIsValid = disabilities.extend(days,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de prorrogar incapacidad ha tenido exito\ntienes una incapacidad activa de " + days + " dias");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDisabilities() + " dias de incapacidad");
                                                }
                                                break;
                                            }
                                            else if(employee.getDisabilities()==0){
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar la incapacidad, ya que nunca has activado una, debes solicitar una incapacidad.");
                                                break;
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas la incapacidad.");
                                                break;
                                            }
                                        default: 
                                            JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                    }


                                }
                            }
                            if(!employeeFound){
                                JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                                break;
                            }
                            break;
                        }
                        case 3 -> {//3. Vacations
                            name = JOptionPane.showInputDialog("Ingreso de  Vacaciones\nIngrese el nombre del empleado: ");
                            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                            id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                            employeeFound = false;

                            for(Employee employee: employees){
                                if(employee.getName().equals(name) && employee.getId().equals(id)){
                                    employeeFound = true;
                                    
                                    employee.showPersonalInformation();
                                    
                                    int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar vacaciones\n2. Prorrogar vacaciones"));
                                    switch(choice){
                                        case 1:
                                            if(employee.getVacations()==0){
                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de vacaciones (maximo 15 dias )?"));
                                                licenseIsValid = vacations.registerDays(days,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de vacaciones ha tenido exito\ntienes unas vacaciones activas de " + days + " dias");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! El limite de vacaciones son 15 dias maximo.");
                                                }
                                                break;
                                            }
                                            else{
                                                if(employee.getVacations() != 15){
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has solicitado vacaciones, actualmente has tomado " + employee.getVacations() + " dias de 15,\n si deseas tomar una parte o la totalidad de " + (15-employee.getVacations()) + " dias que quedan disponibles, debes prorrogar las vacaciones");
                                                    break;
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getVacations() + "  dias, las vacaciones son de maximo 15 dias");
                                                    break;
                                                }
                                            }
                                        case 2:
                                            if(employee.getVacations()!= 15 && employee.getVacations() > 0){
                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea prorrogar las vacaciones (maximo  " + (15-employee.getVacations()) + " dias )?"));
                                                licenseIsValid = vacations.extend(days,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de prorrogar vacaciones ha tenido exito\ntienes unas vacaciones activas de " + days + " dias");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getVacations() + " dias de vacaciones");
                                                }
                                                break;
                                            }
                                            else if(employee.getVacations() == 0 ){
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar las vacaciones, ya que nunca has activado una, debes solicitar vacaciones.");
                                                break;
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas las vacaciones.");
                                                break;
                                            }
                                        default: 
                                            JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                    }


                                }
                            }
                            if(!employeeFound){
                                JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                            }
                            break;
                        }
                        case 4 -> {//4. Permissions
                            name = JOptionPane.showInputDialog("Ingreso de  Permisos\nIngrese el nombre del empleado: ");
                            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                            id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                            employeeFound = false;

                            for(Employee employee: employees){
                                if(employee.getName().equals(name) && employee.getId().equals(id)){
                                    employeeFound = true;
                                    employee.showPersonalInformation();
        
                                    int choice = Integer.parseInt(JOptionPane.showInputDialog("1. Solicitar permiso\n2. Prorrogar permisos"));
                                    switch(choice){
                                        case 1:
                                            if(employee.getPermissions()==0){
                                                hours = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas horas desea tomar de permiso (maximo 5 horas )?"));
                                                licenseIsValid = permissions.registerDays(hours,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de permiso ha tenido exito\ntienes un permiso activo de " + hours + " horas");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! El limite de permiso son 5 horas maximo.");
                                                }
                                                break;
                                            }
                                            else{
                                                if(employee.getPermissions() != 5){
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has solicitado un permiso, actualmente has tomado " + employee.getPermissions() + " horas de 5,\n si deseas tomar una parte o la totalidad de " + (5-employee.getPermissions()) + " horas que quedan disponibles, debes prorrogar el permiso");
                                                    break;
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! Ya has tomado " + employee.getPermissions() + "  horas, el permiso es de maximo 5 horas");
                                                    break;
                                                }
                                            }
                                        case 2:
                                            if(employee.getPermissions()!= 5 && employee.getPermissions() > 0){
                                                hours = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas horas desea prorrogar el permiso (maximo  " + (5-employee.getPermissions()) + " horas )?"));
                                                licenseIsValid = permissions.extend(hours,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de prorrogar permiso ha tenido exito\ntienes un permiso activo de " + hours + " horas");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + hours + " horas disponibles ya has pedido " + employee.getPermissions() + " horas de permiso, debes solicitar una licencia temporal");
                                                }
                                                break;
                                            }
                                            else if(employee.getPermissions() == 0 ){
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar un permiso, ya que nunca has activado uno, debes solicitar un permiso.");
                                                break;
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Error! No puedes prorrogar mas el permiso, debes solicitar una licencia temporal.");
                                                break;
                                            }
                                        default: 
                                            JOptionPane.showMessageDialog(null, choice +" No es una opcion valida");
                                    }


                                }
                            }
                            if(!employeeFound){
                                JOptionPane.showMessageDialog(null, "Error! El empleado no se encontro en la base de datos de la mina\nVerifique la informacion que esta ingresando o registre al empleado");
                                break;
                            }
                            break;
                        }
                        case 5 -> {//back
                            break;
                        }
                        default -> { //Invalid
                            JOptionPane.showMessageDialog(null, optionSec +" No es una opcion valida");
                            }
                        
                    }
                    
                }
                case 3 -> {//Exit
                    JOptionPane.showMessageDialog(null, " Gracias por usar este programa.");
                    break;
                }
                default -> {//Invalid option
                    JOptionPane.showMessageDialog(null, option +" No es una opcion valida");
                    break;
                }
            }

        }
    } 
}