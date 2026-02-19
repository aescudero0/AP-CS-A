/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.vehicledata;
/**
 *
 * @author AEscudero2026
 */
public class VehicleData {
    public static void main(String[] args) {
    
        // create the car data
        Car car1 = new Car(13.2, 130, 2900);
        car1.setMake("Honda");
        car1.setModel("Civic");
        car1.setDriveType("FWD");
        car1.setPower(158);
        System.out.println("--- Car 1 ---");
        System.out.println(car1);

        Car car2 = new Car(22.0, 155, 5500);
        car2.setMake("Chevrolet");
        car2.setModel("Silverado");
        car2.setDriveType("4WD");
        car2.setPower(355);
        System.out.println("--- Car 2 ---");
        System.out.println(car2);

        // create the boat data
        Boat boat1 = new Boat(24.0, 55, 5500);
        boat1.setName("Boston Whaler");
        boat1.setDraft(1.8);
        boat1.setDryWeight(3200);
        boat1.setCargo(1200);
        boat1.setMpg(4.1);
        System.out.println("--- Boat 1 ---");
        System.out.println(boat1);

        Boat boat2 = new Boat(78.0, 38, 18000);
        boat2.setName("Chaparral 280");
        boat2.setDraft(3.5);
        boat2.setDryWeight(9500);
        boat2.setCargo(3000);
        boat2.setMpg(1.9);
        System.out.println("--- Boat 2 ---");
        System.out.println(boat2);

        // create the plane data
        Plane plane1 = new Plane(1050.0, 350, 60000);
        plane1.setName("Cessna 172");
        plane1.setMoc(0.3);
        plane1.setCargo(878);
        plane1.setRoc(730);
        System.out.println("--- Plane 1 ---");
        System.out.println(plane1);

        Plane plane2 = new Plane(16000.0, 870, 175000);
        plane2.setName("Airbus A320");
        plane2.setMoc(0.78);
        plane2.setCargo(42000);
        plane2.setRoc(2500);
        System.out.println("--- Plane 2 ---");
        System.out.println(plane2);

        // create the motorcycle data
        Motorcycles moto1 = new Motorcycles(6.0, 110, 450);
        moto1.setName("Harley Davidson");
        moto1.setBrakeSystem("ABS");
        moto1.setDriveType("Chain");
        moto1.setGroundClearance(5.1);
        moto1.setMpg(45);
        moto1.setPower(93);
        System.out.println("--- Motorcycle 1 ---");
        System.out.println(moto1);

        Motorcycles moto2 = new Motorcycles(4.5, 186, 380);
        moto2.setName("Kawasaki Ninja");
        moto2.setBrakeSystem("ABS");
        moto2.setDriveType("Chain");
        moto2.setGroundClearance(5.3);
        moto2.setMpg(37);
        moto2.setPower(197);
        System.out.println("--- Motorcycle 2 ---");
        System.out.println(moto2);

        // create the spaceship examples
        Spaceships ship1 = new Spaceships(49000.0, 17500, 250000);
        ship1.setName("Falcon 9");
        ship1.setCommunication("S-Band Radio");
        ship1.setDeltaV(9400);
        ship1.setImpulse(311);
        ship1.setPropellantMassRatio(0.94);
        System.out.println("--- Spaceship 1 ---");
        System.out.println(ship1);

        Spaceships ship2 = new Spaceships(264000.0, 24500, 310000);
        ship2.setName("Starship");
        ship2.setCommunication("Ka-Band Radio");
        ship2.setDeltaV(12000);
        ship2.setImpulse(380);
        ship2.setPropellantMassRatio(0.97);
        System.out.println("--- Spaceship 2 ---");
        System.out.println(ship2);
    }
    // set the main variables for all classes
    private double fuelTank = 0.0;
    private double speed = 0.0;
    private double weight = 0.0;
    // create the base constructor
    public VehicleData(){
        fuelTank = 0.0;
        speed = 0.0;
        weight = 0.0;
    }
    public VehicleData(double inFuelTank, double inSpeed, int inWeight){
        fuelTank = inFuelTank;
        speed = inSpeed;
        weight = inWeight;
    }
    // create the getters and setters for each variable
    public VehicleData(double inFuelTank){
        fuelTank = inFuelTank;
    }
    public double getFuelTank(){
        return fuelTank;
    }
    public void setFuelTank(double inFuelTank){
        fuelTank = inFuelTank;
    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double inSpeed){
        speed = inSpeed;
    }
    public double getWeight(){
        return weight;
    }
    public void setWeight(int inWeight){
        weight = inWeight;
    }
    //create the toString and return the result
    public String toString(){
        String result = "Fuel Tank (gal): \t" + getFuelTank() + "\n" +
                "Speed (mph): \t" + getSpeed() + "\n" +
                "Weight (lbs): \t" + getWeight() + "\n";
        return result;
    }
}
//create a speedometer (i didnt know how to get this to work)
interface Speedometer{
    public void setSpeed(double inSpeed);
    public double getSpeed();
}
// create a new class for car
// define that it extends from the main class which is VehicleData
class Car extends VehicleData{
     //create car specific variables
    private String make = "";
    private String model = "";
    private String driveType = "";
    private double power = 0.0;
    // create the constructor that extends from VehicleData
    public Car(double inFuelTank, double inSpeed, int inWeight){
        super(inFuelTank, inSpeed, inWeight);
    }
    // create the getters and setters for car specific variables
    public void setMake(String inMake){
        make = inMake;
    }
    public String getMake(){
        return make;
    }
    public void setModel(String inModel){
        model = inModel;
    }
    public String getModel(){
        return model;
    }
    public void setDriveType(String inDriveType){
        driveType = inDriveType;
    }
    public String getDriveType(){
        return driveType;
    }
    public void setPower(double inPower){
        power = inPower;
    }
    public double getPower(){
        return power;
    }
    // create the toString for the car data
    // add the super.toString to combine with the VehicleData toString
    @Override
    public String toString(){
        String result = super.toString() +
                        "Make: \t\t" + getMake() + "\n" +
                        "Model: \t\t" + getModel() + "\n" +
                        "Drive Type: \t" + getDriveType() + "\n" +
                        "Power (hp): \t" + getPower() + "\n";
        return result;
    }
}
// repeat exact same process as with car
// create new vehicle specific variables
class Boat extends VehicleData{
    private String name = "";
    private double draft = 0.0;
    private double dryWeight = 0.0;
    private double cargo = 0.0;
    private double mpg = 0.0;

