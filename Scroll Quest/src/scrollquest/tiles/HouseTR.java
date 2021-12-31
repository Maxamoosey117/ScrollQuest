package scrollquest.tiles;

import scrollquest.gfx.Assets;

public class HouseTR extends Tile{

	public HouseTR(int id) {
		super(Assets.housetr, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
