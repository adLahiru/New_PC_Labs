class UniversityStudyRoomReservationSystemGroup_Found404 {
    public static void main(String args[]) {
        System.out.println("Hi");
    }
}

class ReservationHandler implements Runnable {
    private StudyRoom studyRoom;

    ReservationHandler(StudyRoom studyRoom) {
        this.studyRoom = studyRoom;
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