

CREATE OR REPLACE PROCEDURE fetch_insurance_data(
    startLimit IN NUMBER,
    endLimit IN NUMBER,
    customerId in number,
    insuranceData OUT SYS_REFCURSOR
) AS
    v_exists NUMBER;
BEGIN
    -- Check if any data exists in the range
    SELECT COUNT(*) INTO v_exists FROM mybank_app_insuranceavailed
    WHERE INSURANCE_COVERAGE BETWEEN startLimit AND endLimit;

    IF v_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No data for this range');
    ELSE
        OPEN insuranceData FOR
            SELECT INSURANCE_AVAIL_ID,CUSTOMER_ID, INSURANCE_ID, INSURANCE_PREMIUM,
                   INSURANCE_TYPE, INSURANCE_NAME, INSURANCE_KEY_BENEFITS, INSURANCE_LIFETIME
            FROM mybank_app_insuranceavailed
            WHERE INSURANCE_COVERAGE BETWEEN startLimit AND endLimit and CUSTOMER_ID= customerId;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
        RAISE;
END;
/


VARIABLE insuranceData REFCURSOR
EXECUTE fetch_insurance_data(30000, 35000,140 ,:insuranceData);
print insurancedata

commit;



