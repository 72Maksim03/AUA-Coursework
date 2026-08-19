-- Organizer
INSERT INTO Organizer (OrganizerID, Name, Email, Phone, Address) VALUES
(1, 'TechWave Productions', 'contact@techwave.com', '+11234567890', '45 Innovation Dr, New York'),
(2, 'Global Fest Events', 'info@globalfest.com', '+442012345678', '10 Event Plaza, London'),
(3, 'Creative Minds Summit', 'hello@creativeminds.org', '+61400987654', '20 Idea Lane, Sydney'),
(4, 'Sports & Fitness Inc.', 'sales@sportsfit.net', '+33123456789', '80 Victory Road, Paris'),
(5, 'Virtual Connect Hub', 'support@vchub.co', '+493098765432', '5 Digital Way, Berlin');

-- Vendor
INSERT INTO Vendor (VendorID, Company_name, Service_Type, Email) VALUES
(101, 'Gourmet Bites Catering', 'Catering', 'bites@gourmet.com'),
(102, 'Secure Perimeter Services', 'Security', 'info@securep.com'),
(103, 'Pixel Perfect Media', 'Photography & Video', 'contact@pixelperfect.com'),
(104, 'Elite Cleaning Solutions', 'Cleaning & Janitorial', 'clean@elite.com'),
(105, 'Flora Dreams Decorations', 'Floral & Decor', 'design@floradreams.com'),
(106, 'Transport Pro', 'Logistics & Transport', 'logistics@transportpro.com'),
(107, 'Quick Print Services', 'Printing & Signage', 'print@quickprint.com'),
(108, 'Digital Streamers', 'Live Streaming', 'stream@digital.com'),
(109, 'Event Staffing Heroes', 'Event Staffing', 'staff@heroes.com'),
(110, 'Eco Waste Management', 'Waste Disposal', 'waste@eco.com');

-- Supplier
INSERT INTO Supplier (SupplierID, Supplier_name, Product_type, Email, Phone) VALUES
(201, 'Audio Tech Rentals', 'Audio Equipment', 'rentals@audiotech.com', '+15551234001'),
(202, 'Stage Light Central', 'Lighting Equipment', 'light@stagecentral.com', '+15551234002'),
(203, 'Visual Display Co.', 'Video Equipment', 'visuals@displayco.com', '+15551234003'),
(204, 'Event Furniture Group', 'Furniture', 'furnish@eventfg.com', '+15551234004'),
(205, 'Acoustics Pro', 'Sound Proofing', 'sound@acoustics.com', '+15551234005'),
(206, 'Digital Signage Solutions', 'Screens and Kiosks', 'signs@digital.com', '+15551234006'),
(207, 'Temporary Structures Inc.', 'Tents & Stages', 'structures@temp.com', '+15551234007'),
(208, 'Power Supply Masters', 'Generators & Power', 'power@masters.com', '+15551234008'),
(209, 'IT Hardware Lease', 'Computers & Networking', 'lease@ithardware.com', '+15551234009'),
(210, 'Security Camera Hire', 'Surveillance Equipment', 'cams@securityhire.com', '+15551234010');

-- Admin
INSERT INTO Admin (AdminID, Full_name, Email, Phone) VALUES
(301, 'Alex Johnson', 'alex.johnson@admin.com', '+11239991111'),
(302, 'Bhavna Singh', 'bhavna.singh@admin.com', '+44209992222'),
(303, 'Carlos Rivera', 'carlos.rivera@admin.com', '+3319993333');

-- Venue
INSERT INTO Venue (VenueID, Venue_name, Country, City, Street, ZIP, Capacity, Venue_type) VALUES
(401, 'Grand Conference Hall', 'USA', 'New York', '700 5th Ave', '10019', 5000, 'conference_hall'),
(402, 'The Globe Arena', 'UK', 'London', '1 Millennium Way', 'SE10 0NF', 20000, 'stadium'),
(403, 'Harbor View Banquet', 'USA', 'Seattle', '10 Pier 56', '98101', 500, 'banquet_hall'),
(404, 'Digital Event Platform', 'Global', 'Virtual', 'N/A', 'VRTUAL', 100000, 'virtual'),
(405, 'Central City Theatre', 'Canada', 'Toronto', '120 King St W', 'M5H 1T9', 800, 'theater'),
(406, 'Green Meadow Park', 'Australia', 'Sydney', 'Park Rd', '2000', 15000, 'outdoor_space'),
(407, 'The Club House', 'Spain', 'Madrid', 'Calle Gran Via 5', '28013', 300, 'club'),
(408, 'Modern Art Gallery', 'Germany', 'Berlin', 'Gallery Lane 1A', '10178', 150, 'gallery'),
(409, 'Engineering City', 'Armenia', 'Yerevan', '21, 1 Bagrevand St', '002', 5000, 'conference_hall');

