package scrollquest.tiles;

import scrollquest.gfx.Assets;

public class Boulder extends Tile{

	public Boulder(int id) {
		super(Assets.boulder, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
