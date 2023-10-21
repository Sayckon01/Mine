package Mine;



import javax.swing.JOptionPane;
public class Employee {
    //Attributes
    private String name, lastName,id;
    private String gender;
    private double totalHoursWorked;
    private int daysTaken,daysTakenMaternity,daysTakenPaternity,vacations,permissions,disabilities;
    
    
    //constructor method
    public Employee(String name, String lastName,String gender, String id,double totalHoursWorked){
            this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
            this.gender = gender.substring(0,1).toUpperCase() + gender.substring(1).toLowerCase();
            this.id = id;
            this.totalHoursWorked = totalHoursWorked;
            }
    
    //Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }

    public int getDaysTakenMaternity() {
        return daysTakenMaternity;
    }

    public void setDaysTakenMaternity(int daysTakenMaternity) {
        this.daysTakenMaternity = daysTakenMaternity;
    }

    public int getDaysTakenPaternity() {
        return daysTakenPaternity;
    }

    public void setDaysTakenPaternity(int dayTakenPaternity) {
        this.daysTakenPaternity = dayTakenPaternity;
    }

    public int getVacations() {
        return vacations;
    }

    public void setVacations(int vacations) {
        this.vacations = vacations;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public int getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(int disabilities) {
        this.disabilities = disabilities;
    }
    
    //method to enter hours worked
    public boolean enterHoursWorked(String startTime,String starDate,String endTime,String endDate){
        int minsWorked;
        int startHour = Integer.parseInt(startTime.substring(0,2));
        int startMin = Integer.parseInt(startTime.substring(3,5));
        int startDay = Integer.parseInt(starDate.substring(0,2));
        int startMonth = Integer.parseInt(starDate.substring(3,5));
        int endHour = Integer.parseInt(endTime.substring(0,2));
        int endMin = Integer.parseInt(endTime.substring(3,5));
        int endDay = Integer.parseInt(endDate.substring(0,2));
        int endMonth = Integer.parseInt(endDate.substring(3,5));
        
        //Validate that only a maximum of 1 day difference is entered
        boolean startDayIsValid = (startDay >= 28) && (startDay <= 31) || startDay == endDay || startDay+1 == endDay; // Valid start day?
        boolean endDayIsValid = endDay == 01 || endDay == startDay || endDay-1 == startDay; //valid end day?
        boolean isValid = (startDay + 1 == endDay || startDay == endDay) || endDayIsValid && startDayIsValid;//Valid start and end day?
        if(!isValid){//Si no es valido
            if((startMonth+1 != endMonth && startMonth != endMonth) && (startMonth-11 != startMonth)){
                JOptionPane.showMessageDialog(null, "Solo esta permitido ingresar fechas con diferencia de 1 dia\nejemplo: (Inicio: (28/02/2023) Fin: (01/03/2023)\nejemplo 2: Inicio: (31/12/2023) Fin: (01/01/2024)");
                return false;
            }
            JOptionPane.showMessageDialog(null, "Solo esta permitido ingresar fechas con diferencia de 1 dia\nejemplo: (Inicio: (01/02/2023) Fin: (02/02/2023)");
            return false;
        }
        
        //Validate that it is a valid day
        if(startDay == endDay && startHour < endHour){ //Is the start and end on the same day?
           //convert hours worked to minutes
           minsWorked = (endHour*60 + endMin) - (startHour*60 + startMin);
           if(minsWorked>=480){ //Validation for a day valid for a minimum of 8 hours
               totalHoursWorked += minsWorked/60;
               double extraMins =  minsWorked%60;
               extraMins/=60;
               totalHoursWorked += extraMins;
               return true;
           }
           else{
               JOptionPane.showMessageDialog(null,"Solo se aceptan jornadas minimo de 8 horas, debes trabajar durante " + (8-minsWorked/60) + " hora(s) mas para completar las 8 horas minimas");
           return false;
           }
        }
        else if((startDay+1) == endDay){//The start is one day and the end is the next day?
            minsWorked = ((endHour+24)*60 + endMin) - (startHour*60 + startMin);
            if (minsWorked>=480){
               totalHoursWorked += minsWorked/60;
               double extraMins =  minsWorked%60;
               extraMins/=60;
               totalHoursWorked += extraMins;
                return true;
            }
            else{
               JOptionPane.showMessageDialog(null,"Solo se aceptan jornadas minimo de 8 horas, debes trabajar durante " + (8-minsWorked/60) + " hora(s) mas para completar las 8 horas minimas");
                return false;
            }
        }
        //Validation for Start day 30/31/28/29 End day 01 of the following month
        else if(startDay > endDay && (startMonth+1) == endMonth || startDay > endDay && (startMonth-11) == endMonth){
            minsWorked = ((endHour+24)*60 + endMin) - (startHour*60 + startMin);
            if (minsWorked>=480){
               totalHoursWorked += minsWorked/60;
               double extraMins =  minsWorked%60;
               extraMins/=60;
               totalHoursWorked += extraMins;
                return true;
            }
            else{
               JOptionPane.showMessageDialog(null,"Solo se aceptan jornadas minimo de 8 horas, debes trabajar durante " + (8-minsWorked/60) + " hora(s) mas para completar las 8 horas minimas");
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Solo esta permitido ingresar fechas con diferencia maxima de 1 dia y 1 mes\nejemplo: (Inicio: (31/12/2023) Fin: (01/01/2023)");
            return false;
        }
    
    }
    
    //method to display information

    public void showPersonalInformation() {
        String totalHoursString = totalHoursWorked+""; //Convert total hours worked to a text string
        String[] partsHoursWorked = totalHoursString.split("\\."); //Convert the text string to an array with 2 elements: hours and minutes
        int hours = Integer.parseInt(partsHoursWorked[0]);  //Convert element 1 (hours) of the partsHoursWorked array to int and save it in hours
        double decimals = Double.parseDouble("0." + partsHoursWorked[1]); //Convert element 2 (hours) of the partsHoursWorked array to double and save it to decimals
        double decimalsToMins = decimals*60; //Convert decimal to minutes
        int minutes = (int) decimalsToMins; //convert double decimals to int and save it in minutes
        JOptionPane.showMessageDialog(null, "Nombre: " + name +"\nApellido: " + lastName + "\nCedula: " + id + "\nGenero: " + gender + "\nTotal tiempo trabajado: " + hours + " horas con " +  minutes + " minutos.");
    }
    
}
