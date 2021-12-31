package scrollquest.tiles;

import scrollquest.gfx.Assets;

public class HouseTL extends Tile{

	public HouseTL(int id) {
		super(Assets.housetl, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
