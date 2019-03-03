--------------------------------------------------------
--  Ref Constraints for Table PASSENGERS
--------------------------------------------------------

  ALTER TABLE "PASSENGERS" ADD FOREIGN KEY ("USERNAME")
	  REFERENCES "USERS" ("USERNAME") ENABLE;
