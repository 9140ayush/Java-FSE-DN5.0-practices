DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, DOB
        FROM Customers;
    v_age NUMBER;
BEGIN
    FOR r_cust IN c_customers LOOP
        IF r_cust.DOB IS NOT NULL THEN
            v_age := MONTHS_BETWEEN(SYSDATE, r_cust.DOB) / 12;
            IF v_age > 60 THEN
                UPDATE Loans
                SET InterestRate = InterestRate - 1
                WHERE CustomerID = r_cust.CustomerID;
            END IF;
        END IF;
    END LOOP;
    COMMIT;
END;
/
