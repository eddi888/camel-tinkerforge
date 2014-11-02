{
	"category" : "Bricklet",
	"description" : "Device for controlling up to 4 general purpose input/output pins",
	"author" : "Olaf L\u00fcke <olaf@tinkerforge.com>",
	"packets" : [
			{
				"elements" : [ [ "value_mask", "uint8", 1, "in" ] ],
				"name" : [ "SetValue", "set_value" ],
				"doc" : [
						"bf",
						{
							"de" : "\nSetzt den Ausgangszustand (logisch 1 oder logisch 0) mittels einer Bitmaske\n(4Bit). Eine 1 in der Bitmaske bedeutet logisch 1 und eine 0 in der Bitmaske\nbedeutet logisch 0.\n\nBeispiel: Der Wert 3 bzw. 0b0011 setzt die Pins 0-1 auf logisch 1 und die\nPins 2-3 auf logisch 0.\n\n.. note::\n Diese Funktion bewirkt keine \u00c4nderung an Pins die als Eingang konfiguriert sind.\n Pull-Up Widerst\u00e4nde k\u00f6nnen mit :func:`SetConfiguration` zugeschaltet werden.\n",
							"en" : "\nSets the output value (high or low) with a bitmask (4bit). A 1 in the bitmask\nmeans high and a 0 in the bitmask means low.\n\nFor example: The value 3 or 0b0011 will turn the pins 0-1 high and the\npins 2-3 low.\n\n.. note::\n This function does nothing for pins that are configured as input.\n Pull-up resistors can be switched on with :func:`SetConfiguration`.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 1,
				"type" : "function"
			},
			{
				"elements" : [ [ "value_mask", "uint8", 1, "out" ] ],
				"name" : [ "GetValue", "get_value" ],
				"doc" : [
						"bf",
						{
							"de" : "\nGibt eine Bitmaske der aktuell gemessenen Zust\u00e4nde zur\u00fcck.\nDiese Funktion gibt die Zust\u00e4nde aller Pins zur\u00fcck, unabh\u00e4ngig ob diese als\nEin- oder Ausgang konfiguriert sind.\n",
							"en" : "\nReturns a bitmask of the values that are currently measured.\nThis function works if the pin is configured to input\nas well as if it is configured to output.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 2,
				"type" : "function"
			},
			{
				"elements" : [
						[ "selection_mask", "uint8", 1, "in" ],
						[
								"direction",
								"char",
								1,
								"in",
								[
										"Direction",
										"direction",
										[ [ "In", "in", "i" ],
												[ "Out", "out", "o" ] ] ] ],
						[ "value", "bool", 1, "in" ] ],
				"name" : [ "SetConfiguration", "set_configuration" ],
				"doc" : [
						"bf",
						{
							"de" : "\nKonfiguriert den Zustand und die Richtung eines angegebenen Pins. M\u00f6gliche\nRichtungen sind 'i' und 'o' f\u00fcr Ein- und Ausgang.\n\nWenn die Richtung als Ausgang konfiguriert ist, ist der Zustand entweder\nlogisch 1 oder logisch 0 (gesetzt als *true* oder *false*).\n\nWenn die Richtung als Eingang konfiguriert ist, ist der Zustand entweder\nPull-Up oder Standard (gesetzt als *true* oder *false*).\n\nBeispiele:\n\n* (15, 'i', true) bzw. (0b1111, 'i', true) setzt alle Pins als Eingang mit Pull-Up.\n* (8, 'i', false) bzw. (0b1000, 'i', true) setzt Pin 3 als Standard Eingang (potentialfrei wenn nicht verbunden).\n* (3, 'o', false) bzw. (0b0011, 'i', true) setzt die Pins 0 und 1 als Ausgang im Zustand logisch 0.\n* (4, 'o', true) bzw. (0b0100, 'i', true) setzt Pin 2 als Ausgang im Zustand logisch 1.\n\nDie Standardkonfiguration ist Eingang mit Pull-Up.\n",
							"en" : "\nConfigures the value and direction of the specified pins. Possible directions\nare 'i' and 'o' for input and output.\n\nIf the direction is configured as output, the value is either high or low\n(set as *true* or *false*).\n\nIf the direction is configured as input, the value is either pull-up or\ndefault (set as *true* or *false*).\n\nFor example:\n\n* (15, 'i', true) or (0b1111, 'i', true) will set all pins of as input pull-up.\n* (8, 'i', false) or (0b1000, 'i', false) will set pin 3 of as input default (floating if nothing is connected).\n* (3, 'o', false) or (0b0011, 'o', false) will set pins 0 and 1 as output low.\n* (4, 'o', true) or (0b0100, 'o', true) will set pin 2 of as output high.\n\nThe default configuration is input with pull-up.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 3,
				"type" : "function"
			},
			{
				"elements" : [ [ "direction_mask", "uint8", 1, "out" ],
						[ "value_mask", "uint8", 1, "out" ] ],
				"name" : [ "GetConfiguration", "get_configuration" ],
				"doc" : [
						"bf",
						{
							"de" : "\nGibt eine Bitmaske f\u00fcr die Richtung und eine Bitmaske f\u00fcr den Zustand der Pins\nzur\u00fcck. Eine 1 in der Bitmaske f\u00fcr die Richtung bedeutet Eingang und eine 0\nin der Bitmaske bedeutet Ausgang.\n\nBeispiel: Ein R\u00fcckgabewert von (3, 5) bzw. (0b0011, 0b0101) f\u00fcr Richtung und\nZustand bedeutet:\n\n* Pin 0 ist als Eingang mit Pull-Up konfiguriert,\n* Pin 1 ist als Standard Eingang konfiguriert,\n* Pin 2 ist als Ausgang im Zustand logisch 1 konfiguriert und\n* Pin 3 ist als Ausgang im Zustand logisch 0 konfiguriert.\n",
							"en" : "\nReturns a value bitmask and a direction bitmask. A 1 in the direction bitmask\nmeans input and a 0 in the bitmask means output.\n\nFor example: A return value of (3, 5) or (0b0011, 0b0101) for direction and\nvalue means that:\n\n* pin 0 is configured as input pull-up,\n* pin 1 is configured as input default,\n* pin 2 is configured as output high and\n* pin 3 is are configured as output low.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 4,
				"type" : "function"
			},
			{
				"elements" : [ [ "debounce", "uint32", 1, "in" ] ],
				"name" : [ "SetDebouncePeriod", "set_debounce_period" ],
				"doc" : [
						"ccf",
						{
							"de" : "\nSetzt die Entprellperiode der :func:`Interrupt` Callback in ms.\n\nBeispiel: Wenn dieser Wert auf 100 gesetzt wird, erh\u00e4lt man den Interrupt\nmaximal alle 100ms. Dies ist notwendig falls etwas prellendes an\ndas IO-4 Bricklet angeschlossen ist, wie z.B. eine Taste.\n\nDer Standardwert ist 100.\n",
							"en" : "\nSets the debounce period of the :func:`Interrupt` callback in ms.\n\nFor example: If you set this value to 100, you will get the interrupt\nmaximal every 100ms. This is necessary if something that bounces is\nconnected to the IO-4 Bricklet, such as a button.\n\nThe default value is 100.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 5,
				"type" : "function"
			},
			{
				"elements" : [ [ "debounce", "uint32", 1, "out" ] ],
				"name" : [ "GetDebouncePeriod", "get_debounce_period" ],
				"doc" : [
						"ccf",
						{
							"de" : "\nGibt die Entprellperiode zur\u00fcck, wie von :func:`SetDebouncePeriod`\ngesetzt.\n",
							"en" : "\nReturns the debounce period as set by :func:`SetDebouncePeriod`.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 6,
				"type" : "function"
			},
			{
				"elements" : [ [ "interrupt_mask", "uint8", 1, "in" ] ],
				"name" : [ "SetInterrupt", "set_interrupt" ],
				"doc" : [
						"ccf",
						{
							"de" : "\nSetzt durch eine Bitmaske die Pins f\u00fcr welche der Interrupt aktiv ist.\nInterrupts werden ausgel\u00f6st bei \u00c4nderung des Spannungspegels eines Pins,\nz.B. ein Wechsel von logisch 1 zu logisch 0 und logisch 0 zu logisch 1.\n\nBeispiel: Eine Interrupt Bitmaske von 10 bzw. 0b1010 aktiviert den Interrupt f\u00fcr\ndie Pins 1 und 3.\n\nDer Interrupt wird mit der Callback :func:`Interrupt` zugestellt.\n",
							"en" : "\nSets the pins on which an interrupt is activated with a bitmask.\nInterrupts are triggered on changes of the voltage level of the pin,\ni.e. changes from high to low and low to high.\n\nFor example: An interrupt bitmask of 10 or 0b1010 will enable the interrupt for\npins 1 and 3.\n\nThe interrupt is delivered with the callback :func:`Interrupt`.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 7,
				"type" : "function"
			},
			{
				"elements" : [ [ "interrupt_mask", "uint8", 1, "out" ] ],
				"name" : [ "GetInterrupt", "get_interrupt" ],
				"doc" : [
						"ccf",
						{
							"de" : "\nGibt die Interrupt Bitmaske zur\u00fcck, wie von :func:`SetInterrupt` gesetzt.\n",
							"en" : "\nReturns the interrupt bitmask as set by :func:`SetInterrupt`.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 8,
				"type" : "function"
			},
			{
				"elements" : [ 
				               [ "interrupt_mask", "uint8", 1, "out" ],
				               [ "value_mask", "uint8", 1, "out" ] 
				              ],
				"name" : [ "Interrupt", "interrupt" ],
				"doc" : [
						"c",
						{
							"de" : "\nDieser Callback wird ausgel\u00f6st sobald eine \u00c4nderung des Spannungspegels\ndetektiert wird, an Pins f\u00fcr welche der Interrupt mit :func:`SetInterrupt`\naktiviert wurde.\n\nDie R\u00fcckgabewerte sind eine Bitmaske der aufgetretenen Interrupts und der\naktuellen Zust\u00e4nde.\n\nBeispiele:\n\n* (1, 1) bzw. (0b0001, 0b0001) bedeutet, dass ein Interrupt am Pin 0 aufgetreten\n  ist und aktuell Pin 0 logisch 1 ist und die Pins 1-3 logisch 0 sind.\n* (9, 14) bzw. (0b1001, 0b1110) bedeutet, dass Interrupts an den Pins 0 und 3\n  aufgetreten sind und aktuell Pin 0 logisch 0 ist und die Pins 1-3 logisch 1 sind.\n",
							"en" : "\nThis callback is triggered whenever a change of the voltage level is detected\non pins where the interrupt was activated with :func:`SetInterrupt`.\n\nThe values are a bitmask that specifies which interrupts occurred\nand the current value bitmask.\n\nFor example:\n\n* (1, 1) or (0b0001, 0b0001) means that an interrupt on pin 0 occurred and\n  currently pin 0 is high and pins 1-3 are low.\n* (9, 14) or (0b1001, 0b1110) means that interrupts on pins 0 and 3\n  occurred and currently pin 0 is low and pins 1-3 are high.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 9,
				"type" : "callback"
			},
			{
				"elements" : [ [ "selection_mask", "uint8", 1, "in" ],
						[ "value_mask", "uint8", 1, "in" ],
						[ "time", "uint32", 1, "in" ] ],
				"name" : [ "SetMonoflop", "set_monoflop" ],
				"doc" : [
						"af",
						{
							"de" : "\nKonfiguriert einen Monoflop f\u00fcr die Pins, wie mittels der 4 Bit langen Bitmaske\ndes ersten Parameters festgelegt. Die festgelegten Pins m\u00fcssen als Ausg\u00e4nge\nkonfiguriert sein. Als Eing\u00e4nge konfigurierte Pins werden ignoriert.\n\nDer zweite Parameter ist eine Bitmaske mit den gew\u00fcnschten Zustanden der\nfestgelegten Ausgangspins. Eine 1 in der Bitmaske bedeutet logisch 1 und\neine 0 in der Bitmaske bedeutet logisch 0.\n\nDer dritte Parameter stellt die Zeit (in ms) dar, welche die Pins den Zustand\nhalten sollen.\n\nWenn diese Funktion mit den Parametern (9, 1, 1500) bzw. (0b1001, 0b0001, 1500)\naufgerufen wird: Pin 0 wird auf logisch 1 und Pin 3 auf logisch 0 gesetzt.\nNach 1,5s wird Pin 0 wieder logisch 0 und Pin 3 logisch 1 gesetzt.\n\nEin Monoflop kann zur Ausfallsicherung verwendet werden. Beispiel:\nAngenommen ein RS485 Bus und ein IO-4 Bricklet ist an ein Slave Stapel verbunden.\nJetzt kann diese Funktion sek\u00fcndlich, mit einem Zeitparameter von 2 Sekunden,\naufgerufen werden. Der Pin wird die gesamte Zeit im Zustand logisch 1 sein.\nWenn jetzt die RS485 Verbindung getrennt wird, wird der Pin nach sp\u00e4testens zwei\nSekunden in den Zustand logisch 0 wechseln.\n",
							"en" : "\nConfigures a monoflop of the pins specified by the first parameter as 4 bit\nlong bitmask. The specified pins must be configured for output. Non-output\npins will be ignored.\n\nThe second parameter is a bitmask with the desired value of the specified\noutput pins. A 1 in the bitmask means high and a 0 in the bitmask means low.\n\nThe third parameter indicates the time (in ms) that the pins should hold\nthe value.\n\nIf this function is called with the parameters (9, 1, 1500) or\n(0b1001, 0b0001, 1500): Pin 0 will get high and pin 3 will get low. In 1.5s pin\n0 will get low and pin 3 will get high again.\n\nA monoflop can be used as a fail-safe mechanism. For example: Lets assume you\nhave a RS485 bus and an IO-4 Bricklet connected to one of the slave\nstacks. You can now call this function every second, with a time parameter\nof two seconds and pin 0 set to high. Pin 0 will be high all the time. If now\nthe RS485 connection is lost, then pin 0 will get low in at most two seconds.\n"
						} ],
				"since_firmware" : [ 1, 1, 1 ],
				"function_id" : 10,
				"type" : "function"
			},
			{
				"elements" : [ [ "pin", "uint8", 1, "in" ],
						[ "value", "uint8", 1, "out" ],
						[ "time", "uint32", 1, "out" ],
						[ "time_remaining", "uint32", 1, "out" ] 
				],
				"name" : [ "GetMonoflop", "get_monoflop" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt (f\u00fcr den angegebenen Pin) den aktuellen Zustand und die Zeit, wie von \n:func:`SetMonoflop` gesetzt, sowie die noch verbleibende Zeit bis zum\nZustandswechsel, zur\u00fcck.\n\nWenn der Timer aktuell nicht l\u00e4uft, ist die noch verbleibende Zeit 0.\n",
							"en" : "\nReturns (for the given pin) the current value and the time as set by\n:func:`SetMonoflop` as well as the remaining time until the value flips.\n\nIf the timer is not running currently, the remaining time will be returned\nas 0.\n"
						} ],
				"since_firmware" : [ 1, 1, 1 ],
				"function_id" : 11,
				"type" : "function"
			},
			{
				"elements" : [ [ "selection_mask", "uint8", 1, "out" ],
						[ "value_mask", "uint8", 1, "out" ] ],
				"name" : [ "MonoflopDone", "monoflop_done" ],
				"doc" : [
						"c",
						{
							"de" : "\nDieser Callback wird ausgel\u00f6st wenn ein Monoflop Timer abl\u00e4uft (0 erreicht).\n:word:`parameters` enthalten die beteiligten Pins als Bitmaske und den aktuellen\nZustand als Bitmaske (der Zustand nach dem Monoflop).\n",
							"en" : "\nThis callback is triggered whenever a monoflop timer reaches 0. The\n:word:`parameters` contain the involved pins and the current value of the pins\n(the value after the monoflop).\n"
						} ],
				"since_firmware" : [ 1, 1, 1 ],
				"function_id" : 12,
				"type" : "callback"
			},
			{
				"elements" : [ [ "selection_mask", "uint8", 1, "in" ],
						[ "value_mask", "uint8", 1, "in" ] ],
				"name" : [ "SetSelectedValues", "set_selected_values" ],
				"doc" : [
						"af",
						{
							"de" : "\nSetzt den Ausgangszustand (logisch 1 oder logisch 0) mittels einer Bitmaske,\nentsprechend der Selektionsmaske. Die Bitmaske hat eine L\u00e4nge von 4 Bit,\n*true* bedeutet logisch 1 und *false*\nlogisch 0.\n\nBeispiel: Die Parameter (9, 4) bzw (0b0110, 0b0100) setzen den Pin 1 auf\nlogisch 0 und den Pin 2 auf logisch 1. Die Pins 0 und 3 bleiben unangetastet.\n\n.. note::\n Diese Funktion bewirkt keine \u00c4nderung an Pins die als Eingang konfiguriert sind.\n Pull-Up Widerst\u00e4nde k\u00f6nnen mit :func:`SetConfiguration` zugeschaltet werden.\n",
							"en" : "\nSets the output value (high or low) with a bitmask, according to\nthe selection mask. The bitmask is 4 bit long, *true* refers to high \nand *false* refers to low.\n\nFor example: The parameters (9, 4) or (0b0110, 0b0100) will turn\npin 1 low and pin 2 high, pin 0 and 3 will remain untouched.\n\n.. note::\n This function does nothing for pins that are configured as input.\n Pull-up resistors can be switched on with :func:`SetConfiguration`.\n"
						} ],
				"since_firmware" : [ 2, 0, 0 ],
				"function_id" : 13,
				"type" : "function"
			},
			{
				"elements" : [ [ "pin", "uint8", 1, "in" ],
						[ "reset_counter", "bool", 1, "in" ],
						[ "count", "uint32", 1, "out" ] ],
				"name" : [ "GetEdgeCount", "get_edge_count" ],
				"doc" : [
						"bf",
						{
							"de" : "\nGibt den aktuellen Wert des Flankenz\u00e4hlers f\u00fcr den ausgew\u00e4hlten Pin zur\u00fcck. Die\nzu z\u00e4hlenden Flanken k\u00f6nnen mit :func:`SetEdgeCountConfig` konfiguriert werden.\n\nWenn reset counter auf *true* gesetzt wird, wird der Z\u00e4hlerstand direkt\nnach dem auslesen auf 0 zur\u00fcckgesetzt.\n",
							"en" : "\nReturns the current value of the edge counter for the selected pin. You can\nconfigure the edges that are counted with :func:`SetEdgeCountConfig`.\n\nIf you set the reset counter to *true*, the count is set back to 0\ndirectly after it is read.\n"
						} ],
				"since_firmware" : [ 2, 0, 1 ],
				"function_id" : 14,
				"type" : "function"
			},
			{
				"elements" : [
						[ "selection_mask", "uint8", 1, "in" ],
						[ "edge_type","uint8",1,"in", ["EdgeType","edge_type",[ [ "Rising", "rising", 0 ],[ "Falling", "falling", 1 ],[ "Both", "both", 2 ] ] ] ],
						[ "debounce", "uint8", 1, "in" ] ],
				"name" : [ "SetEdgeCountConfig", "set_edge_count_config" ],
				"doc" : [
						"af",
						{
							"de" : "\nKonfiguriert den Flankenz\u00e4hler f\u00fcr die ausgew\u00e4hlten Pins.\n\nDer edge type Parameter konfiguriert den zu z\u00e4hlenden Flankentyp. Es k\u00f6nnen\nsteigende, fallende oder beide Flanken gez\u00e4hlt werden f\u00fcr Pins die als Eingang\nkonfiguriert sind. M\u00f6gliche Flankentypen sind:\n\n* 0 = steigend (Standard)\n* 1 = fallend\n* 2 = beide\n\nDie Entprellzeit (debounce) wird in ms angegeben.\n\nDurch das Konfigurieren wird der Wert des Flankenz\u00e4hlers auf 0 zur\u00fcckgesetzt.\n\nFalls unklar ist was dies alles bedeutet, kann diese Funktion einfach\nignoriert werden. Die Standardwerte sind in fast allen Situationen OK.\n\nStandardwerte: 0 (edge type) und 100ms (debounce).\n",
							"en" : "\nConfigures the edge counter for the selected pins.\n\nThe edge type parameter configures if rising edges, falling edges or\nboth are counted if the pin is configured for input. Possible edge types are:\n\n* 0 = rising (default)\n* 1 = falling\n* 2 = both\n\nThe debounce time is given in ms.\n\nConfiguring an edge counter resets its value to 0.\n\nIf you don't know what any of this means, just leave it at default. The\ndefault configuration is very likely OK for you.\n\nDefault values: 0 (edge type) and 100ms (debounce time)\n"
						} ],
				"since_firmware" : [ 2, 0, 1 ],
				"function_id" : 15,
				"type" : "function"
			},
			{
				"elements" : [
						[ "pin", "uint8", 1, "in" ],
						[
								"edge_type",
								"uint8",
								1,
								"out",
								[
										"EdgeType",
										"edge_type",
										[ [ "Rising", "rising", 0 ],
												[ "Falling", "falling", 1 ],
												[ "Both", "both", 2 ] ] ] ],
						[ "debounce", "uint8", 1, "out" ] ],
				"name" : [ "GetEdgeCountConfig", "get_edge_count_config" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt den Flankentyp sowie die Entprellzeit f\u00fcr den ausgew\u00e4hlten Pin zur\u00fcck,\nwie von :func:`SetEdgeCountConfig` gesetzt.\n",
							"en" : "\nReturns the edge type and debounce time for the selected pin as set by\n:func:`SetEdgeCountConfig`.\n"
						} ],
				"since_firmware" : [ 2, 0, 1 ],
				"function_id" : 16,
				"type" : "function"
			},
			{
				"elements" : [ [ "api_version", "uint8", 3, "out" ] ],
				"name" : [ "GetAPIVersion", "get_api_version" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt die Version der API Definition (Major, Minor, Revision) zur\u00fcck, die diese\nAPI Bindings implementieren. Dies ist werder die Release-Version dieser API\nBindings noch gibt es in irgendeiner Weise Auskunft \u00fcber den oder das\nrepr\u00e4sentierte(n) Brick oder Bricklet.\n",
							"en" : "\nReturns the version of the API definition (major, minor, revision) implemented\nby this API bindings. This is neither the release version of this API bindings\nnor does it tell you anything about the represented Brick or Bricklet.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "function_id", "uint8", 1, "in" ],
						[ "response_expected", "bool", 1, "out" ] ],
				"name" : [ "GetResponseExpected", "get_response_expected" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt das Response-Expected-Flag f\u00fcr die Funktion mit der angegebenen Funktions\nIDs zur\u00fcck. Es ist *true* falls f\u00fcr die Funktion beim Aufruf eine Antwort\nerwartet wird, *false* andernfalls.\n\nF\u00fcr Getter-Funktionen ist diese Flag immer gesetzt und kann nicht entfernt\nwerden, da diese Funktionen immer eine Antwort senden. F\u00fcr\nKonfigurationsfunktionen f\u00fcr Callbacks ist es standardm\u00e4\u00dfig gesetzt, kann aber\nentfernt werden mittels :func:`SetResponseExpected`. F\u00fcr Setter-Funktionen ist\nes standardm\u00e4\u00dfig nicht gesetzt, kann aber gesetzt werden.\n\nWenn das Response-Expected-Flag f\u00fcr eine Setter-Funktion gesetzt ist, k\u00f6nnen\nTimeouts und andere Fehlerf\u00e4lle auch f\u00fcr Aufrufe dieser Setter-Funktion\ndetektiert werden. Das Ger\u00e4t sendet dann eine Antwort extra f\u00fcr diesen Zweck.\nWenn das Flag f\u00fcr eine Setter-Funktion nicht gesetzt ist, dann wird keine\nAntwort vom Ger\u00e4t gesendet und Fehler werden stillschweigend ignoriert, da sie\nnicht detektiert werden k\u00f6nnen.\n\nSiehe :func:`SetResponseExpected`\nf\u00fcr die Liste der verf\u00fcgbaren Funktions ID :word:`constants` f\u00fcr diese Funktion.\n",
							"en" : "\nReturns the response expected flag for the function specified by the function\nID parameter. It is *true* if the function is expected to send a response,\n*false* otherwise.\n\nFor getter functions this is enabled by default and cannot be disabled,\nbecause those functions will always send a response. For callback configuration\nfunctions it is enabled by default too, but can be disabled by\n:func:`SetResponseExpected`. For setter functions it is disabled by default\nand can be enabled.\n\nEnabling the response expected flag for a setter function allows to detect\ntimeouts and other error conditions calls of this setter as well. The\ndevice will then send a response for this purpose. If this flag is disabled for\na setter function then no response is send and errors are silently ignored,\nbecause they cannot be detected.\n\nSee :func:`SetResponseExpected`\nfor the list of function ID :word:`constants` available for this function.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "function_id", "uint8", 1, "in" ],
						[ "response_expected", "bool", 1, "in" ] ],
				"name" : [ "SetResponseExpected", "set_response_expected" ],
				"doc" : [
						"af",
						{
							"de" : "\n\u00c4ndert das Response-Expected-Flag f\u00fcr die Funktion mit der angegebenen Funktion\nIDs. Diese Flag kann nur f\u00fcr Setter-Funktionen (Standardwert: *false*) und\nKonfigurationsfunktionen f\u00fcr Callbacks (Standardwert: *true*) ge\u00e4ndert werden.\nF\u00fcr Getter-Funktionen ist das Flag immer gesetzt und f\u00fcr Callbacks niemals.\n\nWenn das Response-Expected-Flag f\u00fcr eine Setter-Funktion gesetzt ist, k\u00f6nnen\nTimeouts und andere Fehlerf\u00e4lle auch f\u00fcr Aufrufe dieser Setter-Funktion\ndetektiert werden. Das Ger\u00e4t sendet dann eine Antwort extra f\u00fcr diesen Zweck.\nWenn das Flag f\u00fcr eine Setter-Funktion nicht gesetzt ist, dann wird keine\nAntwort vom Ger\u00e4t gesendet und Fehler werden stillschweigend ignoriert, da sie\nnicht detektiert werden k\u00f6nnen.\n",
							"en" : "\nChanges the response expected flag of the function specified by the\nfunction ID parameter. This flag can only be changed for setter (default value:\n*false*) and callback configuration functions (default value: *true*). For\ngetter functions it is always enabled and callbacks it is always disabled.\n\nEnabling the response expected flag for a setter function allows to detect\ntimeouts and other error conditions calls of this setter as well. The\ndevice will then send a response for this purpose. If this flag is disabled for\na setter function then no response is send and errors are silently ignored,\nbecause they cannot be detected.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "response_expected", "bool", 1, "in" ] ],
				"name" : [ "SetResponseExpectedAll",
						"set_response_expected_all" ],
				"doc" : [
						"af",
						{
							"de" : "\n\u00c4ndert das Response-Expected-Flag f\u00fcr alle Setter-Funktionen und\nKonfigurationsfunktionen f\u00fcr Callbacks diese Ger\u00e4tes.\n",
							"en" : "\nChanges the response expected flag for all setter and callback configuration\nfunctions of this device at once.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "uid", "string", 8, "out" ],
						[ "connected_uid", "string", 8, "out" ],
						[ "position", "char", 1, "out" ],
						[ "hardware_version", "uint8", 3, "out" ],
						[ "firmware_version", "uint8", 3, "out" ],
						[ "device_identifier", "uint16", 1, "out" ] ],
				"name" : [ "GetIdentity", "get_identity" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt die UID, die UID zu der das Bricklet verbunden ist, die\nPosition, die Hard- und Firmware Version sowie den Device Identifier\nzur\u00fcck.\n\nDie Position kann 'a', 'b', 'c' oder 'd' sein.\n\nEine Liste der Device Identifier Werte ist :ref:`hier <device_identifier>` zu\nfinden. |device_identifier_constant|\n",
							"en" : "\nReturns the UID, the UID where the Bricklet is connected to, \nthe position, the hardware and firmware version as well as the\ndevice identifier.\n\nThe position can be 'a', 'b', 'c' or 'd'.\n\nThe device identifier numbers can be found :ref:`here <device_identifier>`.\n|device_identifier_constant|\n"
						} ],
				"prototype_in_device" : true,
				"since_firmware" : [ 2, 0, 0 ],
				"function_id" : 255,
				"type" : "function"
			} ],
	"device_identifier" : 29,
	"released" : true,
	"common_included" : true,
	"manufacturer" : "Tinkerforge",
	"api_version" : [ 2, 0, 1 ],
	"name" : [ "IO4", "io4", "IO-4" ]
}