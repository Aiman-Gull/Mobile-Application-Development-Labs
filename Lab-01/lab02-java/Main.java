import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    static String batteryStatus(int battery) {
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException(
                    "Battery must be between 0 and 100"
            );
        }

        if (battery < 20) {
            return "LOW";
        } else {
            return "READY";
        }
    }

    static double averageBattery(int[] readings) {
        if (readings == null || readings.length == 0) {
            throw new IllegalArgumentException("Readings cannot be empty");
        }

        int sum = 0;

        for (int reading : readings) {
            if (reading < 0 || reading > 100) {
                throw new IllegalArgumentException(
                        "Battery must be between 0 and 100"
                );
            }

            sum += reading;
        }

        return sum / (double) readings.length;
    }

    public static void main(String[] args) {

        // T1
        System.out.println("=== T1: Battery Status ===");

        int[] values = {0, 19, 20, 100};

        for (int value : values) {
            try {
                System.out.println(
                        "batteryStatus(" + value + "): " +
                                batteryStatus(value)
                );
            } catch (IllegalArgumentException ex) {
                System.out.println(
                        "batteryStatus(" + value + "): " +
                                ex.getMessage()
                );
            }
        }

        // T2
        System.out.println("\n=== T2: Invalid Battery Values ===");

        int[] invalidValues = {-1, 101};

        for (int value : invalidValues) {
            try {
                System.out.println(
                        "batteryStatus(" + value + "): " +
                                batteryStatus(value)
                );
            } catch (IllegalArgumentException ex) {
                System.out.println(
                        "batteryStatus(" + value + "): " +
                                ex.getMessage()
                );
            }
        }

        // T3
        System.out.println("\n=== T3: Average Battery ===");

        try {
            System.out.printf(
                    Locale.ROOT,
                    "Average: %.2f%n",
                    averageBattery(new int[]{15, 80, 45})
            );
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // T4
        System.out.println("\n=== T4: Invalid Arrays ===");

        int[][] testArrays = {
                {},
                {15, 101}
        };

        for (int[] readings : testArrays) {
            try {
                System.out.println(
                        "Average: " + averageBattery(readings)
                );
            } catch (IllegalArgumentException ex) {
                System.out.println(
                        "Array rejected: " + ex.getMessage()
                );
            }
        }

        try {
            System.out.println(
                    "Average: " + averageBattery(null)
            );
        } catch (IllegalArgumentException ex) {
            System.out.println(
                    "Null rejected: " + ex.getMessage()
            );
        }

        // T5
        System.out.println("\n=== T5: Device ===");

        try {
            Device device = new Device("Sensor-A", 15);

            System.out.println(device.summary());
            System.out.println("Name: " + device.getName());
            System.out.println("Battery: " + device.getBattery());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // T6
        System.out.println("\n=== T6: Device Validation ===");

        String[] names = {null, "", "   "};

        for (String name : names) {
            try {
                new Device(name, 50);
                System.out.println("Name accepted");
            } catch (IllegalArgumentException ex) {
                System.out.println(
                        "Invalid name rejected: " +
                                ex.getMessage()
                );
            }
        }

        int[] badBatteries = {-1, 101};

        for (int battery : badBatteries) {
            try {
                new Device("Test", battery);
                System.out.println("Battery accepted");
            } catch (IllegalArgumentException ex) {
                System.out.println(
                        "Invalid battery rejected: " +
                                ex.getMessage()
                );
            }
        }

        // Task 7 - Inventory
        System.out.println("\n=== Task 7: Inventory ===");

        List<Device> devices = new ArrayList<>();

        devices.add(new Device("Sensor-A", 15));

        MobileDevice inventoryPhone =
                new MobileDevice("Phone-B", 80);

        inventoryPhone.connect();

        devices.add(inventoryPhone);

        devices.add(new Device("Tablet-C", 45));

        for (Device device : devices) {
            System.out.println(device.summary());
        }

        // T7
        System.out.println("\n=== T7: Connection ===");

        MobileDevice phone =
                new MobileDevice("Phone-B", 80);

        System.out.println(
                "Connected: " + phone.isConnected()
        );

        phone.connect();

        System.out.println(
                "Connected: " + phone.isConnected()
        );

        phone.connect();

        System.out.println(
                "Connected: " + phone.isConnected()
        );

        // T8
        System.out.println("\n=== T8: Input Parsing ===");

        String[] inputs = {"80", "abc", "120"};

        for (String text : inputs) {
            try {
                int value = Integer.parseInt(text);

                System.out.println(
                        text + ": " + batteryStatus(value)
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                        text + ": enter a whole number"
                );

            } catch (IllegalArgumentException ex) {

                System.out.println(
                        text + ": " + ex.getMessage()
                );
            }
        }
    }
}