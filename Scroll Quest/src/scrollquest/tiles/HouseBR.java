package scrollquest.tiles;

import scrollquest.gfx.Assets;

public class HouseBR extends Tile{

	public HouseBR(int id) {
		super(Assets.housebr, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
