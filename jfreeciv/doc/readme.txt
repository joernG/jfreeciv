use C2J to convert a c/c++ project into java project
use this command to get a sorted list
	wc -l * | sort

add following lines to java file	
import static port.util.*;
import static server.Erfc_status.*;

create class common.unit.Unit
replace
struct\s+([a-zA-Z\_0-9]+)\s+unit *, $1 
->, .
\#define\s+([a-zA-Z0-9\_]+)\s+([0-9a-zA-Z\_\.]+), public static final int $1 = $2;

\s*\(\s*([0-9a-zA-Z\_\.]+)\s*\)
how to deal with prefix match like function 