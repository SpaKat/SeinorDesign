
public class MavLinkCommadPostion extends MavLinkCommad {
	@Override
	public void add(String str) {
		getUavData().add(new UavdataPointPosition(str,getSpilt()));
	}
}
