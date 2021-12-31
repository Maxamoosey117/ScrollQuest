package scrollquest.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import scrollquest.Game;

public class Assets {

	private static final int width = 32, height = 32;

	public static Font font28;

	public static BufferedImage dirt, grass, cobble, tree, bush, boulder;
	public static BufferedImage housetl, housetr, housebl, housebr;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_none_down, player_none_up,
			player_none_left, player_none_right;
	public static BufferedImage[] bat_up;

	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	public static BufferedImage startup, title, play, quit, playh, quith;
	public static BufferedImage scroll;

	public static void init() {
		font28 = new Font("Garamond", Font.BOLD | Font.ITALIC, 11);

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(Game.getHandler(), "/textures/sheet.png"));

		inventoryScreen = ImageLoader.loadImage(Game.getHandler(), "/textures/inventoryScreen.png");

		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		startup = ImageLoader.loadImage(Game.getHandler(), "/images/landscape.png");
		title = ImageLoader.loadImage(Game.getHandler(), "/images/title.png");
		play = ImageLoader.loadImage(Game.getHandler(), "/images/play2.png");
		quit = ImageLoader.loadImage(Game.getHandler(), "/images/quit2.png");
		playh = ImageLoader.loadImage(Game.getHandler(), "/images/play.png");
		quith = ImageLoader.loadImage(Game.getHandler(), "/images/quit.png");

		scroll = sheet.crop(width * 8, height, width, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_none_down = new BufferedImage[2];
		player_none_up = new BufferedImage[2];
		player_none_left = new BufferedImage[2];
		player_none_right = new BufferedImage[2];

		player_down[0] = sheet.crop(0, height, width, height);
		player_down[1] = sheet.crop(width, height, width, height);
		player_up[0] = sheet.crop(width * 2, height, width, height);
		player_up[1] = sheet.crop(width * 3, height, width, height);
		player_right[0] = sheet.crop(width * 2, 0, width, height);
		player_right[1] = sheet.crop(width * 3, 0, width, height);
		player_left[0] = sheet.crop(0, 0, width, height);
		player_left[1] = sheet.crop(width, 0, width, height);
		player_none_down[0] = sheet.crop(0, height * 2, width, height);
		player_none_down[1] = sheet.crop(width, height * 2, width, height);
		player_none_up[0] = sheet.crop(width * 2, height * 3, width, height);
		player_none_up[1] = sheet.crop(width * 3, height * 3, width, height);
		player_none_left[0] = sheet.crop(width * 2, height * 2, width, height);
		player_none_left[1] = sheet.crop(width * 3, height * 2, width, height);
		player_none_right[0] = sheet.crop(0, height * 3, width, height);
		player_none_right[1] = sheet.crop(width, height * 3, width, height);

		bat_up = new BufferedImage[2];
		
		bat_up[0] = sheet.crop(width * 8, height * 8, width, height);
		bat_up[1] = sheet.crop(width * 9, height * 8, width, height);

		housetl = sheet.crop(width * 4, height * 3, width, height);
		housetr = sheet.crop(width * 5, height * 3, width, height);
		housebl = sheet.crop(width * 4, height * 4, width, height);
		housebr = sheet.crop(width * 5, height * 4, width, height);

		dirt = sheet.crop(width * 4, height, width, height);
		grass = sheet.crop(width * 5, 0, width, height);
		cobble = sheet.crop(width * 5, height * 2, width, height);
		tree = sheet.crop(width * 7, 0, width, height);
		bush = sheet.crop(width * 6, height, width, height);
		boulder = sheet.crop(width * 4, height * 2, width, height);

	}

}
