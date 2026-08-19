DROP TABLE IF EXISTS Attendee_Preference;


DROP TABLE IF EXISTS Event_Vendor;
DROP TABLE IF EXISTS Schedule_Performer;
DROP TABLE IF EXISTS Event_Supplier_Equipment;
DROP TABLE IF EXISTS Performer_social_media;
DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS Supplier_Equipment;
DROP TABLE IF EXISTS Venue_Equipment;
DROP TABLE IF EXISTS Performer;
DROP TABLE IF EXISTS Preferences;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS Attendee;
DROP TABLE IF EXISTS Venue;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Supplier;
DROP TABLE IF EXISTS Vendor;
DROP TABLE IF EXISTS Organizer;

DROP TYPE IF EXISTS VENUE_TYPE_ENUM;
DROP TYPE IF EXISTS EVENT_TYPE_ENUM;
DROP TYPE IF EXISTS EVENT_STATUS_ENUM;
DROP TYPE IF EXISTS PREFERENCE_TYPE_ENUM;
DROP TYPE IF EXISTS EXPERTISE_AREA_ENUM;
DROP TYPE IF EXISTS EQUIPMENT_TYPE_ENUM;
DROP TYPE IF EXISTS EQUIPMENT_CONDITION_ENUM;
DROP TYPE IF EXISTS EQUIPMENT_AVAILABILITY_STATUS;
DROP TYPE IF EXISTS PAYMENT_STATUS_ENUM;

CREATE TYPE VENUE_TYPE_ENUM AS ENUM (
    'conference_hall',
    'banquet_hall',
    'auditorium',
    'stadium',
    'theater',
    'outdoor_space',
    'restaurant',
    'hotel',
    'club',
    'gallery',
    'co_working_space',
    'sports_complex',
    'virtual',
    'other'
);

CREATE TYPE EVENT_TYPE_ENUM AS ENUM (
    'conference',
    'seminar',
    'workshop',
    'webinar',
    'concert',
    'festival',
    'party',
    'wedding',
    'meeting',
    'sports_event',
    'exhibition',
    'trade_show',
    'fundraiser',
    'competition',
    'ceremony',
    'performance',
    'networking',
    'training',
    'other'
);

CREATE TYPE EVENT_STATUS_ENUM AS ENUM (
    'scheduled',
    'published',
    'postponed',
    'cancelled',
    'in_progress',
    'completed'
);

CREATE TYPE PREFERENCE_TYPE_ENUM AS ENUM (
    'dietary',
    'seating',
    'accessibility',
    'communication',
    'notification',
    'merchandise',
    'other'
);

CREATE TYPE EXPERTISE_AREA_ENUM AS ENUM (
    'music',
    'dance',
    'theatre',
    'comedy',
    'magic',
    'speaker',
    'host',
    'influencer',
    'visual_arts',
    'dj',
    'other'
);

CREATE TYPE EQUIPMENT_TYPE_ENUM AS ENUM (
    'audio',
    'lighting',
    'video',
    'stage',
    'furniture',
    'decoration',
    'computer',
    'miscellaneous'
);

CREATE TYPE EQUIPMENT_CONDITION_ENUM AS ENUM (
    'NEW',
    'GOOD',
    'FAIR',
    'POOR',
    'BROKEN'
);

CREATE TYPE EQUIPMENT_AVAILABILITY_STATUS AS ENUM (
    'available',
    'reserved',
    'in_use',
    'maintenance',
    'unavailable'
);

CREATE TYPE PAYMENT_STATUS_ENUM AS ENUM (
    'pending',
    'completed',
    'failed',
    'refunded',
    'cancelled',
    'partially_paid'
);


CREATE TABLE Organizer(
	OrganizerID SERIAL PRIMARY KEY,
	Name VARCHAR(100) NOT NULL,
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	Phone VARCHAR(50) CHECK(Phone ~ '^\+?[0-9]+$'),
	Address VARCHAR(100) NOT NULL
);