    public Boat(double inFuelTank, double inSpeed, int inWeight){
        super(inFuelTank, inSpeed, inWeight);
    }
    public void setName(String inName){
        name = inName;
    }
    public String getName(){
        return name;
    }
    public void setDraft(double inDraft){
        draft = inDraft;
    }
    public double getDraft(){
        return draft;
    }
    public void setDryWeight(double inDryWeight){
        dryWeight = inDryWeight;
    }
    public double getDryWeight(){
        return dryWeight;
    }
    public void setCargo(double inCargo){
        cargo = inCargo;
    }
    public double getCargo(){
        return cargo;
    }
    public void setMpg(double inMpg){
        mpg = inMpg;
    }
    public double getMpg(){
        return mpg;
    }
    @Override
    public String toString(){
        String result = super.toString() +
                        "Name: \t\t" + getName() + "\n" +
                        "Draft (ft): \t" + getDraft() + "\n" +
                        "Dry Weight (lbs): " + getDryWeight() + "\n" +
                        "Cargo (lbs): \t" + getCargo() + "\n" +
                        "MPG: \t\t" + getMpg() + "\n";
        return result;
    }
}
// repeat exact same process as with car
// create new vehicle specific variables
class Plane extends VehicleData{
    private String name = "";
    private double moc = 0.0;
    private double cargo = 0.0;
    private double roc = 0.0;

