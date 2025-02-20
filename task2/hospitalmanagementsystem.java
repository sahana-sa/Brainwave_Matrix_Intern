
import java.util.*;

class Patient {
    private int id;
    private String name;
    private int age;
    private String ailment;
    private String medicalHistory;

    public Patient(int id, String name, int age, String ailment, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
        this.medicalHistory = medicalHistory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + 
               ", Ailment: " + ailment + ", Medical History: " + medicalHistory;
    }
    public void updateMedicalHistory(String newHistory) {
        this.medicalHistory = newHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }
}

class Appointment {
    private int patientId;
    private String doctor;
    private String date;

    public Appointment(int patientId, String doctor, String date) {
        this.patientId = patientId;
        this.doctor = doctor;
        this.date = date;
    }

    public String getDetails() {
        return "Appointment[Patient ID: " + patientId + ", Doctor: " + doctor + ", Date: " + date + "]";
    }
}
class Inventory {
    private String itemName;
    private int quantity;

    public Inventory(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getDetails() {
        return "Item: " + itemName + ", Quantity: " + quantity;
    }
}
class Staff {
    private int staffId;
    private String name;
    private String role;

    public Staff(int staffId, String name, String role) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
    }

    public String getDetails() {
        return "Staff ID: " + staffId + ", Name: " + name + ", Role: " + role;
    }
}

class Billing {
    private int patientId;
    private double amount;
    private String status;

    public Billing(int patientId, double amount) {
        this.patientId = patientId;
        this.amount = amount;
        this.status = "Pending";
    }

    public void payBill() {
        this.status = "Paid";
    }

    public String getDetails() {
        return "Billing[Patient ID: " + patientId + ", Amount: $" + amount + ", Status: " + status + "]";
    }
}

class hospitalmanagementsystem {
    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Billing> bills = new ArrayList<>();
    private static int patientCounter = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Inventory> inventoryList = new ArrayList<>();
    private static List<Staff> staffList = new ArrayList<>(); 
    private static int staffCounter = 1; 



    public static void registerPatient() {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter Ailment: ");
            String ailment = scanner.nextLine();
            System.out.print("Enter Medical History: ");
            String medicalHistory = scanner.nextLine();
            
            patients.add(new Patient(patientCounter++, name, age, ailment, medicalHistory));
            System.out.println("\nPatient registered successfully!\n");
        } catch (Exception e) {
            System.out.println(" Invalid input! Try again.");
            scanner.nextLine(); 
        }
    }

    public static void scheduleAppointment() {
        try {
            System.out.print("Enter Patient ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Doctor Name: ");
            String doctor = scanner.nextLine();
            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            appointments.add(new Appointment(id, doctor, date));
            System.out.println("\nAppointment scheduled successfully!\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Try again.");
            scanner.nextLine();
        }
    }

    public static void generateBill() {
        try {
            System.out.print("Enter Patient ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();

            bills.add(new Billing(id, amount));
            System.out.println("\nBill generated successfully!\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Try again.");
            scanner.nextLine();
        }
    }

    public static void payBill() {
        try {
            System.out.print("Enter Patient ID: ");
            int id = scanner.nextInt();

            for (Billing bill : bills) {
                if (bill.getDetails().contains("Patient ID: " + id) && bill.getDetails().contains("Pending")) {
                    bill.payBill();
                    System.out.println("\nBill paid successfully!\n");
                    return;
                }
            }
            System.out.println("No pending bills found for this Patient ID.\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Try again.");
            scanner.nextLine();
        }
    }

    public static void showPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.\n");
            return;
        }
        System.out.println("\nList of Patients:");
        for (Patient p : patients) {
            System.out.println(p.getDetails());
        }
        System.out.println();
    }

    public static void showAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.\n");
            return;
        }
        System.out.println("\nScheduled Appointments:");
        for (Appointment a : appointments) {
            System.out.println(a.getDetails());
        }
        System.out.println();
    }

    public static void showBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills found.\n");
            return;
        }
        System.out.println("\nBilling Records:");
        for (Billing b : bills) {
            System.out.println(b.getDetails());
        }
        System.out.println();
    }
    public static void updateMedicalHistory() {
        try {
            System.out.print("Enter Patient ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Medical Record: ");
            String newHistory = scanner.nextLine();

            for (Patient p : patients) {
                if (p.getId() == id) {
                    p.updateMedicalHistory(newHistory);
                    System.out.println("\nMedical history updated successfully!\n");
                    return;
                }
            }
            System.out.println("Patient not found!\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Try again.");
            scanner.nextLine();
        }
    }

    public static void viewMedicalHistory() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Patient p : patients) {
            if (p.getId() == id) {
                System.out.println("\nMedical History: " + p.getMedicalHistory() + "\n");
                return;
            }
        }
        System.out.println("Patient not found!\n");
    }
    public static void addInventory() {
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        inventoryList.add(new Inventory(itemName, quantity));
        System.out.println("\nInventory added successfully!\n");
    }

    public static void viewInventory() {
        if (inventoryList.isEmpty()) {
            System.out.println("No inventory items found.\n");
            return;
        }
        System.out.println("\nInventory List:");
        for (Inventory item : inventoryList) {
            System.out.println(item.getDetails());
        }
        System.out.println();
    }

    public static void registerStaff() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        staffList.add(new Staff(staffCounter++, name, role));
        System.out.println("\nStaff registered successfully!\n");
    }

    public static void viewStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.\n");
            return;
        }
        System.out.println("\nStaff List:");
        for (Staff staff : staffList) {
            System.out.println(staff.getDetails());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n**Hospital Management System**");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Generate Bill");
            System.out.println("4. Pay Bill");
            System.out.println("5. Show Patients");
            System.out.println("6. Show Appointments");
            System.out.println("7. Show Bills");
            System.out.println("8. Update Medical History");
            System.out.println("9. View Medical History");
            System.out.println("10. Add Inventory");
            System.out.println("11. View Inventory");
            System.out.println("12. Register Staff");
            System.out.println("13. View Staff");
            System.out.println("15. Exit");
            System.out.print("Enter choice: ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> scheduleAppointment();
                case 3 -> generateBill();
                case 4 -> payBill();
                case 5 -> showPatients();
                case 6 -> showAppointments();
                case 7 -> showBills();
                case 8 -> updateMedicalHistory();
                case 9 -> viewMedicalHistory();
                case 10 -> addInventory();
                case 11 -> viewInventory();
                case 12 -> registerStaff();
                case 13 -> viewStaff();
                case 15 -> {
                    System.out.println("Exiting... Stay Healthy!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
