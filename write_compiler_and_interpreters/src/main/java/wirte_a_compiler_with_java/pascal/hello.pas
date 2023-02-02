PROGRAM hello (output);

{Write &apos;Hello, world.&apos; ten times.}

VAR
    i : integer;

BEGIN {hello}
    FOR i := 1 TO 10 DO BEGIN
        writeln(&apos;Hello, world.&apos;);
    END;
END {hello}.