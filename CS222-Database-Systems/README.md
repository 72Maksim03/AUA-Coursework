# Event Management System

A relational event management system built with PostgreSQL and Java Spring Boot. The system manages events, venues, tickets, attendees, performers, vendors, suppliers, and equipment, while providing REST API endpoints for common operations and analytical queries.

The project was developed as coursework for a Database Systems course at the American University of Armenia.

## Overview

The system is designed around an event-management database and provides functionality for:

- Event creation and management
- Ticket generation and booking
- Attendee preferences
- Venue and equipment management
- Performer scheduling
- Vendor and supplier management
- Revenue and profit analysis
- Event and resource utilization reports

The PostgreSQL database contains the core relational model and database-side business logic, while a Java Spring Boot application exposes functionality through REST API endpoints.

## Architecture

```
                    ┌─────────────────────┐
                    │     REST Client     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    │                     │
                    │  Controllers        │
                    │  Services           │
                    │  JDBC / SQL         │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    │                     │
                    │  Relational Schema  │
                    │  SQL Queries        │
                    │  PL/pgSQL Functions │
                    └─────────────────────┘
```

## Main Entities

The database models several parts of an event-management system:

- Events
- Tickets
- Attendees
- Attendee Preferences
- Venues
- Venue Equipment
- Performers
- Schedules
- Vendors
- Suppliers
- Supplier Equipment
- Organizers
- Event Vendors
- Event Supplier Equipment

These entities are connected through relational relationships to represent event scheduling, ticketing, resource management, and financial operations.

## Database Functionality

### CRUD Operations

The database supports CRUD operations for major entities, including:

- Events
- Attendees
- Venues
- Performers
- Vendors
- Suppliers
- Equipment

### Analytical Queries

The project includes SQL queries for generating management reports and statistics.

Examples include:

#### Ticket Sales

Determine ticket sales and revenue for each event:

```sql
SELECT Event.EventID,
       Event.Event_name,
       COUNT(Ticket.TicketID) AS Tickets_Sold,
       SUM(Ticket.price) AS Revenue
FROM Event
JOIN Ticket ON Event.EventID = Ticket.EventID
WHERE Ticket.attendeeID IS NOT NULL
GROUP BY Event.EventID, Event.Event_name
ORDER BY Revenue DESC;
```

#### Venue Utilization

Determine how frequently each venue was used during a specified period.

#### Supplier Utilization

Calculate how many times each supplier provided equipment for events.

#### Performer Statistics

Calculate:

- Number of events attended
- Average number of activities per event
- Average performance duration

#### Attendee Activity

Identify the ten most active attendees within a specified period.

#### Event Report

Generate a report containing:

- Event name
- Event type
- Event status
- Venue
- Organizer
- Number of performers
- Number of attendees

### PostgreSQL Functions

The project also uses PL/pgSQL functions for database-side business logic.

| Function | Description |
|---|---|
| `get_upcoming_events` | Returns upcoming events at a specified venue after a given date. |
| `auto_assign` | Automatically assigns the first available seat to an attendee. |
| `generateSeats` | Generates available ticket/seat records for an event based on the number of rows and seats per row. |
| `profitFromEvent` | Calculates event profit based on ticket revenue, vendor contracts, and supplier equipment costs. |

```
Profit = Ticket Revenue - Vendor Costs - Supplier Costs
```

These functions demonstrate the use of PostgreSQL procedural logic in addition to standard SQL queries.

## REST API

The Spring Boot backend exposes functionality through REST endpoints.

### Event

```
GET  /event/type/{type}
GET  /event/date
GET  /event/searchByName/{name}
GET  /event/eventRevenueByOrganizer/{organizerId}
GET  /event/ticketSales
GET  /event/totalRevenue
GET  /event/report
GET  /event/profit/{eventId}
GET  /event/upcoming/{venueId}
```

### Tickets

```
POST /ticket/book
POST /ticket/cancel
POST /ticket/autoAssign
POST /ticket/generate
```

### Attendees

```
POST /attendeePreference/addPreference
POST /attendeePreference/removePreference
GET  /attendee/actives
```

### Vendors and Suppliers

```
GET  /vendor/serviceType/{type}
GET  /vendor/info
GET  /vendor/upcomingEvents
GET  /supplier/productType/{productType}
GET  /supplierEquipment/availableEquipments/{supplierId}
GET  /supplierEquipment/availableEquipments
GET  /supplier/providedTimes
```

### Venues

```
GET  /venueEquipment/{venueId}/equipments
GET  /venueEquipment/allEquipment
GET  /venue/type/{venueType}
GET  /venue/date
GET  /venue/dateWithStatus
GET  /venue/usedTimes
```

### Performers

```
GET  /performer/performance
```

## Technology Stack

**Database**
- PostgreSQL
- SQL
- PL/pgSQL

**Backend**
- Java
- Spring Boot
- Spring JDBC / JdbcTemplate

**API**
- REST
- HTTP

## Example Use Cases

The system supports scenarios such as:

- Creating an event and assigning it to a venue.
- Generating seats for the event.
- Booking tickets for attendees.
- Automatically assigning available seats.
- Scheduling performers.
- Assigning vendors and suppliers.
- Checking venue availability.
- Monitoring ticket sales and revenue.
- Calculating event profitability.
- Generating event-management reports.

## Academic Context

Developed as coursework for a Database Systems course at the American University of Armenia.

## Author

**Maksim Petrosyan**
GitHub: [https://github.com/72Maksim03](https://github.com/72Maksim03)
