import java.util.Scanner;

public class DungeonCrawler {
	
	static int[] red = {255,0,0};
	static int[] green = {0,255,0};
	static int[] blue = {0, 0, 255};
	static int[] yellow = {255,230,0};
	static int dungeonIndex = 0;
	
	public static void main(String args[]) {
		StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
		int roomNumber = 7;
		DungeonRoom[][] dungeonRooms = new DungeonRoom[roomNumber][5];
		generate(dungeonRooms);
		startGame(dungeonRooms);
	}
	
	public static void startGame(DungeonRoom[][] dungeonRooms) {
		boolean isRunning = true;		
		Scanner scanner = new Scanner(System.in);
		int currentRoom = 1;
		boolean goblinIsDefeated = false;
		boolean wuerfelGedroppt = false;
		
		GameLogic.clearConsole();
		GameLogic.printTitle();
		GameLogic.anythingToContinue();
		GameLogic.clearConsole();
		while(isRunning) {
			//Startraum
			switch(currentRoom) {
				case 1:
					dungeonRooms[currentRoom-1][0].setColor(blue);
					dungeonRooms[currentRoom-1][0].draw();
					GameLogic.printHeading("Du bist grade im " + currentRoom + ". Raum");
					GameLogic.readInt("(1) Zum nächsten Raum", 1);
					GameLogic.clearConsole();
					dungeonRooms[currentRoom-1][0].setColor(green);
					dungeonRooms[currentRoom-1][0].draw();
					currentRoom++;
					break;
				case 2:
				case 3:
				case 5:
				case 6:
					dungeonRooms[currentRoom-1][0].setColor(blue);
					dungeonRooms[currentRoom-1][0].draw();
					GameLogic.printHeading("Du bist grade im " + currentRoom + ". Raum");
					int wahl = GameLogic.readInt("(1) Zum nächsten Raum \n(2) Zum vorherigen Raum", 2);
					if(wahl == 2) {
						dungeonRooms[currentRoom-1][0].setColor(green);
						dungeonRooms[currentRoom-1][0].draw();
						currentRoom--;
					} else {
						dungeonRooms[currentRoom-1][0].setColor(green);
						dungeonRooms[currentRoom-1][0].draw();
						currentRoom++;
					}	
					GameLogic.clearConsole();
					break;
				case 4:
					dungeonRooms[currentRoom-1][0].setColor(blue);
					dungeonRooms[currentRoom-1][0].draw();
					if(!goblinIsDefeated) {
						GameLogic.printHeading(//"Du bist grade im " + currentRoom + ". Raum \n \n" +
											   "Ein Goblin (mit geheimratsecken) will stress. \nDu könntest wegrennen, "
											 + "ihn zu besiegen könnte dir aber etwas nüzliches droppen.");
						wahl = GameLogic.readInt("- Du würfelst einen D6.\n \n(1) Kämpfen ~ 4 oder höher (50%)\n(2) Wegrennen ~ 2 oder höher (83%)", 2);
						if(wahl == 1) {
							int dice = diceRoll();
							if(dice >= 4) { 
								GameLogic.printHeading("Du hast ihn besiegt!");
								goblinIsDefeated = true;
								System.out.println();
								System.out.println(dice);
								GameLogic.anythingToContinue();
								GameLogic.clearConsole();
								GameLogic.printHeading("Der Goblin hat einen Würfel gedroppt.");
								wuerfelGedroppt = true;
								System.out.println();
								GameLogic.anythingToContinue();
								GameLogic.clearConsole();
								GameLogic.printHeading("Du bist grade im " + currentRoom + ". Raum");
								wahl = GameLogic.readInt("(1) Zum nächsten Raum \n(2) Zum vorherigen Raum", 2);
								GameLogic.clearConsole();
								if(wahl == 2) {
									dungeonRooms[currentRoom-1][0].setColor(green);
									dungeonRooms[currentRoom-1][0].draw();
									currentRoom--;
								} else {
									dungeonRooms[currentRoom-1][0].setColor(green);
									dungeonRooms[currentRoom-1][0].draw();
									currentRoom++;
								}
							} else {
								System.out.println();
								GameLogic.printHeading("GAME OVER");
								System.out.println();
								System.out.println(dice);
								GameLogic.anythingToContinue();
								System.exit(1);
							}
							break;
						} else {
							int dice = diceRoll();
							if(dice >= 2) { 
								GameLogic.printHeading("Du bist erfolgreich abgehauen.");
								System.out.println();
								System.out.println(dice);
								GameLogic.anythingToContinue();
								GameLogic.clearConsole();
								GameLogic.printHeading("Du bist grade im " + currentRoom + ". Raum");
								wahl = GameLogic.readInt("(1) Zum nächsten Raum \n(2) Zum vorherigen Raum", 2);
								if(wahl == 2) {
									GameLogic.clearConsole();
									dungeonRooms[currentRoom-1][0].setColor(red);
									dungeonRooms[currentRoom-1][0].draw();
									currentRoom--;
								} else {
									GameLogic.clearConsole();
									dungeonRooms[currentRoom-1][0].setColor(red);
									dungeonRooms[currentRoom-1][0].draw();
									currentRoom++;
								}
							} else {
								System.out.println();
								GameLogic.printHeading("GAME OVER");
								System.out.println();
								System.out.println(dice);
								GameLogic.anythingToContinue();
								System.exit(1);
							}
						}
					} else {
						GameLogic.printHeading("Du bist grade im " + currentRoom + ". Raum");
						wahl = GameLogic.readInt("(1) Zum nächsten Raum \n(2) Zum vorherigen Raum", 2);
						if(wahl == 2) {
							dungeonRooms[currentRoom-1][0].setColor(green);
							dungeonRooms[currentRoom-1][0].draw();
							currentRoom--;
						} else {
							dungeonRooms[currentRoom-1][0].setColor(green);
							dungeonRooms[currentRoom-1][0].draw();
							currentRoom++;
						}
						GameLogic.clearConsole();	
					}
					break;
				case 7:
					dungeonRooms[currentRoom-1][0].setColor(blue);
					dungeonRooms[currentRoom-1][0].draw();
					GameLogic.printHeading("Du bist nun im letzten Raum angekommen. \n"
										 + "Vor dir steht der Endboss.");
					wahl = GameLogic.readInt("- Du würfelst einen D6.\n \n(1) Kämpfen ~ 6 (17%)", 1);
					if(wahl == 1) {
						int dice = diceRoll();
						if(dice == 6) { 
							dungeonRooms[currentRoom-1][0].setColor(green);
							dungeonRooms[currentRoom-1][0].draw();
							GameLogic.printHeading("Du hast gewonnen!");
							System.out.println();
							System.out.println(dice);
							GameLogic.anythingToContinue();
							System.exit(1);
						} else if (wuerfelGedroppt) {
							GameLogic.printHeading("Du hast es nicht geschafft, \ndoch du hast noch den Würfel des Goblins.");
							System.out.println();
							wahl = GameLogic.readInt("(1) Würfeln. \n(2) Aufgeben.", 2);
							if(wahl == 1) {
								dice = diceRoll();
								if(dice == 6) { 
									dungeonRooms[currentRoom-1][0].setColor(green);
									dungeonRooms[currentRoom-1][0].draw();
									GameLogic.printHeading("Du hast gewonnen!");
									System.out.println();
									System.out.println(dice);
									GameLogic.anythingToContinue();
									System.exit(1);
								} else {
									GameLogic.clearConsole();
									System.out.println();
									GameLogic.printHeading("GAME OVER");
									System.out.println();
									System.out.println(dice);
									GameLogic.anythingToContinue();
									System.exit(1);
								}
							} else {
								GameLogic.clearConsole();
								System.out.println();
								GameLogic.printHeading("GAME OVER");
								System.out.println();
								GameLogic.anythingToContinue();
								System.exit(1);							
							}
						} else {
							System.out.println();
							GameLogic.printHeading("GAME OVER");
							System.out.println();
							System.out.println(dice);
							GameLogic.anythingToContinue();
							System.exit(1);						
						}
					}
					break;
				default:
					System.out.println("Du solltest nicht hier sein.");	
			}
			
		}	
	}
	
