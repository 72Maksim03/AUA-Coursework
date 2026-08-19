package com.example.event_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFunctionInitializer implements ApplicationRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args){
        createGetUpcomingEvents();
        createAutoAssign();
        createGenerateSeats();
        createProfitFromEvent();
    }

    private void createGetUpcomingEvents(){
        String sql = """
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
                """;
        jdbcTemplate.execute(sql);
    }

    private void createAutoAssign(){
        String sql= """
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
                """;
        jdbcTemplate.execute(sql);
    }

    private void createGenerateSeats(){
        String sql = """
                CREATE OR REPLACE FUNCTION generateSeats(event_id INT, rowNumber INT, seatPerRow INT)
                RETURNS VOID AS $$
                DECLARE
                	rw INT;
                	st INT;
                	tid INT;
                BEGIN
                    SELECT COALESCE(MAX(ticketID), 0) INTO tid from Ticket WHERE eventId=event_id;
                	FOR rw IN 1..rowNumber LOOP
                		FOR st IN 1..seatPerRow LOOP
                		    tid=tid+1;
                			INSERT INTO Ticket (eventID, ticketId, attendeeID, purchase_date, price, row_number, seat_number) VALUES (event_id, tid, null, null, null, rw, st);
                		END LOOP;
                	END LOOP;
                END;
                $$ LANGUAGE plpgsql;
                """;
        jdbcTemplate.execute(sql);
    }

    private void createProfitFromEvent(){
        String sql = """
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
                """;
        jdbcTemplate.execute(sql);
    }
}
