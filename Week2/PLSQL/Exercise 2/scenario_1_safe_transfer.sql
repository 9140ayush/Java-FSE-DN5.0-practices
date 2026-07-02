CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_source_acc IN NUMBER,
    p_dest_acc   IN NUMBER,
    p_amount     IN NUMBER
) IS
    v_source_bal NUMBER;
    v_dest_bal   NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO v_source_bal FROM Accounts WHERE AccountID = p_source_acc;
    SELECT Balance INTO v_dest_bal FROM Accounts WHERE AccountID = p_dest_acc;

    IF v_source_bal < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_source_acc;

    UPDATE Accounts
    SET Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_dest_acc;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from Account ' || p_source_acc || ' to ' || p_dest_acc);
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in Account ' || p_source_acc);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer failed. ' || SQLERRM);
END;
/
