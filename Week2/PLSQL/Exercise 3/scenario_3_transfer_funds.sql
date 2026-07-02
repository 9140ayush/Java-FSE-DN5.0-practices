CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_acc IN NUMBER,
    p_dest_acc   IN NUMBER,
    p_amount     IN NUMBER
) IS
    v_source_bal NUMBER;
BEGIN
    SELECT Balance INTO v_source_bal FROM Accounts WHERE AccountID = p_source_acc;

    IF v_source_bal < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account ' || p_source_acc);
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_source_acc;

    UPDATE Accounts
    SET Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_dest_acc;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from Account ' || p_source_acc || ' to ' || p_dest_acc);
END;
/
