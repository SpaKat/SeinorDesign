
public class MavLinkCommadPostion extends MavLinkCommad {
	
	public MavLinkCommadPostion(String SaveID) {
		super(SaveID);
	}

	@Override
	public void add(String str) {
		getUavData().add(new UavdataPointPosition(str,getSpilt()));
	}
}
