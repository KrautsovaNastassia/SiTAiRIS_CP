--------------------------------------------------------
--  Ref Constraints for Table TICKETS
--------------------------------------------------------

  ALTER TABLE "TICKETS" ADD FOREIGN KEY ("PASS_ID")
	  REFERENCES "PASSENGERS" ("PASS_ID") ENABLE;
  ALTER TABLE "TICKETS" ADD FOREIGN KEY ("FLIGHT_CODE")
	  REFERENCES "FLIGHTS" ("FLIGHT_CODE") ENABLE;
  ALTER TABLE "TICKETS" ADD FOREIGN KEY ("FLIGHT_CODE", "TRAVEL_CLASS")
	  REFERENCES "PRICES" ("FLIGHT_CODE", "TRAVEL_CLASS") ENABLE;
