
package com.mycompany.vehicledata;

/**
 * @author AEscudero2026
 */
public class VehicleData {

    public static void main(String[] args) {

        // create car data
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

       
        // create sports car data
        SportsCar sports1 = new SportsCar(16.0, 155, 3827);
        sports1.setMake("Ford");
        sports1.setModel("Mustang GT");
        sports1.setDriveType("RWD");
        sports1.setPower(450);
        sports1.setAcceleration(4.3);         
        sports1.setTopSpeed(155);        
        sports1.setTurbo(false);             
        System.out.println("--- Sports Car 1 ---");
        System.out.println(sports1);

        
        SportsCar sports2 = new SportsCar(13.7, 155, 3397);
        sports2.setMake("Toyota");
        sports2.setModel("GR Supra");
        sports2.setDriveType("RWD");
        sports2.setPower(382);
        sports2.setAcceleration(3.9);
        sports2.setTopSpeed(155);
        sports2.setTurbo(true);              
        System.out.println("--- Sports Car 2 ---");
        System.out.println(sports2);

       // create electric car data
        ElectricCar ev1 = new ElectricCar(0.0, 145, 4048);
        ev1.setMake("Tesla");
        ev1.setModel("Model 3 Long Range");
        ev1.setDriveType("AWD");
        ev1.setPower(358);
        ev1.setBattery(82.0);            
        ev1.setRange(358);              
        ev1.setChargeTime(30);            
        System.out.println("--- Electric Car 1 ---");
        System.out.println(ev1);

        
        ElectricCar ev2 = new ElectricCar(0.0, 110, 7148);
        ev2.setMake("Rivian");
        ev2.setModel("R1T");
        ev2.setDriveType("AWD");
        ev2.setPower(835);
        ev2.setBattery(135.0);
        ev2.setRange(314);
        ev2.setChargeTime(60);
        System.out.println("--- Electric Car 2 ---");
        System.out.println(ev2);

      // create boat data
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

       // create plane data
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

       // create motorcycle data
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

       // create spaceship data
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

    
    // create the main variables shared by all vehicles
    private double fuelTank = 0.0;
    private double speed = 0.0;
    private double weight = 0.0;

    // set the default constructor
    public VehicleData() {
        fuelTank = 0.0;
        speed = 0.0;
        weight = 0.0;
    }

    // create the constructor with all three values
    public VehicleData(double inFuelTank, double inSpeed, int inWeight) {
        fuelTank = inFuelTank;
        speed = inSpeed;
        weight = inWeight;
    }

    public VehicleData(double inFuelTank) {
        fuelTank = inFuelTank;
    }

    // create the getters and setters
    public double getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(double inFuelTank) {
        fuelTank = inFuelTank;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double inSpeed) {
        speed = inSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int inWeight) {
        weight = inWeight;
    }

    // create the toString to print all basic data
    public String toString() {
        String result = "Fuel Tank (gal): \t" + getFuelTank() + "\n"
                + "Speed (mph): \t" + getSpeed() + "\n"
                + "Weight (lbs): \t" + getWeight() + "\n";
        return result;
    }
}


interface Speedometer {
    public void setSpeed(double inSpeed);
    public double getSpeed();
}


class Car extends VehicleData {

    // create car specific variables
    private String make = "";
    private String model = "";
    private String driveType = "";
    private double power = 0.0;