CREATE TABLE Vendor(
	VendorID SERIAL PRIMARY KEY,
	Company_name VARCHAR(100) NOT NULL,
	Service_Type VARCHAR(100) NOT NULL,
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE Supplier(
	SupplierID SERIAL PRIMARY KEY,
	Supplier_name VARCHAR(100) NOT NULL,
	Product_type VARCHAR(100) NOT NULL,
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	Phone VARCHAR(50) CHECK(Phone ~ '^\+?[0-9]+$')
);

CREATE TABLE Admin(
	AdminID SERIAL PRIMARY KEY,
	Full_name VARCHAR(100) NOT NULL,
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	Phone VARCHAR(50) CHECK(Phone ~ '^\+?[0-9]+$')
);

CREATE TABLE Venue(
	VenueID SERIAL PRIMARY KEY,
	Venue_name VARCHAR(100) NOT NULL,
	Country VARCHAR(100) NOT NULL,
	City VARCHAR(100) NOT NULL,
	Street VARCHAR(100) NOT NULL,
	ZIP VARCHAR(10) NOT NULL,
	Capacity INT NOT NULL,
	Venue_type VENUE_TYPE_ENUM NOT NULL
);

CREATE TABLE Attendee(
	AttendeeID SERIAL PRIMARY KEY,
	Full_name VARCHAR(100) NOT NULL,
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	Phone VARCHAR(50) CHECK(Phone ~ '^\+?[0-9]+$')
);

CREATE TABLE Event(
	EventID SERIAL PRIMARY KEY,
	Event_name VARCHAR(100) NOT NULL,
	Event_type EVENT_TYPE_ENUM NOT NULL,
	Start_date TIMESTAMP NOT NULL,
	End_date TIMESTAMP NOT NULL,
	Description TEXT,
	Total_Budget NUMERIC(10, 2) NOT NULL,
	Status EVENT_STATUS_ENUM NOT NULL,
	VenueID INT REFERENCES Venue(VenueID) NOT NULL,
	AdminID INT REFERENCES Admin(AdminID) NOT NULL,
	OrganizerID INT REFERENCES Organizer(OrganizerID) NOT NULL,
	CONSTRAINT EVENT_DATES_CHECK CHECK (End_date > Start_date)
);

CREATE TABLE Preferences(
	PreferenceID SERIAL PRIMARY KEY,
	Preference_type PREFERENCE_TYPE_ENUM NOT NULL,
	Preference_value VARCHAR(100) NOT NULL
);

CREATE TABLE Performer(
	PerformerID SERIAL PRIMARY KEY,
	Full_name VARCHAR(100) NOT NULL,
	Expertise_Area EXPERTISE_AREA_ENUM NOT NULL,
	Phone VARCHAR(50) CHECK(Phone ~ '^\+?[0-9]+$'),
	Email VARCHAR(100) CHECK(Email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE Performer_social_media (
    PerformerID INT NOT NULL REFERENCES Performer(PerformerID) ON DELETE CASCADE,
    platform VARCHAR(100) NOT NULL,
    url TEXT NOT NULL,
    PRIMARY KEY (PerformerID, url)
);

CREATE TABLE Venue_Equipment(
	VenueID INT NOT NULL REFERENCES Venue(VenueID) ON DELETE CASCADE,
	EquipmentID SERIAL NOT NULL,
	Equipment_type EQUIPMENT_TYPE_ENUM NOT NULL,
	Condition EQUIPMENT_CONDITION_ENUM NOT NULL,
	Description TEXT,
	PRIMARY KEY (VenueID, EquipmentID)
);

CREATE TABLE Supplier_Equipment(
	SupplierID INT NOT NULL REFERENCES Supplier(SupplierID) ON DELETE CASCADE,
	EquipmentID SERIAL NOT NULL,
	Equipment_type EQUIPMENT_TYPE_ENUM NOT NULL,
	Condition EQUIPMENT_CONDITION_ENUM NOT NULL,
	Availability_status EQUIPMENT_AVAILABILITY_STATUS NOT NULL,
	Description TEXT,
	PRIMARY KEY (SupplierID, EquipmentID)
);

CREATE TABLE Schedule(
	EventID INT NOT NULL REFERENCES Event(EventID) ON DELETE CASCADE,
	ScheduleID SERIAL NOT NULL,
	Event_Date DATE NOT NULL,
	Start_time TIME NOT NULL,
	End_time TIME NOT NULL,
	Activity_name VARCHAR(100) NOT NULL,
	PRIMARY KEY(EventID, ScheduleID),
	CONSTRAINT SCHEDULE_DATES_CHECK CHECK (End_time > Start_time)
);

CREATE TABLE Ticket(
	EventID INT NOT NULL REFERENCES Event(EventID) ON DELETE CASCADE,
	TicketID SERIAL NOT NULL,
	AttendeeID INT REFERENCES Attendee(AttendeeID),
	Purchase_Date DATE,
	Price NUMERIC(10, 2),
	Row_number INT NOT NULL,
	Seat_number INT NOT NULL,
	PRIMARY KEY (EventID, TicketID)
);

CREATE TABLE Event_Vendor(
	EventID INT NOT NULL REFERENCES Event(EventID) ON DELETE CASCADE,
	VendorID INT NOT NULL REFERENCES Vendor(VendorID) ON DELETE CASCADE,
	Contract_amount NUMERIC(10, 2),
	Service_date DATE NOT NULL,
	Payment_status PAYMENT_STATUS_ENUM NOT NULL,
	PRIMARY KEY(EventID, VendorID)
);

CREATE TABLE Schedule_Performer(
	EventID INT NOT NULL,
	ScheduleID INT NOT NULL,
	PerformerID INT NOT NULL REFERENCES Performer(PerformerID) ON DELETE CASCADE,
	FOREIGN KEY (EventID, ScheduleID) REFERENCES Schedule(EventID, ScheduleID) ON DELETE CASCADE,
	PRIMARY KEY (EventID, ScheduleID, PerformerID)
);

CREATE TABLE Event_Supplier_equipment(
	EventID INT NOT NULL REFERENCES Event(EventID) ON DELETE CASCADE,
	SupplierID INT NOT NULL,
	EquipmentID INT NOT NULL,
	Price NUMERIC(10, 2),
	FOREIGN KEY (SupplierID, EquipmentID) REFERENCES Supplier_equipment(SupplierID, EquipmentID) ON DELETE CASCADE,
	PRIMARY KEY (EventID, SupplierID, EquipmentID)
);

CREATE TABLE Attendee_Preference(
	AttendeeID INT NOT NULL REFERENCES Attendee(AttendeeID) ON DELETE CASCADE, 
	PreferenceID INT NOT NULL REFERENCES Preferences(PreferenceID) ON DELETE CASCADE,
	PRIMARY KEY (AttendeeID, PreferenceID)
);