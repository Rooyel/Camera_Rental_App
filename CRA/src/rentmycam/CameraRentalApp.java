package rentmycam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CameraRentalApp {
    private static ArrayList<Camera> cameraList = new ArrayList<>();
    private static Wallet wallet = new Wallet();
    
    private static void addInitialData() {
        cameraList.add(new Camera(1, "Samsung", "DS123", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(2, "Sony", "HD214", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(3, "Panasonic", "XC", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(4, "Canon", "XLR", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(5, "Fujitsu", "J5", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(6, "Sony", "HD226", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(7, "Samsung", "DS246", 500.0, "RENTED"));
        cameraList.add(new Camera(8, "LG", "L123", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(9, "Canon", "XPL", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(10, "Chroma", "Zune", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(11, "Samsung", "Galaxy", 200.0, "RENTED"));
        cameraList.add(new Camera(12, "Some", "Another", 100.0, "RENTED"));
        cameraList.add(new Camera(13, "Canon", "Digital", 123.0, "AVAILABLE"));
        cameraList.add(new Camera(14, "NIKON", "DSLR-D7500", 500.0, "AVAILABLE"));
        cameraList.add(new Camera(15, "Sony", "DSLR12", 200.0, "AVAILABLE"));
        cameraList.add(new Camera(16, "Panasonic", "DS6L3", 580.0, "RENTED"));
        cameraList.add(new Camera(17, "Samsung", "SM123", 200.0, "RENTED"));
        cameraList.add(new Camera(18, "Fujitsu", "XQC", 800.0, "RENTED"));
        cameraList.add(new Camera(19, "SONY", "SONY1234", 123.0, "AVAILABLE"));
        cameraList.add(new Camera(20, "canon", "5050", 25000.0, "AVAILABLE"));
        cameraList.add(new Camera(21, "nikon", "2030", 500.0, "AVAILABLE"));
    }

    public static void main(String[] args) {
        addInitialData();
        System.out.println("Welcome to Camera Rental App");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username to continue: ");
        String username = scanner.nextLine();
        System.out.println("\nHello, " + username + "! Welcome to the application.");
        int choice;
        do {
            System.out.println("Please select an option:");
            System.out.println("1. MY CAMERA");
            System.out.println("2. RENT A CAMERA");
            System.out.println("3. VIEW ALL CAMERAS");
            System.out.println("4. MY WALLET");
            System.out.println("5. EXIT");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    myCameraOptions(scanner);
                    break;
                case 2:
                    System.out.println("Option 2 selected: RENT A CAMERA");
                    rentCamera(scanner);
                    break;
                case 3:
                    viewAllCameras();
                    break;
                case 4:
                    manageWallet(scanner);
                    break;
                case 5:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 5);
    }

    private static void myCameraOptions(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nMY CAMERA Options:");
            System.out.println("1. ADD");
            System.out.println("2. REMOVE");
            System.out.println("3. VIEW MY CAMERAS");
            System.out.println("4. GO TO PREVIOUS MENU");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCamera(scanner);
                    break;
                case 2:
                    System.out.println("Option 2 selected: REMOVE");
                    removeCamera(scanner);
                    break;
                case 3:
                    System.out.println("Option 3 selected: VIEW MY CAMERAS");
                    viewAllCameras();
                    break;
                case 4:
                    System.out.println("Going back to the previous menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 4);
    }

    private static void addCamera(Scanner scanner) {
        System.out.println("\nAdding a new camera:");
        int serialNumber = cameraList.size() + 1;
        System.out.print("Enter camera brand: ");
        String brand = scanner.next();
        System.out.print("Enter camera model: ");
        String model = scanner.next();
        System.out.print("Enter per day rental amount: ");
        double rentalAmount = scanner.nextDouble();
        String status = "AVAILABLE";
        Camera newCamera = new Camera(serialNumber,brand, model, rentalAmount,status);
        cameraList.add(newCamera);
        System.out.println("Your camera has been successfully added to the list.");
    }

    private static void viewAllCameras() {
        System.out.println("\nAll Cameras:");
        System.out.println("==============================================================================");
        System.out.println("CAMERA ID\tBRAND\t\tMODEL\t\tPRICE(PER DAY)\t\tSTATUS");
        System.out.println("==============================================================================");
        for (Camera camera : cameraList) {
            System.out.printf("%-10d\t%-10s\t%-10s\t%-10.2f\t\t%s%n",
                    camera.getSerialNumber(), camera.getBrand(), camera.getModel(),
                    camera.getRentalAmount(), camera.getStatus());
        }
        System.out.println("==============================================================================");
    }

    private static void manageWallet(Scanner scanner) {
        System.out.println("\nCurrent wallet balance: " + wallet.getBalance());
        System.out.print("Do you want to deposit more amount to your wallet? (1.YES 2.NO): ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("Enter the amount to deposit: ");
            double amount = scanner.nextDouble();
            wallet.deposit(amount);
            System.out.println("Your wallet balance updated successfully.");
            System.out.println("Updated wallet balance: " + wallet.getBalance());
        }
    }

    private static void rentCamera(Scanner scanner) {
        viewAllCameras();
        System.out.print("Enter the camera ID you want to rent: ");
        int cameraID = scanner.nextInt();
        Camera selectedCamera = null;
        for (Camera camera : cameraList) {
            if (camera.getSerialNumber() == cameraID) {
                selectedCamera = camera;
                break;
            }
        }
        if (selectedCamera != null) {
            if (selectedCamera.getStatus().equals("AVAILABLE")) {
                if (wallet.getBalance() >= selectedCamera.getRentalAmount()) {
                    selectedCamera.setStatus("RENTED");
                    wallet.deposit(-selectedCamera.getRentalAmount());
                    System.out.println("YOUR TRANSACTION FOR CAMERA - " + selectedCamera.getBrand() + " " + selectedCamera.getModel() +
                            " with rent INR." + selectedCamera.getRentalAmount() + " Has successfully completed.");
                } else {
                    System.out.println("ERROR: Transaction Failed Due to Insufficient Wallet Balance. " +
                            "Please Deposit the Amount To Your Wallet.");
                }
            } else {
                System.out.println("ERROR: Camera with ID " + cameraID + " is not available for rent.");
            }
        } else {
            System.out.println("ERROR: Camera with ID " + cameraID + " not found.");
        }
    }

    private static void removeCamera(Scanner scanner) {
        viewAllCameras();
        System.out.print("Enter the camera ID you want to remove: ");
        int cameraId = scanner.nextInt();
        Iterator<Camera> iterator = cameraList.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Camera camera = iterator.next();
            if (camera.getSerialNumber() == cameraId) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Camera Successfully Removed from The List.");
        } else {
            System.out.println("Camera ID not found. No camera removed.");
            System.out.println("ERROR: Camera with ID " + cameraId + " not found.");
        }
    }   
}                                       