-- Attendee
INSERT INTO Attendee (AttendeeID, Full_name, Email, Phone) VALUES
(501, 'Alice Smith', 'alice.s@example.com', '+1800100001'),
(502, 'Bob Johnson', 'bob.j@example.com', '+1800100002'),
(503, 'Charlie Brown', 'charlie.b@example.com', '+1800100003'),
(504, 'Diana Prince', 'diana.p@example.com', '+1800100004'),
(505, 'Ethan Hunt', 'ethan.h@example.com', '+1800100005'),
(506, 'Fiona Glen', 'fiona.g@example.com', '+1800100006'),
(507, 'George King', 'george.k@example.com', '+1800100007'),
(508, 'Hannah Lee', 'hannah.l@example.com', '+1800100008'),
(509, 'Ivy Chen', 'ivy.c@example.com', '+1800100009'),
(510, 'Jack Ryan', 'jack.r@example.com', '+1800100010'),
(511, 'Kelly O’Connell', 'kelly.o@example.com', '+1800100011'),
(512, 'Liam Parker', 'liam.p@example.com', '+1800100012'),
(513, 'Mia Torres', 'mia.t@example.com', '+1800100013'),
(514, 'Noah Wilson', 'noah.w@example.com', '+1800100014'),
(515, 'Olivia Rodriguez', 'olivia.r@example.com', '+1800100015'),
(516, 'Peter Quill', 'peter.q@example.com', '+1800100016'),
(517, 'Quinn Fabray', 'quinn.f@example.com', '+1800100017'),
(518, 'Robert Stark', 'robert.s@example.com', '+1800100018'),
(519, 'Sarah Jones', 'sarah.j@example.com', '+1800100019'),
(520, 'Thomas Edison', 'thomas.e@example.com', '+1800100020'),
(521, 'Ursula Vance', 'ursula.v@example.com', '+1800100021'),
(522, 'Victor Stone', 'victor.s@example.com', '+1800100022'),
(523, 'Wendy Testaburger', 'wendy.t@example.com', '+1800100023'),
(524, 'Xavier Frost', 'xavier.f@example.com', '+1800100024'),
(525, 'Yara Greyjoy', 'yara.g@example.com', '+1800100025'),
(526, 'Zane Malik', 'zane.m@example.com', '+1800100026'),
(527, 'Aaron Burr', 'aaron.b@example.com', '+1800100027'),
(528, 'Betty Cooper', 'betty.c@example.com', '+1800100028'),
(529, 'Colin Firth', 'colin.f@example.com', '+1800100029'),
(530, 'Daisy Ridley', 'daisy.r@example.com', '+1800100030'),
(531, 'Eric Foreman', 'eric.f@example.com', '+1800100031'),
(532, 'Grace Hopper', 'grace.h@example.com', '+1800100032'),
(533, 'Henry Pym', 'henry.p@example.com', '+1800100033'),
(534, 'Irene Adler', 'irene.a@example.com', '+1800100034'),
(535, 'Julianna Moore', 'julianna.m@example.com', '+1800100035'),
(536, 'Karen Page', 'karen.p@example.com', '+1800100036'),
(537, 'Leo Valdez', 'leo.v@example.com', '+1800100037'),
(538, 'Molly Ringwald', 'molly.r@example.com', '+1800100038'),
(539, 'Nancy Drew', 'nancy.d@example.com', '+1800100039'),
(540, 'Owen Grady', 'owen.g@example.com', '+1800100040'),
(541, 'Pennywise', 'pennywise@example.com', '+1800100041'),
(542, 'Ragnar Lothbrok', 'ragnar.l@example.com', '+1800100042'),
(543, 'Sheldon Cooper', 'sheldon.c@example.com', '+1800100043'),
(544, 'Tina Fey', 'tina.f@example.com', '+1800100044'),
(545, 'Ulysses Klaw', 'ulysses.k@example.com', '+1800100045'),
(546, 'Violet Parr', 'violet.p@example.com', '+1800100046'),
(547, 'Winston Zeddemore', 'winston.z@example.com', '+1800100047'),
(548, 'Yennefer Vengerberg', 'yennefer.v@example.com', '+1800100048'),
(549, 'Zoe Washburne', 'zoe.w@example.com', '+1800100049'),
(550, 'Arthur Dent', 'arthur.d@example.com', '+1800100050');

