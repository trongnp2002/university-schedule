CREATE DATABASE Asignment
USE Asignment

CREATE TABLE Major(
  major_id int IDENTITY(1,1) PRIMARY KEY,
  major_name VARCHAR(50) NOT NULL,
  major_group VARCHAR(10) NOT NULL
)
CREATE TABLE Students (
  student_id int PRIMARY KEY,
  [name] VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE,
  phone_number VARCHAR(20),
  major_id int NOT NULL,
  year_of_study varchar(19) NOT NULL,
  [address] VARCHAR(200),
   FOREIGN KEY (major_id) REFERENCES Major(major_id)
);

CREATE TABLE Departments (
  department_id int IDENTITY(1,1) PRIMARY KEY,
  department_name VARCHAR(50) NOT NULL
);

CREATE TABLE Courses (
  course_id int IDENTITY(1,1) PRIMARY KEY,
  course_code VARCHAR(100) NOT NULL,
  course_name VARCHAR(100) NOT NULL,
  department_id int,
  semester int NOT NULL, 
  FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

CREATE TABLE Buildings (
  building_id int IDENTITY(1,1) PRIMARY KEY,
  building_name VARCHAR(50) NOT NULL,
);

CREATE TABLE Rooms (
  room_id int IDENTITY(1,1) PRIMARY KEY,
  room_name VARCHAR(50) NOT NULL,
  building_id int NOT NULL,
  [floor] int NOT NULL,
  FOREIGN KEY (building_id) REFERENCES Buildings(building_id)
);
CREATE TABLE Slots(
  slot_id  int NOT NULL IDENTITY(1,1) PRIMARY KEY,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL
)

CREATE TABLE TimeSlots (
  timeslot_id int IDENTITY(1,1) PRIMARY KEY,
  day_of_week VARCHAR(10) NOT NULL,
  [date] Date,
  slot_id int not null,
  FOREIGN KEY (slot_id) REFERENCES Slots(slot_id),
);
CREATE TABLE Classes (
  class_id int PRIMARY KEY,
  class_name VARCHAR(50) NOT NULL,
  student_year VARCHAR(10),
  major_id int NOT NULL,
   FOREIGN KEY (major_id) REFERENCES Major(major_id)
);

CREATE TABLE ClassesCourse(
	classCourse_id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	class_id int,
	course_id int,
	room_id int NOT NULL,
	FOREIGN KEY (room_id) REFERENCES Rooms(room_id),
	FOREIGN KEY (class_id) REFERENCES Classes(class_id),
	FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);
CREATE TABLE Enrollments (
  enrollment_id int IDENTITY(1,1) PRIMARY KEY,
  student_id int NOT NULL,
  classCourse_id int NOT NULL,
  FOREIGN KEY (student_id) REFERENCES Students(student_id),
  FOREIGN KEY (classCourse_id) REFERENCES ClassesCourse(classCourse_id)
);

CREATE TABLE TimeSlotsClassesCourse(
	timeslot_id int NOT NULL,
	classCourse_id int NOT NULL,
	  FOREIGN KEY (classCourse_id) REFERENCES ClassesCourse(classCourse_id),
	  FOREIGN KEY (timeslot_id) REFERENCES TimeSlots(timeslot_id)
)


CREATE TABLE Professors (
  professor_id int IDENTITY(1,1) PRIMARY KEY,
  nickname varchar(50),
 [name] VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE,
	department_id int,
	 FOREIGN KEY (department_id) REFERENCES Departments(department_id),
);

CREATE TABLE ProfessorClassesCourse(
	professor_id int,
	classCourse_id int NOT NULL,
	 FOREIGN KEY (classCourse_id) REFERENCES ClassesCourse(classCourse_id),
	  FOREIGN KEY (professor_id) REFERENCES Professors(professor_id)
)


CREATE TABLE Attendance (
  attendance_id int IDENTITY(1,1) PRIMARY KEY,
  timeslot_id int,
  student_id int,
  [status] varchar(50), 
  FOREIGN KEY (student_id) REFERENCES Students(student_id),
  FOREIGN KEY (timeslot_id) REFERENCES TimeSlots(timeslot_id)
);


CREATE TABLE DepartmentCourses (
  department_id int,
  course_id int,
  FOREIGN KEY (department_id) REFERENCES Departments(department_id),
  FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);





CREATE TABLE Accounts (
  account_id int PRIMARY KEY,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  roll int NOT NULL,
  student_id int,
  professor_id int,
  FOREIGN KEY (student_id) REFERENCES Students(student_id),
  FOREIGN KEY (professor_id) REFERENCES Professors(professor_id)
);



INSERT  INTO Accounts
values(0, 'admin', '123', 3, null, null)