    public Plane(double inFuelTank, double inSpeed, int inWeight){
        super(inFuelTank, inSpeed, inWeight);
    }
    public void setName(String inName){
        name = inName;
    }
    public String getName(){
        return name;
    }
    public void setMoc(double inMoc){
        moc = inMoc;
    }
    public double getMoc(){
        return moc;
    }
    public void setCargo(double inCargo){
        cargo = inCargo;
    }
    public double getCargo(){
        return cargo;
    }
    public void setRoc(double inRoc){
        roc = inRoc;
    }
    public double getRoc(){
        return roc;
    }
    @Override
    public String toString(){
        String result = super.toString() +
                        "Name: \t\t" + getName() + "\n" +
                        "Mach (moc): \t" + getMoc() + "\n" +
                        "Cargo (lbs): \t" + getCargo() + "\n" +
                        "Rate of Climb: \t" + getRoc() + "\n";
        return result;
    }
}
// repeat exact same process as with car
// create new vehicle specific variables
class Motorcycles extends VehicleData{
    private String name = "";
    private String brakeSystem = "";
    private String driveType = "";
    private double groundClearance = 0.0;
    private double mpg = 0.0;
    private double power = 0.0;

    public Motorcycles(double inFuelTank, double inSpeed, int inWeight){
        super(inFuelTank, inSpeed, inWeight);
    }
    public void setName(String inName){
        name = inName;
    }
    public String getName(){
        return name;
    }
    public void setBrakeSystem(String inBrakeSystem){
        brakeSystem = inBrakeSystem;
    }
    public String getBrakeSystem(){
        return brakeSystem;
    }
    public void setDriveType(String inDriveType){
        driveType = inDriveType;
    }
    public String getDriveType(){
        return driveType;
    }
    public void setGroundClearance(double inGC){
        groundClearance = inGC;
    }
    public double getGroundClearance(){
        return groundClearance;
    }
    public void setMpg(double inMpg){
        mpg = inMpg;
    }
    public double getMpg(){
        return mpg;
    }
    public void setPower(double inPower){
        power = inPower;
    }
    public double getPower(){
        return power;
    }
    @Override
    public String toString(){
        String result = super.toString() +
                        "Name: \t\t" + getName() + "\n" +
                        "Brake System: \t" + getBrakeSystem() + "\n" +
                        "Drive Type: \t" + getDriveType() + "\n" +
                        "Ground Clearance: " + getGroundClearance() + "\n" +
                        "MPG: \t\t" + getMpg() + "\n" +
                        "Power (hp): \t" + getPower() + "\n";
        return result;
    }
}
// repeat exact same process as with car
// create new vehicle specific variables
class Spaceships extends VehicleData{
    private String name = "";
    private String communication = "";
    private double deltaV = 0.0;
    private double impulse = 0.0;
    private double propellantMassRatio = 0.0;

    public Spaceships(double inFuelTank, double inSpeed, int inWeight){
        super(inFuelTank, inSpeed, inWeight);
    }
    public void setName(String inName){
        name = inName;
    }
    public String getName(){
        return name;
    }
    public void setCommunication(String inComm){
        communication = inComm;
    }
    public String getCommunication(){
        return communication;
    }
    public void setDeltaV(double inDeltaV){
        deltaV = inDeltaV;
    }
    public double getDeltaV(){
        return deltaV;
    }
    public void setImpulse(double inImpulse){
        impulse = inImpulse;
    }
    public double getImpulse(){
        return impulse;
    }
    public void setPropellantMassRatio(double inRatio){
        propellantMassRatio = inRatio;
    }
    public double getPropellantMassRatio(){
        return propellantMassRatio;
    }
    @Override
    public String toString(){
        String result = super.toString() +
                        "Name: \t\t" + getName() + "\n" +
                        "Communication: \t" + getCommunication() + "\n" +
                        "Delta-V (m/s): \t" + getDeltaV() + "\n" +
                        "Impulse (s): \t" + getImpulse() + "\n" +
                        "Propellant Ratio: " + getPropellantMassRatio() + "\n";
        return result;
    }
}