-- Performer
INSERT INTO Performer (PerformerID, Full_name, Expertise_Area, Phone, Email) VALUES
(601, 'Ella Maestro', 'music', '+1900100001', 'ella.m@artist.com'),
(602, 'Dr. Sarah Patel', 'speaker', '+1900100002', 'sarah.p@speaker.com'),
(603, 'The Comedy Duo', 'comedy', '+1900100003', 'duo@comedy.com'),
(604, 'DJ Beat Drop', 'dj', '+1900100004', 'beatdrop@dj.com'),
(605, 'Mystic Max', 'magic', '+1900100005', 'max@magic.com'),
(606, 'Ballet Bloom', 'dance', '+1900100006', 'bloom@dance.com'),
(607, 'John Doe', 'speaker', '+1900100007', 'john.doe@speaker.com'),
(608, 'The Jazz Cats', 'music', '+1900100008', 'jazz@cats.com'),
(609, 'Maria Lopez', 'host', '+1900100009', 'maria.l@host.com'),
(610, 'Innovate Talk Group', 'speaker', '+1900100010', 'talk@innovate.com'),
(611, 'Silent Screams Theatre', 'theatre', '+1900100011', 'theatre@ss.com'),
(612, 'The Rockstar Band', 'music', '+1900100012', 'rock@band.com'),
(613, 'Visual Artist Vee', 'visual_arts', '+1900100013', 'vee@visual.com'),
(614, 'Fitness Guru Fran', 'influencer', '+1900100014', 'fran@fitness.com'),
(615, 'Dr. Tech Genius', 'speaker', '+1900100015', 'genius@tech.com');

-- Event
INSERT INTO Event (EventID, Event_name, Event_type, Start_date, End_date, Description, Total_Budget, Status, VenueID, AdminID, OrganizerID) VALUES
(701, 'Future of AI Conference 2024', 'conference', '2024-11-15 09:00:00', '2024-11-17 17:00:00', 'Premier annual conference on artificial intelligence.', 50000.00, 'published', 401, 301, 1),
(702, 'Summer Music Festival', 'festival', '2025-06-20 18:00:00', '2025-06-22 23:00:00', 'Three days of pop, rock, and indie music.', 150000.00, 'scheduled', 402, 302, 2),
(703, 'Marketing Masterclass Webinar', 'webinar', '2024-12-05 10:00:00', '2024-12-05 12:00:00', 'Learn the latest digital marketing trends.', 2000.00, 'completed', 404, 301, 1),
(704, 'Startup Pitch Competition', 'competition', '2025-01-10 14:00:00', '2025-01-10 18:00:00', 'Local startups compete for seed funding.', 15000.00, 'published', 403, 303, 3),
(705, 'Global Sports Expo', 'exhibition', '2025-03-01 10:00:00', '2025-03-02 18:00:00', 'Exhibition of the latest in sports technology and gear.', 80000.00, 'scheduled', 406, 302, 4),
(706, 'Poetry Night at The Club', 'performance', '2024-10-25 19:30:00', '2024-10-25 22:00:00', 'An evening of spoken word poetry and music.', 3000.00, 'completed', 407, 303, 3),
(707, 'Advanced DevOps Workshop', 'workshop', '2025-02-14 09:00:00', '2025-02-14 16:00:00', 'Hands-on training for DevOps engineers.', 7500.00, 'published', 401, 301, 1),
(708, 'Charity Gala Dinner', 'fundraiser', '2025-04-20 19:00:00', '2025-04-20 23:00:00', 'Annual charity event to raise funds for local schools.', 40000.00, 'scheduled', 403, 302, 2),
(709, 'Horror Film Festival', 'festival', '2025-10-30 18:00:00', '2025-11-01 01:00:00', 'Three nights of independent horror films.', 25000.00, 'published', 405, 303, 2),
(710, 'Abstract Art Showcase', 'exhibition', '2025-05-15 11:00:00', '2025-05-15 17:00:00', 'A one-day exhibition of modern abstract art.', 6000.00, 'scheduled', 408, 301, 5),
(721, 'Future of AI Conference 2025', 'conference', '2025-12-15 09:00:00', '2025-12-17 17:00:00', 'Premier annual conference on artificial intelligence.', 50000.00, 'published', 401, 301, 1);

