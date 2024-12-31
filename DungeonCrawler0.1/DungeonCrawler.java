public class DungeonCrawler {
	
	static int[] red = {255,0,0};
	static int[] green = {0,255,0};
	static int[] yellow = {255,230,0};
	static int a = 0;
	
	public static void main(String args[]) {
		System.out.println("      d8b                                                                                                     d8b                  \n       88P                                                                                                     88P                  \n      d88                                                                                                     d88                   \n  d888888  ?88   d8P  88bd88b  d888b8b   d8888b d8888b   88bd88b      d8888b  88bd88b d888b8b   ?88   d8P  d8P888   d8888b  88bd88b \n d8P' ?88  d88   88   88P' ?8bd8P' ?88  d8b_,dPd8P' ?88  88P' ?8b    d8P' `P  88P'  `d8P' ?88   d88  d8P' d8P'?88  d8b_,dP  88P'  ` \n 88b  ,88b ?8(  d88  d88   88P88b  ,88b 88b    88b  d88 d88   88P    88b     d88     88b  ,88b  ?8b ,88b ,88'  88b 88b     d88      \n `?88P'`88b`?88P'?8bd88'   88b`?88P'`88b`?888P'`?8888P'd88'   88b    `?888P'd88'     `?88P'`88b `?888P'888P'    88b`?888P'd88'      \n                                     )88                                                                                            \n                                    ,88P                                                                                            \n                                `?8888P                                                                                            ");
		System.out.println("Start: ");
		StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
		System.out.println();
		int roomNumber = 7;
		DungeonRoom[][] dungeonRooms = new DungeonRoom[roomNumber][5];
		for(int i = 0; i < 50; i++) {
			generate(dungeonRooms);
			System.out.println(i); //generate Dungeon Rooms
			try {
				// Pause execution for 1000 milliseconds (1 second)
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// Handle the exception
				e.printStackTrace();
			}
			StdDraw.clear();	
			}
	}
	
	public static void generate(DungeonRoom[][] dungeonRoom) {
		int[] nextRoomXY = {55, 55};
		for(a = 0; a < dungeonRoom.length; a++) {
			System.out.println(a + ". Raum");
			System.out.println("Raumspawn: " + nextRoomXY[0] + " " + nextRoomXY[1]);
			dungeonRoom[a][0] = new DungeonRoom(nextRoomXY[0], nextRoomXY[1], red);
			dungeonRoom[a][0].setIsNotSuitable();
			dungeonRoom[a][0].draw();
			StdDraw.show(500);
			dungeonRoom[a][1] = new DungeonRoom(dungeonRoom[a][0].getX(), dungeonRoom[a][0].getY()+10, yellow);
			dungeonRoom[a][2] = new DungeonRoom(dungeonRoom[a][0].getX()+10, dungeonRoom[a][0].getY(), yellow);
			dungeonRoom[a][3] = new DungeonRoom(dungeonRoom[a][0].getX(), dungeonRoom[a][0].getY()-10, yellow);
			dungeonRoom[a][4] = new DungeonRoom(dungeonRoom[a][0].getX()-10, dungeonRoom[a][0].getY(), yellow);
			for(int b = 1; b < dungeonRoom[a].length; b++) {
				//~ dungeonRoom[a][b].draw(); //Chunk Raume
				if(a > 0) {
					checkChunk(dungeonRoom, dungeonRoom[a][b]);
				}
				checkXY(dungeonRoom[a][b]);
			}
			System.out.println();
			nextRoomXY = FindNextRoomLocation(dungeonRoom[a]);
			System.out.println("---------------------------------------- " + (a+1));
			System.out.println();
		}
	}
	
	private static void checkChunk(DungeonRoom[][] dungeonRooms, DungeonRoom dungeonRoom) {
		System.out.println();		
		System.out.println("	=== Current Chunk: " + dungeonRoom.getX() + " " + dungeonRoom.getY() + " ==="); 
		for(int i = 0; i < a; i++) {
				System.out.println();
				System.out.println("Raum-Array: " + i);
			for(int j = 0; j < dungeonRooms[i].length; j++) {
				System.out.println("	Chunk: " + j + " | " + dungeonRooms[i][j].getX() + " " +  dungeonRooms[i][j].getY());
				if(dungeonRoom.getX() == dungeonRooms[i][j].getX() && dungeonRoom.getY() == dungeonRooms[i][j].getY()) {
					System.out.println("	===== Overlap =====");
					dungeonRoom.setIsNotSuitable();
				}
			}
		}
	}
	
	public static void checkXY(DungeonRoom dungeonRoom) {
		if (dungeonRoom.getX() < 0 || dungeonRoom.getX() > 100){
			dungeonRoom.setIsNotSuitable();
			System.out.print(dungeonRoom.getIsSuitable() + "-X ");
			return;
		}
		if (dungeonRoom.getY() < 0 || dungeonRoom.getY() > 100){
			dungeonRoom.setIsNotSuitable();
			System.out.print(dungeonRoom.getIsSuitable() + "-Y ");
			return;
		}
		System.out.print(dungeonRoom.getIsSuitable() + " ");
	}
	
	public static int[] FindNextRoomLocation(DungeonRoom[] dungeonRooms) {
		boolean foundNextLocation = false;
		int[] nextRoomXY = {0, 0};
		while(!foundNextLocation) {
			double randomNumber = Math.random();
			System.out.println("New Number: " + randomNumber);
			if(randomNumber < 0.25 && dungeonRooms[1].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[1].getX();
				nextRoomXY[1] = dungeonRooms[1].getY();
				//~ dungeonRooms[1].setColor(green);
				//~ dungeonRooms[1].draw();
     			//~ System.out.println("Nord-Raum");
     			foundNextLocation = true;
			} 
			if(randomNumber > 0.25 && randomNumber < 0.5 && dungeonRooms[2].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[2].getX();
				nextRoomXY[1] = dungeonRooms[2].getY();
				//~ dungeonRooms[2].setColor(green);
				//~ dungeonRooms[2].draw();
				//~ System.out.println("Ost-Raum");
				foundNextLocation = true;
			}
			if(randomNumber > 0.5 && randomNumber < 0.75 && dungeonRooms[3].getIsSuitable()) {
				nextRoomXY[0] = dungeonRooms[3].getX();
				nextRoomXY[1] = dungeonRooms[3].getY();
				//~ dungeonRooms[3].setColor(green);
				//~ dungeonRooms[3].draw();
				//~ System.out.println("Sud-Raum");
				foundNextLocation = true;
			}
			if(randomNumber > 0.75 && dungeonRooms[4].getIsSuitable()) {	
				nextRoomXY[0] = dungeonRooms[4].getX();
				nextRoomXY[1] = dungeonRooms[4].getY();
				//~ dungeonRooms[4].setColor(green);
				//~ dungeonRooms[4].draw();
				//~ System.out.println("West-Raum");	
				foundNextLocation = true;
			}
		}
		System.out.println();
		return nextRoomXY;
	}
}
