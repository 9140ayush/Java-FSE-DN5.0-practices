DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount, InterestRate
        FROM Loans;
BEGIN
    FOR r_loan IN UpdateLoanInterestRates LOOP
        IF r_loan.LoanAmount > 5000 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 0.5
            WHERE LoanID = r_loan.LoanID;
        ELSE
            UPDATE Loans
            SET InterestRate = InterestRate - 0.25
            WHERE LoanID = r_loan.LoanID;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated based on policy.');
END;
/
