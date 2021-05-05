//import javax.xml.namespace.QName;

public class calc {

    //tax variables and tax constants are initialized
    public static double stateTax, fedTax;
    final static double medicare = .0145, socialsec = .062;

    private static double gross, totalTax;
    

    //getters and setters so program can display all amounts
    public void setGross(double grossIn) { gross = grossIn; }
    public static double getGross() { return gross; }
    public void setTax(double taxIn) { totalTax = taxIn; }
    public static double getTax() { return totalTax; }

    //state tax constants
    //states with several tax brackets, the average was found and used
    final static double Alabama = .035, Alaska = 1, Arizona = .05295, Arkansas = .0395, California = .0715,
    Colorado = .0455, Connecticut = .04995, Delaware = .033, Florida = 1, Georgia = .02875, Hawaii = .062,
    Idaho = .04025, Illinois = .0495, Indiana = .03023, Iowa = .0443, Kansas = .044, Kentucky = .05, Louisiana = .04, Maine = .06475,
    Maryland = .03875, Massachusetts = 1, Michigan = .0425, Minnesota = .076, Mississippi = .04, Missouri = .0345,
    Montana =.0395, Nebraska = .0465, Nevada = 1, NewHampshire = .05, NewJersey = .06075, NewMexico = .038,
    NewYork = .0641, NorthCarolina = .0525, NorthDakota = .02, Ohio = .023985, Oklahoma = .0275, Oregon = .07325,
    Pennsylvania = .0307, RhodeIsland = .0487, SouthCarolina = .035, SouthDakota = 1, Tennessee = 1, Texas = 1,
    Utah = .0495, Vermont = .0605, Virginia = .03875, Washington = .065, WestVirginia = .0475, Wisconsin = .05545,
    Wyoming = 1, EXEMPT = 1;

    public static void setStateTax(String state) {
        
        if (state == "Alabama") {
            stateTax = Alabama;
        }
        else if (state == "Alaska") {
            stateTax = Alaska;
        }
        else if (state == "Arizona") {
            stateTax = Arizona;
        }
        else if (state == "Arkansas") {
            stateTax = Arkansas;
        }
        else if (state == "California") {
            stateTax = California;
        }
        else if (state == "Connecticut") {
            stateTax = Connecticut;
        }
        else if (state == "Delaware") {
            stateTax = Delaware;
        }
        else if (state == "Florida") {
            stateTax = Florida;
        }
        else if (state == "Georgia") {
            stateTax = Georgia;
        }
        else if (state == "Hawaii") {
            stateTax = Hawaii;
        }
        else if (state == "Idaho") {
            stateTax = Idaho;
        }
        else if (state == "Illinois") {
            stateTax = Illinois;
        }
        else if (state == "Indiana") {
            stateTax = Indiana;
        }
        else if (state == "Iowa") {
            stateTax = Iowa;
        }
        else if (state == "Kansas") {
            stateTax = Kansas;
        }
        else if (state == "Kentucky") {
            stateTax = Kentucky;
        }
        else if (state == "Louisiana") {
            stateTax = Louisiana;
        }
        else if (state == "Maine") {
            stateTax = Maine;
        }
        else if (state == "Maryland") {
            stateTax = Maryland;
        }
        else if (state == "Massachusetts") {
            stateTax = Massachusetts;
        }
        else if (state == "Michigan") {
            stateTax = Michigan;
        }
        else if (state == "Minnesota") {
            stateTax = Minnesota;
        }
        else if (state == "Mississippi") {
            stateTax = Mississippi;
        }
        else if (state == "Missouri") {
            stateTax = Missouri;
        }
        else if (state == "Montana") {
            stateTax = Montana;
        }
        else if (state == "Nebraska") {
            stateTax = Nebraska;
        }
        else if (state == "Nevada") {
            stateTax = Nevada;
        }
        else if (state == "New Hampshire") {
            stateTax = NewHampshire;
        }
        else if (state == "New Jersey") {
            stateTax = NewJersey;
        }
        else if (state == "New Mexico") {
            stateTax = NewMexico;
        }
        else if (state == "New York") {
            stateTax = NewYork;
        }
        else if (state == "North Carolina") {
            stateTax = NorthCarolina;
        }
        else if (state == "North Dakota") {
            stateTax = NorthDakota;
        }
        else if (state == "Ohio") {
            stateTax = Ohio;
        }
        else if (state == "Oklahoma") {
            stateTax = Oklahoma;
        }
        else if (state == "Oregon") {
            stateTax = Oregon;
        }
        else if (state == "Pennsylvania") {
            stateTax = Pennsylvania;
        }
        else if (state == "Rhode Island") {
            stateTax = RhodeIsland;
        }
        else if (state == "South Carolina") {
            stateTax = SouthCarolina;
        }
        else if (state == "South Dakota") {
            stateTax = SouthDakota;
        }
        else if (state == "Tennessee") {
            stateTax = Tennessee;
        }
        else if (state == "Texas") {
            stateTax = Texas;
        }
        else if (state == "Utah") {
            stateTax = Utah;
        }
        else if (state == "Vermont") {
            stateTax = Vermont;
        }
        else if (state == "Virginia") {
            stateTax = Virginia;
        }
        else if (state == "Washington") {
            stateTax = Washington;
        }
        else if (state == "West Virginia") {
            stateTax = WestVirginia;
        }
        else if (state == "Wisconsin") {
            stateTax = Wisconsin;
        }
        else if (state == "Wyoming") {
            stateTax = Wyoming;
        }
        else if (state == "EXEMPT") {
            stateTax = EXEMPT;
        }
    }
    public static void setFedTax(String bracket) {

        if (bracket == "$0 to $9,950") {
            fedTax = .10;
        }
        else if (bracket == "$9,951 to $40,525") {
            fedTax = .12;
        }
        else if (bracket == "$40,526 to $86,375") {
            fedTax = .22;
        }
        else if (bracket == "$86,376 to $164,925") {
            fedTax = .24;  
        }
        else if (bracket == "$164,926 to $209,425") {
            fedTax = .32;
        }
        else if (bracket == "$209,426 to $523,600") {
            fedTax = .35;
        }
        else if (bracket == "$523,601 or more") {
            fedTax = .37;
        }
        else if (bracket == "EXEMPT") {
            fedTax = EXEMPT;
        }
    }

    public static double netPay(double pay, double hour) {

        //if there's no state tax or tax exempt, calculate without state tax
        if ((stateTax == 1) && (fedTax == 1)) {
            gross = pay * hour;
            totalTax = (gross * medicare) + (gross * socialsec);
            double net = (gross - totalTax);
            return(net);
        }
        else if (stateTax == 1) {
            gross = pay * hour;
            totalTax = (gross * fedTax) + (gross * medicare) + (gross * socialsec);
            double net = (gross - totalTax);
            return (net);
        }
        else if (fedTax == 1) {
            gross = pay * hour;
            totalTax = (gross * stateTax) + (gross * medicare) + (gross * socialsec);
            double net = (gross - totalTax);
            return(net);
        }
        else if ((stateTax == 1) && (fedTax == 1)) {
            gross = pay * hour;
            totalTax = (gross * medicare) + (gross * socialsec);
            double net = (gross - totalTax);
            return(net);
        }
        else { 
            gross = pay * hour;
            double stateTaxAmt = gross * stateTax;
            double fedTaxAmt =  gross * fedTax;
            double fica = (gross * medicare) + (gross * socialsec);
            totalTax = (stateTaxAmt) + (fedTaxAmt) + (fica);
            double net = (gross) - (totalTax);
            return (net);
        }
    }



 

}