	public static int diceRoll() {
		int dice = 1;
		int time = 25;
		int tries = 100;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < tries; j++) {
				try {
					dice = (int)(Math.random() * 6) + 1;
					GameLogic.clearConsole();
					System.out.println(dice);
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			time = time * 5;
			tries = tries / 5;
		}
		GameLogic.clearConsole();
		return dice;
	}
	
	public static void generate(DungeonRoom[][] dungeonRoom) {
		int[] nextRoomXY = {55, 55};
		for(dungeonIndex = 0; dungeonIndex < dungeonRoom.length; dungeonIndex++) {
			dungeonRoom[dungeonIndex][0] = new DungeonRoom(nextRoomXY[0], nextRoomXY[1], red);
			dungeonRoom[dungeonIndex][0].setIsNotSuitable();
			dungeonRoom[dungeonIndex][0].draw();
			StdDraw.show(500);
			dungeonRoom[dungeonIndex][1] = new DungeonRoom(dungeonRoom[dungeonIndex][0].getX(), dungeonRoom[dungeonIndex][0].getY()+10, yellow);
			dungeonRoom[dungeonIndex][2] = new DungeonRoom(dungeonRoom[dungeonIndex][0].getX()+10, dungeonRoom[dungeonIndex][0].getY(), yellow);
			dungeonRoom[dungeonIndex][3] = new DungeonRoom(dungeonRoom[dungeonIndex][0].getX(), dungeonRoom[dungeonIndex][0].getY()-10, yellow);
			dungeonRoom[dungeonIndex][4] = new DungeonRoom(dungeonRoom[dungeonIndex][0].getX()-10, dungeonRoom[dungeonIndex][0].getY(), yellow);
			for(int b = 1; b < dungeonRoom[dungeonIndex].length; b++) {
				if(dungeonIndex > 0) {
					checkChunk(dungeonRoom, dungeonRoom[dungeonIndex][b]);
				}
				checkXY(dungeonRoom[dungeonIndex][b]);
			}
			nextRoomXY = FindNextRoomLocation(dungeonRoom[dungeonIndex]);
		}
	}
	