-- Schedule
INSERT INTO Schedule (EventID, ScheduleID, Event_Date, Start_time, End_time, Activity_name) VALUES
(701, 1001, '2024-11-15', '09:00:00', '10:00:00', 'Keynote: The Future of Neural Networks'),
(701, 1002, '2024-11-15', '10:30:00', '11:30:00', 'Breakout Session 1: Ethical AI'),
(701, 1003, '2024-11-16', '09:00:00', '11:00:00', 'Deep Dive: ML in Finance'),
(701, 1004, '2024-11-17', '15:00:00', '17:00:00', 'Closing Panel: AI and Society'),
(702, 1005, '2025-06-20', '19:00:00', '20:30:00', 'Opening Act: The Jazz Cats'),
(702, 1006, '2025-06-21', '21:00:00', '23:00:00', 'Headliner: The Rockstar Band'),
(703, 1007, '2024-12-05', '10:00:00', '12:00:00', 'Masterclass Session'),
(704, 1008, '2025-01-10', '14:00:00', '16:00:00', 'Startup Pitches Round 1'),
(704, 1009, '2025-01-10', '16:30:00', '18:00:00', 'Judges Deliberation & Winners'),
(705, 1010, '2025-03-01', '10:00:00', '12:00:00', 'Exhibitor Setup & Public Entry'),
(705, 1011, '2025-03-02', '14:00:00', '16:00:00', 'Fitness Challenge Demo'),
(706, 1012, '2024-10-25', '19:30:00', '20:30:00', 'Open Mic Session'),
(706, 1013, '2024-10-25', '20:45:00', '21:30:00', 'Featured Poet Performance'),
(707, 1014, '2025-02-14', '09:00:00', '12:00:00', 'Session 1: Infrastructure as Code'),
(707, 1015, '2025-02-14', '13:00:00', '16:00:00', 'Session 2: CI/CD Pipelines'),
(708, 1016, '2025-04-20', '19:00:00', '20:00:00', 'Cocktail Reception'),
(708, 1017, '2025-04-20', '20:00:00', '21:00:00', 'Dinner and Auction'),
(709, 1018, '2025-10-30', '19:00:00', '21:00:00', 'Screening: Shadow Creek'),
(709, 1019, '2025-10-31', '21:00:00', '23:00:00', 'Screening: The Attic'),
(710, 1020, '2025-05-15', '11:00:00', '17:00:00', 'Open Exhibition');

-- Preferences
INSERT INTO Preferences (PreferenceID, Preference_type, Preference_value) VALUES
(801, 'dietary', 'Vegan'),
(802, 'dietary', 'Gluten-Free'),
(803, 'accessibility', 'Wheelchair Access'),
(804, 'accessibility', 'Hearing Loop Required'),
(805, 'seating', 'Front Row'),
(806, 'seating', 'Aisle Seat'),
(807, 'notification', 'Email Only'),
(808, 'communication', 'Sign Language Interpreter');

-- Performer_social_media
INSERT INTO Performer_social_media (PerformerID, platform, url) VALUES
(601, 'Instagram', 'https://instagram.com/ellamaestro'),
(601, 'Spotify', 'https://spotify.com/ellamaestro'),
(602, 'LinkedIn', 'https://linkedin.com/in/sarahpatel'),
(603, 'YouTube', 'https://youtube.com/comedyduo'),
(603, 'TikTok', 'https://tiktok.com/thecomedyduo'),
(604, 'SoundCloud', 'https://soundcloud.com/beatdrop'),
(604, 'Twitter', 'https://twitter.com/djbd'),
(605, 'Instagram', 'https://instagram.com/mysticmax'),
(606, 'Vimeo', 'https://vimeo.com/balletbloom'),
(607, 'Twitter', 'https://twitter.com/john_doe_speak'),
(607, 'Website', 'https://johndoe.com'),
(608, 'Instagram', 'https://instagram.com/jazzcatsband'),
(608, 'Bandcamp', 'https://jazzcats.bandcamp.com'),
(609, 'LinkedIn', 'https://linkedin.com/in/marialopezhost'),
(610, 'Website', 'https://innovatetalk.org'),
(611, 'Instagram', 'https://instagram.com/sstheatre'),
(612, 'YouTube', 'https://youtube.com/therockstarband'),
(613, 'Website', 'https://visualvee.com'),
(614, 'Instagram', 'https://instagram.com/franfit'),
(615, 'LinkedIn', 'https://linkedin.com/in/drtechgenius');

