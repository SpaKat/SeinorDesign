
public class MavLinkCommandSystemStatus extends MavLinkCommad {

	public MavLinkCommandSystemStatus(String SaveID) {
		super(SaveID);
		
	}
	@Override
	public void add(String str) {
		getUavData().add(new UavdataPointSystemStatus(str,getSpilt()));
	}
}
