CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount   IN NUMBER,
    p_interest_rate IN NUMBER,
    p_duration_yrs  IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_num_payments NUMBER;
    v_installment  NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / 1200;
    v_num_payments := p_duration_yrs * 12;
    
    IF v_monthly_rate = 0 THEN
        v_installment := p_loan_amount / v_num_payments;
    ELSE
        v_installment := p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments)) / 
                         (POWER(1 + v_monthly_rate, v_num_payments) - 1);
    END IF;
    
    RETURN ROUND(v_installment, 2);
END;
/