-- Venue_Equipment
INSERT INTO Venue_Equipment (VenueID, EquipmentID, Equipment_type, Condition, Description) VALUES
(401, 1, 'audio', 'GOOD', 'Large format mixing console'),
(401, 2, 'lighting', 'GOOD', 'LED stage lighting rig'),
(401, 3, 'video', 'NEW', 'Two large projection screens'),
(402, 1, 'stage', 'GOOD', 'Modular main stage setup'),
(402, 2, 'audio', 'GOOD', 'Stadium line array speaker system'),
(403, 1, 'furniture', 'FAIR', '100 banquet chairs'),
(404, 1, 'computer', 'NEW', 'High-end virtual server cluster'),
(405, 1, 'lighting', 'NEW', 'Spotlight system'),
(405, 2, 'audio', 'FAIR', 'Microphone set (wireless)'),
(406, 1, 'miscellaneous', 'GOOD', 'Portable restroom units (10)'),
(407, 1, 'audio', 'GOOD', 'Club sound system'),
(407, 2, 'lighting', 'GOOD', 'Disco lights and lasers'),
(408, 1, 'decoration', 'NEW', 'Adjustable track lighting'),
(408, 2, 'miscellaneous', 'GOOD', 'Security pedestals'),
(408, 3, 'furniture', 'GOOD', 'Display plinths');

-- Supplier_Equipment
INSERT INTO Supplier_Equipment (SupplierID, EquipmentID, Equipment_type, Condition, Availability_status, Description) VALUES
(201, 1, 'audio', 'NEW', 'available', 'Professional wireless microphone system'),
(201, 2, 'audio', 'GOOD', 'reserved', 'Portable PA system'),
(201, 3, 'audio', 'FAIR', 'in_use', 'Outdoor subwoofers (4 units)'),
(202, 1, 'lighting', 'NEW', 'available', 'Moving head beam lights (12 units)'),
(202, 2, 'lighting', 'GOOD', 'available', 'LED par can lights (50 units)'),
(202, 3, 'lighting', 'POOR', 'unavailable', 'Fog machine'),
(203, 1, 'video', 'GOOD', 'reserved', '98 inch 4K display screen'),
(203, 2, 'video', 'NEW', 'available', 'High-lumen projector'),
(204, 1, 'furniture', 'GOOD', 'available', 'Cocktail tables (20 units)'),
(204, 2, 'furniture', 'FAIR', 'in_use', 'Folding chairs (500 units)'),
(204, 3, 'furniture', 'NEW', 'available', 'Velvet rope stanchions'),
(207, 1, 'stage', 'GOOD', 'reserved', '20x15m outdoor stage structure'),
(207, 2, 'stage', 'NEW', 'available', 'Stage skirting and backdrop'),
(209, 1, 'computer', 'GOOD', 'available', 'Registration laptops (10 units)'),
(209, 2, 'computer', 'NEW', 'available', 'Network switch (48 port)'),
(205, 1, 'miscellaneous', 'GOOD', 'available', 'Acoustic treatment panels'),
(206, 1, 'miscellaneous', 'GOOD', 'in_use', 'Digital entry kiosks'),
(208, 1, 'miscellaneous', 'NEW', 'available', '30kW Diesel Generator'),
(210, 1, 'video', 'GOOD', 'available', 'CCTV camera system'),
(210, 2, 'video', 'FAIR', 'reserved', 'Portable security monitor');

