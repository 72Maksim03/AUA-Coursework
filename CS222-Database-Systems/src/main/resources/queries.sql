	--CRUD Operations for Event Table
	INSERT INTO Event (EventID, Event_name, Event_type, Start_date, End_date, Description, Total_Budget, Status, VenueID, AdminID, OrganizerID) VALUES (711, 'Tech Innovation Expo', 'conference', '2025-11-25 09:00:00', '2025-11-25 12:00:00', 'Annual expo showcasing new tech', 50000, 'scheduled', 401, 301, 1);
	SELECT * FROM Event WHERE EventID=711;
	UPDATE Event SET Event_type='exhibition' WHERE EventID=711;
	DELETE FROM Event WHERE EventID=711;

	-- CRUD Operations for Attendee Table
	INSERT INTO Attendee (AttendeeID, full_name, email, phone) VALUES (551, 'Arthur Washburne', 'arthur.wash@example.com', +37466778899);
	SELECT * FROM Attendee WHERE AttendeeID=551;
	UPDATE Attendee SET full_name='Arthur Smith' WHERE AttendeeID=551;
	DELETE FROM Attendee WHERE AttendeeID=551;

	-- CRUD Operations for Venue Table
	INSERT INTO Venue (VenueID, Venue_name, country, city, street, zip, capacity, venue_type) VALUES (409, 'Yandex Hall', 'Armenia', 'Yerevan', '35 Moskovyan Street', '0002', 300, 'conference_hall');
	SELECT * FROM Venue WHERE VenueID=409;
	UPDATE Venue SET capacity=400 WHERE VenueID=409;
	DELETE FROM Venue WHERE VenueID=409;

	-- CRUD Operations for Performer Table
	INSERT INTO Performer (PerformerID, full_name, expertise_area, phone, email) VALUES (616, 'Leonardo Da Vinci', 'other', '+37499887766', 'leonardo.da@vinci.com');
	SELECT * FROM Performer WHERE PerformerID=616;
	UPDATE Performer SET expertise_area='dj' WHERE PerformerID=616;
	DELETE FROM Performer WHERE PerformerID=616;

	-- CRUD Operations for Vendor Table
	INSERT INTO Vendor (VendorID, Company_name, Service_type, email) VALUES (111, 'Default Name', 'Cleaning & Janitorial', 'default@clean.com');
	SELECT * FROM Vendor WHERE VendorID=111;
	UPDATE Vendor SET email='default.name@clean.com' WHERE VendorID=111;
	DELETE FROM Vendor WHERE VendorID=111;

	-- CRUD Operations for Supplier Table
	INSERT INTO Supplier (SupplierID, Supplier_name, Product_type, email, phone) VALUES (211, 'IT Company', 'Computers & Networking', 'it@gmail.com', '+37499887766');
	SELECT * FROM Supplier WHERE SupplierID=211;
	UPDATE Supplier SET email='itCompany@gmail.com' WHERE SupplierID=211;
	DELETE FROM Supplier WHERE SupplierID=211;

	-- CRUD Operations for Venue_Equipment Table
	INSERT INTO Venue_Equipment (VenueID, EquipmentID, Equipment_type, condition, description) VALUES (408, 4, 'furniture', 'good', 'default description');
	SELECT * FROM Venue_Equipment WHERE VenueID=408;
	UPDATE Venue_Equipment SET condition='new' WHERE VenueID=408 AND EquipmentID=4;
	DELETE FROM Venue_Equipment WHERE VenueID=408 AND EquipmentID=4;

	-- CRUD Operations for Supplier_Equipment Table
	INSERT INTO Supplier_Equipment (SupplierID, EquipmentID, Equipment_type, Condition, Availability_status, description) VALUES (201, 4, 'lighting', 'poor', 'available', 'Description');
	SELECT * FROM Supplier_Equipment WHERE SupplierID=201;
	UPDATE Supplier_Equipment SET Condition='fair' WHERE SupplierID=201 AND EquipmentID=4;
	DELETE FROM Supplier_Equipment WHERE SupplierID=201 AND EquipmentID=4;

	-- User Specific Operations
	-- search/filter events by event_type
	SELECT * FROM Event WHERE event_type='conference';

	-- search/filter events by start_date date range
	SELECT * FROM Event WHERE Start_date BETWEEN '2025-04-01' AND '2025-06-30';

	-- search/filter event by name/keyword
	SELECT * FROM Event WHERE event_name like '%Music%';

	-- Book Tickets
	UPDATE Ticket SET AttendeeID = 504, Purchase_date = '2025-11-16' WHERE EventID = 701  AND Row_number = 12  AND Seat_number = 2  AND AttendeeID IS NULL;

	-- Cancel Booking
	UPDATE Ticket SET AttendeeID = NULL, Purchase_date = NULL, Price = NULL WHERE EventID = 701 AND Row_number = 12 AND Seat_number = 2 AND AttendeeID = 504;

	-- Set Preferences
	INSERT INTO Attendee_Preference (AttendeeID, PreferenceID) VALUES (502, 801);

	-- Remove Preferences
	DELETE FROM Attendee_Preference WHERE AttendeeID=502 AND PreferenceID=801;

	-- Create Events
	INSERT INTO Event (EventID, Event_name, Event_type, start_date, end_date, description, total_budget, status, VenueID, AdminID, OrganizerID) VALUES (711, 'CTF', 'competition', '2025-11-22 15:00:00', '2025-11-23 15:00:00', 'AI Apocalypse', '1000000', 'completed', 409, 301, 3);

	-- Create Schedule for Event
	INSERT INTO Schedule (EventID, Event_date, Start_time, end_time, activity_name) VALUES 
	(711, '2025-11-22', '14:00:00', '15:00:00', 'Registration, Networking & Coffee'),
	(711, '2025-11-22', '15:00:00', '16:00:00', 'Opening speech & CTF start'),
	(711, '2025-11-22', '16:00:00', '18:00:00', 'CTF'),
	(711, '2025-11-22', '18:00:00', '18:30:00', 'Dinner'),
	(711, '2025-11-22', '18:30:00', '22:00:00', 'CTF'),
	(711, '2025-11-22', '22:00:00', '22:30:00', 'Supper'),
	(711, '2025-11-22', '22:30:00', '24:00:00', 'CTF'),
	(711, '2025-11-23', '00:00:00', '08:00:00', 'CTF'),
	(711, '2025-11-23', '08:00:00', '08:30:00', 'Breakfast'),
	(711, '2025-11-23', '08:30:00', '15:00:00', 'CTF');

	-- Update Event details
	UPDATE Event SET Event_name='AI Apocalypse CTF' WHERE EventID=711;

	-- Update Event Schedule
	UPDATE Schedule SET activity_name='CTF & Closing speech' WHERE EventID=711 AND ScheduleID=17;

	-- Cancel Event
	DELETE FROM Event WHERE EventID=702;

	-- Monitor Ticket booking for Organizer
	SELECT Event.EventID, Event.Event_name, COUNT(Ticket.TicketID) AS Tickets_Sold, SUM(Ticket.price) AS Revenue FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE OrganizerID=1 GROUP BY Event.EventID, Event.Event_name;

	-- Search for Vendors by service_type
	SELECT * FROM Vendor WHERE service_type='Security';

	-- Search for Suppliers by provided Product_type
	SELECT * FROM Supplier WHERE product_type='Audio_Equipment';

	-- Search for available products of the specified Supplier
	SELECT * FROM Supplier_Equipment WHERE SupplierID=201 AND availability_status='available';

	-- Search for available products
	SELECT * FROM Supplier_Equipment JOIN Supplier ON Supplier.SupplierID=Supplier_Equipment.SupplierID WHERE availability_status='available';

	-- View Equipment available in specified Venue
	SELECT * FROM Venue_Equipment WHERE VenueID=401;

	-- View Equipment available in every venue
	SELECT Venue.VenueID, Venue.Venue_name, Venue_Equipment.EquipmentID, Venue_Equipment.equipment_type, Venue_Equipment.condition, Venue_Equipment.description FROM Venue_Equipment JOIN Venue ON Venue_Equipment.VenueID=Venue.VenueID;

	-- View all Venues by venue_type
	SELECT * FROM Venue WHERE Venue_type='conference_hall';

	-- View all venues available in the provided time period
	SELECT * FROM Venue WHERE VenueID NOT IN (
		SELECT VenueID FROM Event WHERE start_date < '2025-05-20 20:00' AND End_date > '2025-05-10 14:00'
	);

	-- View list of all venues with their status in the provided time period
	SELECT Venue.VenueID, Venue.Venue_name, 
	CASE
		WHEN Event.EventID IS NULL THEN 'Available'
		ELSE 'Booked'
	END AS Availability
	FROM Venue LEFT JOIN Event ON Event.VenueID=Venue.VenueID AND start_date<'2025-05-20 20:00' AND end_date>'2025-05-10 14:00'
	ORDER BY Venue.VenueID;

	-- Ticket Sales by Event Ordered by revenue in descending order
	SELECT Event.EventID, Event.Event_name, COUNT(Ticket.TicketID) AS Tickets_Sold, SUM(Ticket.price) AS Revenue FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE Ticket.attendeeID IS NOT NULL GROUP BY Event.EventID, Event.Event_name ORDER BY Revenue DESC;

	-- Number of events and total revenue from them in the specified period of time
	SELECT COUNT(DISTINCT Event.EventID) AS Number_Of_Events, SUM(Ticket.Price) FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE Event.end_date<'2025-12-31' AND Event.start_date>'2025-01-01' AND Ticket.attendee_id IS NOT NULL;

	-- Number of times each venue was used in the specified period of time
	SELECT Venue.VenueID, Venue.Venue_name, COUNT(Event.EventID) AS number_of_times_used FROM Venue JOIN EVENT ON Venue.venueID=Event.venueID WHERE Event.end_date<'2025-12-31' AND Event.start_date>'2025-01-01' GROUP BY Venue.VenueID, Venue.Venue_name;

	-- Number of times each supplier provided equipments in the specified period of time
	SELECT Supplier.supplierID, Supplier.Supplier_name, COUNT(Event.EventID) AS Number_of_times_provided FROM Supplier JOIN event_supplier_equipment ON event_supplier_equipment.supplierID=Supplier.supplierID JOIN Event ON event_supplier_equipment.eventID=Event.EventID WHERE Event.end_date<'2025-12-31' AND Event.start_date>'2025-01-01' GROUP BY Supplier.SupplierID, Supplier.Supplier_name;

	-- How many events each performer performed, how many activities they performed on average and how long they performed on average in the specified period of time
	SELECT Performer.PerformerID, Performer.Full_name, COUNT(DISTINCT Event.EventID) AS number_of_events, (COUNT(Schedule.ScheduleID)/NULLIF(COUNT(DISTINCT Event.EventID), 0)) AS avg_activities_per_event, AVG(Schedule.end_time - Schedule.start_time) AS avg_duration FROM Performer JOIN Schedule_performer ON Performer.PerformerID=Schedule_performer.PerformerID JOIN Schedule ON Schedule.ScheduleID=Schedule_performer.ScheduleID AND Schedule.eventID=Schedule_performer.EventID JOIN Event ON Event.EventID=Schedule.EventID WHERE Event.end_date<'2025-12-31' AND Event.start_date>'2025-01-01' GROUP BY Performer.PerformerID, Performer.Full_name;

	-- Shows top 10 most active attendees in the specified period of time
	SELECT Attendee.AttendeeID, Attendee.Full_name, Attendee.email, COUNT(DISTINCT Event.EventID) AS number_of_events FROM Attendee JOIN Ticket ON Attendee.AttendeeID=Ticket.AttendeeID JOIN Event ON Ticket.EventID=Event.EventID WHERE Event.end_date<'2025-12-31' AND Event.start_date>'2024-01-01' GROUP BY Attendee.AttendeeID, Attendee.Full_name, Attendee.email ORDER BY number_of_events DESC LIMIT 10;

	-- Show Event name, type, event status, venue name, organizer name, number of performers, number of attendees
	WITH PerformerCount AS (
		SELECT EventID, COUNT(DISTINCT PerformerID) AS number_of_performers FROM Schedule_performer GROUP BY EventID
	),
	AttendeeCount AS(
		SELECT EventID, COUNT(DISTINCT AttendeeID) AS number_of_attendees FROM Ticket GROUP BY EventID
	)
	SELECT Event.EventID, Event.Event_name, Event.Event_type, Event.Status, Venue.Venue_name, Organizer.name, COALESCE(PerformerCount.number_of_performers, 0) AS performers_number, COALESCE(AttendeeCount.number_of_attendees, 0) AS attendees_number FROM Event JOIN Venue ON Venue.VenueID=Event.VenueID JOIN Organizer ON Organizer.organizerID=Event.OrganizerID LEFT JOIN PerformerCount ON PerformerCount.EventID=Event.EventID LEFT JOIN AttendeeCount ON AttendeeCount.EventID=Event.EventID;

	-- Shows Vendor name, Services provided, number of events they worked in, Average contract amount
	SELECT Vendor.vendorID, Vendor.Company_name, Vendor.Service_type, COUNT(DISTINCT Event_vendor.VendorID), AVG(Event_vendor.contract_amount) FROM Vendor JOIN Event_vendor ON Vendor.VendorID=Event_vendor.VendorID WHERE Event_vendor.payment_status='completed' GROUP BY Vendor.vendorID, Vendor.Company_name, Vendor.Service_type;

	-- Number of upcoming events in each venue
	SELECT Venue.VenueID, Venue.Venue_name, COUNT(DISTINCT Event.EventID) AS number_of_events FROM Venue JOIN Event ON Venue.VenueID=Event.VenueID WHERE Event.Start_date>'2025-01-01' GROUP BY Venue.VenueID, Venue.Venue_name ORDER BY number_of_events DESC;

	-- List of upcoming Events using Function
	CREATE OR REPLACE FUNCTION get_upcoming_events(venue_id INT, initial_date TIMESTAMP)
	RETURNS TABLE (
		event_id INT,
		event_name VARCHAR,
		start_date TIMESTAMP,
		end_date TIMESTAMP
	) AS $$
	BEGIN
		RETURN QUERY
		SELECT Event.EventID, Event.Event_name, Event.Start_date, Event.End_date FROM Event WHERE Event.VenueID = venue_id AND Event.Start_date >= initial_date ORDER BY Event.Start_date;
	END;
	$$ LANGUAGE plpgsql;

	SELECT * FROM get_upcoming_events(403, '2025-01-01');

	-- Function to automatically assign first empty seat to attendee
	CREATE OR REPLACE FUNCTION auto_assign(event_id INT, attendee_id INT, purchase DATE, purchase_price NUMERIC(10,2))
	RETURNS TEXT AS $$
	DECLARE
		r INT;
		s INT;
	BEGIN
		SELECT row_number, seat_number INTO r, s FROM Ticket WHERE eventID=event_id AND attendeeID IS NULL LIMIT 1;

		IF r IS NULL OR s IS NULL THEN
			RETURN 'No seats Available';
		END IF;

		UPDATE Ticket SET attendeeID=attendee_id, purchase_date=purchase, price=purchase_price WHERE eventID=event_id AND row_number=r AND seat_number=s;
		RETURN 'Seat assigned successfully: Row ' || r || ', Seat ' || s;
	END;
	$$ LANGUAGE plpgsql;

	SELECT auto_assign(709, 510, '2025-11-26', 50)

	-- Automatically generate free spaces for event
	CREATE OR REPLACE FUNCTION generateSeats(event_id INT, rowNumber INT, seatPerRow INT)
	RETURNS VOID AS $$
	DECLARE
		rw INT;
		st INT;
	BEGIN
		FOR rw IN 1..rowNumber LOOP
			FOR st IN 1..seatPerRow LOOP
				INSERT INTO Ticket (eventID, attendeeID, purchase_date, price, row_number, seat_number) VALUES (event_id, null, null, null, rw, st);
			END LOOP;
		END LOOP;
	END;
	$$ LANGUAGE plpgsql;

	SELECT generateSeats(711, 2, 2);

	-- Calculate profit from each event in specified period of time
	CREATE OR REPLACE FUNCTION profitFromEvent(event_id INT)
	RETURNS NUMERIC AS $$
	DECLARE
		total_revenue NUMERIC;
		contract_cost NUMERIC;
		supply_cost NUMERIC;
		total_cost NUMERIC;
		profit NUMERIC;
	BEGIN
		SELECT SUM(Price) INTO total_revenue FROM Ticket WHERE eventID=event_id;
		SELECT COALESCE(SUM(Contract_Amount),0) INTO contract_cost FROM event_vendor WHERE eventID=event_id;
		SELECT COALESCE(SUM(price),0) INTO supply_cost FROM event_supplier_equipment WHERE eventID=event_id;

		total_cost := supply_cost+contract_cost;
		profit := total_revenue - total_cost;
		RETURN profit;
	END;
	$$ LANGUAGE plpgsql;

	SELECT profitFromEvent(701);