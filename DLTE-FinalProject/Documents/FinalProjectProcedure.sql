--CREATE OR REPLACE PROCEDURE read_insurance_by_amount (
--    startLimit       IN NUMBER,
--    endLimit         IN NUMBER,
--     avail_id OUT NUMBER,
--    insure_id        OUT NUMBER,
--    customers_id         OUT NUMBER,
--    ins_premium   OUT NUMBER,
--    ins_type      OUT VARCHAR2,
--    ins_name      OUT VARCHAR2,
--    ins_benefits   OUT VARCHAR2,
--    ins_lifetime       OUT NUMBER
--   
--) AS
--BEGIN
--    SELECT
--        INSURANCE_AVAIL_ID,
--        INSURANCE_ID,
--        CUSTOMER_ID,
--        INSURANCE_PREMIUM,
--        INSURANCE_TYPE,
--        INSURANCE_NAME,
--        INSURANCE_KEY_BENEFITS,
--        INSURANCE_LIFETIME
--    INTO
--        avail_id,
--        insure_id,
--        customers_id,
--        ins_premium,
--        ins_type,
--        ins_name,
--        ins_benefits,
--        ins_lifetime
--    FROM
--        mybank_app_insuranceavailed
--    WHERE
--       INSURANCE_COVERAGE
--    BETWEEN
--    startLimit and endLimit;
-- 
--EXCEPTION
--    WHEN NO_DATA_FOUND THEN
--     RAISE_APPLICATION_ERROR(-20002, 'Employee Not Found');
--     WHEN TOO_MANY_ROWS THEN
--        RAISE_APPLICATION_ERROR(-20003, 'Multiple insurance records found for the given range.');
--WHEN OTHERS THEN
--RAISE_APPLICATION_ERROR(-20002, 'Custom Exception');
--END;
--/
-- 
-- 
--VARIABLE avail_id Number
--VARIABLE insure_id Number
--VARIABLE customers_id Number
--VARIABLE ins_premium Number
--VARIABLE ins_type VARCHAR2(255)
--VARIABLE ins_name VARCHAR2(255)
--VARIABLE ins_benefits VARCHAR2(255)
--VARIABLE ins_lifetime Number
--
-- 
--EXECUTE read_insurance_by_amount(30000,50000, :avail_id, :insure_id, :customers_id, :ins_premium, :ins_type, :ins_name, :ins_benefits, :ins_lifetime);
-- 
--PRINT ins_name;

CREATE OR REPLACE PROCEDURE read_insurance_by_amount (
    startLimit IN NUMBER,
    endLimit IN NUMBER
) AS
    -- Declare a cursor to fetch multiple records
    CURSOR insurance_cursor IS
        SELECT INSURANCE_AVAIL_ID, INSURANCE_ID, CUSTOMER_ID, INSURANCE_PREMIUM,
               INSURANCE_TYPE, INSURANCE_NAME, INSURANCE_KEY_BENEFITS, INSURANCE_LIFETIME
        FROM mybank_app_insuranceavailed
        WHERE INSURANCE_COVERAGE BETWEEN startLimit AND endLimit;

    -- Record variable to hold cursor data
    insurance_record insurance_cursor%ROWTYPE;
BEGIN
    -- Open the cursor
    OPEN insurance_cursor;

    -- Fetch each row and process it
    LOOP
        FETCH insurance_cursor INTO insurance_record;
        EXIT WHEN insurance_cursor%NOTFOUND;

    

    -- Close the cursor
    CLOSE insurance_cursor;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle unexpected errors
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
        -- Ensure the cursor is closed if an error occurs
        IF insurance_cursor%ISOPEN THEN
            CLOSE insurance_cursor;
        END IF;
        -- Optionally re-raise the error to propagate it
        RAISE;
END;
/

SET SERVEROUTPUT ON;
EXECUTE read_insurance_by_amount(30000,50000)































CREATE OR REPLACE TYPE insurance_record AS OBJECT (
    insuranceAvailId NUMBER,
    insuranceId      NUMBER,
    customerId       NUMBER,
    premium          NUMBER,
    type             VARCHAR2(100),
    name             VARCHAR2(100),
    benefits         VARCHAR2(400),
    lifetime         NUMBER
);
/
CREATE OR REPLACE TYPE insurance_tbl AS TABLE OF insurance_record;
/



CREATE OR REPLACE PROCEDURE fetch_insurance_data(
    startLimit IN NUMBER,
    endLimit IN NUMBER,
    insuranceData OUT insurance_tbl
) AS
BEGIN
    -- Allocate the collection
    insuranceData := insurance_tbl();

    -- Fetch data into the collection
    FOR r IN (
        SELECT INSURANCE_AVAIL_ID, INSURANCE_ID, CUSTOMER_ID, INSURANCE_PREMIUM,
               INSURANCE_TYPE, INSURANCE_NAME, INSURANCE_KEY_BENEFITS, INSURANCE_LIFETIME
        FROM mybank_app_insuranceavailed
        WHERE INSURANCE_COVERAGE BETWEEN startLimit AND endLimit
    ) LOOP
        insuranceData.EXTEND;
        insuranceData(insuranceData.LAST) := insurance_record(
            r.INSURANCE_AVAIL_ID, r.INSURANCE_ID, r.CUSTOMER_ID, r.INSURANCE_PREMIUM,
            r.INSURANCE_TYPE, r.INSURANCE_NAME, r.INSURANCE_KEY_BENEFITS, r.INSURANCE_LIFETIME
        );
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;
/


SET SERVEROUTPUT ON;
DECLARE
    v_insurance_data insurance_tbl;
BEGIN
    -- Fetch data within the coverage limits
    fetch_insurance_data(10000, 50000, v_insurance_data);
    
    -- Iterate through the collection and print each attribute of each record
    FOR i IN 1 .. v_insurance_data.COUNT LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Record ' || i || ': ' ||
            'Avail ID: ' || v_insurance_data(i).insuranceAvailId || ', ' ||
            'Insurance ID: ' || v_insurance_data(i).insuranceId || ', ' ||
            'Customer ID: ' || v_insurance_data(i).customerId || ', ' ||
            'Premium: ' || v_insurance_data(i).premium || ', ' ||
            'Type: ' || v_insurance_data(i).type || ', ' ||
            'Name: ' || v_insurance_data(i).name || ', ' ||
            'Benefits: ' || v_insurance_data(i).benefits || ', ' ||
            'Lifetime: ' || v_insurance_data(i).lifetime
        );
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


