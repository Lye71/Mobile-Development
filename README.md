substring(): I used idInput.substring(idInput.length() - 2) to extract specifically the last two characters of the Student ID string.
equals(): Since Strings in Java are objects, I used .equals() instead of == to ensure I am comparing the literal character content of the password, which is null-safe and type-safe.
