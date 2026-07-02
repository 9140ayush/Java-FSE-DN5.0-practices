DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType, a.AccountID
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY c.CustomerID, t.TransactionDate;
BEGIN
    FOR r_stmt IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Customer: ' || r_stmt.Name || ' (ID: ' || r_stmt.CustomerID || ') | ' ||
                             'Account ID: ' || r_stmt.AccountID || ' | ' ||
                             'Txn ID: ' || r_stmt.TransactionID || ' | ' ||
                             'Date: ' || TO_CHAR(r_stmt.TransactionDate, 'YYYY-MM-DD') || ' | ' ||
                             'Type: ' || r_stmt.TransactionType || ' | ' ||
                             'Amount: ' || r_stmt.Amount);
    END LOOP;
END;
/
