--------------------------------------------------------
--  Ref Constraints for Table CANCELED_TICKETS
--------------------------------------------------------

  ALTER TABLE "CANCELED_TICKETS" ADD FOREIGN KEY ("PASS_ID")
	  REFERENCES "PASSENGERS" ("PASS_ID") ENABLE;
  ALTER TABLE "CANCELED_TICKETS" ADD FOREIGN KEY ("FLIGHT_CODE")
	  REFERENCES "FLIGHTS" ("FLIGHT_CODE") ENABLE;
