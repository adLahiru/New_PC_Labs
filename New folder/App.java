
/**
 * University Study Room Reservation System (USRRS)
 * 
 * This system is designed to help students at the University of Moratuwa book 
 * study rooms in the Educational Resources Center (ERC). It keeps track of room 
 * availability, ensures fair access, and makes the reservation process smooth.
 *
 * --- Functional Requirements (What the System Does) ---
 * 1. Study Room Management:
 *    - Each study room has a unique number, seating capacity, and availability status.
 *    - Students can view a list of all rooms and check which ones are available.
 * 
 * 2. Reservation and Cancellation:
 *    - Students can book a study room by entering its number.
 *    - If a room is already occupied, the system prevents double booking by throwing 
 *      a StudyRoomUnavailableException.
 *    - Students can cancel their reservation when they no longer need the room.
 * 
 * --- Non-Functional Requirements (How the System Works) ---
 * 1. Ease of Use:
 *    - The interface should be simple and intuitive, requiring minimal effort to navigate.
 * 
 * 2. Performance:
 *    - The system should respond to booking and availability requests within seconds.
 * 
 * 3. Scalability:
 *    - It should be able to support an increasing number of rooms and student users.
 * 
 * 4. Reliability:
 *    - The system should be available with minimal downtime to ensure students can always access it.
 * 
 * 5. Security:
 *    - Only verified students should be able to book rooms to prevent misuse.
 * 
 * 6. Maintainability:
 *    - The system should be designed to allow easy updates and new feature additions.
 * 
 */

import java.util.ArrayList;

/**
 * Main class for the University Study Room Reservation System.
 */
class UniversityStudyRoomReservationSystemGroup_Found404 {
    public static void main(String args[]) {
        int displayWidth = 100;
        String title = "University Study Room Reservation System";

        System.out.print("-".repeat(displayWidth) + "\n|");
        System.out.print(" ".repeat((displayWidth - title.length() - 2) / 2) + title);
        System.out
                .println(" ".repeat(displayWidth - 2 - title.length() - (displayWidth - title.length() - 2) / 2) + "|");
        System.out.print("-".repeat(displayWidth) + "\n");

        ArrayList<Student> students = new ArrayList<>();

        /**
         * Create some students and study rooms, and start reservation handlers.
         */
        // Create some students
        Student student1 = new Student("Alice", "S123", "2023", "Computer Science");
        Student student2 = new Student("Bob", "S124", "2023", "Mathematics");
        Student student3 = new Student("Charlie", "S125", "2023", "Electrical");
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // Create a new study room
        StudyRoom studyRoom = new StudyRoom("Room A", 20, 101, 0);
        StudyRoom studyRoom2 = new StudyRoom("Room B", 2, 102, 0);
        StudyRoom studyRoom3 = new StudyRoom("Room C", 10, 103, 0);

        // Create a reservation handler
        ReservationHandler reservationHandler = new ReservationHandler(studyRoom, students);
        ReservationHandler reservationHandler2 = new ReservationHandler(studyRoom2, students);
        ReservationHandler reservationHandler3 = new ReservationHandler(studyRoom3, students);

        // Start the reservation handler thread
        Thread reservationThread = new Thread(reservationHandler);
        Thread reservationThread2 = new Thread(reservationHandler2);
        Thread reservationThread3 = new Thread(reservationHandler3);

        reservationThread.start();
        reservationThread2.start();
        reservationThread3.start();
    }
}

/**
 * Handles reservations for a specific study room.
 */
class ReservationHandler implements Runnable {
    private StudyRoom studyRoom;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Student> reservedStudents = new ArrayList<>();

    /**
     * Constructor for ReservationHandler.
     * 
     * @param studyRoom The study room to manage reservations for.
     * @param students  The list of students eligible for reservations.
     */
    ReservationHandler(StudyRoom studyRoom, ArrayList<Student> students) {
        this.studyRoom = studyRoom;
        this.students = students;
    }

    /**
     * Adds a reservation for a student if the room is not full.
     * 
     * @param student The student to reserve the room for.
     * @throws StudyRoomUnavailableException If the room is full.
     */
    private void addReservation(Student student) throws StudyRoomUnavailableException {
        if (reservedStudents.size() < studyRoom.getCapacity()) {
            reservedStudents.add(student);
            System.out.println("Reservation added for " + student.getName() + " in " + studyRoom.getRoomName());
        } else {
            throw new StudyRoomUnavailableException(
                    "Room is full. Cannot add reservation for " + student.getName() + " in " + studyRoom.getRoomName());
        }
    }

    /**
     * Frees a reservation for a student.
     * 
     * @param student The student whose reservation is to be freed.
     */
    private void freeReservation(Student student) {
        reservedStudents.remove(student);
        System.out.println("Reservation freed for " + student.getName() + " in " + studyRoom.getRoomName());
    }

    /**
     * Runs the reservation handling logic in a separate thread.
     */
    @Override
    public void run() {
        // Logic to handle reservation
        System.out.println("Handling reservation for " + studyRoom.getRoomNumber() + " - " + studyRoom.getRoomName());
        for (int i = 0; i < students.size(); i++) {
            try {
                addReservation(students.get(i));
                Thread.sleep(1000);
            } catch (StudyRoomUnavailableException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Reservation interrupted for " + students.get(i).getName());
            }
        }
        // Free up reservations after use
        for (int i = 0; i < reservedStudents.size(); i++) {
            freeReservation(reservedStudents.get(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Freeing reservation interrupted for " + reservedStudents.get(i).getName());
            }
        }

    }
}

/**
 * Represents a study room with specific attributes.
 */
class StudyRoom {
    private String roomName;
    private int capacity;
    private int roomNumber;
    private int reservedCount;

    /**
     * Constructor for StudyRoom.
     * 
     * @param roomName      The name of the study room.
     * @param capacity      The maximum capacity of the study room.
     * @param roomNumber    The unique number of the study room.
     * @param reservedCount The current number of reservations.
     */
    StudyRoom(String roomName, int capacity, int roomNumber, int reservedCount) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.reservedCount = reservedCount;
    }

    /**
     * Gets the name of the study room.
     * 
     * @return The name of the study room.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Gets the capacity of the study room.
     * 
     * @return The capacity of the study room.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the room number of the study room.
     * 
     * @return The room number of the study room.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gets the current number of reservations.
     * 
     * @return The current number of reservations.
     */
    public int getReservedCount() {
        return reservedCount;
    }
}

/**
 * Represents a student with specific attributes.
 */
class Student {
    private String name;
    private String studentId;
    private String intake;
    private String department;

    /**
     * Constructor for Student.
     * 
     * @param name       The name of the student.
     * @param studentId  The unique ID of the student.
     * @param intake     The intake year of the student.
     * @param department The department of the student.
     */
    Student(String name, String studentId, String intake, String department) {
        this.name = name;
        this.studentId = studentId;
        this.intake = intake;
        this.department = department;
    }

    /**
     * Gets the name of the student.
     * 
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student ID.
     * 
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the intake year of the student.
     * 
     * @return The intake year of the student.
     */
    public String getIntake() {
        return intake;
    }

    /**
     * Gets the department of the student.
     * 
     * @return The department of the student.
     */
    public String getDepartment() {
        return department;
    }
}

/**
 * Exception thrown when a study room is unavailable for reservation.
 */
class StudyRoomUnavailableException extends Exception {
    /**
     * Constructor for StudyRoomUnavailableException.
     * 
     * @param message The exception message.
     */
    public StudyRoomUnavailableException(String message) {
        super(message);
    }
}