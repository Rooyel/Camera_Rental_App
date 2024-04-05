package rentmycam;

//Camera class to represent camera objects
class Camera {
 private static int count = 0; // Static variable to track the number of cameras added
 private int serialNumber;
 private String brand;
 private String model;
 private double rentalAmount;
 private String status;

 public Camera(int serialNumber,String brand, String model, double rentalAmount,String status) {
     this.serialNumber = ++count;
     this.brand = brand;
     this.model = model;
     this.rentalAmount = rentalAmount;
     this.status = status; 
 }

 // Getters for camera attributes
 public int getSerialNumber() {
     return serialNumber;
 }

 public String getBrand() {
     return brand;
 }

 public String getModel() {
     return model;
 }

 public double getRentalAmount() {
     return rentalAmount;
 }

 public String getStatus() {
     return status;
 }

 // Method to set camera status
 public void setStatus(String status) {
     this.status = status;
 }
}

