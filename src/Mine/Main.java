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
        Disabilities disability = new Disabilities();
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


                                        for(Employee employee: employees){
                                            if(employee.getName().equals(name) && employee.getId().equals(id)){
                                                employeeFound = true;
                                                employee.showPersonalInformation();
                                                days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de licencia temporal (maximo 04 dias )?"));
                                                licenseIsValid = temporaryLicense.registerDays(days,employee);
                                                if(licenseIsValid){
                                                    JOptionPane.showMessageDialog(null, "La solicitud de licencia ha tenido exito\ntienes una licencia temporal activa de " + days + " dias");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDaysTaken() + " dias de licencia temporal\nDebes solicitar vacaciones");
                                                }
                                                break;
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
                                        id = JOptionPane.showInputDialog("nIngrese el numero de cedula de " + name + ": "); 
                                        employeeFound = false;

                                        for(Employee employee: employees){
                                            if(employee.getName().equals(name) && employee.getId().equals(id)){
                                                employeeFound = true;
                                                boolean genderIsValid = (employee.getGender().equals("Femenino"));
                                                
                                                if(genderIsValid){
                                                    employee.showPersonalInformation();
                                                    days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de la licencia de maternidad (maximo 180 dias )?"));
                                                    licenseIsValid = maternityLicense.registerDays(days,employee);
                                                    
                                                    if(licenseIsValid){
                                                        JOptionPane.showMessageDialog(null, "La solicitud de licencia de paternidad ha tenido exito\ntienes una licencia de maternidad activa activa por " + days + " dias\nSi desea mas dias vuelva a ingresar la solicitud para hacer una prorroga de la licencia");
                                                        break;
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido licencia de maternidad dias de licencia de maternidad");
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
                                                    days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de la licencia de paternidad (maximo 15 dias )?"));
                                                    licenseIsValid = paternityLicense.registerDays(days,employee);
                                                    if(licenseIsValid){
                                                        JOptionPane.showMessageDialog(null, "La solicitud de licencia  ha tenido exito\ntienes una licencia de paternidad activa por " + days + " dias\nSi desea mas dias vuelva a ingresar la solicitud para hacer una prorroga de la licencia");
                                                        break;
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDaysTakenPaternity() + " dias de licencia de paternidad, solo puedes tener 15 dias maximo");
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
                                    days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de incapacidad (maximo 20 dias)?"));
                                    licenseIsValid = disability.registerDays(days,employee);
                                    if(licenseIsValid){
                                        JOptionPane.showMessageDialog(null, "La solicitud de incapacidad  ha tenido exito\ntienes una incapacidad activa por " + days + " dias\nSi desea mas dias vuelva a ingresar la solicitud de incapacidad");
                                        break;
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getDisabilities() + " dias, solo pedes tener 20 dias máximo de incapacidad");
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
                        case 3 -> {//3. Vacations
                            name = JOptionPane.showInputDialog("Ingreso de  Vacaciones\nIngrese el nombre del empleado: ");
                            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                            id = JOptionPane.showInputDialog("Ingrese el numero de cedula de " + name + ": "); 
                            employeeFound = false;

                            for(Employee employee: employees){
                                if(employee.getName().equals(name) && employee.getId().equals(id)){
                                    employeeFound = true;
                                    
                                    employee.showPersonalInformation();
                                    days = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos dias desea tomar de vacaciones (maximo 15 dias )?"));
                                    licenseIsValid = vacations.registerDays(days,employee);
                                    if(licenseIsValid){
                                        JOptionPane.showMessageDialog(null, "La solicitud de vacaciones  ha tenido exito\ntienes unas vacaciones activas por " + days + " dias\nSi desea mas dias vuelva a ingresar la solicitud de vacaciones");
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Error! No tienes " + days + " dias disponibles ya has pedido " + employee.getVacations() + " dias de Vacaciones (Solo puedes tener máximo 15 dias)");
                                    }
                                    break;
                                    
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
                                    hours = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas horas desea tomar de permiso (maximo 5 horas )?"));
                                    licenseIsValid = permissions.registerDays(hours,employee);
                                    if(licenseIsValid){
                                        JOptionPane.showMessageDialog(null, "La solicitud de permiso  ha tenido exito\ntienes unas permiso activo por " + hours + " horas\nSi desea mas dias vuelva a ingresar la solicitud de vacaciones");
                                        break;
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Error! No tienes " + hours + " horas disponibles ya has pedido " + employee.getPermissions() + " horas de permiso (Solo puedes tener máximo 5 horas)\nMejor solicita 1 dia de vacaciones o licencia temporal");
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
