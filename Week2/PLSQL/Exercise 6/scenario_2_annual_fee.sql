DECLARE
    v_fee CONSTANT NUMBER := 50;
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts;
BEGIN
    FOR r_acc IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee,
            LastModified = SYSDATE
        WHERE AccountID = r_acc.AccountID;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual maintenance fee applied to all accounts.');
END;
/
