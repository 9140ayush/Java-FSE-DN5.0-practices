CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogDate)
    VALUES (:new.TransactionID, :new.AccountID, :new.TransactionDate, :new.Amount, :new.TransactionType, SYSDATE);
END;
/
