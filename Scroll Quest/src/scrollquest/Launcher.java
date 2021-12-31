package scrollquest;

public class Launcher {

	
	public static void main(String[] args){
		Game game = new Game("Scroll Quest", 720, 480);
		Thread thread = new Thread(game);
		thread.start();
	}
}