	private static void checkChunk(DungeonRoom[][] dungeonRooms, DungeonRoom dungeonRoom) {
		for(int i = 0; i < dungeonIndex; i++) {
			for(int j = 0; j < dungeonRooms[i].length; j++) {
				if(dungeonRoom.getX() == dungeonRooms[i][j].getX() && dungeonRoom.getY() == dungeonRooms[i][j].getY()) {
					dungeonRoom.setIsNotSuitable();
				}
			}
		}
	}
	
	public static void checkXY(DungeonRoom dungeonRoom) {
		if (dungeonRoom.getX() < 0 || dungeonRoom.getX() > 100){
			dungeonRoom.setIsNotSuitable();
			return;
		}
		if (dungeonRoom.getY() < 0 || dungeonRoom.getY() > 100){
			dungeonRoom.setIsNotSuitable();
			return;
		}

	}
	
	public static int[] FindNextRoomLocation(DungeonRoom[] dungeonRooms) {
		boolean foundNextLocation = false;
		int[] nextRoomXY = {0, 0};
		while(!foundNextLocation) {
			double randomNumber = Math.random();
			if(randomNumber < 0.25 && dungeonRooms[1].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[1].getX();
				nextRoomXY[1] = dungeonRooms[1].getY();
     			foundNextLocation = true;
			} 
			if(randomNumber > 0.25 && randomNumber < 0.5 && dungeonRooms[2].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[2].getX();
				nextRoomXY[1] = dungeonRooms[2].getY();
				foundNextLocation = true;
			}
			if(randomNumber > 0.5 && randomNumber < 0.75 && dungeonRooms[3].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[3].getX();
				nextRoomXY[1] = dungeonRooms[3].getY();
				foundNextLocation = true;
			}
			if(randomNumber > 0.75 && dungeonRooms[4].getIsSuitable()) {	
				nextRoomXY[0] = dungeonRooms[4].getX();
				nextRoomXY[1] = dungeonRooms[4].getY();	
				foundNextLocation = true;
			}
		}
		return nextRoomXY;
	}
}