    // create the constructor which extends VehicleData
    public Car(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    // create getters and setters
    public void setMake(String inMake) {
        make = inMake;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String inModel) {
        model = inModel;
    }

    public String getModel() {
        return model;
    }

    public void setDriveType(String inDriveType) {
        driveType = inDriveType;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setPower(double inPower) {
        power = inPower;
    }

    public double getPower() {
        return power;
    }

    // toString adds car data on top of VehicleData toString using the super.toString
    @Override
    public String toString() {
        String result = super.toString()
                + "Make: \t\t" + getMake() + "\n"
                + "Model: \t\t" + getModel() + "\n"
                + "Drive Type: \t" + getDriveType() + "\n"
                + "Power (hp): \t" + getPower() + "\n";
        return result;
    }
}

// create sports car that extends car
class SportsCar extends Car {

    // create sports car specific variables
    private double acceleration = 0.0;   
    private double topSpeed = 0.0; 
    private boolean turbo = false;      

    // create the constructor that extends VehicleData
    public SportsCar(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    // create getters and setters
    public void setAcceleration(double inAcceleration) {
        acceleration = inAcceleration;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setTopSpeed(double inTS) {
        topSpeed = inTS;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTurbo(boolean inTurbo) {
        turbo = inTurbo;
    }

    public boolean getTurbo() {
        return turbo;
    }

    // create the toString extending from VehicleData
    @Override
    public String toString() {
        String result = super.toString()
                + "0-60 mph (sec): \t" + getAcceleration() + "\n"
                + "Top Track Speed (mph): \t" + getTopSpeed() + "\n"
                + "Turbocharged: \t\t" + (getTurbo() ? "Yes" : "No") + "\n";
        return result;
    }
}
// repeat the same process as with sports car data
// create unique variables
class ElectricCar extends Car {

    
    private double battery = 0.0;    
    private double range = 0.0;    
    private int chargeTime = 0;     

   
    public ElectricCar(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    
    public void setBattery(double inBattery) {
        battery = inBattery;
    }

    public double getBattery() {
        return battery;
    }

    public void setRange(double inRange) {
        range = inRange;
    }

    public double getRange() {
        return range;
    }

    public void setChargeTime(int inTime) {
        chargeTime = inTime;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    
    @Override
    public String toString() {
        String result = super.toString()
                + "Battery (kWh): \t\t" + getBattery() + "\n"
                + "Range (miles): \t\t" + getRange() + "\n"
                + "Fast Charge (mins): \t" + getChargeTime() + "\n";
        return result;
    }
}
// repeat same process as car data
// extends only from VehicleData not car
// create unique variables and specs
class Boat extends VehicleData {

    private String name = "";
    private double draft = 0.0;
    private double dryWeight = 0.0;
    private double cargo = 0.0;
    private double mpg = 0.0;

    public Boat(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    public void setName(String inName) { 
        name = inName; 
    }
    public String getName() { 
        return name; 
    }
    public void setDraft(double inDraft) { 
        draft = inDraft; 
    }
    public double getDraft() { 
        return draft; 
    }
    public void setDryWeight(double inDryWeight) { 
        dryWeight = inDryWeight; 
    }
    public double getDryWeight() { 
        return dryWeight; 
    }
    public void setCargo(double inCargo) { 
        cargo = inCargo; 
    }
    public double getCargo() { 
        return cargo; 
    }
    public void setMpg(double inMpg) { 
        mpg = inMpg; 
    }
    public double getMpg() { 
        return mpg; 
    }

    @Override
    public String toString() {
        String result = super.toString()
                + "Name: \t\t" + getName() + "\n"
                + "Draft (ft): \t" + getDraft() + "\n"
                + "Dry Weight (lbs): " + getDryWeight() + "\n"
                + "Cargo (lbs): \t" + getCargo() + "\n"
                + "MPG: \t\t" + getMpg() + "\n";
        return result;
    }
}
// repeat same process as car data
// extends only from VehicleData not car
// create unique variables and specs

class Plane extends VehicleData {

    private String name = "";
    private double moc = 0.0;
    private double cargo = 0.0;
    private double roc = 0.0;

    public Plane(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    public void setName(String inName) { 
        name = inName; 
    }
    public String getName() { 
        return name; 
    }
    public void setMoc(double inMoc) { 
        moc = inMoc; 
    }
    public double getMoc() { 
        return moc; 
    }
    public void setCargo(double inCargo) { 
        cargo = inCargo; 
    }
    public double getCargo() { 
        return cargo; 
    }
    public void setRoc(double inRoc) { 
        roc = inRoc; 
    }
    public double getRoc() { 
        return roc; 
    }

    @Override
    public String toString() {
        String result = super.toString()
                + "Name: \t\t" + getName() + "\n"
                + "Mach (moc): \t" + getMoc() + "\n"
                + "Cargo (lbs): \t" + getCargo() + "\n"
                + "Rate of Climb: \t" + getRoc() + "\n";
        return result;
    }
}
// repeat same process as car data
// extends only from VehicleData not car
// create unique variables and specs

class Motorcycles extends VehicleData {

    private String name = "";
    private String brakeSystem = "";
    private String driveType = "";
    private double groundClearance = 0.0;
    private double mpg = 0.0;
    private double power = 0.0;

    public Motorcycles(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    public void setName(String inName) { 
        name = inName; 
    }
    public String getName() { 
        return name; 
    }
    public void setBrakeSystem(String inBrakeSystem) { 
        brakeSystem = inBrakeSystem; 
    }
    public String getBrakeSystem() { return 
            brakeSystem; 
    }
    public void setDriveType(String inDriveType) { 
        driveType = inDriveType; 
    }
    public String getDriveType() { return 
            driveType; 
    }
    public void setGroundClearance(double inGC) { 
        groundClearance = inGC; 
    }
    public double getGroundClearance() { 
        return groundClearance; 
    }
    public void setMpg(double inMpg) { 
        mpg = inMpg; 
    }
    public double getMpg() { 
        return mpg; 
    }
    public void setPower(double inPower) { 
        power = inPower; 
    }
    public double getPower() { 
        return power; 
    }

    @Override
    public String toString() {
        String result = super.toString()
                + "Name: \t\t" + getName() + "\n"
                + "Brake System: \t" + getBrakeSystem() + "\n"
                + "Drive Type: \t" + getDriveType() + "\n"
                + "Ground Clearance: " + getGroundClearance() + "\n"
                + "MPG: \t\t" + getMpg() + "\n"
                + "Power (hp): \t" + getPower() + "\n";
        return result;
    }
}
// repeat same process as car data
// extends only from VehicleData not car
// create unique variables and specs

class Spaceships extends VehicleData {

    private String name = "";
    private String communication = "";
    private double deltaV = 0.0;
    private double impulse = 0.0;
    private double propellantMassRatio = 0.0;

    public Spaceships(double inFuelTank, double inSpeed, int inWeight) {
        super(inFuelTank, inSpeed, inWeight);
    }

    public void setName(String inName) { 
        name = inName; 
    }
    public String getName() { 
        return name; 
    }
    public void setCommunication(String inComm) { 
        communication = inComm; 
    }
    public String getCommunication() { 
        return communication; 
    }
    public void setDeltaV(double inDeltaV) { 
        deltaV = inDeltaV; 
    }
    public double getDeltaV() { 
        return deltaV; 
    }
    public void setImpulse(double inImpulse) { 
        impulse = inImpulse; 
    }
    public double getImpulse() { 
        return impulse; 
    }
    public void setPropellantMassRatio(double inRatio) { 
        propellantMassRatio = inRatio; 
    }
    public double getPropellantMassRatio() {
        return propellantMassRatio; 
    }

    @Override
    public String toString() {
        String result = super.toString()
                + "Name: \t\t" + getName() + "\n"
                + "Communication: \t" + getCommunication() + "\n"
                + "Delta-V (m/s): \t" + getDeltaV() + "\n"
                + "Impulse (s): \t" + getImpulse() + "\n"
                + "Propellant Ratio: " + getPropellantMassRatio() + "\n";
        return result;
    }
}