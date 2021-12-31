package scrollquest.tiles;

import scrollquest.gfx.Assets;

public class HouseBL extends Tile{

	public HouseBL(int id) {
		super(Assets.housebl, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
