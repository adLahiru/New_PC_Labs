import java.lang.reflect.Array;
import java.util.ArrayList;

class UniversityStudyRoomReservationSystemGroup_Found404 {
    public static void main(String args[]) {
        // Create a new study room
        StudyRoom studyRoom = new StudyRoom("Room A", 20, 101, 0);
        StudyRoom studyRoom2 = new StudyRoom("Room B", 2, 102, 0);
        StudyRoom studyRoom3 = new StudyRoom("Room C", 10, 103, 0);

        // Create a reservation handler
        ReservationHandler reservationHandler = new ReservationHandler(studyRoom);
        ReservationHandler reservationHandler2 = new ReservationHandler(studyRoom2);
        ReservationHandler reservationHandler3 = new ReservationHandler(studyRoom3);

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

    ReservationHandler(StudyRoom studyRoom) {
        this.studyRoom = studyRoom;
    }

    private void addReservation(Student student) throws StudyRoomUnavailableException {
        if (students.size() < studyRoom.getCapacity()) {
            students.add(student);
            System.out.println("Reservation added for " + student.getName() + " in " + studyRoom.getRoomName());
        } else {
            throw new StudyRoomUnavailableException(
                    "Room is full. Cannot add reservation for " + student.getName() + " in " + studyRoom.getRoomName());
        }
    }

    @Override
    public void run() {
        // Logic to handle reservation
        System.out.println("Handling reservation for " + studyRoom.getRoomNumber() + " - " + studyRoom.getRoomName());

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