INSERT INTO Ticket (EventID, TicketID, AttendeeID, Purchase_Date, Price, Row_number, Seat_number) VALUES
(701, 1, 501, '2024-10-01', 599.00, 10, 5),
(701, 2, 502, '2024-10-05', 599.00, 10, 6),
(701, 3, 503, '2024-10-06', 499.00, 12, 1),
(702, 1, 504, '2024-11-15', 85.00, 0, 0),
(702, 2, 505, '2024-11-15', 85.00, 0, 0),
(702, 3, 506, '2024-11-20', 120.00, 1, 1),
(702, 4, 507, '2024-11-21', 85.00, 0, 0),
(703, 1, 508, '2024-11-25', 99.99, 0, 0),
(703, 2, 509, '2024-11-26', 99.99, 0, 0),
(704, 1, 510, '2024-12-01', 50.00, 5, 2),
(704, 2, 511, '2024-12-01', 50.00, 5, 3),
(705, 1, 512, '2025-01-10', 20.00, 0, 0),
(705, 2, 513, '2025-01-15', 20.00, 0, 0),
(706, 1, 514, '2024-10-10', 15.00, 3, 4),
(706, 2, 515, '2024-10-10', 15.00, 3, 5),
(707, 1, 516, '2025-01-20', 350.00, 1, 10),
(707, 2, 517, '2025-01-21', 350.00, 1, 11),
(707, 3, 518, '2025-01-22', 350.00, 2, 5),
(708, 1, 519, '2025-03-01', 250.00, 1, 1),
(708, 2, 520, '2025-03-05', 250.00, 1, 2),
(709, 1, 521, '2025-08-01', 45.00, 10, 8),
(709, 2, 522, '2025-08-02', 45.00, 10, 9),
(709, 3, 523, '2025-08-03', 45.00, 11, 5),
(710, 1, 524, '2025-04-01', 10.00, 0, 0),
(710, 2, 525, '2025-04-05', 10.00, 0, 0),
(701, 4, NULL, NULL, NULL, 12, 2),
(701, 5, NULL, NULL, NULL, 12, 3),
(703, 4, NULL, NULL, NULL, 0, 0),
(704, 4, NULL, NULL, NULL, 5, 4),
(705, 4, NULL, NULL, NULL, 0, 0),
(706, 4, NULL, NULL, NULL, 3, 6),
(707, 5, NULL, NULL, NULL, 2, 6),
(708, 4, NULL, NULL, NULL, 1, 3),
(709, 5, NULL, NULL, NULL, 11, 6),
(710, 4, NULL, NULL, NULL, 0, 0);

-- Event_Vendor
INSERT INTO Event_Vendor (EventID, VendorID, Contract_amount, Service_date, Payment_status) VALUES
(701, 101, 5000.00, '2024-11-15', 'completed'),
(701, 107, 800.00, '2024-11-14', 'completed'),
(702, 102, 12000.00, '2025-06-20', 'pending'),
(702, 105, 1500.00, '2025-06-20', 'partially_paid'),
(703, 108, 400.00, '2024-12-05', 'completed'),
(704, 103, 2500.00, '2025-01-10', 'completed'),
(705, 104, 3000.00, '2025-03-01', 'pending'),
(707, 101, 1500.00, '2025-02-14', 'completed'),
(708, 109, 4000.00, '2025-04-20', 'pending'),
(709, 102, 3500.00, '2025-10-30', 'pending');

-- Schedule_Performer
INSERT INTO Schedule_Performer (EventID, ScheduleID, PerformerID) VALUES
(701, 1001, 602),
(701, 1004, 615),
(702, 1005, 608),
(702, 1006, 612),
(704, 1008, 609),
(706, 1013, 603),
(707, 1014, 607),
(707, 1015, 607),
(708, 1017, 606),
(710, 1020, 613);

-- Event_Supplier_equipment
INSERT INTO Event_Supplier_equipment (EventID, SupplierID, EquipmentID, Price) VALUES
(702, 207, 1, 10),
(702, 201, 1, 10),
(702, 202, 1, 10),
(705, 209, 1, 10),
(709, 203, 1, 10),
(709, 210, 1, 10),
(704, 210, 1, 10),
(707, 210, 1, 10),
(706, 207, 1, 10);

-- Attendee_Preference
INSERT INTO Attendee_Preference (AttendeeID, PreferenceID) VALUES
(501, 801),
(502, 803),
(503, 802),
(504, 801),
(505, 805),
(506, 807),
(507, 803),
(508, 806),
(509, 801),
(510, 804),
(511, 805),
(512, 802),
(513, 807),
(514, 801),
(515, 803),
(516, 806),
(517, 807),
(518, 804),
(519, 802),
(520, 805),
(521, 801),
(522, 803),
(523, 807),
(524, 808),
(525, 802);