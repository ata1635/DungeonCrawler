import java.util.Scanner;

public class GameLogic {
	static Scanner scanner = new Scanner(System.in);
	public static boolean isRunning;
	
	public static int readInt(String prompt, int userChoices) {
		int input;
		
		do {
			System.out.println(prompt);
			System.out.println();
			try {
				input = Integer.parseInt(scanner.next());
			} catch(Exception e) {
				input = -1;
				System.out.println("Bitte eine ganze Zahl eingeben.");
			}
		} while(input < 1 || input > userChoices);
		return input;
	}
	
	public static void clearConsole() {
		for(int i = 0; i < 100; i++) {
			// ...
			System.out.println();
		}
	}
	
	public static void printSeperator(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public static void printTitle() {
		System.out.println("      d8b                                                                                                     d8b                  \n       88P                                                                                                     88P                  \n      d88                                                                                                     d88                   \n  d888888  ?88   d8P  88bd88b  d888b8b   d8888b d8888b   88bd88b      d8888b  88bd88b d888b8b   ?88   d8P  d8P888   d8888b  88bd88b \n d8P' ?88  d88   88   88P' ?8bd8P' ?88  d8b_,dPd8P' ?88  88P' ?8b    d8P' `P  88P'  `d8P' ?88   d88  d8P' d8P'?88  d8b_,dP  88P'  ` \n 88b  ,88b ?8(  d88  d88   88P88b  ,88b 88b    88b  d88 d88   88P    88b     d88     88b  ,88b  ?8b ,88b ,88'  88b 88b     d88      \n `?88P'`88b`?88P'?8bd88'   88b`?88P'`88b`?888P'`?8888P'd88'   88b    `?888P'd88'     `?88P'`88b `?888P'888P'    88b`?888P'd88'      \n                                     )88                                                                                            \n                                    ,88P                                                                                            \n                                `?8888P                                                                                            ");
	}
	
	public static void printHeading(String title) {
		printSeperator(30);
		System.out.println(title);
		printSeperator(30);
		//~ System.out.println();
	}		

	public static void anythingToContinue() {
		System.out.println("\nDrücke eine beliebige Taste um fortzufahren...");
		scanner.next();
	}

	public static void gameLoop() {
		while(isRunning) {
			int input = readInt("-> ", 2);
			if(input == 1) {
				System.out.println("Vorwaerts");
			} else if(input == 2) {
				System.out.println("Ruckwaerts");
			} else {
				isRunning = false;
				System.out.println("You shouldn't be here, bud.");
			}
		}
	}				
}
