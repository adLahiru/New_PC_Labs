import java.lang.reflect.Array;
import java.util.ArrayList;

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

class ReservationHandler implements Runnable {
    private StudyRoom studyRoom;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Student> reservedStudents = new ArrayList<>();

    ReservationHandler(StudyRoom studyRoom, ArrayList<Student> students) {
        this.studyRoom = studyRoom;
        this.students = students;
    }

    private void addReservation(Student student) throws StudyRoomUnavailableException {
        if (reservedStudents.size() < studyRoom.getCapacity()) {
            reservedStudents.add(student);
            System.out.println("Reservation added for " + student.getName() + " in " + studyRoom.getRoomName());
        } else {
            throw new StudyRoomUnavailableException(
                    "Room is full. Cannot add reservation for " + student.getName() + " in " + studyRoom.getRoomName());
        }
    }

    private void freeReservation(Student student) {
        reservedStudents.remove(student);
        System.out.println("Reservation freed for " + student.getName() + " in " + studyRoom.getRoomName());
    }

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

class StudyRoom {
    private String roomName;
    private int capacity;
    private int roomNumber;
    private int reservedCount;

    StudyRoom(String roomName, int capacity, int roomNumber, int reservedCount) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.reservedCount = reservedCount;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getReservedCount() {
        return reservedCount;
    }
}

class Student {
    private String name;
    private String studentId;
    private String intake;
    private String department;

    Student(String name, String studentId, String intake, String department) {
        this.name = name;
        this.studentId = studentId;
        this.intake = intake;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getIntake() {
        return intake;
    }

    public String getDepartment() {
        return department;
    }
}

class StudyRoomUnavailableException extends Exception {
    public StudyRoomUnavailableException(String message) {
        super(message);
    }
}