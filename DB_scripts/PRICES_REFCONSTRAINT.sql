--------------------------------------------------------
--  Ref Constraints for Table PRICES
--------------------------------------------------------

  ALTER TABLE "PRICES" ADD FOREIGN KEY ("FLIGHT_CODE")
	  REFERENCES "FLIGHTS" ("FLIGHT_CODE") ENABLE;
