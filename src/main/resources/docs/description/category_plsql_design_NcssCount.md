Since: PMD 7.19.0

This rule uses the NCSS (Non-Commenting Source Statements) metric to determine the number of lines
            of code in a plsql type or method. NCSS ignores comments, blank lines, and only counts actual
            statements. For more details on the calculation, see the documentation
            {% jdoc plsql::lang.plsql.metrics.PlsqlMetrics#NCSS %}.

Example(s):
```
DECLARE                                     -- total Ncss: 14
    PROCEDURE bigMethod IS                  -- +1
        x NUMBER;                           -- +1
        y NUMBER := 2;                      -- +1
        a BOOLEAN := FALSE;                 -- +1
        b BOOLEAN := TRUE;                  -- +1
    BEGIN
        IF (a OR b) THEN                    -- +1
            LOOP                            -- +1
                x := x + 2;                 -- +1
                EXIT WHEN x >= 12;          -- +1
            END LOOP;

            DBMS_OUTPUT.PUT_LINE('done');   -- +1
        ELSE                                -- +1
            DBMS_OUTPUT.PUT_LINE('false');  -- +1
        END IF;
    EXCEPTION
        WHEN PROGRAM_ERROR THEN DBMS_OUTPUT.PUT_LINE('Error Occurred'); -- +1
    END bigMethod;
BEGIN
    bigMethod();                            -- +1
END;
```
