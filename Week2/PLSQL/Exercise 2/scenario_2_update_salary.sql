CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id     IN NUMBER,
    p_percentage IN NUMBER
) IS
    v_salary NUMBER;
BEGIN
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_emp_id;

    UPDATE Employees
    SET Salary = Salary * (1 + p_percentage / 100)
    WHERE EmployeeID = p_emp_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Updated salary for Employee ' || p_emp_id);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_emp_id || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
